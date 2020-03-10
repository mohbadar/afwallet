package af.gov.anar.dck.instance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import af.gov.anar.dck.instance.model.InstanceAttachment;

public interface InstanceAttachmentRepository extends JpaRepository<InstanceAttachment, Long> {

}
