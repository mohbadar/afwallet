package af.gov.anar.dck.infrastructure.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "eventlog", schema = "log")
public class EventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_log_tbl_generator")
	@SequenceGenerator(name="event_log_tbl_generator", sequenceName = "event_log_tbl_seq", allocationSize = 1)
	@Column(name = "LOG_ID")
    private Long id;

    @Column(name = "ENTRY_DATE", updatable = false)
    @CreationTimestamp
    private Date entryDate;

    @Column(name = "userId")
    private Long userId;
    
    
    @Column(name = "LOGGER")
    @Type(type = "text")
    private String logger;
    
    @Column(name="LOG_LEVEL")
    private String loggerLevel;
    
    @Column(name = "EXCEPTION")
    @Type(type = "text")
    private String exception;
    
    @Column(name = "MESSAGE")
    @Type(type = "text")
    private String message;


    @Column(name = "env_slug")
    private String envSlug;


    public EventLog(Long user, String envSlug, String logger, String loggerLevel, String exception, String message)
    {
        this.userId= user;
        this.logger=logger;
        this.loggerLevel=loggerLevel;
        this.exception = exception;
        this.message = message;
        this.envSlug =envSlug;
    }
}