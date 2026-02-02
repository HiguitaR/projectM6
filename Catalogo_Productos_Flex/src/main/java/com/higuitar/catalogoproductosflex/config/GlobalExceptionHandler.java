package com.higuitar.catalogoproductosflex.config;

import com.higuitar.catalogoproductosflex.exception.ProductAlreadyExistException;
import com.higuitar.catalogoproductosflex.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler is a centralized exception handling class that intercepts and processes
 * exceptions thrown by controller methods in the application. It provides custom responses
 * to specific exception types, ensuring consistency and clarity in API error responses.
 *
 * It uses Spring's @RestControllerAdvice to apply exception handling globally across all
 * controller classes.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type {@code ProductNotFoundException} and returns a standardized
     * error response indicating that the requested product was not found.
     *
     * @param ex the {@code ProductNotFoundException} instance containing details about the exception
     * @param request the current {@code HttpServletRequest} providing request-specific information
     * @return an {@code ApiErrorResponse} object containing the error code, message, and the path
     *         of the API endpoint where the exception occurred
     */
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleProductNotFound(ProductNotFoundException ex, HttpServletRequest request) {
        return new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    /**
     * Handles exceptions of type {@code ProductAlreadyExistException} and returns a standardized
     * error response indicating that the requested product already exists. This method is invoked
     * automatically when a {@code ProductAlreadyExistException} is thrown within the application.
     *
     * @param ex the {@code ProductAlreadyExistException} instance containing details about the exception
     * @param request the current {@code HttpServletRequest} providing request-specific information
     * @return an {@code ApiErrorResponse} object containing the error code, message, and the path
     *         of the API endpoint where the exception occurred
     */
    @ExceptionHandler(ProductAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleProductAlreadyExist(ProductAlreadyExistException ex, HttpServletRequest request) {
        return new ApiErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    /**
     * Handles exceptions of type {@code MethodArgumentNotValidException} and returns
     * a standardized error response. This method is invoked automatically when a
     * {@code MethodArgumentNotValidException} is thrown during request validation.
     *
     * @param ex the {@code MethodArgumentNotValidException} instance containing details
     *           about validation failures, including binding results with field errors
     * @param request the current {@code HttpServletRequest} providing information about the
     *                request that caused the validation exception
     * @return an {@code ApiErrorResponse} object containing the error code, message,
     *         the API endpoint path where the exception occurred, and a map of validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                request.getRequestURI(),
                errors
        );
    }

    /**
     * Handles general exceptions that are not explicitly caught by other exception handlers
     * and returns a standardized error response with an HTTP 500 status.
     *
     * @param ex the {@code Exception} instance containing details about the error that occurred
     * @param request the current {@code HttpServletRequest} providing information about the API request
     *                that caused the exception
     * @return an {@code ApiErrorResponse} object containing the error code, a generic error message,
     *         and the path of the API endpoint where the exception occurred
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleGeneral(Exception ex, HttpServletRequest request) {
        return new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                request.getRequestURI()
        );
    }

}