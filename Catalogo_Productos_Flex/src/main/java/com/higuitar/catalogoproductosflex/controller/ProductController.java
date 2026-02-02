package com.higuitar.catalogoproductosflex.controller;

import com.higuitar.catalogoproductosflex.model.dto.ProductRequest;
import com.higuitar.catalogoproductosflex.model.dto.ProductResponse;
import com.higuitar.catalogoproductosflex.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return productService.getAll();
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct
            (@Valid @RequestBody ProductRequest request) {
        ProductResponse created = productService.productSave(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody ProductRequest request
    ) {
        ProductResponse updated = productService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchByNameOrDescription(
            @RequestParam("q") @NotBlank(message = "q must not be blank") String text
    ) {
        return ResponseEntity.ok(productService.searchByNameOrDescription(text.trim()));
    }

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
