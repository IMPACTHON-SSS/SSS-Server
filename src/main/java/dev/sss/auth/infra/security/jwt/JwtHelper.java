package dev.sss.auth.infra.security.jwt;

import dev.sss.auth.infra.security.MemberDetailsAdapter;
import dev.sss.member.domain.Member;
import dev.sss.member.domain.MemberJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class JwtHelper {

    private final ParseJwtAdapter parseJwtAdapter;
    private final MemberJPARepository memberJPARepository;

    void setAuthentication(final String accessToken) {
        if(accessToken != null) {
            final Long memberId = parseJwtAdapter.getSubjectFromAccessToken(accessToken);
            final Member member = memberJPARepository.getById(memberId);
            final Authentication authentication = getAuthentication(member);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private Authentication getAuthentication(final Member member) {
        final MemberDetailsAdapter details = new MemberDetailsAdapter(member);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

}