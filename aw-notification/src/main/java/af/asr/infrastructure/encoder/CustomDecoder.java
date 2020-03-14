
package af.asr.infrastructure.encoder;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;

@Component
public class CustomDecoder implements Decoder {

  private final Decoder defaultDecoder;
  private final GsonDecoder gsonDecoder;

  public CustomDecoder(final GsonDecoder gsonDecoder) {
    this.gsonDecoder = gsonDecoder;
    this.defaultDecoder = new Default();
  }

  @Override
  public Object decode(Response response, Type type) throws IOException, FeignException {
    if (byte[].class.equals(type)) {
      return this.defaultDecoder.decode(response, type);
    }

    return this.gsonDecoder.decode(response, type);
  }

}
