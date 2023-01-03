package synchronizerservice.synchronizerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentModel {
    private String cardNumber;
    private String pmNumber;
    private String agentId;
}
