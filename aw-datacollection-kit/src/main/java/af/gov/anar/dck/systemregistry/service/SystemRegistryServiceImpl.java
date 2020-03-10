package af.gov.anar.dck.systemregistry.service;


import af.gov.anar.dck.systemregistry.model.SystemRegistry;
import af.gov.anar.dck.systemregistry.repository.SystemRegistryRepository;
import af.gov.anar.dck.useradministration.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemRegistryServiceImpl implements SystemRegistryService {

    @Autowired
    private SystemRegistryRepository systemRegistryRepository;

    @Autowired
    private UserService userService;

    @Override
    public SystemRegistry create(SystemRegistry systemRegistry) {
        return systemRegistryRepository.save(systemRegistry);
    }

    @Override
    public SystemRegistry delete(Long id) {
    	SystemRegistry systemRegistry = findById(id);
        if(systemRegistry != null){
        	systemRegistryRepository.delete(systemRegistry);
        }
        return systemRegistry;
    }

    @Override
    public List findAll() {
        return systemRegistryRepository.findAll();
    }

    @Override
    public List findAllByEnv(String envSlug) {
        return systemRegistryRepository.findByEnvSlug(envSlug);
    }

    @Override
    public SystemRegistry findById(Long id) {
    	Optional<SystemRegistry> optionalObj =  systemRegistryRepository.findById(id);
		return optionalObj.get();
    }

    @Override
    public boolean update(Long id, SystemRegistry systemRegistry) {
        if(id != null) {
            systemRegistry.setId(id);
            systemRegistry.setCreatedAt(findById(id).getCreatedAt());
        }
        systemRegistry.setEnvSlug(userService.getCurrentEnv());
        systemRegistryRepository.save(systemRegistry);
        return true;
    }
}