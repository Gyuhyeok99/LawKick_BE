package azaza.lawkick.report.service;

import azaza.lawkick.config.code.status.ErrorStatus;
import azaza.lawkick.config.exception.handler.TempHandler;
import azaza.lawkick.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }

}
