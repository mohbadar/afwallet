package af.gov.anar.dck.infrastructure.event.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import af.gov.anar.dck.infrastructure.event.model.EventLog;

import java.util.List;

@Repository
public interface EventLogRepository extends JpaRepository<EventLog, Long> {

    List<EventLog> findByLoggerLevel(String level);
    // List<EventLog> findByUserId(long userId);
}