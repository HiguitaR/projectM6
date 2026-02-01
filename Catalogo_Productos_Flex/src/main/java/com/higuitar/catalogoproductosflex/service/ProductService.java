package com.higuitar.catalogoproductosflex.service;

import com.higuitar.catalogoproductosflex.model.dto.ProductRequest;
import com.higuitar.catalogoproductosflex.model.dto.ProductResponse;

import java.util.List;
import java.util.Set;

public interface ProductService {

    ProductResponse productSave(ProductRequest product);
    List<ProductResponse> getAll();
    ProductResponse getById(String id);
    void delete(String id);
    List<ProductResponse> searchByNameOrDescription(String text);
    List<ProductResponse> findByTags(Set<String> tags);
    List<ProductResponse> findBySpec(String key, Object value);
    ProductResponse update(String id, ProductRequest request);
}
