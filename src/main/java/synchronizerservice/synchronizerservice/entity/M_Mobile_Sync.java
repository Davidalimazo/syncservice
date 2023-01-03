package synchronizerservice.synchronizerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "m_mobile_sync")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class M_Mobile_Sync {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "agent_code")
    private String agentCode;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "pm_number")
    private String pmNumber;
    @Column(name = "agent_id")
    private Long agentId;
}
