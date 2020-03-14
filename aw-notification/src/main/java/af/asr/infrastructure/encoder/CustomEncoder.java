
package af.asr.infrastructure.encoder;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import feign.gson.GsonEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;

@Component
public class CustomEncoder implements Encoder {

  private final Encoder defaultEncoder;
  private final GsonEncoder gsonEncoder;
  private final SpringFormEncoder springFormEncoder;

  public CustomEncoder(final GsonEncoder gsonEncoder, final SpringFormEncoder springFormEncoder) {
    this.gsonEncoder = gsonEncoder;
    this.springFormEncoder = springFormEncoder;
    this.defaultEncoder = new Default();
  }

  @Override
  public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
    if (bodyType.equals(MultipartFile.class)) {
      this.springFormEncoder.encode(object, bodyType, template);
    }else {
      this.gsonEncoder.encode(object, bodyType, template);
    }
  }
}
