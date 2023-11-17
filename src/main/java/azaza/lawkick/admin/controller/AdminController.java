package azaza.lawkick.admin.controller;

import azaza.lawkick.admin.dto.AdminRes;
import azaza.lawkick.admin.dto.JudgeRes;
import azaza.lawkick.admin.service.AdminService;
import azaza.lawkick.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public BaseResponse<List<AdminRes>> findALlByCreateDateDesc() {
        return BaseResponse.onSuccess(adminService.findALlReportbyAdmin());
    }

    @PatchMapping("/{reportId}/true")
    public BaseResponse<JudgeRes> reportTrue(@PathVariable Long reportId) {
        return BaseResponse.onSuccess(adminService.reportTrue(reportId));
    }
    @PatchMapping("/{reportId}/false")
    public BaseResponse<JudgeRes> reportFalse(@PathVariable Long reportId) {
        return BaseResponse.onSuccess(adminService.reportFalse(reportId));
    }
}
