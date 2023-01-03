package synchronizerservice.synchronizerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synchronizerservice.synchronizerservice.entity.M_mobile_subscriber_card;

import java.util.Optional;

@Repository
public interface M_mobile_subscriber_card_repository extends JpaRepository<M_mobile_subscriber_card, Long> {
    public M_mobile_subscriber_card findBySubscriberId(Long subscriberId);
}
