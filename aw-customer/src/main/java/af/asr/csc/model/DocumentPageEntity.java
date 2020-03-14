package af.asr.csc.model;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;


@Entity
@Table(name = "document_page")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class DocumentPageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_id")
    private DocumentEntity document;

    @Column(name = "page_number")
    private Integer pageNumber;

    @Column(name = "content_type")
    private String contentType;


    @Column(name = "size")
    private Long size;

    @Lob
    @Column(name = "image")
    private byte[] image;


}