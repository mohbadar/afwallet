package af.gov.anar.ebreshna.configuration.billing.behaviour;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BehaviourService {

    @Autowired
    private BehaviourRepository repository;

    @Autowired
    private UserService userService;

    public BehaviourConfiguration save(BehaviourConfiguration obj)
    {
        return repository.save(obj);
    }

    public List<BehaviourConfiguration> findall()
    {
        return repository.findAll();
    }

    public BehaviourConfiguration findOne(long id)
    {
        return repository.findById(id).get();
    }

    public void delete(long id)
    {
        BehaviourConfiguration obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
