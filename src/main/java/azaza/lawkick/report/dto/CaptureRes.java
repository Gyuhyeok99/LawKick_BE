package azaza.lawkick.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CaptureRes {

    private Long reportId;
    private String imageUrl;
}
