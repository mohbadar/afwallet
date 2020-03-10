package af.gov.anar.dck.infrastructure.util;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

@Component
public class ExceptionUtil {
    static Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);
    
    @Value("${spring.mail.to}")
	private String emailTo;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailUtil emailUtil;
    
    public static void dumpException(Throwable exception, File file) {
        FileOutputStream fos = null;
        PrintStream ps = null;
        try {
            fos = new FileOutputStream(file);
            ps = new PrintStream(fos);  
            exception.printStackTrace(ps);  
        } catch (FileNotFoundException e) {
        	logger.trace("dumpException: ", e);
        } finally {
            IOUtils.closeQuietly(ps);
            IOUtils.closeQuietly(fos);
        }
    }

    public void notifyDevTeam(String emailSubj, Exception e, User currentLoggedInUser, String envSlug, File attachment, HttpServletRequest request, boolean reThrowExcep) throws Exception {
        String errorStr = ExceptionUtils.getStackTrace(e);
        if (!(e instanceof AccessDeniedException)){
            emailUtil.sendMessageWithDetails(emailTo, emailSubj, errorStr, attachment,
                currentLoggedInUser, envSlug , request);  
        }

        if(reThrowExcep) {
            throw e;
        }
    }
 
}