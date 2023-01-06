package synchronizerservice.synchronizerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "agent_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent_Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "authorized")
    private Boolean authorized;
    @Column(name = "agent_id")
    private Long agentId;
}
