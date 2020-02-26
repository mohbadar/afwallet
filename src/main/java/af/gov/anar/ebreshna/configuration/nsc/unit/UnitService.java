package af.gov.anar.ebreshna.configuration.nsc.unit;

import af.gov.anar.ebreshna.configuration.nsc.unit.UnitMaster;
import af.gov.anar.ebreshna.configuration.nsc.unit.UnitRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UnitService {

    @Autowired
    private UnitRepository repository;

    @Autowired
    private UserService userService;

    public UnitMaster save(UnitMaster obj)
    {
        return repository.save(obj);
    }

    public List<UnitMaster> findall()
    {
        return repository.findAll();
    }

    public UnitMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        UnitMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
