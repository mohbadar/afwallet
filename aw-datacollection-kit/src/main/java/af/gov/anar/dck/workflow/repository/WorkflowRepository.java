
package af.gov.anar.dck.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import af.gov.anar.dck.workflow.model.Workflow;

import java.util.List;


@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Long>{
    public List<Workflow> findByEnvSlug(String envSlug);

    @Query("SELECT new af.gov.anar.dck.workflow.model.Workflow(w.id, w.name, w.description, w.envSlug, w.createdAt) from Workflow w where w.envSlug = ?1 order by w.id")
	public List<Workflow> findAllWithoutJSONContent(String envSlug);
}
