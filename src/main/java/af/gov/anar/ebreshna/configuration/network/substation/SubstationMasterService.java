package af.gov.anar.ebreshna.configuration.network.substation;

import af.gov.anar.ebreshna.configuration.network.substation.SubstationMaster;
import af.gov.anar.ebreshna.configuration.network.substation.SubstationMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubstationMasterService {

    @Autowired
    private SubstationMasterRepository repository;

    @Autowired
    private UserService userService;

    public SubstationMaster save(SubstationMaster obj)
    {
        return repository.save(obj);
    }

    public List<SubstationMaster> findall()
    {
        return repository.findAll();
    }

    public SubstationMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        SubstationMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
