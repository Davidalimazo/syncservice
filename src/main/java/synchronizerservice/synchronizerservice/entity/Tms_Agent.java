package synchronizerservice.synchronizerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import synchronizerservice.synchronizerservice.utils.Auditable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tms_Agent extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "contact_phone_no")
    private String pmNumber;
    @Column(name = "agent_code")
    private String agentCode;
    @Column(name = "authorized")
    private Boolean approval;
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Column(name = "last_modified_date")
    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;
}
