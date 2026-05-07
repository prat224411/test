package com.cra.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * OrganizationResponseDto — Represents the data we send BACK to the client.
 *
 * NOTICE the difference from RequestDto:
 * - We INCLUDE id, createdAt, updatedAt here (client needs these to reference the resource)
 * - The client was NOT allowed to SET these fields (hence they're absent from RequestDto)
 *
 * This separation gives you full control over your API contract:
 * "You send me {name, industry, status}. I give you back {id, name, industry, status, createdAt, updatedAt}."
 *
 * @Builder here allows service code to build responses elegantly:
 *   OrganizationResponseDto.builder()
 *       .id(org.getId())
 *       .name(org.getName())
 *       .build();
 */
@Data
@Builder
public class OrganizationResponseDto {

    private Long id;
    private String name;
    private String industry;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
