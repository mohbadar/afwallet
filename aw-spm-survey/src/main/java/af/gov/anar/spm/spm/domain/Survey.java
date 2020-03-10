
package af.gov.anar.spm.spm.domain;


import af.gov.anar.lang.data.AbstractPersistableCustom;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "m_surveys")
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Survey extends AbstractPersistableCustom<Long> {

    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    @OrderBy("sequenceNo")
    private List<Component> components;

    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    @OrderBy("sequenceNo")
    private List<Question> questions;

    @Column(name = "a_key", length = 32)
    private String key;

    @Column(name = "a_name", length = 255)
    private String name;

    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "country_code", length = 2)
    private String countryCode;

    @Column(name = "valid_from")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date validFrom;

    @Column(name = "valid_to")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date validTo;

    public Survey() {
        super();
    }


    public void setQuestions(List<Question> questions) {
        if(this.questions != null){
            this.questions.clear();;
        }else{
            this.questions = new ArrayList<>();
        }
        
        this.questions.addAll(questions);
    }


}
