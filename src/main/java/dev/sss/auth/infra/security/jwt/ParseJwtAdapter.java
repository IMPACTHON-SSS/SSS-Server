package dev.sss.auth.infra.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class ParseJwtAdapter {

    private final JwtProperties jwtProperties;

    public Long getSubjectFromAccessToken(final String accessToken) {
        return getSubject(accessToken);
    }

    private Long getSubject(final String token) {
        final String key = jwtProperties.getAccessKey();

        final Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(key).parseClaimsJws(extractToken(token));

        return Long.valueOf(jwsClaims.getBody().getSubject());
    }

    private String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return token;
    }

}