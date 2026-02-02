package com.higuitar.catalogoproductosflex.service;

import com.higuitar.catalogoproductosflex.model.dto.ProductRequest;
import com.higuitar.catalogoproductosflex.model.dto.ProductResponse;

import java.util.List;
import java.util.Set;

/**
 * Service interface for managing products in the application.
 * Provides methods to create, retrieve, update, and delete products,
 * as well as advanced query support such as search and filtering by various criteria.
 *
 * Methods:
 *
 * productSave:
 *  - Saves a new product to the repository.
 *  - Takes a ProductRequest object containing product details.
 *  - Returns a ProductResponse object representing the saved product.
 *
 * getAll:
 *  - Retrieves all products from the repository.
 *  - Returns a list of ProductResponse objects representing all registered products.
 *
 * getById:
 *  - Fetches a specific product by its unique identifier.
 *  - Takes a String id as input.
 *  - Returns a ProductResponse object representing the requested product.
 *  - Throws an exception if the product does not exist.
 *
 * delete:
 *  - Deletes a product by its unique identifier.
 *  - Takes a String id as input.
 *  - Does not return any response.
 *  - Throws an exception if the product does not exist.
 *
 * searchByNameOrDescription:
 *  - Searches for products whose name or description matches the provided text.
 *  - Takes a String text as input.
 *  - Returns a list of ProductResponse objects matching the search criteria.
 *
 * findByTags:
 *  - Retrieves products associated with any of the specified tags.
 *  - Takes a Set of Strings representing tags as input.
 *  - Returns a list of ProductResponse objects matching the tags.
 *
 * findBySpec:
 *  - Searches for products based on a specific key-value pair in their specifications.
 *  - Takes a key as a String and a value as an Object.
 *  - Returns a list of ProductResponse objects matching the specification.
 *  - Throws an IllegalArgumentException if the key is null or empty.
 *
 * update:
 *  - Updates an existing product with new details.
 *  - Takes a String id and a ProductRequest object with updated details.
 *  - Returns a ProductResponse object representing the updated product.
 *  - Throws an exception if the product does not exist.
 */
public interface ProductService {

    /**
     * Saves a new product based on the provided product details and returns the saved product's information.
     *
     * @param product the product data encapsulated within a {@code ProductRequest} object,
     *                including attributes such as name, price, description, specifications, and tags.
     * @return a {@code ProductResponse} object containing details of the saved product,
     *         such as its unique identifier, name, price, description, specifications, and tags.
     */
    ProductResponse productSave(ProductRequest product);

    /**
     * Retrieves all products currently stored in the system.
     *
     * @return a list of {@code ProductResponse} objects, each containing details
     *         such as the product's unique identifier, name, price, description,
     *         specifications, and categorization tags.
     */
    List<ProductResponse> getAll();

    /**
     * Retrieves the product details for a given unique identifier.
     *
     * @param id the unique identifier of the product to be retrieved.
     * @return a {@code ProductResponse} object containing details of the product,
     *         such as its unique identifier, name, price, description, specifications, and tags.
     * @throws ProductNotFoundException if no product is found for the given identifier.
     */
    ProductResponse getById(String id);

    /**
     * Deletes a product from the system using its unique identifier.
     *
     * @param id the unique identifier of the product to be deleted.
     *           If no product is found with the provided identifier, a {@code ProductNotFoundException} is thrown.
     */
    void delete(String id);

    /**
     * Searches for products whose name or description contains the specified text (case-insensitive).
     *
     * @param text the text to search for within the name or description of the products.
     *             If the text is null or empty, no results will be found.
     * @return a list of {@code ProductResponse} objects representing the products
     *         that match the search criteria. Each response includes details such as
     *         the product's unique identifier, name, description, price, specifications, and categorization tags.
     */
    List<ProductResponse> searchByNameOrDescription(String text);

    /**
     * Retrieves a list of products that match the specified set of tags.
     * The method filters products based on whether their categorization tags
     * intersect with the given set of tags.
     *
     * @param tags a set of tags used to filter the products. Each product
     *             returned will contain at least one tag from this set
     *             in its categorization tags. If the provided set is null
     *             or empty, no products will be returned.
     * @return a list of {@code ProductResponse} objects representing the
     *         products that match the specified tags. Each response includes
     *         details such as the product's unique identifier, name, price,
     *         description, specifications, and tags.
     */
    List<ProductResponse> findByTags(Set<String> tags);

    /**
     * Finds products by a specific key-value pair from their specifications.
     * This method filters products based on the key and value provided,
     * matching them against the products' specifications.
     *
     * @param key the key within the product specifications to search for.
     *            This could represent attributes like "color", "brand", "size", etc.
     * @param value the corresponding value to match for the specified key.
     *              The value can be of any type, such as String, Integer, etc.
     * @return a list of {@code ProductResponse} objects representing products
     *         that match the specified key-value pair in their specifications.
     *         Each response includes details such as the product's unique identifier,
     *         name, price, description, specifications, and tags.
     */
    List<ProductResponse> findBySpec(String key, Object value);

    /**
     * Updates an existing product identified by its unique identifier with the new details
     * provided in the request. The update operation replaces the product's current
     * attributes with the ones specified in the provided request.
     *
     * @param id the unique identifier of the product to be updated.
     *           If no product is found with the specified identifier, an appropriate
     *           exception may be thrown by the implementing method.
     * @param request a {@code ProductRequest} object containing the updated product details,
     *                including attributes such as name, price, description, specifications,
     *                and tags.
     * @return a {@code ProductResponse} object representing the updated product and
     *         containing details such as its unique identifier, name, price, description,
     *         specifications, and tags. If the update operation fails, an
     *         appropriate exception may be thrown depending on the implementation.
     */
    ProductResponse update(String id, ProductRequest request);
}
