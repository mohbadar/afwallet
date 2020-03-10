package af.gov.anar.dck.infrastructure.util.enumeration;

/**
 * @author Rahul Goel created on 16/6/18
 */


public enum ExceptionType {

    NoHandlerFoundException,
    Exception,
    HttpRequestMethodNotSupportedException,
    MethodArgumentNotValidException,
    MissingServletRequestParameterException,
    ConstraintViolationException,
    HttpMessageNotReadableException,
    HttpMediaTypeNotSupportedException,

    ASMISException,
    ASMISDataSourceException,
    ASMISResourceNotFoundException;

    private ExceptionType(){

    }
}
