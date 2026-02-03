package com.higuitar.catalogoproductosflex.controller;

import com.higuitar.catalogoproductosflex.model.dto.ProductRequest;
import com.higuitar.catalogoproductosflex.model.dto.ProductResponse;
import com.higuitar.catalogoproductosflex.service.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * REST controller for managing product-related operations.
 * Handles operations such as retrieving, creating, updating, deleting,
 * and searching for products based on various criteria.
 */
@Validated
@RestController
@RequestMapping("/api/product")

public class ProductController {

    private final ProductService productService;

    /**
     * Constructs a ProductController object with the specified ProductService.
     *
     * @param productService the service used to manage product-related operations
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Retrieves a list of all registered products.
     *
     * @return a list of {@code ProductResponse} objects representing the registered products.
     */

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return productService.getAll();
    }


    /**
     * Creates a new product based on the provided request and returns the created product resource.
     *
     * @param request the product request object containing the details of the product to be created
     * @return a ResponseEntity containing the created product resource and HTTP status code 201 (Created)
     */

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct
            (@Valid @RequestBody ProductRequest request) {
        ProductResponse created = productService.productSave(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    /**
     * Updates an existing product by its ID.
     *
     * @param id the unique identifier of the product to update
     * @param request the product update request containing the updated fields
     * @return a ResponseEntity containing the updated product details on success
     */

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody ProductRequest request
    ) {
        ProductResponse updated = productService.update(id, request);
        return ResponseEntity.ok(updated);
    }


    /**
     * Deletes a product identified by its unique ID.
     *
     * @param id The unique identifier of the product to be deleted.
     * @return A ResponseEntity with no content if the deletion is successful (HTTP 204),
     *         or an appropriate error response (e.g., HTTP 404 if the product is not found).
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }


    /**
     * Searches for products that match the provided text in either the name or description.
     * This method trims the input text and performs a case-insensitive search on the product data.
     * It returns a list of matching products wrapped in a ResponseEntity object.
     *
     * @param text A non-blank string representing the search text to look for in product names or descriptions.
     *             The parameter must not be null or empty, and any leading/trailing spaces will be trimmed.
     * @return A ResponseEntity containing a list of ProductResponse objects representing the search results.
     *         If no products match the criteria, an empty list is returned.
     */

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchByNameOrDescription(
            @RequestParam("q") @NotBlank(message = "q must not be blank") String text
    ) {
        return ResponseEntity.ok(productService.searchByNameOrDescription(text.trim()));
    }


    /**
     * Finds and returns a list of products that match the given set of tags.
     *
     * @param tags a set of tags used to filter the products. Each tag must
     *             be a non-empty and non-blank string.
     * @return a ResponseEntity containing a list of products wrapped in
     *         ProductResponse objects that match the provided tags.
     */

    @GetMapping("/tags")
    public ResponseEntity<List<ProductResponse>> findByTags(
            @RequestParam("tags")
            @NotEmpty(message = "tags must not be empty")
            Set<@NotBlank(message = "tag must not be blank") String> tags
    ) {
        Set<String> normalizedTags = tags.stream()
                .map(String::trim)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(productService.findByTags(normalizedTags));
    }


    /**
     * Filters products based on a specified key/value pair, such as a specific
     * attribute and value combination (e.g., key=color, value=black).
     *
     * @param key   the specification key used for filtering products; must not be blank
     * @param value the specification value used for filtering products; must not be blank
     * @return a ResponseEntity containing a list of products that match the given specification key/value pair
     */

    @GetMapping("/spec")
    public ResponseEntity<List<ProductResponse>> findBySpec(
            @RequestParam("key") @NotBlank(message = "key must not be blank") String key,
            @RequestParam("value") @NotBlank(message = "value must not be blank") String value
    ) {

        String normalizedKey = key.trim();
        String normalizedValue = value.trim();
        return ResponseEntity.ok(productService.findBySpec(normalizedKey, normalizedValue));
    }
}
