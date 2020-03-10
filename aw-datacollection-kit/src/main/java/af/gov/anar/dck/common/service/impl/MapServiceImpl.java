package af.gov.anar.dck.common.service.impl;


import af.gov.anar.dck.common.service.MapService;
import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.form.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {
	
    @Autowired
    private FormService formService;
    
    @Override
    public List<Form> getMainMapForms() {
    	return formService.findAllByShowOnMapHasGeometry(true, true);
    }
}