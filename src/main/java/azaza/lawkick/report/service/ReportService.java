package azaza.lawkick.report.service;

import azaza.lawkick.config.exception.handler.MemberHandler;
import azaza.lawkick.config.exception.handler.ReportHandler;
import azaza.lawkick.domain.Member;
import azaza.lawkick.domain.Report;
import azaza.lawkick.member.repository.MemberRepository;
import azaza.lawkick.report.dto.*;
import azaza.lawkick.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import static azaza.lawkick.config.code.status.ErrorStatus.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    private String localurl = "http://127.0.0.1:5000/api/ocr";
    private String flaskurl = "http://3.36.198.248:5000/api/ocr";
    @Transactional
    public CaptureRes save(ReportReq reportReq) {
        //멤버 하드코딩 ㅎㅎ.. 그냥 1번을 사용자로 하죠
        Member findMember = memberRepository.findById(1L)
                .orElseThrow(() -> new MemberHandler(MEMBER_NOT_FOUND));
        log.info("{} member 조회", findMember.getId());
        Report report = reportRepository.save(new Report(reportReq.getSerialNumber(), reportReq.getKickboardType(), reportReq.getImageUrl(), findMember));
        log.info("{} report 저장", report.getId());
        return new CaptureRes(report.getId(), report.getImageUrl());
    }

    public String ocr(MultipartFile file) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        log.info("Flask 서버 API 호출");
        ResponseEntity<String> response = restTemplate.exchange(
                flaskurl, HttpMethod.POST, requestEntity, String.class);
        log.info("Flask 서버 API 호출 성공 {}", response.getBody());
        return response.getBody();
    }

    public ReportRes reportPage(Long reportId) {
        Report findReport = reportRepository.findById(reportId)
                .orElseThrow(() -> new ReportHandler(INVALID_REPORT_ID));
        log.info("{} report 조회 성공", findReport.getId());
        return new ReportRes(findReport);
    }

    @Transactional
    public SubmitRes update(SubmitReq submitReq, Long reportId) {
        Report findReport = reportRepository.findById(reportId)
                .orElseThrow(() -> new ReportHandler(INVALID_REPORT_ID));
        log.info("{} report 조회 성공", findReport.getId());
        findReport.updateReport(submitReq.getKickboardType(), submitReq.getSerialNumber(),
                submitReq.getLatitude(), submitReq.getLatitude(), submitReq.getHelmet(), submitReq.getMultiPerson(), submitReq.getContent());
        log.info("{} report 업데이트", findReport.getId());
        return new SubmitRes(findReport.getId());
    }


}
