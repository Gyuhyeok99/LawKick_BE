package azaza.lawkick.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KickboardType implements EnumModel{
    SINGSING("씽씽")
    ,GCOO("지쿠")
    ,BEAM("빔")
    ,KICKGOING("킥고잉")
    ,DEER("디어")
    ,SWING("스윙")
    ;

    private final String value;
    @Override
    public String getKey() {
        return name();
    }
}
