package af.gov.anar.ebreshna.configuration.payment.service;

import af.gov.anar.ebreshna.configuration.payment.model.InstallmentPenalty;
import af.gov.anar.ebreshna.configuration.payment.repository.InstallmentPenalyRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InstallmentPenaltyService {

    @Autowired
    private InstallmentPenalyRepository repository;

    @Autowired
    private UserService userService;

    public InstallmentPenalty save(InstallmentPenalty obj)
    {
        return repository.save(obj);
    }

    public List<InstallmentPenalty> findall()
    {
        return repository.findAll();
    }

    public InstallmentPenalty findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        InstallmentPenalty obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
