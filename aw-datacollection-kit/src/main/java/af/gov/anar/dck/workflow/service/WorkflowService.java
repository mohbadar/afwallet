
package af.gov.anar.dck.workflow.service;


import org.springframework.transaction.annotation.Transactional;

import af.gov.anar.dck.workflow.model.Workflow;

import java.util.List;

@Transactional
public interface WorkflowService {

    public Workflow createOrUpdate(Workflow workflow);
    public Workflow update(Workflow workflow, Long id);
    public List<Workflow> findAll();
    public List<Workflow> findAllByEnv(String envSlug);
    public List<Workflow> findAllWithoutJSONContent();
    public Workflow findById(Long id);
    public Workflow delete(Long id);
    public void delete(Workflow workflow);
}
