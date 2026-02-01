package com.higuitar.catalogoproductosflex.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException() {
        this("The Product doesn't exist!");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }


}
