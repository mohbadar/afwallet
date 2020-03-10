package af.gov.anar.dck.notification.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import af.gov.anar.dck.notification.sms.model.Sms;

import java.util.List;

public interface SmsRepository extends JpaRepository<Sms, Long> {
	public List<Sms> findByEnvSlug(String envSlug);
}
