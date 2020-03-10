
package af.gov.anar.spm.spm.domain;


import af.gov.anar.lang.data.AbstractPersistableCustom;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "m_survey_scorecards")
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Scorecard extends AbstractPersistableCustom<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_id")
    private Response response;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private AppUser appUser;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "client_id")
//    private Client client;

    @Column(name = "created_on")
    @Temporal(value = TemporalType.TIMESTAMP)
    @OrderBy("createdOn DESC")
    private Date createdOn;

    @Column(name = "a_value", precision = 4)
    private Integer value;

    public Scorecard() {
        super();
    }

}
