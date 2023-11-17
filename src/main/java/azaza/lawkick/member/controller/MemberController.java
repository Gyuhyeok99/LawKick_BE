package azaza.lawkick.member.controller;


import azaza.lawkick.config.BaseResponse;
import azaza.lawkick.member.dto.MyPageRes;
import azaza.lawkick.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
@CrossOrigin
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public BaseResponse<MyPageRes> findALlReportbyMember() {
        return BaseResponse.onSuccess(memberService.findALlReportbyMember());
    }
}
