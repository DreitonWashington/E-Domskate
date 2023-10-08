package com.coralsoft.domproduct.servicies;

import com.coralsoft.domproduct.dtos.ProductModelDto;
import com.coralsoft.domproduct.models.ClothesModel;
import com.coralsoft.domproduct.models.ProductModel;
import com.coralsoft.domproduct.models.ShoesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    ProductModel save(ProductModelDto productModelDto);

    Page<ProductModelDto> findAll(Specification<ProductModel> spec, Pageable pageable);

    Optional<ProductModel> findById(UUID productId);

    void deleteProductById(UUID productId);

    ClothesModel updateClothes(UUID productId, ClothesModel clothesModel);

    ShoesModel updateShoes(UUID productId, ShoesModel shoesModel);

}
