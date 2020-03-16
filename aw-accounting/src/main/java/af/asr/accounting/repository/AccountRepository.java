
package af.asr.accounting.repository;

import af.asr.accounting.model.AccountEntity;
import af.asr.accounting.model.LedgerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>, JpaSpecificationExecutor<AccountEntity> {

  List<AccountEntity> findByLedger(final LedgerEntity ledgerEntity);

  Page<AccountEntity> findByLedger(final LedgerEntity ledgerEntity, final Pageable pageable);

  AccountEntity findByIdentifier(final String identifier);

  @Query("SELECT CASE WHEN count(a) > 0 THEN true ELSE false END FROM AccountEntity a where a.referenceAccount = :accountEntity")
  Boolean existsByReference(@Param("accountEntity") final AccountEntity accountEntity);

  Stream<AccountEntity> findByBalanceIsNot(final Double value);
}
