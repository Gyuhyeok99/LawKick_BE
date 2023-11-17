package azaza.lawkick.admin.service;


import azaza.lawkick.admin.dto.AdminRes;
import azaza.lawkick.admin.dto.JudgeRes;
import azaza.lawkick.config.exception.handler.ReportHandler;
import azaza.lawkick.domain.Report;
import azaza.lawkick.domain.enums.ReportStatus;
import azaza.lawkick.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static azaza.lawkick.config.code.status.ErrorStatus.INVALID_REPORT_ID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
@Slf4j
public class AdminService {

    private final ReportRepository reportRepository;

    //관리자 페이지에서 최신순으로 신고 리스트 전체 조회
    public List<AdminRes> findALlReportbyAdmin() {
        List<Report> admins = reportRepository.findALlReportbyAdmin(ReportStatus.WRITING);
        List<AdminRes> adminRes = new ArrayList<>();
        for (Report report : admins) {
            adminRes.add(new AdminRes(report));
        }
        return adminRes;
    }

    @Transactional
    public JudgeRes reportTrue(Long reportId) {
        Report findReport = reportRepository.findById(reportId)
                .orElseThrow(() -> new ReportHandler(INVALID_REPORT_ID));
        findReport.updateReportTrue();
        log.info("최종 신고 완료로 마일리지 적립 {}", findReport.getReportStatus());
        //마일리지 적립 기능 추가해야함
        return new JudgeRes(reportId);

    }

}
