package dev.sss.auth.ui;

import dev.sss.auth.application.dto.LoginRequest;
import dev.sss.auth.application.dto.LoginResponse;
import dev.sss.auth.application.OAuth2LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth API")
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OAuth2LoginService oAuth2LoginService;

    @Operation(description = "애플 로그인")
    @PostMapping("/login/apple")
    public LoginResponse loginWithApple(@RequestBody LoginRequest request) {
        return oAuth2LoginService.loginWithApple(request);
    }

}