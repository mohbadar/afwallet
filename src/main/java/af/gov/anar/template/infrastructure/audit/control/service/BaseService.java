package af.gov.anar.template.infrastructure.audit.control.service;


import static af.gov.anar.template.infrastructure.constant.ApplicationGenericConstants.APPLICATION_ID;
import static af.gov.anar.template.infrastructure.constant.ApplicationGenericConstants.APPLICATION_NAME;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import af.gov.anar.lib.logger.Logger;
import af.gov.anar.template.infrastructure.constant.ApplicationGenericConstants;
import af.gov.anar.template.infrastructure.dto.ErrorResponseDTO;
import af.gov.anar.template.infrastructure.dto.ResponseDTO;
import af.gov.anar.template.infrastructure.dto.SuccessResponseDTO;
import af.gov.anar.template.infrastructure.util.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is a base class for service package. The common functionality across the
 * 'services' classes are implemented in this class to inherit this property at
 * the required extended classes.
 *
 */
@Service
public class BaseService {

    /**
     * Instance of LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    /**
     * serviceDelegateUtil which processes the HTTPRequestDTO requests

    /**
     * create success response.
     *
     * @param responseDTO
     *            the response DTO
     * @param message
     *            the message
     * @param attributes
     *            the attributes
     * @return ResponseDTO returns the responseDTO after creating appropriate
     *         success response and mapping to it
     */
    public ResponseDTO setSuccessResponse(ResponseDTO responseDTO, String message, Map<String, Object> attributes) {

        /** Success Response */
        SuccessResponseDTO successResponseDTO = new SuccessResponseDTO();

        successResponseDTO.setMessage(message);
        successResponseDTO.setCode(ApplicationGenericConstants.ALERT_INFORMATION);

        /** Adding attributes to success response */
        successResponseDTO.setOtherAttributes(attributes);

        responseDTO.setSuccessResponseDTO(successResponseDTO);
        return responseDTO;
    }

    /**
     * create error response.
     *
     * @param response
     *            the response
     * @param message
     *            the message
     * @param attributes
     *            the attributes
     * @return ResponseDTO returns the responseDTO after creating appropriate error
     *         response and mapping to it
     */
    protected ResponseDTO setErrorResponse(final ResponseDTO response, final String message,
                                           final Map<String, Object> attributes) {

        /** Create list of Error Response */
        List<ErrorResponseDTO> errorResponses = (response.getErrorResponseDTOs() != null)
                ? response.getErrorResponseDTOs()
                : new LinkedList<>();

        /** Error response */
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();

        errorResponse.setCode(ApplicationGenericConstants.ERROR);
        errorResponse.setMessage(message);

        errorResponse.setOtherAttributes(attributes);

        errorResponses.add(errorResponse);

        /** Adding list of error responses to response */
        response.setErrorResponseDTOs(errorResponses);
        return response;

    }




    /**
     * Checks if is null.
     *
     * @param list
     *            the list
     * @return true, if is null
     */
    public boolean isNull(List<?> list) {
        /* Check Whether the list is Null or not */
        return list == null;

    }

    /**
     * Checks if is empty.
     *
     * @param list
     *            the list
     * @return true, if is empty
     */
    public boolean isEmpty(List<?> list) {
        /* Check Whether the list is empty or not */
        return list.isEmpty();
    }


    protected boolean isNull(String val) {
        return (val == null || val.equalsIgnoreCase("NULL"));
    }

    /**

    /**
     * Validates the input {@link List} is either <code>null</code> or empty
     *
     * @param listToBeValidated
     *            the {@link List} object to be validated
     * @return <code>true</code> if {@link List} is either <code>null</code> or
     *         empty, else <code>false</code>
     */
    protected boolean isListEmpty(List<?> listToBeValidated) {
        return listToBeValidated == null || listToBeValidated.isEmpty();
    }

    /**
     * Validates the input {@link Set} is either <code>null</code> or empty
     *
     * @param setToBeValidated
     *            the {@link Set} object to be validated
     * @return <code>true</code> if {@link Set} is either <code>null</code> or
     *         empty, else <code>false</code>
     */
    protected boolean isSetEmpty(Set<?> setToBeValidated) {
        return setToBeValidated == null || setToBeValidated.isEmpty();
    }

    /**
     * Validates the input {@link String} is either <code>null</code> or empty
     *
     * @param stringToBeValidated
     *            the {@link String} object to be validated
     * @return <code>true</code> if input {@link String} is either <code>null</code>
     *         or empty, else <code>false</code>
     */
    protected boolean isStringEmpty(String stringToBeValidated) {
        return stringToBeValidated == null || stringToBeValidated.isEmpty();
    }

    /**
     * Validates the input {@link Map} is either <code>null</code> or empty
     *
     * @param mapToBeValidated
     *            the {@link Map} object to be validated
     * @return <code>true</code> if {@link Map} is either <code>null</code> or
     *         empty, else <code>false</code>
     */
    protected boolean isMapEmpty(Map<?, ?> mapToBeValidated) {
        return mapToBeValidated == null || mapToBeValidated.isEmpty();
    }

    /**
     * Validates the input byte array is either <code>null</code> or empty
     *
     * @param byteArrayToBeValidated
     *            the byte array to be validated
     * @return <code>true</code> if byte array is either <code>null</code> or empty,
     *         else <code>false</code>
     */
    protected boolean isByteArrayEmpty(byte[] byteArrayToBeValidated) {
        return byteArrayToBeValidated == null || byteArrayToBeValidated.length == 0;
    }


}
