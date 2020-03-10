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

import af.gov.anar.dck.instance.repository.InstanceHistoryRepository;
import af.gov.anar.dck.instance.repository.InstanceWatcherRepository;
import af.gov.anar.dck.infrastructure.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstanceWatcherServiceImpl implements InstanceWatcherService {
	@Autowired
	InstanceHistoryRepository InstanceHistoryRepository;
	@Autowired
	private EmailUtil emailUtil;
	// @Autowired
    // private JavaMailSender javaMailSender;
	
	@Autowired
    private InstanceWatcherRepository instanceWatcherRepository;
    
    @Autowired
    private UserService userService;
	
	@Autowired
	private InstanceServiceImpl instanceService;
	@Autowired
	private Environment env;
	
	@Override
	public InstanceWatcher create(Instance instance, Long watcherId) {
		User watcher = userService.findById(watcherId);
		InstanceWatcher newWatcher = create(instance, watcher);
        return newWatcher;
	}
    
	@Override
	public InstanceWatcher create(Instance instance, User watcher) {
		InstanceWatcher instanceWatcher = new InstanceWatcher();
		instanceWatcher.setUsername(watcher.getUsername());
		instanceWatcher.setWatcherId(watcher.getId());
		instanceWatcher.setInstance(instance);
    	
		InstanceWatcher newWatcher = create(instanceWatcher);
        return newWatcher;
	}

	@Override
	public InstanceWatcher create(InstanceWatcher instanceWatcher) {
		return instanceWatcherRepository.save(instanceWatcher);
	}

	@Override
	public void delete(Long id) {
		 instanceWatcherRepository.deleteById(id);;
	}

	@Override
	public InstanceWatcher findById(Long id) {
		return instanceWatcherRepository.findById(id).get();
	}

	@Override
	public List<InstanceWatcher> findAllByInstance(Long instanceId) {
		
		return instanceWatcherRepository.findByInstance(instanceService.findById(instanceId));
	}

	@Override
	public List findAllByWatcher(Long watcherId) {
		return instanceWatcherRepository.findByWatcherId(watcherId);
	}
	public List<User> getWatchersEmailByInstanceId(Long instanceId){
		return instanceWatcherRepository.getWatchersEmailByInstanceId(instanceId);
	}

	@Override
	public void sendEmail(Long instanceId,List<InstanceHistory> histories) {

		System.out.print("HERE IS THE RESPONSE ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		List<User> watchersList = getWatchersEmailByInstanceId(instanceId);

		String subject = emailSubject(histories);
		String body =emailBody(histories);
		 for(User user : watchersList) {
			emailUtil.sendMessage(user.getEmail().toString(),subject,body);	
		    System.out.print("Email Sent Done! ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");	
		} 
	}

	public String emailSubject(List<InstanceHistory> history){
		return "Instance Updated";
	}

	public String emailBody(List<InstanceHistory> history){
		System.out.println("HERE IS THE HISTORY VALUE:"+history.toString());
		String startBody = "Dear Sir/Madam,\n Here is the instance update report:\n"+"<table border='1' style='border-collapse: collapse;'><tr>"+
		"<th>Instance ID</th><th>User ID</th><th>Updated Filed</th><th>Old Value</th><th>New Value</th></tr>";
		String rows = "";
		String appURL = env.getProperty("app.url");
		for (InstanceHistory instanceHistory : history) {
			rows += "<tr><td><a href='"+appURL+"more/instances/ "+instanceHistory.getInstanceId().toString()+"'>"+instanceHistory.getInstanceId().toString()+"</a></td>"+
					"<td>"+instanceHistory.getUserId().toString()+"</td>"+
					"<td>"+instanceHistory.getField().toString()+"</td>"+
					"<td>"+instanceHistory.getOldValue().toString()+"</td>"+
					"<td>"+instanceHistory.getNewValue().toString()+"</td></tr>";
		}
		String endBody = "</table>\n Best Regards, Big Data Directorate";
		return startBody+rows+endBody;		
	}
}
