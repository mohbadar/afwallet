package af.gov.anar.ebreshna.configuration.csc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeOfService {

    NO_CURRENT_QUERIES("No Current Queries"),
    OTHER_QUERIES ("Other Queries");

    private String content;
}
