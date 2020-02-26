package af.gov.anar.ebreshna.configuration.metering.service;

import af.gov.anar.ebreshna.configuration.metering.model.ActivityRelation;
import af.gov.anar.ebreshna.configuration.metering.repository.ActivityRelationRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityRelationService {

    @Autowired
    private ActivityRelationRepository repository;

    @Autowired
    private UserService userService;

    public ActivityRelation save(ActivityRelation obj)
    {
        return repository.save(obj);
    }

    public List<ActivityRelation> findall()
    {
        return repository.findAll();
    }

    public ActivityRelation findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ActivityRelation obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
