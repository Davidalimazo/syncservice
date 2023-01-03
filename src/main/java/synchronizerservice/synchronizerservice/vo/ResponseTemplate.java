package synchronizerservice.synchronizerservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import synchronizerservice.synchronizerservice.entity.M_Mobile_Sync;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate {
    private M_Mobile_Sync m_mobile_sync;
}
