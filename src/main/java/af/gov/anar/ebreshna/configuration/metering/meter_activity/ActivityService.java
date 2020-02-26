package af.gov.anar.ebreshna.configuration.metering.meter_activity;

import af.gov.anar.ebreshna.configuration.metering.meter_activity.MeteringActivityMaster;
import af.gov.anar.ebreshna.configuration.metering.meter_activity.ActivityRepository;
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

    public MeteringActivityMaster save(MeteringActivityMaster obj)
    {
        return repository.save(obj);
    }

    public List<MeteringActivityMaster> findall()
    {
        return repository.findAll();
    }

    public MeteringActivityMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        MeteringActivityMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
