package af.gov.anar.ebreshna.configuration.office.designation;

import af.gov.anar.ebreshna.configuration.office.designation.DesignationMaster;
import af.gov.anar.ebreshna.configuration.office.designation.DesignationMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DesignationMasterService {

    @Autowired
    private DesignationMasterRepository repository;

    @Autowired
    private UserService userService;

    public DesignationMaster save(DesignationMaster obj)
    {
        return repository.save(obj);
    }

    public List<DesignationMaster> findAll()
    {
        return repository.findAll();
    }

    public DesignationMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        DesignationMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
