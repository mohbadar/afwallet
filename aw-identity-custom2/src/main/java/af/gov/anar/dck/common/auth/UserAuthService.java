package af.gov.anar.dck.common.auth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;

import java.util.List;

/**
 * @author Jalil haidari
 * 
 *         Class to check if the user has the authority of the action for which
 *         he has requested.
 * @PreAuthorized annotation of spring-boot is used to check if particular
 *                authority is present in Principal object for current user.
 *
 */

@Service
public class UserAuthService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('USER_CREATE')")
    public User create(String userJson) {
        logger.info("Entry UserAuthService>create() - POST");
        return userService.create(userJson);
    }

    @PreAuthorize("hasAuthority('USER_DELETE')")
    public User delete(Long id) {
        logger.info("Entry UserAuthService>delete() - DELETE");
        return userService.delete(id);
    }

    @PreAuthorize("hasAuthority('USER_LIST')")
    public List<User> findAll() {
        logger.info("Entry UserAuthService>findAll() - GET");
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('USER_LIST')")
    public List<User> findAllByEnv(String envSlug) {
        logger.info("Entry UserAuthService>findAllByEnv() - GET");
        return userService.findAllByEnv(envSlug);
    }

    @PreAuthorize("hasAuthority('USER_LIST')")
    public List<User> findAll_Calc() {
        logger.info("Entry UserAuthService>create() - GET");
        return userService.findAll_Calc();
    }

    @PreAuthorize("hasAuthority('USER_VIEW')")
    public User findById(Long id) {
        logger.info("Entry UserAuthService>create() - GET");
        return userService.findById(id);
    }

    @PreAuthorize("hasAuthority('USER_VIEW')")
    public User findByUsername(String username) {
        logger.info("Entry UserAuthService>create() - GET");
        return userService.findByUsername(username);
    }

    @PreAuthorize("hasAuthority('USER_EDIT')")
    public boolean update(Long id, String userJson) {
        logger.info("Entry UserAuthService>create() - PUT");
        return userService.update(id, userJson);
    }

    public void updateAvatar(User user, String avatarFilename) {
        userService.updateAvatar(user, avatarFilename);
    }
    
    public User getLoggedInUser() {
        return userService.getLoggedInUser();
    }

    public User getLoggedInUser(Boolean forceFresh) {
        return userService.getLoggedInUser(forceFresh);
    }
    
    public User updatePreferences(String preferences){
        return userService.updatePreferences(preferences);
    }

    public String getCurrentEnv() {
        return userService.getCurrentEnv();
    }

	public boolean updateUserPassword(String currentPassword, String newPassword) {
		return userService.updateUserPassword(currentPassword, newPassword);
    }

	public boolean updateUserOdkPassword(String currentPassword, String newPassword) {
		return userService.updateUserOdkPassword(currentPassword, newPassword);
	}

}