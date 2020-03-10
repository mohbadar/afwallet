package af.gov.anar.dck.datasource.service;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import af.gov.anar.dck.datasource.model.Datasource;
import af.gov.anar.dck.datasource.repository.DatasourceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DatasourceServiceImpl implements DatasourceService {

    @Autowired
    private DatasourceRepository datasourceRepository;

    @Override
    public Datasource create(Datasource datasource) {
        return datasourceRepository.save(datasource);
    }

    @Override
    public Datasource delete(Long id) {
        Datasource project = findById(id);
        if(project != null){
        	datasourceRepository.delete(project);
        }
        return project;
    }

    @Override
    public List findAll() {
        return IteratorUtils.toList(datasourceRepository.findAll().iterator());
    }

    @Override
    public List findAllByEnv(String envSlug) {
        return IteratorUtils.toList(datasourceRepository.findByEnvSlug(envSlug).iterator());
    }

    @Override
    public Datasource findById(Long id) {
    	Optional<Datasource> optionalObj =  datasourceRepository.findById(id);
		return optionalObj.get();
    }

    @Override
    public boolean update(Long id, Datasource datasource) {
        if(id != null) {
            datasource.setId(id);
        }
        datasourceRepository.save(datasource);
        return true;
    }
}