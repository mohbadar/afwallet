package af.gov.anar.dck.datasource.service;

import java.util.List;

import af.gov.anar.dck.datasource.model.Datasource;

public interface DatasourceService {
	public Datasource create(Datasource datasource);
    public Datasource delete(Long id);
    public List findAll();
    public List findAllByEnv(String envSlug);
    public Datasource findById(Long id);
    public boolean update(Long id, Datasource datasource);
}
