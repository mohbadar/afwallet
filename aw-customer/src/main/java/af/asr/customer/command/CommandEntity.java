package af.asr.customer.command;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@Entity
@Table(name = "maat_commands")
public class CommandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    @Column(name = "a_type")
    private String type;
    @Column(name = "a_comment")
    private String comment;
//    @Column(name = "created_by")
//    private String createdBy;
//    @Column(name = "created_on")
//    private LocalDateTime createdOn;

}