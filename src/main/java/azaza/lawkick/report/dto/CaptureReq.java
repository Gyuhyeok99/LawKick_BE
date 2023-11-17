package azaza.lawkick.report.dto;

import azaza.lawkick.domain.enums.KickboardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CaptureReq {

    private KickboardType kickboardType;
    private String serialNumber;

}
