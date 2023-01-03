package synchronizerservice.synchronizerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synchronizerservice.synchronizerservice.entity.M_mobile_agent;

@Repository
public interface M_mobile_agent_repository extends JpaRepository<M_mobile_agent, Long> {
    public M_mobile_agent findByPhoneNo(String phoneNo);
    public M_mobile_agent findByCardNum(String cardNum);
}
