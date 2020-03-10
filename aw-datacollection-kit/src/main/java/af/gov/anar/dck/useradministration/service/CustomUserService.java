
package af.gov.anar.dck.useradministration.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import af.gov.anar.dck.useradministration.model.CustomUser;

@Transactional
public interface CustomUserService extends UserDetailsService {
	 
//	 User findByEmail(String email);
	 
	public CustomUser loadUserByUsername(String username);
	public CustomUser loadUserByUsername(String username, String currentEnv, String currentLang);

	public String getCurrentEnv();
}
