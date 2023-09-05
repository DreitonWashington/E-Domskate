package com.coralsoft.domproduct.repositories;

import com.coralsoft.domproduct.models.SizeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SizeRepository extends JpaRepository<SizeModel, UUID> {

    @Query(value = "select tb_sizes.id,tb_sizes.quantity,tb_sizes.size from tb_sizes inner join tb_products_sizes on tb_sizes.id = tb_products_sizes.sizes_id where product_model_id = :productId", nativeQuery = true)
    List<SizeModel> findAllByProductId(UUID productId);
}
