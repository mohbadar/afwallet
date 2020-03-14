
package af.asr.csc.mapper;

import af.asr.csc.model.PortraitEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Component
public class PortraitMapper {

  private PortraitMapper() {
    super();
  }

  public static PortraitEntity map(final MultipartFile multipartFile) throws IOException {
    final PortraitEntity portraitEntity = new PortraitEntity();
    portraitEntity.setImage(multipartFile.getBytes());
    portraitEntity.setSize(multipartFile.getSize());
    portraitEntity.setContentType(multipartFile.getContentType());
    return portraitEntity;
  }

}
