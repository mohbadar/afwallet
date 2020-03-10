package af.gov.anar.ebreshna.configuration.network.meter_reader;

import af.gov.anar.ebreshna.configuration.network.meter_reader.MeterReaderMaster;
import af.gov.anar.ebreshna.configuration.network.meter_reader.MeterReaderMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeterReaderMasterService {

    @Autowired
    private MeterReaderMasterRepository repository;

    @Autowired
    private UserService userService;

    public MeterReaderMaster save(MeterReaderMaster obj)
    {
        return repository.save(obj);
    }

    public List<MeterReaderMaster> findAll()
    {
        return repository.findAll();
    }

    public MeterReaderMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        MeterReaderMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
