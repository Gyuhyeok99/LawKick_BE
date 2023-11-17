package azaza.lawkick.domain;

import azaza.lawkick.domain.enums.ReportStatus;
import azaza.lawkick.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String nickName;
    private Long mileage;


    public Long updateMileage() {
        this.mileage += 10;
        return this.mileage;
    }
}
