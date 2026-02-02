package com.higuitar.catalogoproductosflex.mapper;


import com.higuitar.catalogoproductosflex.model.document.Product;
import com.higuitar.catalogoproductosflex.model.dto.ProductRequest;
import com.higuitar.catalogoproductosflex.model.dto.ProductResponse;
import org.springframework.stereotype.Component;

/**
 * A component responsible for mapping between data transfer objects (DTOs)
 * and the Product entity used in persistence operations.
 */
@Component
public class ProductMapper {

    /**
     * Maps a {@link ProductRequest} object to a {@link Product} entity.
     *
     * @param request the {@link ProductRequest} object containing product details.
     * @return a {@link Product} entity populated with the data from the provided {@link ProductRequest}.
     */
    public Product toRequest(ProductRequest request){

        var response = new Product();
        response.setName(request.getName());
        response.setPrice(request.getPrice());
        response.setDescription(request.getDescription());
        response.setSpecs(request.getSpecs());
        response.setTags(request.getTags());
        return response;
    }

    /**
     * Converts a {@link Product} entity into a {@link ProductResponse} object.
     *
     * @param product the {@link Product} entity to be converted.
     * @return a {@link ProductResponse} object containing the mapped data.
     */
    public ProductResponse toResponse(Product product){

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getSpecs(),
                product.getTags()
        );
    }
}
