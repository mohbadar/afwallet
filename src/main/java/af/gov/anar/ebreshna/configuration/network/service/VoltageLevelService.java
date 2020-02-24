package af.gov.anar.ebreshna.configuration.network.service;

import af.gov.anar.ebreshna.configuration.network.model.VoltageLevel;
import af.gov.anar.ebreshna.configuration.network.repository.VoltageLevelRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VoltageLevelService {

    @Autowired
    private VoltageLevelRepository repository;

    @Autowired
    private UserService userService;

    public VoltageLevel save(VoltageLevel obj)
    {
        return repository.save(obj);
    }

    public List<VoltageLevel> findall()
    {
        return repository.findAll();
    }

    public VoltageLevel findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        VoltageLevel obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
