
package af.gov.anar.spm.spm.data;

public class ResponseData {

    private Long id;
    private String text;
    private Integer value;
    private Integer sequenceNo;

    public ResponseData() {
        super();
    }

    public ResponseData(final Long id, final String text, final Integer value,
                        final Integer sequenceNo) {
        super();
        this.id = id;
        this.text = text;
        this.value = value;
        this.sequenceNo = sequenceNo;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
}
