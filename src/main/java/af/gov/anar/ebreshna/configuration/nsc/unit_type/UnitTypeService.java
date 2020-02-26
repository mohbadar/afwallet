package af.gov.anar.ebreshna.configuration.nsc.unit_type;

import af.gov.anar.ebreshna.configuration.nsc.unit_type.UnitType;
import af.gov.anar.ebreshna.configuration.nsc.unit_type.UnitTypeRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UnitTypeService {

    @Autowired
    private UnitTypeRepository repository;

    @Autowired
    private UserService userService;

    public UnitType save(UnitType obj)
    {
        return repository.save(obj);
    }

    public List<UnitType> findall()
    {
        return repository.findAll();
    }

    public UnitType findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        UnitType obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
