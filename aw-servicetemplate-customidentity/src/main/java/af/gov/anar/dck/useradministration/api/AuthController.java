package af.gov.anar.dck.useradministration.api;

import af.gov.anar.api.annotation.ThrowsException;
import af.gov.anar.api.annotation.ThrowsExceptions;
import af.gov.anar.api.config.EnableApiFactory;
import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.dck.infrastructure.exception.InternalServerProblemException;
import af.gov.anar.dck.infrastructure.exception.ResourceNotFoundException;
import af.gov.anar.dck.infrastructure.exception.SubmissionException;
import af.gov.anar.dck.infrastructure.security.JwtTokenUtil;
import af.gov.anar.dck.useradministration.model.CustomUser;
import af.gov.anar.dck.useradministration.model.Environment;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.CustomUserService;
import af.gov.anar.dck.useradministration.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import af.gov.anar.dck.common.auth.UserAuthService;
import af.gov.anar.dck.infrastructure.util.EmailUtil;
import af.gov.anar.dck.infrastructure.util.LoggerLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.Principal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping({"/api"})
@Slf4j
@EnableApiFactory
public class AuthController extends ResponseHandler {

    Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${app.url}")
    private String appUrl;


	@Autowired
    private UserAuthService userAuthService;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private UserService userService;

    @Autowired
	private EmailUtil emailUtil;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    ObjectMapper mapper = new ObjectMapper();

