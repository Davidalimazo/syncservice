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
    private Long id;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "CONTACT")
    private String contact;
    @Column(name = "AGENT_NAME")
    private String AgentName;
    @Column(name = "AGENT_ID")
    private String agentId;
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @Column(name = "CARD_NUM")
    private String cardNum;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "AGGREGATOR_ID")
    private String aggregatorId;

}