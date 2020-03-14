package af.asr.csc.model;

import af.asr.csc.domain.ContactDetail;
import af.asr.csc.model.CustomerEntity;
import javafx.scene.image.PixelFormat;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ContactDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    @Column(name = "a_type")
    private String type;
    @Column(name = "a_group")
    private String group;
    @Column(name = "a_value")
    private String value;
    @Column(name = "preference_level")
    private Integer preferenceLevel;
    @Column(name = "validated")
    private Boolean valid;


    public void setType(ContactDetail.Type type)
    {
        this.type = type.name();
    }

    public void setGroup(ContactDetail.Group group)
    {
        this.group = group.name();
    }
}