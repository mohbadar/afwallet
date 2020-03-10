package af.gov.anar.lib.workflow.repository;

import af.gov.anar.lib.workflow.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Long>{
    public List<Workflow> findByObjectType(String objectType);
}
