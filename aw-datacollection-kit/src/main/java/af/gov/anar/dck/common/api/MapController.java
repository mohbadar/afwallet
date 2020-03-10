package af.gov.anar.dck.common.api;


import af.gov.anar.dck.common.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/map"})

public class MapController {
    
	Logger logger = LoggerFactory.getLogger(MapController.class);
	
	@Autowired
    private MapService mapService;

	@GetMapping()
    public List getMainMapForms(){
    	logger.info("Entry FormController>getMainMapFroms() - GET");
        return mapService.getMainMapForms();
    }
}