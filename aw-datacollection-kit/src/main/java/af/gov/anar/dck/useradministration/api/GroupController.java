package af.gov.anar.dck.useradministration.api;

import af.gov.anar.dck.useradministration.model.*;
import af.gov.anar.dck.useradministration.service.RoleService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import af.gov.anar.dck.common.auth.GroupAuthService;
import af.gov.anar.dck.form.api.FormController;
import af.gov.anar.dck.infrastructure.security.JwtTokenUtil;
import af.gov.anar.dck.useradministration.service.CustomUserService;
import af.gov.anar.dck.useradministration.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// import af.gov.anar.dck.domain.service.GroupService;

/**
 * @author Jalil haidari
 * 
 *         Class for CRUD operation on Group
 *
 */

@RestController
@RequestMapping("/api/groups")

public class GroupController {

	Logger logger = LoggerFactory.getLogger(GroupController.class);
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	@Autowired
	private GroupAuthService groupAuthService;

	@GetMapping()
	public List<Group> groups() {
		// fetch all the groups and
		String envSlug = userService.getCurrentEnv();
		return groupAuthService.findAllByEnv(envSlug);
	}

	@PostMapping()
	public Group create(@Valid @RequestBody String group) {

		Gson gson = new Gson();
		Group newGroup = gson.fromJson(group, Group.class);
		newGroup.setEnvSlug(userService.getCurrentEnv());
		return groupAuthService.create(newGroup);
	}

	@GetMapping(path = "/{id}")
	public ObjectNode getGroupById(@PathVariable(value = "id") Long id) {
		// given the group, find it from database and return it
		Group group = groupAuthService.findById(id);
		List<Role> allRoles = roleService.findAll();
		ArrayNode allRolesTree = mapper.valueToTree(allRoles);
		JsonNode groupJson = mapper.convertValue(group, JsonNode.class);
		JsonNode rolesJson = mapper.convertValue(group.getRoles(), JsonNode.class);
		ObjectNode groupObj = mapper.createObjectNode();
		groupObj.set("group", groupJson);
		groupObj.set("roles", rolesJson);
		groupObj.set("allRoles", allRolesTree);
		return groupObj;

	}

	@PutMapping(path = "/{id}")
	public boolean updateGroupById(@PathVariable(value = "id") Long id, @Valid @RequestBody String groupString) {
		Gson gson = new Gson();
		Group group = gson.fromJson(groupString, Group.class);
		group.setEnvSlug(userService.getCurrentEnv());
		return groupAuthService.update(id, group);
	}
}
