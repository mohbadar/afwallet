
package af.gov.anar.spm.spm.domain;



import af.gov.anar.lang.data.AbstractPersistableCustom;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "m_survey_components")
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Component extends AbstractPersistableCustom<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(name = "a_key", length = 32)
    private String key;

    @Column(name = "a_text", length = 255)
    private String text;

    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "sequence_no", precision = 4)
    private Integer sequenceNo;

    public Component() {
        super();
    }

}
