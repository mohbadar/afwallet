package af.gov.anar.dck.odkx.data;

import java.util.ArrayList;
import java.util.List;

/**
 * This holds the list of odkClientVersion values available on the server.
 * 
 * @author mitchellsundt@gmail.com
 *
 */
public class ClientVersionList extends ArrayList<String> {

  /**
   * 
   */
  private static final long serialVersionUID = -5668906194279093300L;

  /**
   * Constructor used by Jackson
   */
  public ClientVersionList() {
  }

  public ClientVersionList(List<String> versions) {
    super.addAll(versions);
  }
}
