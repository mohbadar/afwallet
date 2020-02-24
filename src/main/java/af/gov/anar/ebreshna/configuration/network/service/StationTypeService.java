package af.gov.anar.ebreshna.configuration.network.service;

import af.gov.anar.ebreshna.configuration.network.model.StationType;
import af.gov.anar.ebreshna.configuration.network.repository.StationTypeRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StationTypeService {

    @Autowired
    private StationTypeRepository repository;

    @Autowired
    private UserService userService;

    public StationType save(StationType obj)
    {
        return repository.save(obj);
    }

    public List<StationType> findall()
    {
        return repository.findAll();
    }

    public StationType findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        StationType obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
