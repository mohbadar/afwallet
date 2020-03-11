package af.gov.anar.ebreshna.configuration.csc.approval_limit;

import af.gov.anar.ebreshna.configuration.csc.approval_limit.ApprovalLimit;
import af.gov.anar.ebreshna.configuration.csc.approval_limit.ApprovalLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalLimitService {

    @Autowired
    private ApprovalLimitRepository repository;



    public ApprovalLimit save(ApprovalLimit approvalLimit) {
        return repository.save(approvalLimit);
    }


    public ApprovalLimit findOne(long id) {
        return repository.getOne(id);
    }


    public List<ApprovalLimit> findall() {
        return repository.findAll();
    }


    public void delete(long id) {
        repository.deleteById(id);
    }
}
