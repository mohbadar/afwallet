package af.gov.anar.dck.instance.repository;

import af.gov.anar.dck.infrastructure.util.enumeration.InstanceHistoryStatus;
import af.gov.anar.dck.instance.model.InstanceHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstanceHistoryRepository extends JpaRepository<InstanceHistory, Long> {
    public List<InstanceHistory> findByInstanceId(Long instanceId);
    public List<InstanceHistory> findByInstanceHistoryStatusAndUserId(InstanceHistoryStatus instanceHistoryStatus, Long userId);
    public List<InstanceHistory> findByInstanceHistoryStatus(InstanceHistoryStatus instanceHistoryStatus);
    
    @Query("DELETE from InstanceHistory ih where ih.instanceId=?1")
	public boolean deleteByInstanceId(Long instanceId);
}
