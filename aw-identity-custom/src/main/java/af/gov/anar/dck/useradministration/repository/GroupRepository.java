package af.gov.anar.dck.useradministration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import af.gov.anar.dck.useradministration.model.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{
    public List<Group> findByEnvSlug(String envSlug);
}
