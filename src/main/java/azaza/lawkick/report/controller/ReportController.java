package azaza.lawkick.report.controller;

import azaza.lawkick.config.BaseResponse;
import azaza.lawkick.report.dto.ReportRes;
import azaza.lawkick.report.service.ReportService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    //신고하는 기능
    //일단 OCR 작동시키는 기능만 넣었는데 이야기좀 해봐야겠네요
    @PostMapping
    public BaseResponse<String> capture(@RequestPart("file") MultipartFile file) {
        //s3 저장하는 기능 추가

        //ocr 돌리는 기능
        reportService.ocr(file);
        return BaseResponse.onSuccess("OK"); //응답값 뭐로할지 통일을 안해서 일단 그냥 OK로 해놓았어요
    }

    @GetMapping({"reportId"})
    public BaseResponse<ReportRes> showReport(@PathVariable Long reportId) {
        return BaseResponse.onSuccess(reportService.reportPage(reportId));
    }

    /*@PostMapping
    public BaseResponse<Long> submitReport() {

    }*/
}
