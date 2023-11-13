package azaza.lawkick.report.controller;

import azaza.lawkick.config.BaseResponse;
import azaza.lawkick.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public BaseResponse<String> testAPI(){

        return BaseResponse.onSuccess("OK");
    }

}
