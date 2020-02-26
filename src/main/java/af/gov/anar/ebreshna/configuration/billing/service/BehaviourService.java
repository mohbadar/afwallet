package af.gov.anar.ebreshna.configuration.billing.service;

import af.gov.anar.ebreshna.configuration.billing.model.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.repository.BehaviourRepository;
import af.gov.anar.ebreshna.configuration.office.model.PremisesSubCategory;
import af.gov.anar.ebreshna.configuration.office.repository.PremisesSubCategoryRepository;
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
        return repository.getOne(id);
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
