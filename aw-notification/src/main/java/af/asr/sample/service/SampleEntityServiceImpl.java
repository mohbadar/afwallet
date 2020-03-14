package af.asr.sample.service;

import af.asr.sample.model.SampleEntity;
import af.asr.sample.repository.SampleEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SampleEntityServiceImpl implements SampleEntityService {

    @Autowired
    private SampleEntityRepository repository;

    @Override
    public SampleEntity save(SampleEntity sampleEntity) {
        return repository.save(sampleEntity);
    }

    @Override
    public SampleEntity findById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<SampleEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
