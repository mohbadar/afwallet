package af.gov.anar.dck.infrastructure.exception.odkx;

/**
 * Exception when a problem occurs with the datastore
 *
 * @author wbrunette@gmail.com
 * @author mitchellsundt@gmail.com
 * 
 */
public class ODKDatastoreException extends Exception {

  /**
   * Serial number for serialization
   */
  private static final long serialVersionUID = -2320458565807877689L;

  /**
   * Default constructor
   */
  public ODKDatastoreException() {
    super();
  }
  
  /**
   * Construct exception with the error message
   * 
   * @param message
   *    exception message
   */
  public ODKDatastoreException(String message) {
    super(message);
  }

  /**
   * Construction exception with error message and throwable cause
   * 
   * @param message
   *    exception message
   * @param cause
   *    throwable cause
   */
  public ODKDatastoreException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construction exception with throwable cause
   * 
   * @param cause
   *    throwable cause
   */
  public ODKDatastoreException(Throwable cause) {
    super(cause);
  }

}
