package af.gov.anar.spm.spm.data;

public class LookupTableEntry {

    private Integer valueFrom;
    private Integer valueTo;
    private Double score;

    public LookupTableEntry() {
        super();
    }

    public LookupTableEntry(final Integer valueFrom, final Integer valueTo, final Double score) {
        super();
        this.valueFrom = valueFrom;
        this.valueTo = valueTo;
        this.score = score;
    }

    public Integer getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(Integer valueFrom) {
        this.valueFrom = valueFrom;
    }

    public Integer getValueTo() {
        return valueTo;
    }

    public void setValueTo(Integer valueTo) {
        this.valueTo = valueTo;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
