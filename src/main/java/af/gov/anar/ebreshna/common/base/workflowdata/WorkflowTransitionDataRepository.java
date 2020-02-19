package af.gov.anar.ebreshna.common.base.workflowdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowTransitionDataRepository extends JpaRepository<WorkflowTransitionData, Long> {
}
