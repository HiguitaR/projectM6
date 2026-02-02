package com.higuitar.catalogoproductosflex.repository;

import com.higuitar.catalogoproductosflex.model.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;
import java.util.Set;

/**
 * Repository interface for performing CRUD operations and custom queries on the Product collection in MongoDB.
 * Extends the {@code MongoRepository} interface to inherit basic data access functionalities.
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    /**
     * Checks whether a product with the specified name exists in the repository.
     *
     * @param name the name of the product to check for existence
     * @return {@code true} if a product with the given name exists, {@code false} otherwise
     */
    boolean existsByName(String name);

    /**
     * Finds a list of products where the name contains the given name fragment (case-insensitive),
     * or the description contains the given description fragment (case-insensitive).
     *
     * @param name the fragment of the product name to search for; case-insensitive
     * @param description the fragment of the product description to search for; case-insensitive
     * @return a list of products matching the search criteria
     */
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase
            (String name, String description);

    /**
     * Finds a list of products whose associated tags match any of the tags in the given set.
     *
     * @param tags a set of tags to match against the product tags
     * @return a list of products that have at least one tag matching one of the input tags
     */
    List<Product> findByTagsIn(Set<String> tags);

    /**
     * Finds a list of products where the specified key in the "specs" field matches the given value.
     *
     * @param key the key in the "specs" field to search for
     * @param value the value to match against the specified key in the "specs" field
     * @return a list of products that have the specified key-value pair in their "specs" field
     */
    @Query("{ 'specs.?0': ?1 }")
    List<Product> findBySpecField(String key, Object value);
}
