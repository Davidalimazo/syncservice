package synchronizerservice.synchronizerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import synchronizerservice.synchronizerservice.entity.M_Mobile_Sync;

import java.util.List;
import java.util.Optional;

@Repository
public interface SyncRepo extends JpaRepository<M_Mobile_Sync, Long> {
    public Optional<M_Mobile_Sync> findById(Long id);
    public M_Mobile_Sync findByPmNum(String pmNum);
    public M_Mobile_Sync findByPmAgentId(Long agentId);
    public M_Mobile_Sync findByTmsAgentId(Long agentId);
    public M_Mobile_Sync findByCardNum(String cardumber);
    @Query(value = "SELECT * FROM m_mobile_sync WHERE card_num IS NULL AND pm_agent_id IS NULL", nativeQuery = true)
    public List<M_Mobile_Sync> findAgentWithoutPmNumAndCardNum();
}
