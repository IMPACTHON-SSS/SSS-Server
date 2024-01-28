package dev.sss.auth.infra.security;

import dev.sss.member.domain.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberSessionHolder {

    public Member current() {
        return ((MemberDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
    }

}