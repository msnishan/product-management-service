package com.khmdsaleh.productmanagementservice.controller;

import com.khmdsaleh.productmanagementservice.model.ProductRequest;
import com.khmdsaleh.productmanagementservice.model.ProductResponse;
import com.khmdsaleh.productmanagementservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse persistProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/products/{product_type}")
    public List<ProductResponse> getProductsByType(@PathVariable("product_type") String productType) {
        return productService.retrieveProductsByType(productType);
    }

    @DeleteMapping("/products/{product_id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("product_id") String productId,
                               @RequestParam(value = "delete_permanent", required = false) boolean deletePermanent) {
        productService.removeProduct(productId, deletePermanent);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

}
