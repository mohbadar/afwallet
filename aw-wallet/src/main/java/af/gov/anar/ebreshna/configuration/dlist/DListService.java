package af.gov.anar.ebreshna.configuration.dlist;

import af.gov.anar.ebreshna.configuration.dlist.DList;
import af.gov.anar.ebreshna.configuration.dlist.DListRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DListService {

    @Autowired
    private DListRepository repository;

    @Autowired
    private UserService userService;

    public DList save(DList obj)
    {
        return repository.save(obj);
    }

    public List<DList> findall()
    {
        return repository.findAll();
    }

    public DList findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        DList obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
