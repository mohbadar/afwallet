package af.gov.anar.ebreshna.configuration.metering.service;

import af.gov.anar.ebreshna.configuration.metering.model.MeterMakeSerial;
import af.gov.anar.ebreshna.configuration.metering.model.Zone;
import af.gov.anar.ebreshna.configuration.metering.repository.MeterMakeSerialRepository;
import af.gov.anar.ebreshna.configuration.metering.repository.ZoneRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository repository;

    @Autowired
    private UserService userService;

    public Zone save(Zone obj)
    {
        return repository.save(obj);
    }

    public List<Zone> findall()
    {
        return repository.findAll();
    }

    public Zone findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        Zone obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
