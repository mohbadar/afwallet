package af.asr.lib.healthid.repository;

import af.asr.lib.healthid.entity.Rid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Rid Generator repository.
 */
@Repository
public interface RidRepository extends JpaRepository<Rid, Integer> {
	/**
	 * Method to fetch last updated sequence no.
	 * 
	 * @return the entity.
	 */
	@Query(value = "select r.curr_seq_no, r.cr_by, r.cr_dtimes, r.upd_by, r.upd_dtimes FROM healthid_seq r ", nativeQuery = true)
	Rid findLastRid();

	/**
	 * Method to update previous sequence no.
	 * 
	 * @param currentId  the current sequence no.
	 * @param previousId the previous sequence no.
	 * @return the number of rows updated.
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Rid SET currentSequenceNo=?1 WHERE currentSequenceNo=?2")
	int updateRid(int currentId, int previousId);
}
