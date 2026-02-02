package com.higuitar.catalogoproductosflex.config;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Represents a standardized error response for APIs.
 * This class is used to convey information about errors that occur during
 * the execution of API requests, including details such as an error code,
 * a message describing the error, the endpoint path where the error occurred,
 * and the timestamp when the error was recorded.
 *
 * It optionally contains additional validation error details for scenarios
 * such as request validation failures.
 */
public class ApiErrorResponse {
    private final Integer code;
    private final String message;
    private final String path;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private Map<String, String> errors;

    /**
     * Constructs an ApiErrorResponse instance with the provided error code, message, and path.
     *
     * @param code the error code representing the type of error
     * @param message a detailed message describing the error
     * @param path the path of the API endpoint where the error occurred
     */
    public ApiErrorResponse(Integer code, String message, String path) {
        this.code = code;
        this.message = message;
        this.path = path;
    }

    /**
     * Constructs an ApiErrorResponse instance with the provided error code, message, path,
     * and additional validation errors.
     *
     * @param code the error code representing the type of error
     * @param message a detailed message describing the error
     * @param path the path of the API endpoint where the error occurred
     * @param errors a map containing additional validation error details, where the key is the field name
     *               and the value is the associated error message
     */
    public ApiErrorResponse(Integer code, String message, String path, Map<String, String> errors) {
        this(code, message, path);
        this.errors = errors;
    }

    /**
     * Returns the error code associated with the API response.
     * The error code typically represents the type of error
     * encountered during the execution of an API request.
     *
     * @return an {@code Integer} representing the error code.
     */
    // Getters
    public Integer getCode() { return code; }

    /**
     * Retrieves the error message associated with this API error response.
     * The message provides a detailed description of the error that occurred.
     *
     * @return a {@code String} containing the error message.
     */
    public String getMessage() { return message; }

    /**
     * Retrieves the path of the API endpoint where the error occurred.
     * This represents the specific URI or route that triggered the error.
     *
     * @return a {@code String} containing the endpoint path associated with the error.
     */
    public String getPath() { return path; }

    /**
     * Retrieves the timestamp indicating when the error was recorded.
     * This timestamp represents the moment the ApiErrorResponse object
     * was created, providing a temporal reference for the occurrence of the error.
     *
     * @return a {@code LocalDateTime} instance representing the timestamp of the error.
     */
    public LocalDateTime getTimestamp() { return timestamp; }

    /**
     * Retrieves the map of validation errors associated with this API error response.
     * Each entry in the map represents a validation issue, where the key is the field name
     * and the value is the corresponding error message.
     *
     * @return a {@code Map<String, String>} containing the validation errors,
     *         with field names as keys and their respective error messages as values.
     */
    public Map<String, String> getErrors() { return errors; }
}

