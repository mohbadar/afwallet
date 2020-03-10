package af.gov.anar.dck.jobs.batch;

import af.gov.anar.dck.form.model.FormSyncEndpointsQueue;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.jobs.service.JobSchedularService;
import af.gov.anar.dck.form.service.FormSyncEndpointsQueueServiceImpl;
import af.gov.anar.dck.infrastructure.util.enumeration.FormSyncStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FormSyncBatch {

    @Autowired
    private FormSyncEndpointsQueueServiceImpl formSyncEndpointsQueueService;

    @Autowired
    JobSchedularService jobService;


    // @Scheduled(fixedDelay = 5000)
    // @Scheduled(cron = "0 0 0 * * *",zone = "Asia/Kabul") 12:00 A.M in the morning
    // @Scheduled(cron = "0 0 12 1/1 * ? *")
    /**
     * http://www.cronmaker.com/
     */
    public void executeFormSyncForFailedSyncOps(){ 
        System.out.println("Scheduled Operation....");


          //get list of endpoints
          List<FormSyncEndpointsQueue> endpoints = formSyncEndpointsQueueService.findByFormSyncStatus(FormSyncStatus.FAILED);


          System.out.println("FormSyncQueue Data \n...." + endpoints.toString());

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
      
              HttpEntity<Instance> entity = new HttpEntity<>(endpoint.getInstance(), headers);
      
              ResponseEntity<HttpStatus> responseEntity = restTemplate.exchange(endpoint.getEndpointUrl(), HttpMethod.POST, entity, HttpStatus.class);
              
              int responseStatusCode = responseEntity.getStatusCode().value();
  
              if( responseStatusCode== HttpStatus.OK.value() || responseStatusCode == HttpStatus.ACCEPTED.value())
              {
                  endpoint.setFormSyncStatus(FormSyncStatus.SUCCESSFUL);
                  endpoint.setEndpointResponse(responseEntity.toString());
                  formSyncEndpointsQueueService.store(endpoint);
              }
          }
    }
}