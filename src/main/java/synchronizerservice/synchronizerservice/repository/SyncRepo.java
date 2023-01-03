package synchronizerservice.synchronizerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synchronizerservice.synchronizerservice.entity.M_Mobile_Sync;

import java.util.Optional;

@Repository
public interface SyncRepo extends JpaRepository<M_Mobile_Sync, Long> {
    public Optional<M_Mobile_Sync> findById(Long id);
    public M_Mobile_Sync findByPmNumber(String pmNumber);
    public M_Mobile_Sync findByAgentId(int agentId);
    public M_Mobile_Sync findByCardNumber(String cardumber);
}
