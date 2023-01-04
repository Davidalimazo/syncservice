package synchronizerservice.synchronizerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import synchronizerservice.synchronizerservice.utils.Auditable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "m_mobile_sync")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class M_Mobile_Sync extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "tms_agent_id")
    private Long tmsAgentId;
    @Column(name = "pm_agent_id")
    private String pmAgentId;
    @Column(name = "card_num")
    private String cardNum;
    @Column(name = "pm_num")
    private String pmNum;
    @Column(name = "payout_schedule")
    private int payoutSchedule;
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Column(name = "last_modified_date")
    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;
}
