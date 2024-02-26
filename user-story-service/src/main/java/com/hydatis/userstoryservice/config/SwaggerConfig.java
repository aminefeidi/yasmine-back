package com.hydatis.userstoryservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SCHEME_NAME = "Keycloak";
    private static final String SCHEME = "bearer";
    @Value("${app.openapi.project-title}")
    private String title;
    @Value("${app.openapi.project-version}")
    private String version;
    @Value("${app.openapi.gateway-address}")
    private String gatewayAddress;

    @Value("${app.openapi.token-url}")
    private String tokenUrl;

    @Bean
    public OpenAPI springOpenAPI() {
        Server gatewayServer = new Server();
        gatewayServer.setUrl(gatewayAddress);
        gatewayServer.setDescription("Gateway");
        return new OpenAPI()
//                .addServersItem(gatewayServer)
                .info(new Info().
                        title(title)
                        .contact(new Contact()
                                .name("Hydatis")
                                .url("https://www.hydatis.com/"))
                        .version("1")
                ).components(new Components()
                        .addSecuritySchemes(SCHEME_NAME, createSecurityScheme())

                ).addSecurityItem(new SecurityRequirement()
                        .addList(SCHEME_NAME)
                );
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.OAUTH2)
                .flows(new OAuthFlows()
                        .password(new OAuthFlow()
                                .tokenUrl(tokenUrl)
                                .scopes(new Scopes()
                                        .addString("openid scope", "openid")
                                )
                        )
                )
                .scheme(SCHEME);
    }

}