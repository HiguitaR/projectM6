package com.higuitar.catalogoproductosflex.exception;

/**
 * Exception thrown to indicate that a product was not found.
 * This exception is typically used when attempting to retrieve or manipulate
 * a product that does not exist in the system.
 *
 * This custom exception extends {@link RuntimeException}, making it an unchecked exception.
 */
public class ProductNotFoundException extends RuntimeException{

    /**
     * Constructs a new {@code ProductNotFoundException} with a default message
     * indicating that the product does not exist.
     *
     * This constructor is typically used when a specific product cannot be found in the system,
     * such as during retrieval or update operations.
     */
    public ProductNotFoundException() {
        this("The Product doesn't exist!");
    }

    /**
     * Constructs a new {@code ProductNotFoundException} with a specified detail message.
     *
     * @param message the detail message that provides additional information about the exception.
     *                This message is typically used to describe the reason why the product was not found.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }


}
