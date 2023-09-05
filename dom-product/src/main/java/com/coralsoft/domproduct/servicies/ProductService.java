package com.coralsoft.domproduct.servicies;

import com.coralsoft.domproduct.dtos.ProductModelDto;
import com.coralsoft.domproduct.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductModel save(ProductModelDto productModelDto);

    Page<ProductModelDto> findAll(Pageable pageable);
}
