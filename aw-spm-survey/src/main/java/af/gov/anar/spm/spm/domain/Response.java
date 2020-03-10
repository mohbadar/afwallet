
package af.gov.anar.spm.spm.domain;


import af.gov.anar.lang.data.AbstractPersistableCustom;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "m_survey_responses")
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Response extends AbstractPersistableCustom<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "a_text", length = 255)
    private String text;

    @Column(name = "a_value", precision = 4)
    private Integer value;

    @Column(name = "sequence_no", precision = 4)
    private Integer sequenceNo;

    public Response() {
        super();
    }


}
