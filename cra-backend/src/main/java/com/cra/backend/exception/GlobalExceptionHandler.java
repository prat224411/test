package com.cra.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler — Catches exceptions from ALL controllers in one place.
 *
 * WITHOUT THIS CLASS:
 * If a controller throws an exception, Spring Boot would return its default error
 * response (an HTML page or generic JSON with a stack trace). That's ugly and leaks
 * internal details to clients.
 *
 * WITH THIS CLASS:
 * You define exactly what JSON structure is returned for each type of exception.
 * All your error responses follow the same consistent format.
 *
 * @RestControllerAdvice:
 * A combination of @ControllerAdvice + @ResponseBody.
 * @ControllerAdvice → this class "advises" all controllers globally (interceptor pattern)
 * @ResponseBody     → all return values are serialized to JSON
 *
 * @ExceptionHandler(SomeException.class):
 * This method is called whenever SomeException (or its subclasses) is thrown
 * anywhere in any controller, service, or repository — Spring intercepts it.
 *
 * HOW IT WORKS:
 * Controller throws Exception
 *   → Spring looks for an @ExceptionHandler that matches the exception type
 *   → Calls that handler method
 *   → Returns the handler's ResponseEntity as the HTTP response
 *   → Client gets your clean, consistent error JSON instead of a stack trace
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation failures from @Valid on @RequestBody parameters.
     * Triggered when a field fails a constraint like @NotBlank, @Size, @Email, etc.
     *
     * Example response:
     * {
     *   "status": 400,
     *   "error": "Validation Failed",
     *   "message": "Input validation failed",
     *   "fieldErrors": {
     *     "name": "Organization name is required",
     *     "industry": "Industry is required"
     *   },
     *   "timestamp": "2025-01-15T10:30:00"
     * }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException ex) {

        log.warn("Validation failed: {}", ex.getMessage());

        // Collect all field errors into a map: fieldName → error message
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());  // 400
        response.put("error", "Validation Failed");
        response.put("message", "Input validation failed");
        response.put("fieldErrors", fieldErrors);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handles ResourceNotFoundException — returns HTTP 404 with a clear message.
     *
     * Example response:
     * {
     *   "status": 404,
     *   "error": "Not Found",
     *   "message": "Organization not found with id : '99'",
     *   "timestamp": "2025-01-15T10:30:00"
     * }
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        log.warn("Resource not found: {}", ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());  // 404
        response.put("error", "Not Found");
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Catch-all handler — handles any unexpected exception that isn't caught above.
     * Always have this as a safety net — you never want raw stack traces sent to clients.
     *
     * Example response:
     * {
     *   "status": 500,
     *   "error": "Internal Server Error",
     *   "message": "An unexpected error occurred. Please try again later.",
     *   "timestamp": "2025-01-15T10:30:00"
     * }
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        // Log the full stack trace for debugging — but don't send it to the client
        log.error("Unexpected error occurred", ex);

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());  // 500
        response.put("error", "Internal Server Error");
        response.put("message", "An unexpected error occurred. Please try again later.");
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
