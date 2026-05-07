package com.cra.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * AppConfig — Central configuration class for the application.
 *
 * WHY THIS CLASS EXISTS:
 * @EnableJpaAuditing activates the auditing feature in Spring Data JPA.
 * Without this, the @CreatedDate and @LastModifiedDate annotations in
 * BaseEntity will NOT work — those timestamps will never be populated.
 *
 * @Configuration tells Spring: "This class contains configuration settings."
 * Spring will load it during startup. You can add @Bean methods here later
 * to create and register custom objects (beans) in the Spring container.
 *
 * As the project grows, this class will also hold:
 *   - Jackson (JSON) configuration
 *   - ModelMapper / custom mappers
 *   - CORS configuration
 *   - AsyncConfig for @Async methods
 */
@Configuration
@EnableJpaAuditing
public class AppConfig {
    // Will be extended as the project grows.
    // Currently activates JPA auditing for BaseEntity timestamps.
}
