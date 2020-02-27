package af.gov.anar.ebreshna.nsc.lpu_applicant;

import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.lpu_applicant.LpuApplicantInfoDetail;
import af.gov.anar.ebreshna.nsc.lpu_applicant.LpuApplicantInfoDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LpuApplicantInfoDetailService {

    @Autowired
    private LpuApplicantInfoDetailRepository repository;

    @Autowired
    private UserService userService;

    public LpuApplicantInfoDetail save(LpuApplicantInfoDetail obj)
    {
        return repository.save(obj);
    }

    public List<LpuApplicantInfoDetail> findall()
    {
        return repository.findAll();
    }

    public LpuApplicantInfoDetail findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        LpuApplicantInfoDetail obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