	@Value("${app.user.avatar}")
    private String uploadAvatarDir;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
            @ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
    })
    public ResponseEntity login(@RequestBody Map<String, String> loginUser) throws AuthenticationException {
        final String username = loginUser.get("username");
        final String password = loginUser.get("passwrd");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    username,
                    password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final CustomUser customUser = customUserService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(customUser);

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);


        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpServletRequest request) {
        // TODO delete the token
        
        return ResponseEntity.ok(true);
    }
  
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
    }

    @RequestMapping(value="/forgotpassword", method = RequestMethod.POST)
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
            @ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
    })
    public boolean forgotpassword(@RequestBody String email, HttpServletRequest request) throws Exception {
        User user = userService.findByEmail(email);
        if (user == null){
            return false;
        }

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token, true);

        String msg =    "Dear Mr." +user.getName()+",<br /><br />"+"We have received a request to reset your password, if you did not make this request just ignore this email. Otherwise , you can reset your password using this link <br /><br />Note: This link can be use for one time and validated for 30 minutes only.<br /><br />"+ appUrl +"changepassword?token=" + token;
        emailUtil.sendMessage(email, "Password Reset", msg);
        
        return true;
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.PUT)
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
            @ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
    })
    public boolean changePassword(@RequestParam String newPassword, @RequestParam String confirmPassword, @RequestParam String token) {

        boolean result = userService.validatePasswordResetToken(token);
        if (result == true) {
            return userService.createNewPassword(newPassword, confirmPassword, token);
        }
        return false;
    }

    @PutMapping(path = "/config")
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class)
    })
    public ObjectNode updateConfig(Authentication authentication, @RequestParam("lang") String lang, @RequestParam("env") String envSlug) throws JsonParseException, IOException
    {
        String currentEnv = envSlug;
        String currentLang = lang;
        System.out.println("current environment:"+currentEnv+"current lang"+currentLang);
        CustomUser userDetails = (CustomUser) authentication.getPrincipal();
        userDetails.setCurrentEnv(currentEnv);
        userDetails.setCurrentLang(currentLang);
        System.out.println("inside user details "+userDetails.getCurrentEnv()+"--"+userDetails.getCurrentLang());
        userDetails = customUserService.loadUserByUsername(userDetails.getUsername(), currentEnv, currentLang);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        // as CurrentEnv and CurrentLang are changed. so it is required to create new token and share it with client
        final String newToken = jwtTokenUtil.generateToken(userDetails);

        User user = userService.findByUsername(userDetails.getUsername());

        ObjectNode result = getConfiguration(user, userDetails);
        result.put("token", newToken);
        return result;
    }
    
    @GetMapping(path = "/config")
	public ObjectNode config(Authentication authentication)throws JsonParseException, IOException {
        CustomUser userDetails = (CustomUser) authentication.getPrincipal();
       
        User user = userService.findByUsername(userDetails.getUsername());
        return getConfiguration(user, userDetails);
    }

    private ObjectNode getConfiguration(User user, CustomUser userDetails) throws JsonParseException, IOException{
        ObjectNode config = mapper.createObjectNode();

        // User Details
        config.put("id", user.getId());
        config.put("name", user.getName());
        config.put("username", userDetails.getUsername());
        config.put("authenticated", true);
        config.put("currentEnv", userDetails.getCurrentEnv());
        config.put("currentLang", userDetails.getCurrentLang());

        // user preferences
        String prefStr = user.getPreferences();
        JsonNode prefJson = null;
        if(prefStr != null && prefStr.equals("") == false) {
            prefJson = mapper.readTree(prefStr);
        }
        config.set("envPreferences", prefJson );

        // User Autorities
        ArrayNode authorities = mapper.createArrayNode();
        for(GrantedAuthority auth : userDetails.getAuthorities()) {
            authorities.add(auth.getAuthority());
        }
        config.set("authorities", authorities);

        // List all accessed environments
        ArrayNode environments = mapper.createArrayNode();
        for(Environment env : user.getEnvironments()) {
            ObjectNode newEnvObj = mapper.createObjectNode();
            newEnvObj.put("id", env.getId());
            newEnvObj.put("name", env.getName());
            newEnvObj.put("slug", env.getSlug());
            environments.add(newEnvObj);
        }
        config.set("environments", environments);

        config.set("preferences", environments);

        // System.out.println("final config is:"+config.toString());
        return config;
    }
	
	@RequestMapping("/token")
	public Map<String,String> token(HttpSession session) {
	    return Collections.singletonMap("token", session.getId());
	}
	
	@GetMapping("/profile")
	public User profile() {
		logger.debug("Getting loggedin user profile");
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.toString();
		
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		
		return userAuthService.findByUsername(username);
	}
	
	@PatchMapping("/avatar")
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
            @ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
    })
    public User updateAvatar(@RequestParam(value = "avatar", required = true) MultipartFile file)  throws IOException {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName = formatter.format(Calendar.getInstance().getTime()) + "_thumbnail.jpg";
        User user = userAuthService.getLoggedInUser();
        if (!file.isEmpty()) {
            try {
                String saveDirectory = uploadAvatarDir + File.separator + user.getId() + File.separator;
                File test = new File(saveDirectory);
                if(!test.exists()) {
                    test.mkdirs();
                }
                
                byte[] bytes = file.getBytes();

                ByteArrayInputStream imageInputStream = new ByteArrayInputStream(bytes);
                BufferedImage image = ImageIO.read(imageInputStream);
                BufferedImage thumbnail = Scalr.resize(image, 200);
                
                File thumbnailOut = new File(saveDirectory + fileName);
                ImageIO.write(thumbnail, "png", thumbnailOut);
                
                userAuthService.updateAvatar(user, fileName);
                System.out.println("Image Saved::: " + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userAuthService.getLoggedInUser(true); //Force refresh of cached User
    }
	
	@RequestMapping(value="/user/profile-picture", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] profilePicture() throws IOException {
        User user = userAuthService.getLoggedInUser();
        String profilePicture = uploadAvatarDir + File.separator + user.getId() + File.separator + user.getAvatar();
        if(new File(profilePicture).exists()) {
            return IOUtils.toByteArray(new FileInputStream(profilePicture));
        } else {
            return null;
        }
    }
	
	@RequestMapping(value="/user/avatar", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] profileAvatar() throws IOException {
        User user = userAuthService.getLoggedInUser();
        String profilePicture = uploadAvatarDir + File.separator + user.getId() + File.separator + user.getAvatar();
        if(new File(profilePicture).exists()) {
        	BufferedImage bufferedImage = ImageIO.read(new FileInputStream(profilePicture));
            BufferedImage thumbnail = Scalr.resize(bufferedImage, 128);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(thumbnail, "jpg", bos );
            
			return bos.toByteArray();
        } else {
            return null;
        }
    }
}
