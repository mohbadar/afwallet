package af.gov.anar.ebreshna.nsc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  MeterWorkingType {

    WHOLE_METER_TYPE ("Whole Meter Type"),
    CT_TYPE_METER("CT-Type Meter");

    private String content;
}
