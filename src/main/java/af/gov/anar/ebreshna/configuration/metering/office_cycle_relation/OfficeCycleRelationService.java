package af.gov.anar.ebreshna.configuration.metering.office_cycle_relation;

import af.gov.anar.ebreshna.configuration.metering.office_cycle_relation.OfficeCycleRelation;
import af.gov.anar.ebreshna.configuration.metering.office_cycle_relation.OfficeCycleRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OfficeCycleRelationService {

    @Autowired
    private OfficeCycleRepository repository;

    @Autowired
    private UserService userService;

    public OfficeCycleRelation save(OfficeCycleRelation obj)
    {
        return repository.save(obj);
    }

    public List<OfficeCycleRelation> findall()
    {
        return repository.findAll();
    }

    public OfficeCycleRelation findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        OfficeCycleRelation obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
