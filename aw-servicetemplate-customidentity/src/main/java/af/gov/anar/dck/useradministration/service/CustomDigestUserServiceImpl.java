package af.gov.anar.dck.useradministration.service;


import af.gov.anar.dck.useradministration.model.Group;
import af.gov.anar.dck.useradministration.model.Permission;
import af.gov.anar.dck.useradministration.model.Role;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

//import com.nsia.config.CustomUserDetailService;

@Service
public class CustomDigestUserServiceImpl implements CustomDigestUserService {
	
	@Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
	 
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
	 
	 @Override
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 System.out.println("Entry to CustomDigestUserServiceImpl: " + username);
		 
		 User user = userRepository.findByUsername(username);
		 if (user == null) {
			 throw new UsernameNotFoundException("Invalid username or password.");
		 }
	
		 UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), 
				 user.getPassword(), user.isActive(), true, true, true,
				//  getAuthorities(user.getGroups()));
				getAuthorities(user.getGroups()));
 
		 return userDetails;
	 }
	 
	 private Collection<? extends GrantedAuthority> getAuthorities(Collection<Group> groups) {
		 List<Role> roles =  new ArrayList<>();
		for(Group group: groups) {
			
			roles.addAll(group.getRoles());
		}
		// make a hashset to remove the duplicated roles
		HashSet hs = new HashSet();
        hs.addAll(roles);
        roles.clear();
        roles.addAll(hs);

		return getGrantedAuthorities(getPermissions(roles));
	 }
 
	 private List<String> getPermissions(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Permission> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPermissions());
        }
        for (Permission item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }
 
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
