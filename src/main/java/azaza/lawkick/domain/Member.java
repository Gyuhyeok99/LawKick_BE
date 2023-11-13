package azaza.lawkick.domain;

import azaza.lawkick.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String password;
    private String nickName;
    private Long mileage;

    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL)
    private List<Report> reports = new ArrayList<>();
}
