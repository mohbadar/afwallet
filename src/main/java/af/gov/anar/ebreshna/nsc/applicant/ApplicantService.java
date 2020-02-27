package af.gov.anar.ebreshna.nsc.applicant;

import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.applicant.Applicant;
import af.gov.anar.ebreshna.nsc.applicant.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository repository;

    @Autowired
    private UserService userService;

    public Applicant save(Applicant obj)
    {
        return repository.save(obj);
    }

    public List<Applicant> findall()
    {
        return repository.findAll();
    }

    public Applicant findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        Applicant obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
