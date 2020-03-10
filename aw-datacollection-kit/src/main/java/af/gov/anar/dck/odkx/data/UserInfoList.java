package af.gov.anar.dck.odkx.data;

import java.util.ArrayList;
import java.util.List;

/**
 * This holds the list of UserInfo structures describing
 * the users that can manipulate odkTables appName data. This may
 * include users with a ROLE_USER but without any synchronization
 * capability.
 * 
 * @author mitchellsundt@gmail.com
 *
 */
public class UserInfoList extends ArrayList<UserInfo> {

  /**
   * 
   */
  private static final long serialVersionUID = -566890619427903300L;

  /**
   * Constructor used by Jackson
   */
  public UserInfoList() {
  }

  public UserInfoList(List<UserInfo> userInfo) {
    super.addAll(userInfo);
  }
}
