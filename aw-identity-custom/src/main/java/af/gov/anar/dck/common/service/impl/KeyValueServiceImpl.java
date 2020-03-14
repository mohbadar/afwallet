package af.gov.anar.dck.common.service.impl;

import af.gov.anar.dck.common.model.KeyValue;
import af.gov.anar.dck.common.repository.KeyValueRepository;
import af.gov.anar.dck.common.service.KeyValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeyValueServiceImpl implements KeyValueService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KeyValueRepository keyValueRepository;

    @Override
    public KeyValue create(KeyValue keyValue) {
        return keyValueRepository.save(keyValue);
    }

    @Override
    public KeyValue create(String key, String value) {
        KeyValue keyValue = new KeyValue(key, value);
        return keyValueRepository.save(keyValue);
    }

    @Override
	public boolean delete(Long id) {
		KeyValue keyValue = findById(id);
		if (keyValue != null) {
			keyValueRepository.delete(keyValue);
			return true;
		}
		return false;
    }
    
    @Override
	public List<KeyValue> findAll() {
		return keyValueRepository.findAll();
	}

    @Override
    public KeyValue findById(Long id) {
        Optional<KeyValue> optionalObj = keyValueRepository.findById(id);
		return optionalObj.get();
    }

    @Override
    public List<KeyValue> findByKey(String key) {
        return keyValueRepository.findByKey(key);
    }

    @Override
    public List<KeyValue> findByValue(String value) {
        return keyValueRepository.findByValue(value);
    }

    @Override
    public boolean update(Long id, KeyValue keyValue) {
        if (id != null) {
			keyValue.setId(id);
			keyValueRepository.save(keyValue);
			return true;
		}
		return false;
    }
}