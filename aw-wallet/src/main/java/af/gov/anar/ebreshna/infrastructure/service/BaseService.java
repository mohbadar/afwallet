package af.gov.anar.ebreshna.infrastructure.service;


import af.gov.anar.ebreshna.infrastructure.constant.ApplicationGenericConstants;
import af.gov.anar.ebreshna.infrastructure.dto.ErrorResponseDTO;
import af.gov.anar.ebreshna.infrastructure.dto.ResponseDTO;
import af.gov.anar.ebreshna.infrastructure.dto.SuccessResponseDTO;
import af.gov.anar.ebreshna.infrastructure.util.LoggerFactory;
import af.gov.anar.lib.logger.Logger;
import af.gov.anar.lib.logger.annotation.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * This is a base class for service package. The common functionality across the
 * 'services' classes are implemented in this class to inherit this property at
 * the required extended classes.
 *
 */
@Service
public class BaseService<T extends BaseEntity ,S extends BaseRepository>{

    private final S repository;

    @Autowired
    private UserService userService;

    @Autowired
    public BaseService(S repository)
    {
        this.repository =repository;
    }



    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    @Transactional
    public T save(T obj)
    {
        return (T) repository.save(obj);
    }

    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Iterable<T> findAll(){
        return repository.findAll();
    }

    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Optional<T> findById(Long id)
    {
        return  repository.findById(id);
    }

    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Iterable<T> findAllThatIsNotDeleted()
    {
        return  repository.findByDeleted(false);
    }

    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Iterable<T> findAllThatIsDeleted()
    {
        return  repository.findByDeleted(true);
    }


    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    @Async
    @Transactional
    public void delete(Long id)
    {
        T obj = (T) repository.findById(id).get();
        obj.setDeleted(true);
        obj.setDeletedBy(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

    @Loggable
    public boolean existsById(long id)
    {
        return repository.existsById(id);
    }

    @Loggable
    public long count()
    {
        return repository.count();
    }


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
