package com.cra.backend.controller;

import com.cra.backend.dto.OrganizationRequestDto;
import com.cra.backend.dto.OrganizationResponseDto;
import com.cra.backend.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrganizationController — Handles HTTP requests related to Organizations.
 *
 * WHAT IS A CONTROLLER?
 * The controller is the entry point for HTTP requests into your application.
 * It is the THINNEST layer — it should:
 *   1. Parse the incoming HTTP request
 *   2. Validate the request (using @Valid)
 *   3. Hand off to the service
 *   4. Wrap the service result in an HTTP response
 *   5. Return the response
 *
 * The controller should contain ZERO business logic. If you catch yourself
 * writing "if" conditions about business rules in a controller, move that to the service.
 *
 * @RestController → handles HTTP, returns JSON automatically
 * @RequestMapping → base path: all endpoints here live under /api/organizations
 * @RequiredArgsConstructor → constructor injection for OrganizationService
 */
@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    /**
     * POST /api/organizations
     * Creates a new organization.
     *
     * @PostMapping  → maps HTTP POST requests to this method
     * @RequestBody  → tells Spring to deserialize the JSON request body into OrganizationRequestDto
     * @Valid        → triggers Bean Validation on the DTO (@NotBlank, @Size, etc.)
     *                 If validation fails, Spring throws MethodArgumentNotValidException
     *                 BEFORE this method is even called.
     *                 Our GlobalExceptionHandler will catch that and return a 400 response.
     *
     * HTTP 201 Created:
     * The correct HTTP status for a successful resource creation is 201 (not 200).
     * ResponseEntity.status(HttpStatus.CREATED).body(response) sets this correctly.
     */
    @PostMapping
    public ResponseEntity<OrganizationResponseDto> createOrganization(
            @Valid @RequestBody OrganizationRequestDto requestDto) {

        OrganizationResponseDto response = organizationService.createOrganization(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /api/organizations
     * Returns all organizations.
     *
     * @GetMapping  → maps HTTP GET requests to this method
     * ResponseEntity.ok(list) → sets HTTP 200 + the list as JSON body
     *
     * The List<OrganizationResponseDto> is automatically serialized to a JSON array:
     * [ {"id": 1, "name": "Acme", ...}, {"id": 2, "name": "Globex", ...} ]
     */
    @GetMapping
    public ResponseEntity<List<OrganizationResponseDto>> getAllOrganizations() {
        List<OrganizationResponseDto> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizations);
    }
}
