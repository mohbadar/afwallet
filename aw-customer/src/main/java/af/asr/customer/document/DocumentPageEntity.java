package af.asr.customer.document;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;


@Entity
@Table(name = "maat_document_pages")
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