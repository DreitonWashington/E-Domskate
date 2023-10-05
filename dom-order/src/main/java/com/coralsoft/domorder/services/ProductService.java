package com.coralsoft.domorder.services;

import com.coralsoft.domorder.models.ProductModel;

import java.util.UUID;

public interface ProductService {

    ProductModel saveProduct(ProductModel productModel);

    ProductModel findProductById(UUID productId);

    void deleteProductById(UUID productId);
}
