package af.gov.anar.ebreshna.configuration.nsc.service;

import af.gov.anar.ebreshna.configuration.nsc.model.SupplyVoltage;
import af.gov.anar.ebreshna.configuration.nsc.repository.SupplyVoltageRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SupplyVoltageService {

    @Autowired
    private SupplyVoltageRepository repository;

    @Autowired
    private UserService userService;

    public SupplyVoltage save(SupplyVoltage obj)
    {
        return repository.save(obj);
    }

    public List<SupplyVoltage> findall()
    {
        return repository.findAll();
    }

    public SupplyVoltage findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        SupplyVoltage obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
