package af.gov.anar.ebreshna.configuration.metering.cycle;

import af.gov.anar.ebreshna.configuration.metering.cycle.CycleConfiguration;
import af.gov.anar.ebreshna.configuration.metering.cycle.CycleRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CycleService {

    @Autowired
    private CycleRepository repository;

    @Autowired
    private UserService userService;

    public CycleConfiguration save(CycleConfiguration obj)
    {
        return repository.save(obj);
    }

    public List<CycleConfiguration> findall()
    {
        return repository.findAll();
    }

    public CycleConfiguration findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        CycleConfiguration obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}