package com.khmdsaleh.productmanagementservice.utils;

import com.khmdsaleh.productmanagementservice.db.model.Product;
import com.khmdsaleh.productmanagementservice.db.model.ProductType;
import com.khmdsaleh.productmanagementservice.model.ProductRequest;
import com.khmdsaleh.productmanagementservice.model.ProductResponse;

import java.time.LocalDate;

public final class ObjectFactory {

    private ObjectFactory() {
    }

    public static Product mapProductRequest(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductType(productRequest.getProductType());
        product.setProductDescription(productRequest.getProductDescription());
        product.setCreatedBy(productRequest.getCreatedOrUpdatedBy());
        product.setCreateDate(LocalDate.now());
        product.setActive(true);
        return product;
    }

    public static ProductResponse mapProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(String.valueOf(product.getProductId()));
        productResponse.setProductName(product.getProductName());
        productResponse.setProductType(product.getProductType().value());
        productResponse.setProductDescription(product.getProductDescription());
        return productResponse;
    }
}
