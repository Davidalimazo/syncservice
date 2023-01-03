package synchronizerservice.synchronizerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "m_mobile_agent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class M_mobile_agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGENT_ID")
    private String agentId;
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @Column(name = "CARD_NUM")
    private String cardNum;
    @Column(name = "approve")
    private Boolean approve;

}