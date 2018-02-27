package com.khmdsaleh.productmanagementservice.model;

import com.khmdsaleh.productmanagementservice.db.model.ProductType;

public class ProductRequest {
    private String productName;
    private ProductType productType;
    private String productDescription;
    private String createdOrUpdatedBy;

    public ProductRequest() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCreatedOrUpdatedBy() {
        return createdOrUpdatedBy;
    }

    public void setCreatedOrUpdatedBy(String createdOrUpdatedBy) {
        this.createdOrUpdatedBy = createdOrUpdatedBy;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", createdOrUpdatedBy='" + createdOrUpdatedBy + '\'' +
                '}';
    }
}
