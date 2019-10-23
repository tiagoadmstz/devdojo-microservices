package academy.devdojo.youtube.security.token.converters;

import academy.devdojo.youtube.core.properties.JwtConfiguration;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {
        @Autowired
})
public class TokenConverter {

    private final JwtConfiguration jwtConfiguration;

    @SneakyThrows
    public String decryptToken(String encryptedToken) {
        JWEObject jweObject = JWEObject.parse(encryptedToken);
        DirectDecrypter directDecrypter = new DirectDecrypter(jwtConfiguration.getPrivateKey().getBytes());
        jweObject.decrypt(directDecrypter);
        return jweObject.getPayload().toSignedJWT().serialize();
    }

    @SneakyThrows
    public void validateTokenSignature(String signedToken) {
        SignedJWT signedJWT = SignedJWT.parse(signedToken);
        RSAKey publicKey = RSAKey.parse(signedJWT.getHeader().getJWK().toJSONObject());
        if (!signedJWT.verify(new RSASSAVerifier(publicKey)))
            throw new AccessDeniedException("Invalid token signature!");
    }

}
