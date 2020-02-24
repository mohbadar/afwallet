package af.gov.anar.ebreshna.configuration.office.service;

import af.gov.anar.ebreshna.configuration.office.model.OfficeMaster;
import af.gov.anar.ebreshna.configuration.office.repository.OfficeMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OfficeMasterService {

    @Autowired
    private OfficeMasterRepository repository;

    @Autowired
    private UserService userService;

    public OfficeMaster save(OfficeMaster obj)
    {
        return repository.save(obj);
    }

    public List<OfficeMaster> findall()
    {
        return repository.findAll();
    }

    public OfficeMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        OfficeMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
