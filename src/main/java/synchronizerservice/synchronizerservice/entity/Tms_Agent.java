package synchronizerservice.synchronizerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "m_mobile_gate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tms_Agent{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "pm_number")
    private String pmNumber;
    @Column(name = "agent_code")
    private String agentCode;
    @Column(name = "approval")
    private Boolean approval;
    @Column(name = "created_by")
    private String createdDate;
    @Column(name = "last_modified_by")
    private String lastModifiedDate;
}
