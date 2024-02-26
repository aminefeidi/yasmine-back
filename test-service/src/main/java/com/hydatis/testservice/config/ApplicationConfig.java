package com.hydatis.testservice.config;

import com.hydatis.testservice.entities.audit.ApplicationAuditAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * Class containing app configuration with required beans
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new ApplicationAuditAware();
    }
}
