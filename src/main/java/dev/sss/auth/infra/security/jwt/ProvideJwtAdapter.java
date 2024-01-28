package dev.sss.auth.infra.security.jwt;

import dev.sss.member.domain.MemberRole;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ProvideJwtAdapter {

    private final JwtProperties jwtProperties;

    public String generateAccessToken(final Long id, final MemberRole memberRole) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS)
                .setSubject(id.toString())
                .claim("authority", memberRole)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtProperties.getAccessExpire())))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getAccessKey())
                .compact();
    }

}