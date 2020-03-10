package af.gov.anar.ebreshna.configuration.office.govt_code;

import af.gov.anar.ebreshna.configuration.office.govt_code.GovCodeMaster;
import af.gov.anar.ebreshna.configuration.office.govt_code.GovCodeMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GovCodeMasterService {

    @Autowired
    private GovCodeMasterRepository repository;

    @Autowired
    private UserService userService;

    public GovCodeMaster save(GovCodeMaster obj)
    {
        return repository.save(obj);
    }

    public List<GovCodeMaster> findAll()
    {
        return repository.findAll();
    }

    public GovCodeMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        GovCodeMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
