package synchronizerservice.synchronizerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "m_mobile_subscriber")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class M_mobile_subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name = "appid")
    private Long appId;
}
