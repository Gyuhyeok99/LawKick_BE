package azaza.lawkick.admin.dto;

import azaza.lawkick.domain.Report;
import azaza.lawkick.domain.enums.KickboardType;
import azaza.lawkick.domain.enums.ReportStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminRes {

    private Long reportId; // 신고 아이디
    private String nickName; //신고자 아이디
    private String content; //신고 내용
    private String serialNumber; //일련번호
    private KickboardType kickboardType; //킥보드 종류
    private String imageUrl; //이미지 url
    private Boolean helmet; // true면 헬멧미착용
    private Boolean multiPerson; // true면 다인탑승
    private ReportStatus reportStatus; //신고 상태 - 작성중, 제출, 허위신고, 최종신고
    private String createdAt; // 신고 날짜


    public AdminRes(Report report) {
        this.reportId = report.getId();
        this.nickName = report.getReporter().getNickName();
        this.content = report.getContent();
        this.serialNumber = report.getSerialNumber();
        this.kickboardType = report.getKickboardType();
        this.imageUrl = report.getImageUrl();
        this.helmet = report.getHelmet();
        this.multiPerson = report.getMultiPerson();
        this.reportStatus = report.getReportStatus();
        createAt(report.getModifiedDate());
    }

    private void createAt(LocalDateTime modifiedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.createdAt = modifiedDate.format(formatter);
    }

}
