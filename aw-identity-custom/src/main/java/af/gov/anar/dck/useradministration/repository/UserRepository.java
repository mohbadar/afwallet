package af.gov.anar.dck.useradministration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import af.gov.anar.dck.useradministration.model.Group;
import af.gov.anar.dck.useradministration.model.User;

import javax.transaction.Transactional;
import java.util.List;

//JpaRepository<User, Integer> : User is the entity type and Integer is the primary key.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);

    @Query("SELECT new af.gov.anar.dck.useradministration.model.User(u.id, u.name, u.address, u.username, u.active, u.email, u.avatar, u.createdAt, u.updatedAt) from User u INNER JOIN u.environments e where e.slug=?1 order by u.id")
    public List<User> findAllByEnvSlug(String envSlug);

    // @Query("SELECT * from User u INNER JOIN u.groups g where u.username = ?1 and g.envSlug = ?2")
    // User findByUsernameAndGroupEnv(String username, String envSlug);

    @Modifying
    @Transactional
    @Query("update User u set u.avatar = ?2 where u.username = ?1")
    int updateAvatar(String username, String avatar);

    @Query("SELECT new af.gov.anar.dck.useradministration.model.Group(g.id, g.name, g.description, g.active, g.envSlug) from User u INNER JOIN u.groups g where u.id = ?1 and g.envSlug = ?2")
    List<Group> findAllGroupsByUserAndEnv(Long userId, String envSlug);
    
    @Query("SELECT count(*) from User u inner join u.environments e where e.slug = ?1")
    long countByEnvSlug(String envSlug);

    @Query("SELECT count(*) from User u inner join u.environments e where e.slug = ?1 and u.active = ?2")
    long countByEnvSlugAndActive(String envSlug, boolean active);
}
