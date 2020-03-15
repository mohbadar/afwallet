
package af.asr.office.util;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;


public interface Utils {
  static Date utcNow() {
    return Date.from(Instant.now(Clock.systemUTC()));
  }
}
