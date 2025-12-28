package com.higuitar.catalogoproductosflex.repository;

import com.higuitar.catalogoproductosflex.model.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
