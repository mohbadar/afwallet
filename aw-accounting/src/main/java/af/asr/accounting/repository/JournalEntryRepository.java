
package af.asr.accounting.repository;

import af.asr.accounting.model.JournalEntryEntity;
import af.asr.accounting.model.JournalEntryLookup;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.Result;
import org.apache.fineract.cn.cassandra.core.CassandraSessionProvider;
import org.apache.fineract.cn.cassandra.core.TenantAwareCassandraMapperProvider;
import org.apache.fineract.cn.cassandra.core.TenantAwareEntityTemplate;
import org.apache.fineract.cn.lang.DateConverter;
import org.apache.fineract.cn.lang.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings({"unused"})
@Repository
public class JournalEntryRepository {

  private final CassandraSessionProvider cassandraSessionProvider;
  private final TenantAwareCassandraMapperProvider tenantAwareCassandraMapperProvider;
  private final TenantAwareEntityTemplate tenantAwareEntityTemplate;

  @Autowired
  public JournalEntryRepository(final CassandraSessionProvider cassandraSessionProvider,
                                final TenantAwareCassandraMapperProvider tenantAwareCassandraMapperProvider,
                                final TenantAwareEntityTemplate tenantAwareEntityTemplate) {
    super();
    this.cassandraSessionProvider = cassandraSessionProvider;
    this.tenantAwareCassandraMapperProvider = tenantAwareCassandraMapperProvider;
    this.tenantAwareEntityTemplate = tenantAwareEntityTemplate;
  }

  public void saveJournalEntry(final JournalEntryEntity journalEntryEntity) {
    this.tenantAwareEntityTemplate.save(journalEntryEntity);

    final JournalEntryLookup journalEntryLookup = new JournalEntryLookup();
    journalEntryLookup.setTransactionIdentifier(journalEntryEntity.getTransactionIdentifier());
    journalEntryLookup.setDateBucket(journalEntryEntity.getDateBucket());
    this.tenantAwareEntityTemplate.save(journalEntryLookup);
  }

  public List<JournalEntryEntity> fetchJournalEntries(final DateRange range) {
    final Session tenantSession = this.cassandraSessionProvider.getTenantSession();

    final List<String> datesInBetweenRange
        = range.stream()
        .map(DateConverter::toIsoString)
        .collect(Collectors.toList());

    final Statement stmt = new SimpleStatement(
        QueryBuilder
            .select().all()
            .from("acc_journal_entries")
            .where(QueryBuilder.in("date_bucket", datesInBetweenRange)).getQueryString(),
        datesInBetweenRange.toArray()
    );

    final ResultSet resultSet = tenantSession.execute(stmt);

    final Mapper<JournalEntryEntity> mapper = this.tenantAwareCassandraMapperProvider.getMapper(JournalEntryEntity.class);
    final Result<JournalEntryEntity> journalEntryEntities = mapper.map(resultSet);
    return journalEntryEntities.all();
  }

  public Optional<JournalEntryEntity> findJournalEntry(final String transactionIdentifier) {
    final Optional<JournalEntryLookup> optionalJournalEntryLookup = this.tenantAwareEntityTemplate.findById(JournalEntryLookup.class, transactionIdentifier);
    if (optionalJournalEntryLookup.isPresent()) {
      final JournalEntryLookup journalEntryLookup = optionalJournalEntryLookup.get();
      final List<JournalEntryEntity> journalEntryEntities = this.tenantAwareEntityTemplate.fetchByKeys(JournalEntryEntity.class, journalEntryLookup.getDateBucket(),
          journalEntryLookup.getTransactionIdentifier());
      return Optional.of(journalEntryEntities.get(0));
    } else {
      return Optional.empty();
    }
  }
}
