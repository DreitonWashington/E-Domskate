package com.coralsoft.domorder.services.impl;

import com.coralsoft.domorder.exceptions.ProductNotFoundException;
import com.coralsoft.domorder.models.ProductModel;
import com.coralsoft.domorder.repositories.ProductRepository;
import com.coralsoft.domorder.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    final
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    @Override
    public ProductModel saveProduct(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    @Override
    public ProductModel findProductById(UUID productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Override
    public void deleteProductById(UUID productId) {
        this.findProductById(productId);
        productRepository.deleteById(productId);
    }
}
