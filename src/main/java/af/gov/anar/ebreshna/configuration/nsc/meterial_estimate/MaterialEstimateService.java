package af.gov.anar.ebreshna.configuration.nsc.meterial_estimate;

import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialEstimateService {

    @Autowired
    private MaterialEstimateRepository repository;

    @Autowired
    private UserService userService;

    public MaterialEstimateMaster save(MaterialEstimateMaster obj)
    {
        return repository.save(obj);
    }

    public List<MaterialEstimateMaster> findall()
    {
        return repository.findAll();
    }

    public MaterialEstimateMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        MaterialEstimateMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
