package af.gov.anar.corona.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

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
