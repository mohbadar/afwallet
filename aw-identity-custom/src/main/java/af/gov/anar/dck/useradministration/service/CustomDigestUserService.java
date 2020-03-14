package af.gov.anar.dck.useradministration.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomDigestUserService extends UserDetailsService {
	 
//	 User findByEmail(String email);
	 
	 public UserDetails loadUserByUsername(String username);

}
