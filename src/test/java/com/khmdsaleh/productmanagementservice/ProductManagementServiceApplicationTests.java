package com.khmdsaleh.productmanagementservice;

import com.khmdsaleh.productmanagementservice.db.model.ProductType;
import com.khmdsaleh.productmanagementservice.model.ProductRequest;
import com.khmdsaleh.productmanagementservice.model.ProductResponse;
import com.khmdsaleh.productmanagementservice.repository.ProductRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ProductManagementServiceApplication.class
)
@AutoConfigureMockMvc
public class ProductManagementServiceApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("Test Product");
        productRequest.setProductType(ProductType.CLOTHING);
        productRequest.setProductDescription("Test Product Description");
        productRequest.setCreatedOrUpdatedBy("Test User");

        ResponseEntity<ProductResponse> productResponse = testRestTemplate.postForEntity("/products",
                productRequest, ProductResponse.class);
        ProductResponse responseBody = productResponse.getBody();

        assertEquals(HttpStatus.CREATED, productResponse.getStatusCode());
        assertEquals("Test Product", responseBody.getProductName());
        assertEquals("clothing", responseBody.getProductType());
    }

    @Test
    public void retrieve() {
        saveProducts(ProductType.MOBILE);
        ResponseEntity<List<ProductResponse>> productResponse = testRestTemplate.exchange("/products/mobile",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductResponse>>() {
                });
        List<ProductResponse> responseList = productResponse.getBody();
        assertEquals(2, responseList.size());
    }

    @Test
    public void delete() {
        saveProducts(ProductType.MOBILE);
        testRestTemplate.delete("/products/1");
        testRestTemplate.delete("/products/2");
        ResponseEntity<List<ProductResponse>> productResponse = testRestTemplate.exchange("/products/mobile",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductResponse>>() {
                });
        List<ProductResponse> responseList = productResponse.getBody();
        assertEquals(0, responseList.size());
    }

    @After
    public void clearData() {
        productRepository.deleteAll();
    }

    private void saveProducts(ProductType productType) {

        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("Test Product");
        productRequest.setProductType(productType);
        productRequest.setProductDescription("Test Product Description");
        productRequest.setCreatedOrUpdatedBy("Test User");

        ProductRequest productRequest2 = new ProductRequest();
        productRequest2.setProductName("Test Product 1");
        productRequest2.setProductType(productType);
        productRequest2.setProductDescription("Test Product Description 1");
        productRequest2.setCreatedOrUpdatedBy("Test User 1");

        testRestTemplate.postForEntity("/products",
                productRequest, ProductResponse.class);
        testRestTemplate.postForEntity("/products",
                productRequest2, ProductResponse.class);
    }

}
