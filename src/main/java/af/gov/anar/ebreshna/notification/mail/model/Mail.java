package af.gov.anar.ebreshna.notification.mail.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Mail {

    private String subject;
    private String content;
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String attachmentPath;
    private String attachmentName;
    private boolean hasAttachment;
}
