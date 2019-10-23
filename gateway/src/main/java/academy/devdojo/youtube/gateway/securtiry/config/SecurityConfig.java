package academy.devdojo.youtube.gateway.securtiry.config;

import academy.devdojo.youtube.core.properties.JwtConfiguration;
import academy.devdojo.youtube.gateway.securtiry.filters.GatewayJwtTokenAuthorizationFilter;
import academy.devdojo.youtube.security.config.SecurityTokenConfig;
import academy.devdojo.youtube.security.token.converters.TokenConverter;
import lombok.SneakyThrows;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends SecurityTokenConfig {

    private final TokenConverter tokenConverter;

    public SecurityConfig(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new GatewayJwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }
}
