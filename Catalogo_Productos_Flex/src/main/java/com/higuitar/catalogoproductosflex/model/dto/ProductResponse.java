package com.higuitar.catalogoproductosflex.model.dto;

import java.util.List;
import java.util.Map;

public class ProductResponse {

    //attributes
    private String name;
    private Double price;
    private String description;
    private Map<String, Object> specs;
    private List<String> tags;

    //Setters and Getters

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
