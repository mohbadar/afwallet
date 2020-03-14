package af.asr.customer.util;

import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@ConfigurationProperties(prefix="upload")
@Validated
public class UploadProperties {
    @Valid
    private final Image image = new Image();

    public static class Image {
        @Range(min = 0L)
        private long maxSize;

        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }
    }

    public Image getImage() {
        return image;
    }
}