package synchronizerservice.synchronizerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import synchronizerservice.synchronizerservice.entity.Agent_Account;
import synchronizerservice.synchronizerservice.entity.Tms_Agent;

import java.util.List;

@Repository
public interface Tms_Agent_Repository extends JpaRepository<Tms_Agent, Long> {
    @Query(value = "SELECT DISTINCT id FROM Agent", nativeQuery = true)
    public List<Tms_Agent> findTmsAgentIds();

    public List<Tms_Agent> findByPmNumber(String pmNumber);

    List<Tms_Agent> findAllById(Long i);
    @Query(value = "SELECT * FROM Agent WHERE agent_type IS NULL AND email IS NULL", nativeQuery = true)
    public List<Tms_Agent> findNullValues();
}
