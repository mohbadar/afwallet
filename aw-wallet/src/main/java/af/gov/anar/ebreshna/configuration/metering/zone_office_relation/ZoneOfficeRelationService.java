package af.gov.anar.ebreshna.configuration.metering.zone_office_relation;

import af.gov.anar.ebreshna.configuration.metering.zone_office_relation.ZoneOfficeRelation;
import af.gov.anar.ebreshna.configuration.metering.zone_office_relation.ZoneOfficeRelationRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZoneOfficeRelationService {

    @Autowired
    private ZoneOfficeRelationRepository repository;

    @Autowired
    private UserService userService;

    public ZoneOfficeRelation save(ZoneOfficeRelation obj)
    {
        return repository.save(obj);
    }

    public List<ZoneOfficeRelation> findall()
    {
        return repository.findAll();
    }

    public ZoneOfficeRelation findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ZoneOfficeRelation obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }


}
