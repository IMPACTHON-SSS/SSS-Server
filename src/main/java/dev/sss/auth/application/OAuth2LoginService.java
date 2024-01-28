package dev.sss.auth.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sss.auth.application.dto.LoginRequest;
import dev.sss.auth.application.dto.LoginResponse;
import dev.sss.auth.infra.apple.AppleClient;
import dev.sss.auth.infra.apple.ApplePublicKey;
import dev.sss.auth.infra.apple.ApplePublicKeys;
import dev.sss.auth.infra.security.jwt.ProvideJwtAdapter;
import dev.sss.member.domain.Member;
import dev.sss.member.domain.MemberJPARepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuth2LoginService {

    private final MemberJPARepository memberJPARepository;
    private final ProvideJwtAdapter provideJwtAdapter;
    private final AppleClient appleClient;
    private final ObjectMapper objectMapper;

    public LoginResponse loginWithApple(LoginRequest request) {
        Map<String, String> tokenHeaders = getParseHeaders(request.token());
        ApplePublicKeys applePublicKeys = appleClient.applePublicKeys();
        PublicKey publicKey = getKey(tokenHeaders, applePublicKeys);
        Claims token = getClaims(request.token(), publicKey);

        AtomicBoolean isFirstLogin = new AtomicBoolean(false);

        String oauth = token.getSubject();
        Optional<Member> memberOrNull = memberJPARepository.findByOauth(oauth);
        Member member = memberOrNull
                .orElseGet(
                        () -> {
                            isFirstLogin.set(true);
                            return memberJPARepository.save(
                                Member.builder()
                                        .oauth(oauth)
                                        .build()
                            );
                        }
                );

        String accessToken = provideJwtAdapter.generateAccessToken(member.getId(), member.getRole());
        return new LoginResponse(accessToken, isFirstLogin);
    }

    private Claims getClaims(String token, PublicKey publicKey) {
        try {
            return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private PublicKey getKey(Map<String, String> tokenHeaders, ApplePublicKeys applePublicKeys) {
        ApplePublicKey publicKey = applePublicKeys.matchesKey(tokenHeaders.get("alg"), tokenHeaders.get("kid"));

        try {
            return KeyFactory.getInstance(publicKey.kty()).generatePublic(publicKey.publicKeySpec());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public Map<String, String> getParseHeaders(String token) {
        try {
            String encodedHeader = token.split("\\.")[0];
            String decodedHeader = new String(Base64.getUrlDecoder().decode(encodedHeader));

            return objectMapper.readValue(decodedHeader, Map.class);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

}