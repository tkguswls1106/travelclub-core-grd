package shjgroup.travelclubcoregrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shjgroup.travelclubcoregrd.service.MemberService;
import shjgroup.travelclubcoregrd.service.sdo.MemberCdo;

@RestController
@RequestMapping("/member")
public class MemberController {  // 로직들은 거의 ClubController과 유사하므로 참고하자.

    private MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping  // http://localhost:8080/member
    public String register(@RequestBody MemberCdo memberCdo) {
        return memberService.registerMember(memberCdo);
    }

}