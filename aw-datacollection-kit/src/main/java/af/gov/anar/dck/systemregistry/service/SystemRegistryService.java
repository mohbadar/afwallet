package af.gov.anar.dck.systemregistry.service;



import java.util.List;

import af.gov.anar.dck.systemregistry.model.SystemRegistry;

public interface SystemRegistryService {
	public SystemRegistry create(SystemRegistry systemRegistry);
    public SystemRegistry delete(Long id);
    public List findAll();
    public List findAllByEnv(String envSlug);
    public SystemRegistry findById(Long id);
    public boolean update(Long id, SystemRegistry systemRegistry);
}
