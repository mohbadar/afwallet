package af.gov.anar.dck.form.service;

import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.form.model.FormSyncEndpointsQueue;
import af.gov.anar.dck.infrastructure.util.enumeration.FormSyncStatus;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.service.InstanceService;
import af.gov.anar.dck.form.repository.FormSyncEndpointsQueueRepository;
import af.gov.anar.dck.infrastructure.util.enumeration.FormSyncStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class FormSyncEndpointsQueueServiceImpl  {

    @Autowired
    private FormSyncEndpointsQueueRepository formSyncEndpointsQueueRepository;

    @Autowired
    private InstanceService instanceService;

    
    public List<FormSyncEndpointsQueue> findAll() {
        return formSyncEndpointsQueueRepository.findAll();
    }

    
    public FormSyncEndpointsQueue store(FormSyncEndpointsQueue formSyncEndpointsQueue) {
        return formSyncEndpointsQueueRepository.save(formSyncEndpointsQueue);
    }

    
    public List<FormSyncEndpointsQueue> findByInstance(Instance instance) {
        return formSyncEndpointsQueueRepository.findByInstance(instance);
    }

    
    public FormSyncEndpointsQueue findById(long id) {
        return formSyncEndpointsQueueRepository.getOne(id);
    }

    
    public void deleteById(long id) {
        formSyncEndpointsQueueRepository.deleteById(id);
    }

    
    public List<FormSyncEndpointsQueue> findByFormSyncStatus(FormSyncStatus status) {
        return formSyncEndpointsQueueRepository.findByFormSyncStatus(status);
    }

    
    public List<FormSyncEndpointsQueue> findByForm(Form form) {
        return formSyncEndpointsQueueRepository.findByForm(form);
    }

    @Async
    
    public void syncInstanceWithEndpoints(Instance instance) {
        
        //get list of endpoints
        List<FormSyncEndpointsQueue> endpoints = this.findByForm(instance.getForm());

        //terminate if doesn't have any endpoints
        if(endpoints.size() < 1)
        {
            return;
        }


        //Send object to each endpoint 
        for(FormSyncEndpointsQueue endpoint: endpoints)
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = new RestTemplate();
    
            HttpEntity<Instance> entity = new HttpEntity<>(instance, headers);
    
            ResponseEntity<HttpStatus> responseEntity = restTemplate.exchange(endpoint.getEndpointUrl(), HttpMethod.POST, entity, HttpStatus.class);
            
            int responseStatusCode = responseEntity.getStatusCode().value();

            if( responseStatusCode== HttpStatus.OK.value() || responseStatusCode == HttpStatus.ACCEPTED.value())
            {
                endpoint.setInstance(instance);
                endpoint.setFormSyncStatus(FormSyncStatus.SUCCESSFUL);
                endpoint.setEndpointResponse(responseEntity.toString());
                this.store(endpoint);
            }else {
                endpoint.setInstance(instance);
                endpoint.setFormSyncStatus(FormSyncStatus.FAILED);
                endpoint.setEndpointResponse(responseEntity.toString());
                this.store(endpoint);
            }
        }


    }






    

}