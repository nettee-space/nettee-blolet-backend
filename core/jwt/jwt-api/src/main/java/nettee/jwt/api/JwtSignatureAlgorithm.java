package nettee.jwt.api;

import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SignatureAlgorithm;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public enum JwtSignatureAlgorithm implements SupportedSignatureAlgorithm<PrivateKey, PublicKey> {
    RS256("RSA", SIG.RS256),
    RS384("RSA", SIG.RS384),
    RS512("RSA", SIG.RS512),
    PS256("RSA", SIG.PS256),
    PS384("RSA", SIG.PS384),
    PS512("RSA", SIG.PS512),
    ES256("EC", SIG.ES256),
    ES384("EC", SIG.ES384),
    ES512("EC", SIG.ES512),
    EdDSA("Ed25519", SIG.EdDSA),
    ;

    private final KeyFactory keyFactory;
    private final SignatureAlgorithm algorithm;

    JwtSignatureAlgorithm(String keyFactoryInstanceName, SignatureAlgorithm algorithm) {
        try {
            this.keyFactory = KeyFactory.getInstance(keyFactoryInstanceName);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        this.algorithm = algorithm;
    }

    @Override
    public SecureDigestAlgorithm<PrivateKey, PublicKey> jjwtSignatureAlgorithm() {
        return algorithm;
    }

    public PrivateKey privateKeyFromBase64(String base64PrivateKey) {
        base64PrivateKey = base64PrivateKey
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);

        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            return keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
