package af.gov.anar.ebreshna.common.office.service;

import af.gov.anar.ebreshna.common.office.model.PremisesNature;
import af.gov.anar.ebreshna.common.office.repository.PremisesNatureRepository;
import af.gov.anar.ebreshna.helpdesk.model.Comment;
import af.gov.anar.ebreshna.helpdesk.repository.CommentRepository;
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

    public List<PremisesNature> findall()
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
