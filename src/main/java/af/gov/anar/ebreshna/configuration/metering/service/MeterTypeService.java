package af.gov.anar.ebreshna.configuration.metering.service;

import af.gov.anar.ebreshna.configuration.metering.model.MeterMakeSerial;
import af.gov.anar.ebreshna.configuration.metering.model.MeteringType;
import af.gov.anar.ebreshna.configuration.metering.repository.MeterMakeSerialRepository;
import af.gov.anar.ebreshna.configuration.metering.repository.MeteringTypeRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeterTypeService {

    @Autowired
    private MeteringTypeRepository repository;

    @Autowired
    private UserService userService;

    public MeteringType save(MeteringType obj)
    {
        return repository.save(obj);
    }

    public List<MeteringType> findall()
    {
        return repository.findAll();
    }

    public MeteringType findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        MeteringType obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
