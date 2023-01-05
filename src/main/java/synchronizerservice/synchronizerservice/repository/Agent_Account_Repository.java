package synchronizerservice.synchronizerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synchronizerservice.synchronizerservice.entity.Agent_Account;

import java.util.List;

@Repository
public interface Agent_Account_Repository extends JpaRepository<Agent_Account, Long> {
    public List<Agent_Account> findAllById(long id);
    public List<Agent_Account> findByAccountNumber(String pmNumber);
}
