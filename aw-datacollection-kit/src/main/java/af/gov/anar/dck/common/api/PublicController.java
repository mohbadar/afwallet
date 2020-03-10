package af.gov.anar.dck.common.api;

import af.gov.anar.dck.form.service.FormService;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.service.InstanceService;
import af.gov.anar.dck.useradministration.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import af.gov.anar.dck.infrastructure.util.EmailUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping({ "/api/public" })
public class PublicController{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${spring.mail.to}")
    private String emailTo;

    @Autowired
    private FormService formService;
    
    @Autowired
    private EmailUtil emailUtil;
    
    @Autowired
    private UserService userService;

    @Autowired
    private InstanceService instanceService;

    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "instances/{instanceId}/image/{imageName}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public String getImage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("instanceId") Long id, @PathVariable("imageName") String image) throws IOException {
        logger.info("Entry InstanceController>getImage() - GET");
        
            return "public route - test string to fetch image";
    }

    @RequestMapping(value = "instances/{instanceId}/video/{videoName}", method = RequestMethod.GET)
	public Resource getVideo(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("instanceId") Long id, @PathVariable("videoName") String video) throws IOException {
        logger.info("Entry InstanceController>getVideo() - GET");
        
        Instance instance = instanceService.findById(id);
		String videoPath = instance.getInstanceFolderName() + File.separator + video;
		File file = new File(videoPath);
		if (file.exists()) {

			try {
				InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
				
				Path path = file.toPath();
				String mimeType = Files.probeContentType(path);
				// response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + video);
				response.setContentType("video/mp4");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Raseed");
				return resource;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>File Nte Exist");
		return null;
    }

    @RequestMapping(value = "instances/{instanceId}/audio/{audioName}", method = RequestMethod.GET)
	public @ResponseBody byte[] getAudio(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("instanceId") Long id, @PathVariable("audioName") String audio) throws IOException {
                logger.info("Entry InstanceController>getAudio() - GET");
                //User currentLoggedInUser = userService.getLoggedInUser();		
                try {
                    Instance instance = instanceService.findById(id);
                    String audioPath = instance.getInstanceFolderName() + File.separator + audio;
                    if (new File(audioPath).exists()) {
                        System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>File Exist");
                        return IOUtils.toByteArray(new FileInputStream(audioPath));
                    } else {
                        System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>File Don't Exist & Return Null");
                        return null;
                    }
                } catch (Exception e) {
                    String errorStr = ExceptionUtils.getStackTrace(e);
                   // emailUtil.sendMessageWithDetails(emailTo, "500 - ASIMS Exception - Instance", errorStr, null, currentLoggedInUser, userService.getCurrentEnv(), request);
                    return null;
                }
    }




    @RequestMapping(value = "instances/{instanceId}/file/{fileName}", method = RequestMethod.GET)
	public String getFile(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("instanceId") Long id, @PathVariable("fileName") String file) throws IOException {
        logger.info("Entry InstanceController>getFile() - GET");
        
            return "public route - test string to fetch file";
    }

}