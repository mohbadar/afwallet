package af.asr.csc.repository;

import af.asr.csc.model.CommandEntity;
import af.asr.csc.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface CommandRepository extends JpaRepository<CommandEntity, Long> {

    Stream<CommandEntity> findByCustomer(final CustomerEntity customerEntity);
}