package com.hydatis.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(authorize ->
                        authorize
                                .pathMatchers(
                                        "/doc/*/v3/api-docs/**", // Custom swagger path
                                        "/v3/api-docs/**",// OpenAPI v3 API docs
                                        "/swagger-ui.html", // Swagger UI HTML
                                        "/swagger-ui/**", // Swagger UI resources
                                        "/swagger-resources/**",
                                        "/webjars/**",
                                        "/api/v1/project/unsecured"
                                )
                                .permitAll()
                                .anyExchange()
                                .authenticated()
                )

                .oauth2ResourceServer(o2r -> o2r
                        .jwt(withDefaults())
                )
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}