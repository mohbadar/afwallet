package af.gov.anar.ebreshna.configuration.network.ptr;

import af.gov.anar.ebreshna.configuration.network.ptr.PTRMaster;
import af.gov.anar.ebreshna.configuration.network.ptr.PTRMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PTRMasterService {

    @Autowired
    private PTRMasterRepository repository;

    @Autowired
    private UserService userService;

    public PTRMaster save(PTRMaster obj)
    {
        return repository.save(obj);
    }

    public List<PTRMaster> findall()
    {
        return repository.findAll();
    }

    public PTRMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        PTRMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
