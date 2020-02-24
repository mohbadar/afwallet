package af.gov.anar.ebreshna.configuration.csc.service;

import af.gov.anar.ebreshna.configuration.csc.model.ApprovalLimit;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public interface ApprovalLimitService {

    public ApprovalLimit save(ApprovalLimit approvalLimit);
    public ApprovalLimit findOne(long id);
    public List<ApprovalLimit> findall();
    public void delete(long id);

}
