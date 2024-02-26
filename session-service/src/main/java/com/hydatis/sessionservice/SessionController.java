package com.hydatis.sessionservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/session")
@RestController
public class SessionController {

    @GetMapping
    public String hello() {
        return "hello session";
    }
}
