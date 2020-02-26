package af.gov.anar.ebreshna.configuration.metering.meter_capacity;

import af.gov.anar.ebreshna.configuration.metering.meter_capacity.MeterCapacityMaster;
import af.gov.anar.ebreshna.configuration.metering.meter_capacity.MeterCapacityRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeterCapacityService {

    @Autowired
    private MeterCapacityRepository repository;

    @Autowired
    private UserService userService;

    public MeterCapacityMaster save(MeterCapacityMaster obj)
    {
        return repository.save(obj);
    }

    public List<MeterCapacityMaster> findall()
    {
        return repository.findAll();
    }

    public MeterCapacityMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        MeterCapacityMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }


}