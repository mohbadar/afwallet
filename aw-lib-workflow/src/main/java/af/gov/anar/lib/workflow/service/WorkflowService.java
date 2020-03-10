package af.gov.anar.lib.workflow.service;

import af.gov.anar.lib.workflow.model.Workflow;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public interface WorkflowService {

    public Workflow createOrUpdate(Workflow workflow);
    public Workflow update(Workflow workflow, Long id);
    public List<Workflow> findAll();
    public List<Workflow> findByObjectType(String objectType);
    public Workflow findById(Long id);
    public Workflow delete(Long id);
    public void delete(Workflow workflow);

}
