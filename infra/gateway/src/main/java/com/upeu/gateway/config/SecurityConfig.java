package com.upeu.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/actuator/health", "/actuator/info").permitAll()
                        .pathMatchers("/actuator/prometheus").permitAll()
                        .pathMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/webjars/**", "/docs/**").permitAll()
                        .pathMatchers("/auth/**", "/api/v1/auth/**").permitAll()
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .pathMatchers("/api/v1/usuarios/**").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/api/v1/tecnicos/**").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/api/v1/tecnicos/**").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/api/v1/tecnicos/**").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/api/v1/tecnicos/**").hasAnyAuthority("ADMIN", "CLIENTE")
                        .pathMatchers(HttpMethod.DELETE, "/api/v1/clientes/**").hasAuthority("ADMIN")
                        .pathMatchers("/api/v1/clientes/**").hasAnyAuthority("ADMIN", "CLIENTE")
                        .pathMatchers("/api/v1/solicitudes/**").hasAnyAuthority("ADMIN", "CLIENTE")
                        .pathMatchers("/api/v1/asignaciones/**").hasAnyAuthority("ADMIN", "CLIENTE")
                        .pathMatchers("/api/v1/pagos/**").hasAnyAuthority("ADMIN", "CLIENTE")
                        .pathMatchers("/api/v1/reviews/**").hasAnyAuthority("ADMIN", "CLIENTE")
                        .pathMatchers(HttpMethod.POST, "/api/v1/notificaciones").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/api/v1/notificaciones/**").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/api/v1/notificaciones").hasAnyAuthority("ADMIN", "CLIENTE")
                        .pathMatchers(HttpMethod.GET, "/api/v1/notificaciones/**").hasAnyAuthority("ADMIN", "CLIENTE")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .build();
    }

    @Bean
    NimbusReactiveJwtDecoder jwtDecoder(JwtProperties jwtProperties) {
        byte[] keyBytes = jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        NimbusReactiveJwtDecoder decoder = NimbusReactiveJwtDecoder.withSecretKey(secretKey).build();
        decoder.setJwtValidator(JwtValidators.createDefaultWithIssuer(jwtProperties.getIssuer()));
        return decoder;
    }

    @Bean
    ReactiveJwtAuthenticationConverterAdapter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
