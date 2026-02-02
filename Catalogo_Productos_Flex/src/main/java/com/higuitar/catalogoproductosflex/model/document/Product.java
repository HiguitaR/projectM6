package com.higuitar.catalogoproductosflex.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;
import java.util.Set;

/**
 * Represents a product entity within the system.
 * This class is mapped to the "products" collection in the database.
 * It contains attributes that define a product, such as its ID, name, price, description,
 * specifications, and associated tags.
 */
@Document(collection = "products")
public class Product {

    // Attributes
    @Id
    private String id; //must be String to sending mongoDB
    private String name;
    private Double price;
    private String description;
    @Field("especificaciones")
    private Map<String, Object> specs;
    private Set<String> tags;

    // Constructors
    public Product() {}

    public Product(String id, String name, Double price, String description,
                   Map<String, Object> specs, Set<String> tags) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.specs = specs;
        this.tags = tags;
    }

    // Setters and Getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
