package af.gov.anar.ebreshna.infrastructure.dto;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * The DTO Class ErrorResponseDTO.
 */
@Getter
@Setter
public class ErrorResponseDTO {

    private String code;
    private String message;
    private Map<String, Object> otherAttributes;
    private String infoType;
}
