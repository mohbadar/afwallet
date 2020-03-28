package af.gov.anar.corona.infrastructure.keycloak;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakClient {

    @Value("${keycloak.auth-server-url}")
    private String keycloakAuthServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Autowired
    private KeycloakRestTemplate template;

//    @PreAuthorize("hasRole('CLIENT_USER')")
    public List<KeyCloakUser> getUsers() {
        String userUrl = keycloakAuthServerUrl + "/admin/realms/"+realm+"/users";
        ResponseEntity<KeyCloakUser[]> response = template.getForEntity(userUrl, KeyCloakUser[].class);
        return Arrays.stream(response.getBody()).collect(Collectors.toList());
    }
}