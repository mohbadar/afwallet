package af.gov.anar.ebreshna.common.network.service;

import af.gov.anar.ebreshna.common.network.model.FeederMaster;
import af.gov.anar.ebreshna.common.network.repository.FeederMasterRepository;
import af.gov.anar.ebreshna.common.office.model.DesignationMaster;
import af.gov.anar.ebreshna.common.office.repository.DesignationMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FeederMasterService {

    @Autowired
    private FeederMasterRepository repository;

    @Autowired
    private UserService userService;

    public FeederMaster save(FeederMaster obj)
    {
        return repository.save(obj);
    }

    public List<FeederMaster> findall()
    {
        return repository.findAll();
    }

    public FeederMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        FeederMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
