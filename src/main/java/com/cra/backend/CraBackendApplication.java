package com.cra.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the CRA Backend Spring Boot application.
 *
 * HOW SPRING BOOT STARTS:
 * 1. JVM calls main() just like any Java program
 * 2. SpringApplication.run() bootstraps the entire framework:
 *    a. Creates the Spring Application Context (the container that manages all your beans/objects)
 *    b. Starts the embedded Tomcat web server on port 8080
 *    c. Scans all classes in this package and sub-packages for Spring annotations
 *    d. Connects to the database, creates tables if needed (ddl-auto: update)
 *    e. Registers all controllers, services, repositories
 *    f. App is now ready to accept HTTP requests
 *
 * @SpringBootApplication is a shortcut for THREE annotations combined:
 *   @Configuration      → this class can define Spring beans (objects managed by Spring)
 *   @EnableAutoConfiguration → Spring Boot auto-configures things based on your dependencies
 *                              (e.g., detects MySQL driver → auto-configures DataSource)
 *   @ComponentScan      → scans this package + all sub-packages for @Component, @Service,
 *                         @Repository, @Controller, @RestController, @Configuration, etc.
 *                         This is how Spring "finds" all your classes automatically.
 */
@SpringBootApplication
public class CraBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CraBackendApplication.class, args);
    }
}
