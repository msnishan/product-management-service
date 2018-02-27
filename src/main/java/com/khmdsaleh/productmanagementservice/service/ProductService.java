package com.khmdsaleh.productmanagementservice.service;

import com.khmdsaleh.productmanagementservice.db.model.Product;
import com.khmdsaleh.productmanagementservice.db.model.ProductType;
import com.khmdsaleh.productmanagementservice.exception.BusinessException;
import com.khmdsaleh.productmanagementservice.model.ProductRequest;
import com.khmdsaleh.productmanagementservice.model.ProductResponse;
import com.khmdsaleh.productmanagementservice.repository.ProductRepository;
import com.khmdsaleh.productmanagementservice.utils.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = ObjectFactory.mapProductRequest(productRequest);
        return ObjectFactory.mapProductResponse(productRepository.saveAndFlush(product));
    }

    public List<ProductResponse> retrieveProductsByType(String productType) {
        List<Product> products = productRepository.findProductsByProductType(ProductType.fromValue(productType));
        return products.stream()
                .filter(Product::getActive)
                .map(ObjectFactory::mapProductResponse)
                .collect(Collectors.toList());
    }

    public boolean removeProduct(String productId, boolean deletePermanent) {
        if (productRepository.exists(Long.valueOf(productId))) {
            if (deletePermanent) {
                productRepository.delete(Long.valueOf(productId));
                return true;
            } else {
                Product product = productRepository.findOne(Long.valueOf(productId));
                product.setActive(false);
                productRepository.save(product);
                return true;
            }
        } else {
            throw new BusinessException("Product Not Found");
        }
    }
}
