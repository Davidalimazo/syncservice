package synchronizerservice.synchronizerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synchronizerservice.synchronizerservice.entity.M_mobile_subscriber;

import java.util.List;

@Repository
public interface M_mobile_subscriber_repository extends JpaRepository<M_mobile_subscriber, Long> {
    public List<M_mobile_subscriber> findByMobileNo(String mobileNo);
    public M_mobile_subscriber findByAppId(String appid);
    public M_mobile_subscriber findByMobileNoAndAppId(String mobileNo, Long appId);
}
