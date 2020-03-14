package af.asr.customer.identification_card.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import af.asr.customer.customer.model.CustomerEntity;

@Entity
@Table(name = "maat_identification_cards")
public class IdentificationCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "a_type")
    private String type;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    @Column(name = "a_number")
    private String number;
    @Column(name = "expiration_date")
//    @Convert(converter = LocalDateConverter.class)
    private LocalDate expirationDate;
    @Column(name = "issuer")
    private String issuer;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdOn;
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    @Column(name = "last_modified_on")
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime lastModifiedOn;


}