package azaza.lawkick.domain;

import azaza.lawkick.domain.enums.KickboardType;
import azaza.lawkick.domain.enums.ReportStatus;
import azaza.lawkick.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private boolean helmet; // true면 헬멧미착용
    private boolean multiPerson; // true면 다인탑승
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus //신고 상태 - 작성중, 제출, 허위신고, 최종신고


    @ManyToOne(fetch = LAZY)
    private Member reporter; //신고자


}
