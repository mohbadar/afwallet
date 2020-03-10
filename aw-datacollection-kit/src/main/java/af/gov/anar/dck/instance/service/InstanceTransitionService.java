package af.gov.anar.dck.instance.service;

import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.model.InstanceAttachment;
import af.gov.anar.dck.instance.model.InstanceComment;
import af.gov.anar.dck.instance.model.InstanceHistory;
import af.gov.anar.dck.instance.model.InstanceTransition;
import af.gov.anar.dck.instance.repository.InstanceCommentRepository;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;



import java.util.List;

public interface InstanceTransitionService {

    public InstanceTransition saveOrUpdate(InstanceTransition instanceTransition);
    public List<InstanceTransition> findAll();
    public InstanceTransition findById(Long id);
    public InstanceTransition delete(Long id);
    public boolean deleteByInstance(Long instanceId);
    public List<InstanceTransition> findByUserId(Long userId);
    public List<InstanceTransition> findByInstanceId(Long instanceId);
    public List<InstanceTransition> findByInstanceIdBrief(Long instanceId);

}
