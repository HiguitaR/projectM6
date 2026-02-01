package com.higuitar.catalogoproductosflex.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProductRequest {

    // Attributes and Validations
    @NotBlank(message = "The name is required")
    private String name;
    @NotNull(message = "The price is required")
    @DecimalMin(value = "1.0", message = "The price must no be less than 1.0")
    private Double price;
    @NotBlank(message = "The description is required")
    private String description;
    @NotEmpty(message = "This field is required")
    @Valid
    private Map<@NotBlank(message = "This field is required")String, Object> specs;
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
