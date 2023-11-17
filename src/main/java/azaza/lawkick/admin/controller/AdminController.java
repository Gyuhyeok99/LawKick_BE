package azaza.lawkick.admin.controller;

import azaza.lawkick.admin.dto.AdminRes;
import azaza.lawkick.admin.service.AdminService;
import azaza.lawkick.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
