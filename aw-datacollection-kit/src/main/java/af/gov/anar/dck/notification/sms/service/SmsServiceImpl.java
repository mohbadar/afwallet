package af.gov.anar.dck.notification.sms.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

import af.gov.anar.dck.infrastructure.util.DataTablesUtil;
import af.gov.anar.dck.notification.sms.model.Sms;
import af.gov.anar.dck.notification.sms.repository.SmsRepository;
import af.gov.anar.dck.useradministration.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class SmsServiceImpl implements SmsService {
	Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Autowired
	private SmsRepository smsRepository;
	
	@Autowired
	private UserService userService;

	@Autowired
    private DataTablesUtil dataTablesUtil;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
    public List<Sms> create_batch(List<Sms> smsList) {
        return smsRepository.saveAll(smsList);
	}
	
	public Sms create(Sms sms) {
		return smsRepository.save(sms);
	}

	public List<Sms> delete(Long id) {
		Sms sms = smsRepository.findById(id).get();
        if (sms != null) {
            smsRepository.deleteById(id);
            smsRepository.flush();

        }
        return smsRepository.findAll();
		
	}

	public List<Sms> findAllByEnv(String envSlug) {
		return smsRepository.findByEnvSlug(envSlug);
	}

	public Object findAllByEnv(DataTablesInput input) {
		
		return dataTablesUtil.get_data_list("sms.sms", null, input);


		// Object[] retObj = new Object[2];
		// String envSlug = userService.getCurrentEnv();
		// int start = input.getStart();
		// int limit = input.getLength();
		// if (limit < 0) {
		// 	limit = 99999;
		// }
		// String regex = input.getSearch().getValue();

		// Query query;
		// List<Object[]> smsObjects;
		// Long recordTotal = null;
		// // Find all forms according to the user's groups
		// List smsList = new ArrayList();

		// if (regex != null && !"".equals(regex)) {
		// 	query = this.entityManager.createNativeQuery(
		// 			"SELECT f.id, f.name, f.description, f.xml_form_id, f.form_type, f.form_category, f.active, COUNT(i) AS count from form f left join instance i on f.id=i.form_id WHERE f.env_slug='"
		// 					+ envSlug + "' AND (f.name ILIKE '%" + regex + "%' OR f.form_type ILIKE '" + regex
		// 					+ "%' ) group by f.id limit " + limit
		// 					+ " offset " + start);
		// 	smsObjects = query.getResultList();
		// 	// recordTotal = getTotalRecords(envSlug, null);

		// } else {
		// 	query = this.entityManager.createNativeQuery(
		// 			"SELECT f.id, f.name, f.description, f.xml_form_id, f.form_type, f.form_category, f.active, COUNT(i) AS count from form f left join instance i on f.id=i.form_id WHERE f.env_slug='"
		// 					+ envSlug + "' group by f.id limit " + limit + " offset " + start);
		// 	smsObjects = query.getResultList();
		// 	// recordTotal = getTotalRecords(envSlug, null);
		// }
		// retObj[0] = smsObjects;
		// retObj[1] = recordTotal;
		// return retObj;
	}

	public Sms findById(Long id) {
		return this.smsRepository.getOne(id);

	}

}
