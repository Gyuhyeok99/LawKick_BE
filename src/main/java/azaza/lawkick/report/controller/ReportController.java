package azaza.lawkick.report.controller;

import azaza.lawkick.config.BaseResponse;
import azaza.lawkick.domain.enums.KickboardType;
import azaza.lawkick.report.dto.CaptureReq;
import azaza.lawkick.report.dto.CaptureRes;
import azaza.lawkick.report.dto.ReportReq;
import azaza.lawkick.report.dto.ReportRes;
import azaza.lawkick.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/report")
@Slf4j
public class ReportController {

    private final ReportService reportService;

    //신고하는 기능
    //일단 OCR 작동시키는 기능만 넣었는데 이야기좀 해봐야겠네요
    @PostMapping
    public BaseResponse<CaptureRes> capture(@RequestPart("captureReq") CaptureReq captureReq,
                                            @RequestParam("image") MultipartFile image
                                                ) {
        log.info("capture api 호출");
        //s3 저장하는 기능 추가

        String imageUrl = "http~~.jpg";//일단 저장했다고 쳤습니다
        //마커를 안찍었을 때만 ocr 돌리면 되지 않을까요?
        String serialNumber = captureReq.getSerialNumber();
        if(serialNumber == null || serialNumber.isEmpty())
            serialNumber = reportService.ocr(image);
        return BaseResponse.onSuccess(reportService.save(new ReportReq(serialNumber, captureReq.getKickboardType(), imageUrl))); //응답값 뭐로할지 통일을 안해서 일단 그냥 OK로 해놓았어요
    }

    @GetMapping("{reportId}")
    public BaseResponse<ReportRes> showReport(@PathVariable Long reportId) {
        return BaseResponse.onSuccess(reportService.reportPage(reportId));
    }

//    @PostMapping
//    public BaseResponse<Long> submitReport() {
//
//    }
}
