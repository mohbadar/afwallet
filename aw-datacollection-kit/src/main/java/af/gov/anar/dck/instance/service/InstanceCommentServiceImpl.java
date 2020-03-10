package af.gov.anar.dck.instance.service;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.model.InstanceAttachment;
import af.gov.anar.dck.instance.model.InstanceComment;
import af.gov.anar.dck.instance.repository.InstanceCommentRepository;
import af.gov.anar.dck.useradministration.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstanceCommentServiceImpl implements InstanceCommentService {

    @Autowired
    private InstanceCommentRepository instanceCommentRepository;
    
    @Autowired
    private UserService userService;
    
    @Override
    public InstanceComment create(Instance instance, String comment) {
    	InstanceComment instanceComment = new InstanceComment();
    	instanceComment.setComment(comment);
    	instanceComment.setUser(userService.getLoggedInUser());
    	instanceComment.setInstance(instance);
    	
    	InstanceComment newComment = create(instanceComment);
        return newComment;
    }
    
    @Override
    public InstanceComment create(InstanceComment instanceComment) {
        return instanceCommentRepository.save(instanceComment);
    }

	@Override
	public InstanceComment delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public boolean deleteByInstance(Long instanceId) {
        instanceCommentRepository.deleteByInstanceId(instanceId);
        return true;
    }

	@Override
	public InstanceComment findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAllByForm(Long instanceId) {
		// TODO Auto-generated method stub
		return null;
	}

    
} 