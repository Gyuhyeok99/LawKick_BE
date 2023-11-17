package azaza.lawkick.report.dto;

import azaza.lawkick.domain.enums.KickboardType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubmitReq {

    private String serialNumber;
    private KickboardType kickboardType;
    private Double latitude;
    private Double longitude;
    private String content;
    private Boolean helmet;
    private Boolean multiPerson;
}
