package af.gov.anar.dck.useradministration.api;

import af.gov.anar.api.annotation.ThrowsException;
import af.gov.anar.api.annotation.ThrowsExceptions;
import af.gov.anar.api.config.EnableApiFactory;
import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.dck.infrastructure.exception.InternalServerProblemException;
import af.gov.anar.dck.infrastructure.exception.ResourceNotFoundException;
import af.gov.anar.dck.infrastructure.exception.SubmissionException;
import af.gov.anar.dck.useradministration.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({ "/api/permissions" })
@EnableApiFactory
public class PermissionController extends ResponseHandler {

	@Autowired
	private PermissionService permissionService;

	Logger logger = LoggerFactory.getLogger(PermissionController.class);

	@GetMapping
	@ThrowsExceptions({
			@ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
	})
	public List findAll() {
		return permissionService.findAll();
	}
}
