package com.khmdsaleh.productmanagementservice.repository;

import com.khmdsaleh.productmanagementservice.db.model.Product;
import com.khmdsaleh.productmanagementservice.db.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByProductType(ProductType productType);
}
