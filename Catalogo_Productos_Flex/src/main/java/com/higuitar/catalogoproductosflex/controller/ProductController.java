package com.higuitar.catalogoproductosflex.controller;

import com.higuitar.catalogoproductosflex.model.dto.ProductRequest;
import com.higuitar.catalogoproductosflex.model.dto.ProductResponse;
import com.higuitar.catalogoproductosflex.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * Controller for managing products in the system. Provides API endpoints for
 * listing, creating, updating, deleting, and searching products, as well as
 * filtering by tags or specifications.
 *
 * Annotations:
 * - @Validated: Enables validation on input data.
 * - @RestController: Marks the class as a REST controller.
 * - @RequestMapping("/api/product"): Maps requests to paths starting with "/api/product".
 * - @Tag: Provides OpenAPI metadata for the "Products" resource.
 */
@Validated
@RestController
@RequestMapping("/api/product")
@Tag(
        name = "Products",
        description = "Product management API: CRUD operations and search endpoints " +
                "(text, tags, and specifications).")
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
    @Operation(
            summary = "List products",
            description = "Returns all registered products."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of products",
                    content = @Content(array = @ArraySchema(schema =
                    @Schema(implementation = ProductResponse.class)))
            )
    })
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
    @Operation(
            summary = "Create product",
            description = "Creates a new product and returns the created resource."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Product created",
                    content = @Content(schema = @Schema(implementation =
                            ProductResponse.class))
            ),
            @ApiResponse(responseCode = "400", description =
                    "Validation failed", content = @Content)
    })
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
    @Operation(
            summary = "Update product",
            description = "Updates an existing product by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product updated",
                    content = @Content(schema = @Schema(implementation =
                            ProductResponse.class))
            ),
            @ApiResponse(responseCode = "400", description =
                    "Validation failed", content = @Content),
            @ApiResponse(responseCode = "404", description =
                    "Product not found", content = @Content)
    })
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
    @Operation(
            summary = "Delete product",
            description = "Deletes a product by its ID. Returns 204 when deleted successfully."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
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
    @Operation(
            summary = "Search by name or description",
            description = "Searches for products that contain the provided " +
                    "text in the name or description."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Search results",
                    content = @Content(array = @ArraySchema(schema = @Schema
                            (implementation = ProductResponse.class)))
            ),
            @ApiResponse(responseCode = "400", description =
                    "Invalid query parameter 'q'", content = @Content)
    })
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
    @Operation(
            summary = "Filter by tags",
            description = "Returns products matching the provided tags."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Results filtered by tags",
                    content = @Content(array = @ArraySchema(schema =
                    @Schema(implementation = ProductResponse.class)))
            ),
            @ApiResponse(responseCode = "400", description =
                    "Invalid 'tags' parameter", content = @Content)
    })
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
    @Operation(
            summary = "Filter by specification",
            description = "Filters products by a specification key/value " +
                    "pair (e.g., key=color, value=black)."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Results filtered by specification",
                    content = @Content(array = @ArraySchema(schema =
                    @Schema(implementation = ProductResponse.class)))
            ),
            @ApiResponse(responseCode = "400", description =
                    "Invalid 'key'/'value' parameters", content = @Content)
    })
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
