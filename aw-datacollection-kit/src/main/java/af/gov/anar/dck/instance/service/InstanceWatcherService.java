package af.gov.anar.dck.instance.service;

import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.model.InstanceAttachment;
import af.gov.anar.dck.instance.model.InstanceComment;
import af.gov.anar.dck.instance.model.InstanceHistory;
import af.gov.anar.dck.instance.model.InstanceTransition;
import af.gov.anar.dck.instance.model.InstanceWatcher;
import af.gov.anar.dck.instance.repository.InstanceCommentRepository;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;

import java.util.List;

public interface InstanceWatcherService {
	public InstanceWatcher create(Instance instance, Long watcherId);
	public InstanceWatcher create(Instance instance, User watcher);
    public InstanceWatcher create(InstanceWatcher instanceWatcher);
    public void delete(Long id);
    public InstanceWatcher findById(Long id);
    public List<InstanceWatcher> findAllByInstance(Long instanceId);
    public List<InstanceWatcher> findAllByWatcher(Long watcherId);
    public void sendEmail(Long instanceId, List<InstanceHistory> histories);
}