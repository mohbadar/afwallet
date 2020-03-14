package af.gov.anar.dck.useradministration.api;

import af.gov.anar.api.annotation.ThrowsException;
import af.gov.anar.api.annotation.ThrowsExceptions;
import af.gov.anar.api.config.EnableApiFactory;
import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.dck.infrastructure.exception.InternalServerProblemException;
import af.gov.anar.dck.infrastructure.exception.ResourceNotFoundException;
import af.gov.anar.dck.infrastructure.exception.SubmissionException;
import af.gov.anar.dck.infrastructure.logger.Loggable;
import af.gov.anar.dck.useradministration.model.Group;
import af.gov.anar.dck.useradministration.service.GroupService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import af.gov.anar.dck.common.auth.UserAuthService;
import af.gov.anar.dck.infrastructure.security.JwtTokenUtil;
import af.gov.anar.dck.useradministration.model.CustomUser;
import af.gov.anar.dck.useradministration.model.Environment;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.CustomUserService;
import af.gov.anar.dck.useradministration.service.UserService;
import af.gov.anar.dck.infrastructure.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping({ "/api/users" })
@EnableApiFactory
public class UserController  extends ResponseHandler {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${spring.mail.to}")
    private String emailTo;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private GroupService groupService;

    @Autowired
	private ExceptionUtil exceptionUtil;

    private String failureEmailSub = "500 - ASIMS Exception - User";
    
    ObjectMapper mapper = new ObjectMapper();

    @GetMapping

    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class)
    })
    public List<User> findAll() {
        String envSlug = userAuthService.getCurrentEnv();
        return userAuthService.findAllByEnv(envSlug);
    }

    @GetMapping("/{id}")
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class)
    })
    public ObjectNode detail(@PathVariable("id") Long id) {
        User user = userAuthService.findById(id);
        List<Group> groupList = groupService.findAllByEnv(userAuthService.getCurrentEnv());
        ArrayNode allGroups = mapper.valueToTree(groupList);
        JsonNode userJson = mapper.convertValue(user, JsonNode.class);
        JsonNode groupJson = mapper.convertValue(user.getGroups(), JsonNode.class);
        ObjectNode userObj = mapper.createObjectNode();
        userObj.set("user", userJson);
        userObj.set("groups", groupJson);
        userObj.set("allGroups", allGroups);
        return userObj;
    }

    @PostMapping
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
            @ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
    })
    public User create(@RequestBody String userPayload, HttpServletRequest request) throws Exception {
        logger.info("Entry UserController>CREATE() - POST");
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return userAuthService.create(userPayload);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @PutMapping("/{id}")
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
            @ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
    })
    public boolean update(@PathVariable("id") Long id, @RequestBody String userPayload, HttpServletRequest request)
            throws Exception {
        logger.info("Entry UserController>update() - PUT");
        // Gson gson=new Gson();
        // User newUser=gson.fromJson(user, User.class);
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return userAuthService.update(id, userPayload);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }

    @Loggable
    @GetMapping(value = "/{id}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
    })
    public Collection<Group> getLoggedInUserGroups(@PathVariable(value = "id", required = true) Long loggedInUserId) {
        return userAuthService.findById(loggedInUserId).getGroups();
    }

    @Loggable
    @PutMapping(value = "/preferences")
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
    })
    public User updatePreferences(@RequestBody String preferences, HttpServletRequest request) throws Exception {
        logger.info("Entry UserController>updatePreferences() - PUT");
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return userAuthService.updatePreferences(preferences);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @PutMapping(value = "/cpassword")
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
            @ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
    })
    public boolean updateUserPassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest request) throws Exception {
        logger.info("Entry UserController>updateUserPassword() - PUT");

        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return userAuthService.updateUserPassword(currentPassword, newPassword);
        } catch (Exception e) {

            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }


    @Loggable
    @PutMapping(value = "/change-odk-password")
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
            @ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
    })
    public boolean updateUserOkdPassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest request) throws Exception {
        
        logger.info("Entry UserController>updateUserOkdPassword() - PUT");
       
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return userAuthService.updateUserOdkPassword(currentPassword, newPassword);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }

}
