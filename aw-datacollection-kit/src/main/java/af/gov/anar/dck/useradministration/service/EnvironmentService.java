package af.gov.anar.dck.useradministration.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import af.gov.anar.dck.useradministration.model.Environment;

import java.util.List;

@Service
@Transactional
public interface EnvironmentService {

    public Environment create(Environment environment);
    public Environment delete(Long id);
    public List findAll();
    public Environment findById(Long id);
    public Environment findBySlug(String slug);
    public Environment findBySecretKey(String secretKey);
    public boolean update(Long id, Environment environment);
}