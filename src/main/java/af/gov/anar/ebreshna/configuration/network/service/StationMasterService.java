package af.gov.anar.ebreshna.configuration.network.service;

import af.gov.anar.ebreshna.configuration.network.model.StationMaster;
import af.gov.anar.ebreshna.configuration.network.repository.StationMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StationMasterService {

    @Autowired
    private StationMasterRepository repository;

    @Autowired
    private UserService userService;

    public StationMaster save(StationMaster obj)
    {
        return repository.save(obj);
    }

    public List<StationMaster> findall()
    {
        return repository.findAll();
    }

    public StationMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        StationMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}