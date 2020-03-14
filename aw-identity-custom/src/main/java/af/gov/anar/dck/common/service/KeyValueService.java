package af.gov.anar.dck.common.service;

import af.gov.anar.dck.common.model.KeyValue;

import java.util.List;

public interface KeyValueService {
	public KeyValue create(KeyValue keyValue);
    public KeyValue create(String key, String value);
    public boolean delete(Long id);
    public List<KeyValue> findAll();
    public KeyValue findById(Long id);
    public List<KeyValue> findByKey(String key);
    public List<KeyValue> findByValue(String value);
    public boolean update(Long id, KeyValue keyValue);
}
