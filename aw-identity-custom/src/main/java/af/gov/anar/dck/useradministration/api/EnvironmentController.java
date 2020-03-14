package af.gov.anar.dck.useradministration.api;

import af.gov.anar.api.annotation.ThrowsException;
import af.gov.anar.api.annotation.ThrowsExceptions;
import af.gov.anar.api.config.EnableApiFactory;
import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.dck.common.auth.EnvironmentAuthService;
import af.gov.anar.dck.infrastructure.exception.InternalServerProblemException;
import af.gov.anar.dck.infrastructure.exception.ResourceNotFoundException;
import af.gov.anar.dck.infrastructure.exception.SubmissionException;
import af.gov.anar.dck.useradministration.model.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/environments"})
@EnableApiFactory
public class EnvironmentController extends ResponseHandler {
    
	Logger logger = LoggerFactory.getLogger(EnvironmentController.class);
	
	@Autowired
    private EnvironmentAuthService environmentAuthService;

    @PostMapping
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
            @ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
    })
    public Environment create(@RequestBody Environment environment){
    	logger.info("Entry EnvironmentController>CREATE() - POST");
        return environmentAuthService.create(environment);
    }

    @GetMapping(path = {"/{id}"})
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
    })
    public Environment findOne(@PathVariable("id") Long id){
    	logger.info("Entry EnvironmentController>findOne() - GET");
        return environmentAuthService.findById(id);
    }
    
    @PutMapping(path = {"/{id}"})
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class),
            @ThrowsException(status = HttpStatus.NO_CONTENT, exception = SubmissionException.class)
    })
    public boolean update(@PathVariable("id") Long id, @RequestBody Environment environment){
    	logger.info("Entry EnvironmentController>update() - PUT");
        return environmentAuthService.update(id, environment);
    }

    @GetMapping
    @ThrowsExceptions({
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception = ResourceNotFoundException.class),
    })
    public List findAll(){
    	logger.info("Entry EnvironmentController>findAll() - GET");
        return environmentAuthService.findAll();
    }
}