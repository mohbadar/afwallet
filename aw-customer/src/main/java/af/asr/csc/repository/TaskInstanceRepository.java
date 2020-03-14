package af.asr.csc.repository;

import af.asr.csc.model.CustomerEntity;
import af.asr.csc.model.TaskDefinitionEntity;
import af.asr.csc.model.TaskInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskInstanceRepository extends JpaRepository<TaskInstanceEntity, Long> {

    List<TaskInstanceEntity> findByCustomer(final CustomerEntity customer);

    List<TaskInstanceEntity> findByCustomerAndTaskDefinition(final CustomerEntity customer,
                                                             final TaskDefinitionEntity taskDefinitionEntity);
}