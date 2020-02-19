package af.gov.anar.ebreshna.common.nsc.service;

import af.gov.anar.ebreshna.common.nsc.model.UnitMaster;
import af.gov.anar.ebreshna.common.nsc.repository.UnitRepository;
import af.gov.anar.ebreshna.common.office.model.DesignationMaster;
import af.gov.anar.ebreshna.common.office.repository.DesignationMasterRepository;
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
