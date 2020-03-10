package af.gov.anar.dck.instance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import af.gov.anar.dck.instance.model.InstanceTransition;

import java.util.List;

@Repository
public interface InstanceTransitionRepository extends JpaRepository<InstanceTransition, Long> {

    public List<InstanceTransition> findByUserId(Long userId);
    public List<InstanceTransition> findByInstanceId(Long instanceId);

    @Query("SELECT new af.gov.anar.dck.instance.model.InstanceTransition(it.id, it.user, it.previousStep, it.nextStep, it.resolution, it.remarks, it.createdAt, it.updatedAt) from InstanceTransition it left join it.instance i where it.instance.id=?1 order by it.id desc")
    public List<InstanceTransition> findByInstanceIdBrief(Long instanceId);

    @Query("DELETE from InstanceTransition it where it.instance.id=?1")
	public boolean deleteByInstanceId(Long instanceId);
}
