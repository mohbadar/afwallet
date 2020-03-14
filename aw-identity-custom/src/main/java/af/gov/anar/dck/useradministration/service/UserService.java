package af.gov.anar.dck.useradministration.service;


import java.util.List;

import af.gov.anar.dck.useradministration.model.User;

public interface UserService {

    public User create(String userJson);
    public User delete(Long id);
    public List findAll();
    public List findAllByEnv(String envSlug);
    public List findAll_Calc();

    public User findById(Long id);
    public User findByUsername(String username);
    public boolean update(Long id, String userJson);
    public void updateAvatar(User user, String avatarFilename);
    public User getLoggedInUser();
    public User getLoggedInUser(Boolean forceFresh);
    public String getSecurityContextHolderUsername(Boolean forceFresh);
    public boolean isAdmin();
    public String getCurrentEnv();

    public long countByEnvSlug(String envSlug);
    public long countByEnvSlugAndActive(String envSlug, Boolean active);
    public User updatePreferences(String preferences);
	public boolean updateUserPassword(String currentPassword, String newPassword);
    public boolean updateUserOdkPassword(String currentPassword, String newPassword);
    public User findByEmail(String email);
    public void createPasswordResetTokenForUser(User user, String token, boolean active);
    public boolean validatePasswordResetToken(String token);
    public boolean createNewPassword(String newPassword, String confirmPassword, String token);
}
