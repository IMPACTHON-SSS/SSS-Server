package dev.sss.auth.infra.apple;

import java.util.List;
import java.util.Objects;

public record ApplePublicKeys(
        List<ApplePublicKey> keys
) {
    public ApplePublicKey matchesKey(String alg, String kid) {
        return keys.stream()
                .filter(it -> Objects.equals(it.alg(), alg) && Objects.equals(it.kid(), kid))
                .findFirst()
                .orElse(null);
    }
}