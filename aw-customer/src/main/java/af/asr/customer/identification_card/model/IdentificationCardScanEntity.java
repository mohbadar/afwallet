package af.asr.customer.identification_card.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "identification_card_scan")
public class IdentificationCardScanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "identifier")
    private String identifier;
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "image")
    private byte[] image;
    @Column(name = "size")
    private Long size;
    @Column(name = "content_type")
    private String contentType;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "identification_card_id")
    private IdentificationCardEntity identificationCard;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdOn;



}
