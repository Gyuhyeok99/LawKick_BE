package azaza.lawkick.domain;

import azaza.lawkick.domain.enums.KickboardType;
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
    private String number; //번호판 번호
    @Enumerated(EnumType.STRING)
    private KickboardType kickboardType; //킥보드 종류
    private Double latitude; //위도
    private Double longitude; //경도

    @ManyToOne(fetch = LAZY)
    private Member reporter; //신고자
}
