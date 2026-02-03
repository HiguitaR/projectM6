package com.higuitar.catalogoproductosflex.exception;

/**
 * Exception indicating that a product already exists.
 * This exception is typically thrown when attempting to add or save a product
 * that has the same name or identifier as an existing product in the system.
 *
 * This custom exception extends {@link RuntimeException}, making it an unchecked exception.
 */
public class ProductAlreadyExistException extends RuntimeException{

    /**
     * Exception thrown to indicate that a product with the same unique identifier
     * or name already exists in the system.
     * <p>
     * This exception is typically used in service methods when attempting to save
     * or add a product that conflicts with the existing data in the repository.
     * <p>
     * It extends {@link RuntimeException}, making it an unchecked exception.
     */
    public ProductAlreadyExistException() {

        super("The Product already exists!");
    }
}
