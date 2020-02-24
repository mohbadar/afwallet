package af.gov.anar.ebreshna.configuration.payment.service;

import af.gov.anar.ebreshna.configuration.payment.model.CounterMaster;
import af.gov.anar.ebreshna.configuration.payment.model.InstallmentApprovalLimit;
import af.gov.anar.ebreshna.configuration.payment.repository.CounterRepository;
import af.gov.anar.ebreshna.configuration.payment.repository.InstallmentApprovalLimitRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InstallmentApprovalLimitService {

    @Autowired
    private InstallmentApprovalLimitRepository repository;

    @Autowired
    private UserService userService;

    public InstallmentApprovalLimit save(InstallmentApprovalLimit obj)
    {
        return repository.save(obj);
    }

    public List<InstallmentApprovalLimit> findall()
    {
        return repository.findAll();
    }

    public InstallmentApprovalLimit findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        InstallmentApprovalLimit obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
