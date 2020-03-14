package af.asr.customer.command.repository;

import af.asr.customer.command.model.CommandEntity;
import af.asr.customer.customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface CommandRepository extends JpaRepository<CommandEntity, Long> {

    Stream<CommandEntity> findByCustomer(final CustomerEntity customerEntity);
}