package af.gov.anar.dck.infrastructure.event.service;

import af.gov.anar.dck.infrastructure.event.model.EventLog;
import af.gov.anar.dck.infrastructure.event.repository.EventLogRepository;
import af.gov.anar.dck.infrastructure.event.service.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventLogServiceImpl implements EventLogService{

    @Autowired
    private EventLogRepository eventLogRepository;

    public List<EventLog> findAll() {
        return eventLogRepository.findAll();
    }

    public EventLog store(EventLog eventLog) {
        return null;
    }

    public List<EventLog> findByUser(Long userId) {
        // return eventLogRepository.findByUserId(userId);
        return eventLogRepository.findAll();
    }

    public EventLog findById(long id) {
        return eventLogRepository.findById(id).get();
    }

    
    public List<EventLog> findByLoggerLevel(String level) {
        return eventLogRepository.findByLoggerLevel(level);
    }

    @Retryable
    @Async
    public void log(Long userId, String envSlug, String logger, String loggerLevel, String exception, String detailsMessage){
        EventLog eventLog = new EventLog(userId, envSlug, logger, loggerLevel, exception, detailsMessage);
        eventLogRepository.save(eventLog);
    }
}