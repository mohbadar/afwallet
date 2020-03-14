package af.gov.anar.dck.useradministration.service;

import af.gov.anar.dck.useradministration.model.Group;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.repository.GroupRepository;
import af.gov.anar.dck.useradministration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Group create(Group group) {
		
		return groupRepository.saveAndFlush(group);
	}

	@Override
	public List<Group> delete(Long id) {
		
		Optional<Group> group=groupRepository.findById(id);
		if(group.isPresent())
		{
			groupRepository.delete(group.get());
			groupRepository.flush();
		}
	
		return groupRepository.findAll();
		
	}

	@Override
	public List<Group> findAll() {
		return groupRepository.findAll();
	}

	@Override
	public List<Group> findAllByEnv(String envSlug) {
		return groupRepository.findByEnvSlug(envSlug);
	}

	@Override
	public List<Group> findAllByUserAndEnv(User user, String envSlug) {
		return userRepository.findAllGroupsByUserAndEnv(user.getId(), envSlug);
	}

	@Override
	public Group findById(Long id) {
		
		return groupRepository.findById(id).get();
	}

	@Override
	public boolean update(Long id, Group group) {
		if(id != null) {
			group.setId(id);
        }
		groupRepository.save(group);
        return true;
	}

	
}
