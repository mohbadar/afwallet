
package af.gov.anar.spm.spm.data;

import java.util.Date;

public class ScorecardValue {

    private Long questionId;
    private Long responseId;
    private Integer value;
    private Date createdOn;

    public ScorecardValue() {
        super();
    }

    private ScorecardValue(final Long questionId, final Long responseId, final Integer value, final Date createdOn) {
        this.questionId = questionId;
        this.responseId = responseId;
        this.value = value;
        this.createdOn = createdOn;
    }

    public static ScorecardValue instance(final Long questionId, final Long responseId, final Integer value, final Date createdOn) {
        return new ScorecardValue(questionId, responseId, value, createdOn);
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    
    public Date getCreatedOn() {
        return this.createdOn;
    }

    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    
    
}
