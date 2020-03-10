package af.gov.anar.dck.useradministration.api;

import af.gov.anar.dck.useradministration.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({ "/api/permissions" })

public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	Logger logger = LoggerFactory.getLogger(PermissionController.class);

	@GetMapping
	public List findAll() {
		return permissionService.findAll();
	}
}
