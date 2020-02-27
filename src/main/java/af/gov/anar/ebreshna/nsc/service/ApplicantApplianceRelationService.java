package af.gov.anar.ebreshna.nsc.service;

import af.gov.anar.ebreshna.configuration.office.office.OfficeMaster;
import af.gov.anar.ebreshna.configuration.office.office.OfficeMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.model.Applicant;
import af.gov.anar.ebreshna.nsc.model.ApplicantApplianceRelation;
import af.gov.anar.ebreshna.nsc.repository.ApplicantApplianceRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplicantApplianceRelationService {

    @Autowired
    private ApplicantApplianceRelationRepository repository;

    @Autowired
    private UserService userService;

    public ApplicantApplianceRelation save(ApplicantApplianceRelation obj)
    {
        return repository.save(obj);
    }

    public List<ApplicantApplianceRelation> findall()
    {
        return repository.findAll();
    }

    public ApplicantApplianceRelation findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ApplicantApplianceRelation obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
