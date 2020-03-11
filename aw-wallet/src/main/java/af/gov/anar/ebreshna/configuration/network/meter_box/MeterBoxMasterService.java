package af.gov.anar.ebreshna.configuration.network.meter_box;


import af.gov.anar.ebreshna.configuration.network.meter_box.MeterBoxMaster;
import af.gov.anar.ebreshna.configuration.network.meter_box.MeterBoxMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeterBoxMasterService {

    @Autowired
    private MeterBoxMasterRepository repository;

    @Autowired
    private UserService userService;

    public MeterBoxMaster save(MeterBoxMaster obj)
    {
        return repository.save(obj);
    }

    public List<MeterBoxMaster> findall()
    {
        return repository.findAll();
    }

    public MeterBoxMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        MeterBoxMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
