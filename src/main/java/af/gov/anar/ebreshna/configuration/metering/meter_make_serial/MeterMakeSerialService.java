package af.gov.anar.ebreshna.configuration.metering.meter_make_serial;

import af.gov.anar.ebreshna.configuration.metering.meter_make_serial.MeterMakeSerial;
import af.gov.anar.ebreshna.configuration.metering.meter_make_serial.MeterMakeSerialRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeterMakeSerialService {

    @Autowired
    private MeterMakeSerialRepository repository;

    @Autowired
    private UserService userService;

    public MeterMakeSerial save(MeterMakeSerial obj)
    {
        return repository.save(obj);
    }

    public List<MeterMakeSerial> findall()
    {
        return repository.findAll();
    }

    public MeterMakeSerial findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        MeterMakeSerial obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
