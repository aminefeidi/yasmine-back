package com.hydatis.projectservice;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/project")
@RestController
public class ProjectController {


    @GetMapping("/unsecured")
    public String unsecured() {
        return "this endpoint is unsecured";
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello, %s!", jwt.getClaim(StandardClaimNames.PREFERRED_USERNAME).toString());
    }
}
