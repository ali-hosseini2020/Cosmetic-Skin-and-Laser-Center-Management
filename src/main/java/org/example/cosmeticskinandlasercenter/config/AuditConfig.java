package org.example.cosmeticskinandlasercenter.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider") // Enable JPA Auditing
@Slf4j
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
                return Optional.of("System"); // System or Anonymous user if no auth context
            }

            String username = authentication.getName(); // Get username from SecurityContext

            log.debug("Current auditor: {}", username); // Logging auditor for debugging

            return Optional.ofNullable(username);
        };
    }
}