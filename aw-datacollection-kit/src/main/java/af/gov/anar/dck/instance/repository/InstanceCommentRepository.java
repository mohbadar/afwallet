package af.gov.anar.dck.instance.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import af.gov.anar.dck.instance.model.InstanceComment;

public interface InstanceCommentRepository extends JpaRepository<InstanceComment, Long> {

    @Query("DELETE from InstanceComment ic where ic.instance.id=?1")
	public boolean deleteByInstanceId(Long instanceId);
}
