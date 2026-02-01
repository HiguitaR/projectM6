package com.higuitar.catalogoproductosflex.repository;

import com.higuitar.catalogoproductosflex.model.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends MongoRepository<Product, String> {

    boolean existByProductName(String name);
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase
            (String name, String description);
    List<Product> findByTagsIn(Set<String> tags);

    @Query("{ 'specs.?0': ?1 }")
    List<Product> findBySpecField(String key, Object value);
}
