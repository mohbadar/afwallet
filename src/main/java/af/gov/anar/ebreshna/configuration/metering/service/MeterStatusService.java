package af.gov.anar.ebreshna.configuration.metering.service;

import af.gov.anar.ebreshna.configuration.metering.model.MeterMakeSerial;
import af.gov.anar.ebreshna.configuration.metering.model.MeterStatusMaster;
import af.gov.anar.ebreshna.configuration.metering.repository.MeterMakeSerialRepository;
import af.gov.anar.ebreshna.configuration.metering.repository.MeterStatusRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeterStatusService {

    @Autowired
    private MeterStatusRepository repository;

    @Autowired
    private UserService userService;

    public MeterStatusMaster save(MeterStatusMaster obj)
    {
        return repository.save(obj);
    }

    public List<MeterStatusMaster> findall()
    {
        return repository.findAll();
    }

    public MeterStatusMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        MeterStatusMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
