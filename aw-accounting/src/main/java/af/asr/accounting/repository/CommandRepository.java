
package af.asr.accounting.repository;

import af.asr.accounting.model.AccountEntity;
import af.asr.accounting.model.CommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<CommandEntity, Long> {

  List<CommandEntity> findByAccount(final AccountEntity accountEntity);
}
