package azaza.lawkick.member.service;

import azaza.lawkick.admin.dto.AdminRes;
import azaza.lawkick.config.exception.handler.MemberHandler;
import azaza.lawkick.domain.Member;
import azaza.lawkick.domain.Report;
import azaza.lawkick.domain.enums.ReportStatus;
import azaza.lawkick.member.dto.MyPageRes;
import azaza.lawkick.member.dto.MyReportDto;
import azaza.lawkick.member.repository.MemberRepository;
import azaza.lawkick.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static azaza.lawkick.config.code.status.ErrorStatus.MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final ReportRepository reportRepository;

    public MyPageRes findALlReportbyMember() {
        Map<String, Object> myPage = new HashMap<>();

        Member findMember = memberRepository.findById(1L)
                .orElseThrow(() -> new MemberHandler(MEMBER_NOT_FOUND));
        log.info("{} member 조회 성공", findMember.getId());
        myPage.put("email", findMember.getEmail());
        myPage.put("nickName", findMember.getNickName());
        myPage.put("mileage", findMember.getMileage());
        myPage.put("createAt", createAt(findMember.getCreateDate()));
        List<Report> aLlReportbyMember = reportRepository.findALlReportbyMember(ReportStatus.WRITING, 1L);
        log.info("{} 멤버의 마이페이지 조회", findMember.getId());
        List<MyReportDto> myReportDtos = new ArrayList<>();
        for (Report report : aLlReportbyMember) {
            myReportDtos.add(new MyReportDto(report));
        }
        myPage.put("myReports", myReportDtos);
        return new MyPageRes(myPage);
    }
    public List<AdminRes> findALlReportbyAdmin() {
        List<Report> admins = reportRepository.findALlReportbyAdmin(ReportStatus.WRITING);
        List<AdminRes> adminRes = new ArrayList<>();
        for (Report report : admins) {
            adminRes.add(new AdminRes(report));
        }
        return adminRes;
    }


    private String createAt(LocalDateTime modifiedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return modifiedDate.format(formatter);
    }

}
