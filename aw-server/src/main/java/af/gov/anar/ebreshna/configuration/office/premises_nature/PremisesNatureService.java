package af.gov.anar.ebreshna.configuration.office.premises_nature;

import af.gov.anar.ebreshna.configuration.office.premises_nature.PremisesNature;
import af.gov.anar.ebreshna.configuration.office.premises_nature.PremisesNatureRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PremisesNatureService {

    @Autowired
    private PremisesNatureRepository repository;

    @Autowired
    private UserService userService;

    public PremisesNature save(PremisesNature obj)
    {
        return repository.save(obj);
    }

    public List<PremisesNature> findAll()
    {
        return repository.findAll();
    }

    public PremisesNature findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        PremisesNature obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}