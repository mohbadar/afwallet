package af.asr.lib.healthid.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * The entity for rid generator.
 */
@Entity
@Table(name = "healthid_seq")
@Data
public class Rid {

	/**
	 * the current sequence no.
	 * 
	 */
	@Id
	@Column(name = "curr_seq_no")
	private int currentSequenceNo;

	@Column(name = "cr_by", nullable = false, length = 256)
	private String createdBy;

	@Column(name = "cr_dtimes", nullable = false)
	private LocalDateTime createdDateTime;

	@Column(name = "upd_by", length = 256)
	private String updatedBy;

	@Column(name = "upd_dtimes")
	private LocalDateTime updatedDateTime;
}