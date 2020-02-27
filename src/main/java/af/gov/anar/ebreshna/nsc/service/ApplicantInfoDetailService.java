package af.gov.anar.ebreshna.nsc.service;

import af.gov.anar.ebreshna.configuration.office.office.OfficeMaster;
import af.gov.anar.ebreshna.configuration.office.office.OfficeMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.model.Applicant;
import af.gov.anar.ebreshna.nsc.model.ApplicantInfoDetail;
import af.gov.anar.ebreshna.nsc.repository.ApplicantInfoDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplicantInfoDetailService {

    @Autowired
    private ApplicantInfoDetailRepository repository;

    @Autowired
    private UserService userService;

    public ApplicantInfoDetail save(ApplicantInfoDetail obj)
    {
        return repository.save(obj);
    }

    public List<ApplicantInfoDetail> findall()
    {
        return repository.findAll();
    }

    public ApplicantInfoDetail findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ApplicantInfoDetail obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
