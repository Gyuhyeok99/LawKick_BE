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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static azaza.lawkick.config.code.status.ErrorStatus.*;
import static java.net.URLDecoder.decode;

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
        Report report = reportRepository.save(new Report(reportReq.getSerialNumber(), reportReq.getImageUrl(), findMember));
        log.info("{} report 저장", report.getId());
        return new CaptureRes(report.getId(), report.getImageUrl());
    }

    public String ocr(String imageUrl) {
        // URL 디코딩
        String decodedUrl = null;
        try {
            decodedUrl = URLDecoder.decode(imageUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 데이터 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("imageUrl", decodedUrl);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // Flask 서버 API 호출
        String flaskUrl = "http://localhost:5000/api/ocr";  // Flask 서버 URL
        ResponseEntity<String> response = restTemplate.exchange(
                flaskUrl, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }

    @Transactional
    public SubmitRes marker(CaptureReq captureReq, Long reportId) {
        Report findReport = reportRepository.findById(reportId)
                .orElseThrow(() -> new ReportHandler(INVALID_REPORT_ID));
        findReport.updateMarker(captureReq.getKickboardType(), captureReq.getSerialNumber());
        log.info("킥보드 타입과 시리얼 넘버 업데이트 완료");
        return new SubmitRes(findReport.getId());
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
