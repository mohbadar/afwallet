package af.gov.anar.dck.infrastructure.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import af.gov.anar.dck.useradministration.model.User;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Component
public class EmailUtil {

    Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendMessage(String to, String subject, String text) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);

            String[] recipientList = to.split(",");
            InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
            int counter = 0;
            for (String recipient : recipientList) {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }

            message.setRecipients(Message.RecipientType.TO, recipientAddress);

            // helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
        
            emailSender.send(message);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.debug(e.getMessage());
        }
    }

    public void sendMessageWithAttachment(String to, String subject, String text, File attachment) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);

            String[] recipientList = to.split(",");
            InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
            int counter = 0;
            for (String recipient : recipientList) {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }

            message.setRecipients(Message.RecipientType.TO, recipientAddress);

            // helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
                
            FileSystemResource file = new FileSystemResource(attachment);
            helper.addAttachment("File", file);
        
            emailSender.send(message);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            logger.debug(e.getMessage());
        }
    }

    public void sendMessageWithDetails(String to, String subject, String text, File attachment, User user,
            String currentEnvSlug, HttpServletRequest request) {
    
        String fullURL = "";
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

        if (queryString == null) {
            fullURL = requestURL.toString();
        } else {
            fullURL = requestURL.append('?').append(queryString).toString();
        }

        String requiredDetails = "";
        String currentUser = "";
        if(user != null) {
            currentUser = user.getName() + " (" + user.getUsername() + " - " + user.getEmail() + ")";
        }

        requiredDetails += "App Profile: " + activeProfile;
        requiredDetails += "<br>";
        requiredDetails += "Environment: " + currentEnvSlug;
        requiredDetails += "<br>";
        requiredDetails += "LoggedIn User: " + currentUser;
        requiredDetails += "<br>";
        requiredDetails += "Requested Method: " + request.getMethod();
        requiredDetails += "<br>";
        requiredDetails += "Requested URL: " + fullURL;
        requiredDetails += "<br>";
        requiredDetails += "DateTime: " + dateTimeUtil.getCurrentDate();
        requiredDetails += "<br>";
        requiredDetails += "User-Agent: " + request.getHeader("User-Agent");
        requiredDetails += "<br>";

        text = requiredDetails + text;
        if(attachment == null) {
            sendMessage(to, subject, text);
        } else {
            sendMessageWithAttachment(to, subject, text, attachment);
        }
        
    }
}
