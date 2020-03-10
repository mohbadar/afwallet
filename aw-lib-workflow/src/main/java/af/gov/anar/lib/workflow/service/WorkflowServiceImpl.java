package af.gov.anar.lib.workflow.service;


import java.util.List;

import af.gov.anar.lib.workflow.model.Workflow;
import af.gov.anar.lib.workflow.repository.WorkflowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private WorkflowRepository workflowRepository;
    
    @Override
    public Workflow createOrUpdate(Workflow workflow) {
        return workflowRepository.save(workflow);
    }

    

    @Override
    public Workflow update(Workflow workflow, Long id) {
        workflow.setId(id);
        return workflowRepository.save(workflow);
    }

    
    @Override
    public List<Workflow> findAll() {
        return workflowRepository.findAll();
    }

    
    @Override
    public List<Workflow> findByObjectType(String objectType) {
        return workflowRepository.findByObjectType(objectType);
    }


    
    @Override
    public Workflow findById(Long id) {
        return workflowRepository.getOne(id);
    }

    
    @Override
    public Workflow delete(Long id) {
        Workflow workflow = workflowRepository.getOne(id);
        if (workflow != null) {
            workflowRepository.delete(workflow);
        }
        return workflow;
    }

    
    @Override
    public void delete(Workflow workflow) {
        if (workflow != null) {
            workflowRepository.delete(workflow);
        }
    }

}
