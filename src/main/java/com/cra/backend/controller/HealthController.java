package com.cra.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * HealthController — A simple custom health check endpoint.
 *
 * NOTE: Spring Actuator already provides /actuator/health automatically.
 * This custom endpoint at GET /api/health is a human-friendly "ping" endpoint
 * that your frontend team or ops team can call to quickly verify the API is up.
 *
 * @RestController:
 * A combination of @Controller + @ResponseBody.
 * Every method return value is automatically serialized to JSON and written
 * to the HTTP response body. You don't need to call objectMapper.writeValueAsString() manually.
 *
 * @RequestMapping("/api/health"):
 * Defines the base URL path for ALL methods in this controller.
 * Since HealthController only has one method (GET), the full path is: GET /api/health
 *
 * ResponseEntity<T>:
 * Gives you full control over the HTTP response:
 *   - Status code (200 OK, 201 Created, 404 Not Found, etc.)
 *   - Response headers
 *   - Response body
 * ResponseEntity.ok(body) → sets status 200 + the body.
 *
 * Map.of() creates an immutable Map in one line — a quick way to build
 * a simple JSON object: {"status": "CRA Backend Running"}
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of("status", "CRA Backend Running"));
    }
}
