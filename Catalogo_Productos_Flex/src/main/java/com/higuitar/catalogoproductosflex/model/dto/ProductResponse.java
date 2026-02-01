package com.higuitar.catalogoproductosflex.model.dto;


import java.util.Map;
import java.util.Set;

public record ProductResponse (String name, Double price, String description,
                               Map<String, Object> specs, Set<String> tags){}
