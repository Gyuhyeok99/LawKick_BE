package azaza.lawkick.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KickboardType implements EnumModel{
    SINGSING("씽씽")
    ,GCOO("지쿠")
    ,LIME("라임")
    ,KICKGOING("킥고잉")
    ,ALPACA("알파카")
    ;

    private final String value;
    @Override
    public String getKey() {
        return name();
    }
}
