package synchronizerservice.synchronizerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "m_mobile_subscriber_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class M_mobile_subscriber_card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "subscriber_id")
    private Long subscriberId;
    @Column(name = "card_number")
    private String cardNumber;
}
