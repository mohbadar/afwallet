package af.gov.anar.dck.infrastructure.exception.odkx;

public class AppNameMismatchException extends ODKTablesException {

  private static final long serialVersionUID = 1L;

  public AppNameMismatchException() {
    super();
  }

  public AppNameMismatchException(String message) {
    super(message);
  }

  public AppNameMismatchException(Throwable cause) {
    super(cause);
  }

  public AppNameMismatchException(String message, Throwable cause) {
    super(message, cause);
  }

}
