
package af.gov.anar.dck.common.api;

import af.gov.anar.dck.infrastructure.logger.Loggable;
import af.gov.anar.dck.useradministration.model.Role;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.CustomUserServiceImpl;
import af.gov.anar.dck.useradministration.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/")
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserServiceImpl customUserService;

    /**
     * Authenticate Route for Kafka Integration
     *
     * @param username
     * @param password
     * @param response
     * @return JSON contains token and user after success authentication.
     * @throws IOException
     */
    @RequestMapping(value = "authenticate", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password, HttpServletResponse response)
            throws IOException {

        Map<String, Object> tokenMap = new HashMap<String, Object>();

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User appUser = userService.findByUsername(username);
        final String token = Jwts.builder().setSubject(username).claim("groups", appUser.getGroups())
                .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "asims-integration-module").compact();

        tokenMap.put("token", token);
        tokenMap.put("user", appUser);
        tokenMap.put("roles", customUserService.getRoles(appUser.getGroups()));
        tokenMap.put("is_admin", hasSysAdminRole(customUserService.getRoles(appUser.getGroups())));
        return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);


    }

    private boolean hasSysAdminRole(Collection<Role> roles) {
        boolean result = false;
        for (Role r : roles) {
            if (r.getName().equals("ADMIN_ROLE")) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Loggable
    @RequestMapping(value = "/**/{path:[^\\.]*}")
    public String nonApiRequests(HttpServletRequest request) {
        logger.warn("No match found for route : " + request.getRequestURL());
        return "forward:/";
    }
}
