package academy.devdojo.youtube.security.token.creators;

import academy.devdojo.youtube.core.models.ApplicationUser;
import academy.devdojo.youtube.core.properties.JwtConfiguration;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {
        @Autowired
})
public class TokenCreator {

    private final JwtConfiguration jwtConfiguration;

    @SneakyThrows
    public SignedJWT createSignedJWT(Authentication auth) {
        ApplicationUser applicationUser = (ApplicationUser) auth.getPrincipal();
        JWTClaimsSet jwtClaimsSet = createJWTClaimsSet(auth, applicationUser);
        KeyPair rsaKey = generatedKeyPair();
        JWK jwk = new RSAKey.Builder((RSAPublicKey) rsaKey.getPublic()).keyID(UUID.randomUUID().toString()).build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).jwk(jwk).type(JOSEObjectType.JWT)
                .build(), jwtClaimsSet);
        RSASSASigner signer = new RSASSASigner(rsaKey.getPrivate());
        signedJWT.sign(signer);
        return signedJWT;
    }

    private JWTClaimsSet createJWTClaimsSet(Authentication auth, ApplicationUser applicationUser) {
        return new JWTClaimsSet.Builder()
                .subject(applicationUser.getUsername())
                .claim("authorities", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .claim("userId", applicationUser.getId())
                .issuer("http://academy.devdojo")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + (jwtConfiguration.getExpiration() * 1000)))
                .build();
    }

    @SneakyThrows
    private KeyPair generatedKeyPair() {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.genKeyPair();
    }

    public String encryptToken(SignedJWT signedJWT) throws JOSEException {
        DirectEncrypter directEncrypter = new DirectEncrypter(jwtConfiguration.getPrivateKey().getBytes());
        JWEObject jweObject = new JWEObject(new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256)
                .contentType("JWT")
                .build(), new Payload(signedJWT));
        jweObject.encrypt(directEncrypter);
        return jweObject.serialize();
    }

}
