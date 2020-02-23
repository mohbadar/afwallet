package af.gov.anar.ebreshna.common.base.workingcalender.service;

import af.gov.anar.ebreshna.common.base.workingcalender.model.WorkingCalenderTemplate;
import af.gov.anar.ebreshna.common.base.workingcalender.repository.WorkingCalenderTemplateRepository;
import af.gov.anar.ebreshna.common.office.model.PremisesSubCategory;
import af.gov.anar.ebreshna.common.office.repository.PremisesSubCategoryRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkingCalenderTemplateService {

    @Autowired
    private WorkingCalenderTemplateRepository repository;

    @Autowired
    private UserService userService;

    public WorkingCalenderTemplate save(WorkingCalenderTemplate obj)
    {
        return repository.save(obj);
    }

    public List<WorkingCalenderTemplate> findall()
    {
        return repository.findAll();
    }

    public WorkingCalenderTemplate findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        WorkingCalenderTemplate obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
