package azaza.lawkick.report.service;

import azaza.lawkick.config.code.status.ErrorStatus;
import azaza.lawkick.config.exception.handler.TempHandler;
import azaza.lawkick.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;

    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
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
                "http://127.0.0.1:5000/api/ocr", HttpMethod.POST, requestEntity, String.class);
        log.info("Flask 서버 API 호출 성공 {}", response.getBody());
        return response.getBody();
    }


}
