package af.gov.anar.dck.useradministration.service;

import org.springframework.stereotype.Service;

import af.gov.anar.dck.useradministration.model.Role;

import java.util.List;

@Service
public interface RoleService {

    public Role create(Role role);
    public List<Role> delete(Long id);
    public List<Role> findAll();
    public List<Role> findAllByEnv(String envSlug);
    public Role findById(Long id);
    public boolean update(Long id, Role role);
}
