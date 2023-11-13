package azaza.lawkick.report.service;

import azaza.lawkick.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
}
