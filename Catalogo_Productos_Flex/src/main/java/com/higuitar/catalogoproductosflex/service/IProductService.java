package com.higuitar.catalogoproductosflex.service;

import com.higuitar.catalogoproductosflex.exception.ProductAlreadyExistException;
import com.higuitar.catalogoproductosflex.exception.ProductNotFoundException;
import com.higuitar.catalogoproductosflex.mapper.ProductMapper;
import com.higuitar.catalogoproductosflex.model.document.Product;
import com.higuitar.catalogoproductosflex.model.dto.ProductRequest;
import com.higuitar.catalogoproductosflex.model.dto.ProductResponse;
import com.higuitar.catalogoproductosflex.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class IProductService implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public IProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse productSave(ProductRequest product) {

        if(productRepository.existByProductName(product.getName())){
            throw new ProductAlreadyExistException();
        }
        var document = productMapper.toRequest(product);
        document = productRepository.save(document);
        return productMapper.toResponse(document);
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toResponse)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void delete(String id) {
        if(productRepository.existsById(id)){
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> searchByNameOrDescription(String text) {
        return  productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase
                        (text, text)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> findByTags(Set<String> tags) {
        return productRepository.findByTagsIn(tags)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> findBySpec(String key, Object value) {
        if(key == null || key.isBlank()){
            throw new IllegalArgumentException("The Key cannot be null or empty!");
        }
        return productRepository.findBySpecField(key, value)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse update(String id, ProductRequest request) {
        Product existing = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        existing.setName(request.getName());
        existing.setPrice(request.getPrice());
        existing.setDescription(request.getDescription());
        existing.setSpecs(request.getSpecs());
        existing.setTags(request.getTags());
        Product updated = productRepository.save(existing);
        return productMapper.toResponse(updated);
    }

}
