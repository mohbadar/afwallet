package af.gov.anar.ebreshna.csc.ivr_call;

import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IvrCallService {


    @Autowired
    private IvrCallRepository repository;

    @Autowired
    private UserService userService;

    public IvrCall save(IvrCall obj)
    {
        return repository.save(obj);
    }

    public List<IvrCall> findall()
    {
        return repository.findAll();
    }

    public IvrCall findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        IvrCall obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }


}
