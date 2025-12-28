package com.higuitar.catalogoproductosflex.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "products")
public class Product {

    // Attributes
    @Id
    private String id; //must be String to sending mongoDB
    private String name;
    private Double price;
    private String description;
    private Map<String, Object> specs;
    private List<String> tags;

    // Constructors
    public Product() {}

    public Product(String id, String name, Double price, String description,
                   Map<String, Object> specs, List<String> tags) {
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
