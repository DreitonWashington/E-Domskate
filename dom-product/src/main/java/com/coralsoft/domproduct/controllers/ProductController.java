package com.coralsoft.domproduct.controllers;

import com.coralsoft.domproduct.dtos.ProductModelDto;
import com.coralsoft.domproduct.models.ProductModel;
import com.coralsoft.domproduct.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    public ResponseEntity<Page<ProductModelDto>> getAllProducts(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(pageable));
    }

}
