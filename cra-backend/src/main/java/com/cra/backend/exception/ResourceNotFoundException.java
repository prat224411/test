package com.cra.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceNotFoundException — Thrown when a requested resource doesn't exist in the DB.
 *
 * WHY A CUSTOM EXCEPTION?
 * When you do organizationRepository.findById(id) and the ID doesn't exist,
 * you want to return HTTP 404 Not Found with a meaningful message.
 * A custom exception lets you express that intent clearly in your service code:
 *   throw new ResourceNotFoundException("Organization", "id", id);
 * ...instead of trying to handle it in the controller.
 *
 * extends RuntimeException:
 * RuntimeExceptions are unchecked — you don't have to declare them in method signatures.
 * Spring's @Transactional rolls back the transaction on RuntimeException by default.
 *
 * @ResponseStatus(HttpStatus.NOT_FOUND):
 * If this exception propagates all the way to the controller without being caught,
 * Spring MVC will automatically set the HTTP response status to 404.
 * Our GlobalExceptionHandler catches it explicitly for a cleaner response format.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Creates a descriptive message like: "Organization not found with id : '42'"
     *
     * @param resourceName  The entity type (e.g., "Organization")
     * @param fieldName     The field used to look it up (e.g., "id")
     * @param fieldValue    The value that wasn't found (e.g., 42)
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
