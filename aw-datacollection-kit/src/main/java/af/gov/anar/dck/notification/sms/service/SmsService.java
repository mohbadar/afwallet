package af.gov.anar.dck.notification.sms.service;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

import af.gov.anar.dck.notification.sms.model.Sms;

import java.util.List;


@Service
public interface SmsService {
    public List<Sms> create_batch(List<Sms> smsList);
    public Sms  create(Sms role);
    public List<Sms> delete(Long id);
    public List<Sms> findAllByEnv(String envSlug);
    public Object findAllByEnv(DataTablesInput input);
    public Sms findById(Long id);
   

}
