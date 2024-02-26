package com.hydatis.userstoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
public class UserStoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserStoryServiceApplication.class, args);
    }

}
