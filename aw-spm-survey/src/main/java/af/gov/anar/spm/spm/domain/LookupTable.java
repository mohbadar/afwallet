
package af.gov.anar.spm.spm.domain;


import af.gov.anar.lang.data.AbstractPersistableCustom;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "m_survey_lookup_tables")
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class LookupTable extends AbstractPersistableCustom<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(name = "a_key", length = 32)
    private String key;

    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "value_from", precision = 4)
    private Integer valueFrom;

    @Column(name = "value_to", precision = 4)
    private Integer valueTo;

    @Column(name = "score", precision = 5, scale = 2)
    private Double score;

    public LookupTable() {
        super();
    }

}
