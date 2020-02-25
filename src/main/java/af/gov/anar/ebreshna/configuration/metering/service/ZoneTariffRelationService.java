package af.gov.anar.ebreshna.configuration.metering.service;

import af.gov.anar.ebreshna.configuration.metering.model.Zone;
import af.gov.anar.ebreshna.configuration.metering.model.ZoneTariffRelation;
import af.gov.anar.ebreshna.configuration.metering.repository.ZoneRepository;
import af.gov.anar.ebreshna.configuration.metering.repository.ZoneTariffRelationRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZoneTariffRelationService {

    @Autowired
    private ZoneTariffRelationRepository repository;

    @Autowired
    private UserService userService;

    public ZoneTariffRelation save(ZoneTariffRelation obj)
    {
        return repository.save(obj);
    }

    public List<ZoneTariffRelation> findall()
    {
        return repository.findAll();
    }

    public ZoneTariffRelation findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ZoneTariffRelation obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
