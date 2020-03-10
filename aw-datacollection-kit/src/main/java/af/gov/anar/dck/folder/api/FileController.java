package af.gov.anar.dck.folder.api;

import af.gov.anar.dck.common.auth.UserAuthService;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping({ "/api/files" })
public class FileController  {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${app.upload.temp}")
    private String uploadTempDirPath;

    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping(value = "/image/**", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String imageUrl = uri.replaceAll("/api/files/image/", "");
        boolean isThumbnil = imageUrl.endsWith("timg");

        if(isThumbnil) {
            imageUrl = imageUrl.substring(0, imageUrl.lastIndexOf("/timg"));
        }

        if(new File(imageUrl).exists()) {
            if(isThumbnil) {
                BufferedImage bufferedImage = ImageIO.read(new FileInputStream(imageUrl));
                BufferedImage thumbnail = Scalr.resize(bufferedImage, 250);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(thumbnail, "jpg", bos);

                return bos.toByteArray();
            } else {
                return IOUtils.toByteArray(new FileInputStream(imageUrl));
            }

        } else {
			return null;
		}
    }
    
    @RequestMapping(value = "/download/temp", method = RequestMethod.GET)
	public Resource getUploadTempFile(@RequestParam("file") String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File uploadTempDir = new File(uploadTempDirPath);
        File file = new File(uploadTempDir + "/" + userAuthService.getCurrentEnv() + "/" + fileName);
        if (file.exists()) {
            try {
                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
                
                Path path = file.toPath();
                String mimeType = Files.probeContentType(path);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachment; filename=" + fileName);
                response.setContentType("application/octet-stream");
                
                return resource;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
	}
}
