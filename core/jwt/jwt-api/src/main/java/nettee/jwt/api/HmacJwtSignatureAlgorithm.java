package nettee.jwt.api;

import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.MacAlgorithm;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;

public enum HmacJwtSignatureAlgorithm
        implements SupportedSignatureAlgorithm<SecretKey, SecretKey> {
    HS256(SIG.HS256),
    HS384(SIG.HS384),
    HS512(SIG.HS512),
    ;

    private final MacAlgorithm algorithm;

    HmacJwtSignatureAlgorithm(MacAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public SecureDigestAlgorithm<SecretKey, SecretKey> jjwtSignatureAlgorithm() {
        return algorithm;
    }
}
