package af.gov.anar.dck.notification.sms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

// @Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
// @ToString(exclude="groups, instances, workflow, reports, childForms,
// childFormOf, createdAt, updatedAt")
// @EqualsAndHashCode
@Entity(name = "Sms")
@Table(name = "sms", schema = "sms")

public class Sms {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_generator")
    @SequenceGenerator(name = "sms_generator", sequenceName = "sms_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "env_slug")
    private String envSlug;
    // 0 means recieved sms and 1 means sent sms
    @Column(name = "type")
    private int type;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "text")
    private String text;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "sent_timestamp")
    private LocalDateTime sentTimestamp;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @Override
    public String toString() {
        return "Sms [id=" + id + ", content ="+ this.text + ", created_at="
                + createdAt + ", updated_at=" + updatedAt + "]";
    }
}
