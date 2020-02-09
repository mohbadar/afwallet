package af.gov.anar.ebreshna.helpdesk.service;

import af.gov.anar.ebreshna.helpdesk.model.Comment;
import af.gov.anar.ebreshna.helpdesk.model.Notification;
import af.gov.anar.ebreshna.helpdesk.repository.CommentRepository;
import af.gov.anar.ebreshna.helpdesk.repository.NotificationRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private UserService userService;

    public Notification save(Notification obj)
    {
        return repository.save(obj);
    }

    public List<Notification> findall()
    {
        return repository.findAll();
    }

    public Notification findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        Notification obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
