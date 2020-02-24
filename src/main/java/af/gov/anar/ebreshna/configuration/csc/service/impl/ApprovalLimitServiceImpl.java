package af.gov.anar.ebreshna.configuration.csc.service.impl;

import af.gov.anar.ebreshna.configuration.csc.model.ApprovalLimit;
import af.gov.anar.ebreshna.configuration.csc.repository.ApprovalLimitRepository;
import af.gov.anar.ebreshna.configuration.csc.service.ApprovalLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalLimitServiceImpl  implements ApprovalLimitService {

    @Autowired
    private ApprovalLimitRepository repository;


    @Override
    public ApprovalLimit save(ApprovalLimit approvalLimit) {
        return repository.save(approvalLimit);
    }

    @Override
    public ApprovalLimit findOne(long id) {
        return repository.getOne(id);
    }

    @Override
    public List<ApprovalLimit> findall() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
