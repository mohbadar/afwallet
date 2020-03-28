package af.gov.anar.corona.infrastructure.keycloak;//package af.gov.anar.ebreshna.infrastructure.keycloak;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RestController
//@RequestMapping(produces = APPLICATION_JSON_VALUE, value = "/api/users")
//public class KeycloakController {
//
//    @Autowired
//    private final KeycloakClient keycloakRemoteService;
//
//    public KeycloakController(KeycloakClient keycloakRemoteService) {
//        this.keycloakRemoteService = keycloakRemoteService;
//    }
//
//    @GetMapping("")
//    public List<KeyCloakUser> getUsers() {
//        return keycloakRemoteService.getUsers();
//    }
//
//}