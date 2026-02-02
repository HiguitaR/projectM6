package com.higuitar.catalogoproductosflex.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import java.util.Set;

/**
 * Represents a response payload for a product returned by the API.
 * This record contains the attributes that define the basic details
 * of a product along with its specifications and categorization tags.
 *
 * Attributes:
 * - id: The unique identifier of the product.
 * - name: The name of the product.
 * - price: The price of the product.
 * - description: A detailed description of the product.
 * - specs: A map of key-value pairs providing flexible specifications for the product.
 * - tags: A set of tags used for filtering and categorizing the product.
 */
@Schema(
        name = "ProductResponse",
        description = "Product representation returned by the API."
)
public record ProductResponse (
        @Schema(
                description = "Unique product identifier.",
                example = "65f1c2a3b4c5d6e7f8a9b0c1"
        )
        String id,

        @Schema(
                description = "Product name.",
                example = "Fender Stratocaster"
        )
        String name,

        @Schema(
                description = "Product price.",
                example = "1299.99"
        )
        Double price,

        @Schema(
                description = "Product description.",
                example = "Electric guitar with alder body and maple neck."
        )
        String description,

        @Schema(
                description = "Flexible key/value specifications for the product.",
                example = "{\"color\":\"black\",\"brand\":\"Fender\",\"strings\":6,\"condition\":\"new\"}"
        )
        Map<String, Object> specs,

        @Schema(
                description = "Tags used for filtering and categorization.",
                example = "[\"electric\",\"guitar\",\"rock\"]"
        )
        Set<String> tags){}
