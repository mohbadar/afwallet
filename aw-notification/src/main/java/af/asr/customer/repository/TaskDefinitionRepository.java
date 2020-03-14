package af.asr.customer.repository;

import af.asr.customer.model.TaskDefinitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDefinitionRepository extends JpaRepository<TaskDefinitionEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TaskDefinitionEntity t WHERE t.identifier = :identifier")
    Boolean existsByIdentifier(@Param("identifier") final String identifier);

    TaskDefinitionEntity findByIdentifier(final String identifier);

    List<TaskDefinitionEntity> findByAssignedCommandsContaining(final String command);
}