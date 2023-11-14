package azaza.lawkick.report.controller;

import azaza.lawkick.config.BaseResponse;
import azaza.lawkick.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    //예외처리 테스트
    @GetMapping("/exception")
    public BaseResponse<Object> exceptionAPI(@RequestParam Integer flag){
        reportService.CheckFlag(flag);
        return BaseResponse.onSuccess(flag);
    }

    //신고하는 기능
    //일단 OCR 작동시키는 기능만 넣었는데 이야기좀 해봐야겠네요
    @PostMapping
    public BaseResponse<String> report(@RequestPart("file") MultipartFile file) {
        return BaseResponse.onSuccess(reportService.ocr(file));
    }
}
