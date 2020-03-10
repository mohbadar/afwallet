package af.gov.anar.dck.useradministration.api;

import af.gov.anar.api.annotation.ThrowsException;
import af.gov.anar.api.annotation.ThrowsExceptions;
import af.gov.anar.api.config.EnableApiFactory;
import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.dck.infrastructure.exception.InternalServerProblemException;
import af.gov.anar.dck.infrastructure.exception.ResourceNotFoundException;
import af.gov.anar.dck.infrastructure.exception.SubmissionException;
import af.gov.anar.dck.useradministration.model.*;
import af.gov.anar.dck.useradministration.service.PermissionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import af.gov.anar.dck.common.auth.RoleAuthService;
import af.gov.anar.dck.infrastructure.security.JwtTokenUtil;
import af.gov.anar.dck.useradministration.service.CustomUserService;
import af.gov.anar.dck.useradministration.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/roles")
@EnableApiFactory
public class RoleController  extends ResponseHandler {

	Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleAuthService roleAuthService;
	@Autowired
	private UserService userService;

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private PermissionService permissionService;

	@GetMapping()
	public List<Role> roles() {
		// fetch all roles and return it
		String envSlug = userService.getCurrentEnv();
		return roleAuthService.findAllByEnv(envSlug);
	}

	@PostMapping
	@ThrowsExceptions({
			@ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
			@ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
			@ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
	})
	public Role postRole(@Valid @RequestBody String role) {
		// create a new role and return it
		Gson g = new Gson();
		Role newRole = g.fromJson(role, Role.class);
		newRole.setEnvSlug(userService.getCurrentEnv());
		return roleAuthService.create(newRole);
	}

	@GetMapping(value = "/{id}")
	@ThrowsExceptions({
			@ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
			@ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
	})
	public ObjectNode getRoleById(@PathVariable(value = "id") Long id) {

		Role role = roleAuthService.findById(id);
		List<Permission> allP = permissionService.findAll();
		ArrayNode allPermissions = mapper.valueToTree(allP);

		JsonNode roleJson = mapper.convertValue(role, JsonNode.class);
		JsonNode permissionJson = mapper.convertValue(role.getPermissions(), JsonNode.class);

		ObjectNode roleObj = mapper.createObjectNode();
		roleObj.set("role", roleJson);
		roleObj.set("permissions", permissionJson);
		roleObj.set("allPermissions", allPermissions);

		return roleObj;
	}

	@PutMapping(value = "/{id}")
	@ThrowsExceptions({
			@ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
			@ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
			@ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
	})
	public boolean updateRoleById(@PathVariable(value = "id") Long id, @Valid @RequestBody String roleString) {

		Gson g = new Gson();
		Role role = g.fromJson(roleString, Role.class);
		role.setEnvSlug(userService.getCurrentEnv());

		return roleAuthService.update(id, role);

	}
}
