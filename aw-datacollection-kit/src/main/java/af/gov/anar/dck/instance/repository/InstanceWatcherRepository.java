package af.gov.anar.dck.instance.repository;


import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.model.InstanceWatcher;
import af.gov.anar.dck.useradministration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstanceWatcherRepository extends JpaRepository<InstanceWatcher, Long> {

    List<InstanceWatcher> findByWatcherId(Long watcherId);
    List<InstanceWatcher> findByInstance(Instance instance);
    @Query("SELECT new af.gov.anar.dck.useradministration.model.User(u.id, u.email, u.name) from User u inner join InstanceWatcher iw on u.username=iw.username where iw.instance.id = ?1 order by u.id")
	public List<User> getWatchersEmailByInstanceId(Long instanceId);

}