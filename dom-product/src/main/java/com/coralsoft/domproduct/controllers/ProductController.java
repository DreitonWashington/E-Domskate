package com.coralsoft.domproduct.controllers;

import com.coralsoft.domproduct.dtos.ProductModelDto;
import com.coralsoft.domproduct.models.ClothesModel;
import com.coralsoft.domproduct.models.ProductModel;
import com.coralsoft.domproduct.models.ShoesModel;
import com.coralsoft.domproduct.servicies.ProductService;
import com.coralsoft.domproduct.specifications.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody ProductModelDto productModelDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModelDto));
    }

    @GetMapping
    public ResponseEntity<Page<ProductModelDto>> getAllProducts(SpecificationTemplate.ProductSpec spec,Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(spec,pageable));
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable(value = "productId")UUID productId){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(productId));
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Object> deleteProductById(@PathVariable(value = "productId")UUID productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully!");
    }

    @PutMapping(value = "/{productId}/updateClothes")
    public ResponseEntity<Object> updateProductClothes(@PathVariable(value = "productId")UUID productId, @RequestBody ClothesModel clothesModel){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateClothes(productId, clothesModel));
    }

    @PutMapping(value = "/{productId}/updateShoes")
    public ResponseEntity<Object> updateProductShoes(@PathVariable(value = "productId")UUID productId, @RequestBody ShoesModel shoesModel){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateShoes(productId, shoesModel));
    }

}
