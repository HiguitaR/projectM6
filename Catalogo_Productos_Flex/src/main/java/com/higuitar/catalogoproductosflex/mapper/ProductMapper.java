package com.higuitar.catalogoproductosflex.mapper;


import com.higuitar.catalogoproductosflex.model.document.Product;
import com.higuitar.catalogoproductosflex.model.dto.ProductRequest;
import com.higuitar.catalogoproductosflex.model.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toRequest(ProductRequest request){

        var response = new Product();
        response.setName(request.getName());
        response.setPrice(request.getPrice());
        response.setDescription(request.getDescription());
        response.setSpecs(request.getSpecs());
        response.setTags(request.getTags());
        return response;
    }

    public ProductResponse toResponse(Product product){

        return new ProductResponse(
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getSpecs(),
                product.getTags()
        );
    }
}
