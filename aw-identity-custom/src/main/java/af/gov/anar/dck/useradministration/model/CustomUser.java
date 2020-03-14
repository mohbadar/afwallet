package af.gov.anar.dck.useradministration.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUser extends User {
    
    private static final long serialVersionUID = -3531439484732724601L;

    private String currentEnv;
    private String currentLang;

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities, String currentEnv, String currentLang) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        
        this.currentEnv = currentEnv;
        this.currentLang = currentLang;
    }


}
