package dev.sss.member.ui;

import dev.sss.member.applicaiton.MemberService;
import dev.sss.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member API")
@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(description = "내 정보 조회")
    @GetMapping("/my")
    public Member getMy() {
        return memberService.getMy();
    }

}