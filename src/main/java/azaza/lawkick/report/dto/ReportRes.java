package azaza.lawkick.report.dto;

import azaza.lawkick.domain.Report;
import azaza.lawkick.domain.enums.KickboardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReportRes {

    private Long reportId;
    private String serialNumber;
    private KickboardType kickboardType;
    private String imageUrl;

    public ReportRes(Report report) {
        this.serialNumber = report.getSerialNumber();
        this.kickboardType = report.getKickboardType();
        this.imageUrl = report.getImageUrl();
    }
}
