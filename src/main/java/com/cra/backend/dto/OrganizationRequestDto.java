package com.cra.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * OrganizationRequestDto — Represents the data a client sends when creating an Organization.
 *
 * WHAT IS A DTO (Data Transfer Object)?
 * A DTO is a plain Java class used to carry data between layers of your application.
 * Specifically, it separates what the CLIENT sends/receives from what your DATABASE stores.
 *
 * WHY NOT JUST USE THE ENTITY DIRECTLY?
 * 1. Security: Your entity might have fields (createdAt, id) you don't want clients to set
 * 2. Validation: You validate input at the DTO level before it touches your DB
 * 3. Flexibility: Your API shape can evolve independently from your DB schema
 * 4. Clarity: Makes it obvious what data is expected as INPUT
 *
 * NAMING CONVENTION:
 * [EntityName]RequestDto  → data coming IN  (client → server)
 * [EntityName]ResponseDto → data going OUT  (server → client)
 *
 * VALIDATION ANNOTATIONS (from spring-boot-starter-validation):
 * @NotBlank → field must not be null, empty, or only whitespace
 * @Size     → constrains the string length
 * message   → the error message returned to the client if validation fails
 */
@Data
public class OrganizationRequestDto {

    @NotBlank(message = "Organization name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @NotBlank(message = "Industry is required")
    @Size(max = 100, message = "Industry must not exceed 100 characters")
    private String industry;

    @NotBlank(message = "Status is required")
    @Size(max = 50, message = "Status must not exceed 50 characters")
    private String status;
}
