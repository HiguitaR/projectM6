package com.higuitar.catalogoproductosflex.exception;

public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException() {

        super("The Product already exists!");
    }
}
