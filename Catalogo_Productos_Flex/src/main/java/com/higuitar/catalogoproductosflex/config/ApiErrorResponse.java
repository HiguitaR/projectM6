package com.higuitar.catalogoproductosflex.config;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiErrorResponse {
    private final Integer code;
    private final String message;
    private final String path;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private Map<String, String> errors;

    public ApiErrorResponse(Integer code, String message, String path) {
        this.code = code;
        this.message = message;
        this.path = path;
    }

    public ApiErrorResponse(Integer code, String message, String path, Map<String, String> errors) {
        this(code, message, path);
        this.errors = errors;
    }

    // Getters
    public Integer getCode() { return code; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public Map<String, String> getErrors() { return errors; }
}

