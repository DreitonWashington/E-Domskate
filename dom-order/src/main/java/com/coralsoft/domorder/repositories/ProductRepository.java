package com.coralsoft.domorder.repositories;

import com.coralsoft.domorder.dtos.ProductDto;
import com.coralsoft.domorder.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

}
