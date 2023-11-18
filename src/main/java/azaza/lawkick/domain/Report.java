package azaza.lawkick.domain;

import azaza.lawkick.domain.enums.KickboardType;
import azaza.lawkick.domain.enums.ReportStatus;
import azaza.lawkick.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; //신고 내용
    private String serialNumber; //일련번호
    @Enumerated(EnumType.STRING)
    private KickboardType kickboardType; //킥보드 종류
    private String imageUrl; //이미지 url
    private Double latitude; //위도
    private Double longitude; //경도
    @Nullable
    private Boolean helmet; // true면 헬멧미착용
    @Nullable
    private Boolean multiPerson; // true면 다인탑승
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus; //신고 상태 - 작성중, 제출, 허위신고, 최종신고

    @ManyToOne(fetch = LAZY)
    private Member reporter; //신고자

    public Report(String serialNumber, String imageUrl, Member reporter) {
        this.serialNumber = serialNumber;
        this.imageUrl = imageUrl;
        this.reporter = reporter;
        this.reportStatus = getDefaultStatus();
        this.helmet = Boolean.FALSE;
        this.multiPerson = Boolean.FALSE;
    }



    public Report updateReport(KickboardType kickboardType, String serialNumber, Double latitude,
                               Double longitude, Boolean helmet, Boolean multiPerson, String content) {
        this.kickboardType = kickboardType;
        this.serialNumber = serialNumber;
        this.latitude = latitude;
        this. longitude = longitude;
        this.helmet = helmet;
        this.multiPerson = multiPerson;
        this.reportStatus = getSubmitStatus();
        this.content = content;
        return this;
    }

    private ReportStatus getDefaultStatus() {
        return ReportStatus.WRITING;
    }

    private ReportStatus getSubmitStatus() {
        return ReportStatus.SUBMIT;
    }

    public void updateReportTrue() {
        this.reportStatus = ReportStatus.TRUE;
    }
    public void updateReportFalse() {
        this.reportStatus = ReportStatus.FALSE;
    }

    public void updateMarker(KickboardType kickboardType, String serialNumber) {
        this.kickboardType = kickboardType;
        this.serialNumber = serialNumber;
    }
}
