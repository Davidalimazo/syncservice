package synchronizerservice.synchronizerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import synchronizerservice.synchronizerservice.entity.M_Mobile_Sync;

import java.util.List;
import java.util.Optional;

@Repository
public interface SyncRepo extends JpaRepository<M_Mobile_Sync, Long> {
    @Query(value = "SELECT * FROM m_mobile_sync WHERE tms_agent_id =?1 AND card_num IS NOT NULL AND pm_agent_id IS NOT NULL", nativeQuery = true)
    public List<M_Mobile_Sync> findTmsAgentsWhoseIdAndCardNumAreNotNull(Long tmsAgentId);
    @Query(value = "SELECT * FROM m_mobile_sync WHERE card_num IS NULL AND pm_agent_id IS NULL", nativeQuery = true)
    public List<M_Mobile_Sync> findAgentWithoutPmNumAndCardNum();
}
