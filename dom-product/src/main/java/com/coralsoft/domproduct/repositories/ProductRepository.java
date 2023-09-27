package com.coralsoft.domproduct.repositories;

import com.coralsoft.domproduct.dtos.ProductModelDto;
import com.coralsoft.domproduct.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID>, JpaSpecificationExecutor<ProductModel> {

    @EntityGraph(attributePaths = {"sizes","images"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<ProductModel> findById(UUID productId);

    @EntityGraph(attributePaths = {"sizes","images"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<ProductModel> findAll(Specification<ProductModel> spec, Pageable pageable);
}
