package af.gov.anar.ebreshna.configuration.payment.branch;

import af.gov.anar.ebreshna.configuration.payment.branch.BankBranch;
import af.gov.anar.ebreshna.configuration.payment.branch.BankBranchRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BankBranchService {

    @Autowired
    private BankBranchRepository repository;

    @Autowired
    private UserService userService;

    public BankBranch save(BankBranch obj)
    {
        return repository.save(obj);
    }

    public List<BankBranch> findall()
    {
        return repository.findAll();
    }

    public BankBranch findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        BankBranch obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
