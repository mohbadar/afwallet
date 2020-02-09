package af.gov.anar.ebreshna.common.notifcation;

import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
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

    public List<Notification> findByNotificationStatus(NotificationStatus status)
    {
        return repository.findByNotificationStatus(status);
    }

}
