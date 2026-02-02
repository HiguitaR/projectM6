package com.higuitar.catalogoproductosflex.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Represents a request payload for creating or updating a product.
 * This class contains the attributes and the corresponding validation constraints
 * necessary to define a product in the system.
 */
@Schema(
        name = "ProductRequest",
        description = "Payload used to create or update a product."
)
public class ProductRequest {

    // Attributes and Validations
    @Schema(
            description = "Product name.",
            example = "Fender Stratocaster",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "The name is required")
    private String name;

    @Schema(
            description = "Product price. Must be greater than or equal to 1.0.",
            example = "1299.99",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "The price is required")
    @DecimalMin(value = "1.0", message = "The price must no be less than 1.0")
    private Double price;

    @Schema(
            description = "Product description.",
            example = "Electric guitar with alder body and maple neck.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "The description is required")
    private String description;

    @Schema(
            description = "Flexible key/value specifications for the product (e.g., color, brand, model, condition).",
            example = "{\"color\":\"black\",\"brand\":\"Fender\",\"strings\":6,\"condition\":\"new\"}",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = "This field is required")
    @Valid
    private Map<@NotBlank(message = "This field is required")String, Object> specs;

    @Schema(
            description = "Tags used for filtering and categorization.",
            example = "[\"electric\",\"guitar\",\"rock\"]",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = "This field is required")
    @Valid
    private Set<@NotBlank(message = "This field is required")String> tags;

    // Setters and Getters
    public  String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getSpecs() {
        return specs;
    }

    public void setSpecs(Map<String, Object> specs) {
        this.specs = specs;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
