package af.gov.anar.ebreshna.common.nsc.service;

import af.gov.anar.ebreshna.common.nsc.model.UnitType;
import af.gov.anar.ebreshna.common.office.model.DesignationMaster;
import af.gov.anar.ebreshna.common.office.repository.DesignationMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UnitTypeService {

    @Autowired
    private UnitTypeService repository;

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
