package af.gov.anar.ebreshna.configuration.billing.voltage_unit;

import af.gov.anar.ebreshna.configuration.billing.voltage_unit.VoltageUnit;
import af.gov.anar.ebreshna.configuration.billing.voltage_unit.VoltageUnitRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VoltageUnitService {

    @Autowired
    private VoltageUnitRepository repository;

    @Autowired
    private UserService userService;

    public VoltageUnit save(VoltageUnit obj)
    {
        return repository.save(obj);
    }

    public List<VoltageUnit> findall()
    {
        return repository.findAll();
    }

    public VoltageUnit findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        VoltageUnit obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
