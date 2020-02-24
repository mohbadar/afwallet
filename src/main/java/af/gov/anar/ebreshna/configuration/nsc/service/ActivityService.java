package af.gov.anar.ebreshna.configuration.nsc.service;

import af.gov.anar.ebreshna.configuration.nsc.model.ActivityMaster;
import af.gov.anar.ebreshna.configuration.nsc.repository.ActivityRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    @Autowired
    private UserService userService;

    public ActivityMaster save(ActivityMaster obj)
    {
        return repository.save(obj);
    }

    public List<ActivityMaster> findall()
    {
        return repository.findAll();
    }

    public ActivityMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ActivityMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
