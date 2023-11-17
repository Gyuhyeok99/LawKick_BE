package azaza.lawkick.report.dto;

import azaza.lawkick.domain.enums.KickboardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReportReq {

    private String serialNumber;
    private KickboardType kickboardType;
    private String imageUrl;
}
