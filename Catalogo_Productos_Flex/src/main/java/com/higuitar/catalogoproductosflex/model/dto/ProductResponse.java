package com.higuitar.catalogoproductosflex.model.dto;



import java.util.Map;
import java.util.Set;


/**
 * Represents the response model for a product.
 * This immutable record encapsulates essential product information
 * including its unique identifier, name, price, description,
 * specifications, and associated tags.
 * <p>
 * Attributes:
 * - `id`: Unique identifier of the product.
 * - `name`: Name of the product.
 * - `price`: Price of the product.
 * - `description`: Description providing details about the product.
 * - `specs`: Key-value map containing additional specifications of the product.
 * - `tags`: Set of tags associated with the product for categorization or search purposes.
 */
public record ProductResponse (

        String id,
        String name,
        Double price,
        String description,
        Map<String, Object> specs,
        Set<String> tags){}
