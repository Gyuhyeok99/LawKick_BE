package azaza.lawkick.domain.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportStatus implements EnumModel {
    WRITING("작성중"),
    SUBMIT("제출"),
    FALSE("허위신고"),
    TRUE("최종신고")
    ;
    private final String value;
    @Override
    public String getKey() {
        return name();
    }

}
