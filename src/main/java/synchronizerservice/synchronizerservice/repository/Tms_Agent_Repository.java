package synchronizerservice.synchronizerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synchronizerservice.synchronizerservice.entity.Tms_Agent;

@Repository
public interface Tms_Agent_Repository extends JpaRepository<Tms_Agent, Long> {
    public Tms_Agent findByAgentCode(String agentCode);
    public Tms_Agent findByPmNumber(String pmNumber);
}
