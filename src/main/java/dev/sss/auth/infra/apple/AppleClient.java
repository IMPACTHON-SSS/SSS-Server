package dev.sss.auth.infra.apple;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "AppleClient",
        url = "https://appleid.apple.com/auth"
)
public interface AppleClient {

    @GetMapping("/keys")
    ApplePublicKeys applePublicKeys();

}