package af.gov.anar.query.infrastructure.dto;

import java.util.List;

/**
 * The DTO Class ResponseDTO.
 */
public class ResponseDTO {
    private List<ErrorResponseDTO> errorResponseDTOs;
    private SuccessResponseDTO successResponseDTO;
    public List<ErrorResponseDTO> getErrorResponseDTOs() {
        return errorResponseDTOs;
    }
    public void setErrorResponseDTOs(List<ErrorResponseDTO> errorResponseDTOs) {
        this.errorResponseDTOs = errorResponseDTOs;
    }
    public SuccessResponseDTO getSuccessResponseDTO() {
        return successResponseDTO;
    }
    public void setSuccessResponseDTO(SuccessResponseDTO successResponseDTO) {
        this.successResponseDTO = successResponseDTO;
    }

}
