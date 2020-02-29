package af.gov.anar.ebreshna.csc.ivr_feedback;

import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    @Autowired
    private UserService userService;

    public Feedback save(Feedback obj)
    {
        return repository.save(obj);
    }

    public List<Feedback> findall()
    {
        return repository.findAll();
    }

    public Feedback findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        Feedback obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
