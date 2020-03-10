package af.gov.anar.dck.useradministration.api;

import af.gov.anar.dck.common.auth.EnvironmentAuthService;
import af.gov.anar.dck.useradministration.model.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/environments"})

public class EnvironmentController {
    
	Logger logger = LoggerFactory.getLogger(EnvironmentController.class);
	
	@Autowired
    private EnvironmentAuthService environmentAuthService;

    @PostMapping
    public Environment create(@RequestBody Environment environment){
    	logger.info("Entry EnvironmentController>CREATE() - POST");
        return environmentAuthService.create(environment);
    }

    @GetMapping(path = {"/{id}"})
    public Environment findOne(@PathVariable("id") Long id){
    	logger.info("Entry EnvironmentController>findOne() - GET");
        return environmentAuthService.findById(id);
    }
    
    @PutMapping(path = {"/{id}"})
    public boolean update(@PathVariable("id") Long id, @RequestBody Environment environment){
    	logger.info("Entry EnvironmentController>update() - PUT");
        return environmentAuthService.update(id, environment);
    }

    @GetMapping
    public List findAll(){
    	logger.info("Entry EnvironmentController>findAll() - GET");
        return environmentAuthService.findAll();
    }
}