package azaza.lawkick.report.controller;

import azaza.lawkick.config.BaseResponse;
import azaza.lawkick.config.s3.S3Uploader;
import azaza.lawkick.report.dto.*;
import azaza.lawkick.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/report")
@Slf4j
@CrossOrigin
public class ReportController {

    private final ReportService reportService;
    private final S3Uploader s3Uploader;

    //신고하는 기능
    //일단 OCR 작동시키는 기능만 넣었는데 이야기좀 해봐야겠네요
    @PostMapping
    public BaseResponse<CaptureRes> capture(@RequestPart("captureReq") CaptureReq captureReq,
                                            @RequestPart("image") MultipartFile image) throws IOException {
        log.info("capture api 호출");
        //s3 저장하는 기능 추가
        String imageUrl = s3Uploader.fileUpload(image, "reportImage/");//일단 저장했다고 쳤습니다
        String serialNumber = reportService.ocr(image);
        return BaseResponse.onSuccess(reportService.save(new ReportReq(serialNumber, imageUrl))); //응답값 뭐로할지 통일을 안해서 일단 그냥 OK로 해놓았어요
    }

    @PostMapping("/{reportId}")
    public BaseResponse<SubmitRes> marker(@RequestBody CaptureReq captureReq, @PathVariable Long reportId)   {
        return BaseResponse.onSuccess(reportService.marker(captureReq, reportId));
    }

    @GetMapping("{reportId}")
    public BaseResponse<ReportRes> showReport(@PathVariable Long reportId) {
        return BaseResponse.onSuccess(reportService.reportPage(reportId));
    }

    @PostMapping("{reportId}/submit")
    public BaseResponse<SubmitRes> submitReport(@RequestBody SubmitReq submitReq, @PathVariable Long reportId) {
        return BaseResponse.onSuccess(reportService.update(submitReq, reportId));
    }
}
