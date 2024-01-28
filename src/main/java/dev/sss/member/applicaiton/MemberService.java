package dev.sss.member.applicaiton;

import dev.sss.auth.infra.security.MemberSessionHolder;
import dev.sss.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberSessionHolder memberSessionHolder;

    public Member getMy() {
        return memberSessionHolder.current();
    }

}