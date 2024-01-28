package dev.sss.auth.infra.apple;

import java.math.BigInteger;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

public record ApplePublicKey(
        String kty,
        String kid,
        String use,
        String alg,
        String n,
        String e
) {
    public RSAPublicKeySpec publicKeySpec() {
        BigInteger nBytes = new BigInteger(1, Base64.getUrlDecoder().decode((this.n)));
        BigInteger eBytes = new BigInteger(1, Base64.getUrlDecoder().decode((this.e)));

        return new RSAPublicKeySpec(nBytes, eBytes);
    }
}