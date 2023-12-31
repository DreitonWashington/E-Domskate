package com.coralsoft.domproduct.repositories;

import com.coralsoft.domproduct.models.SizeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface SizeRepository extends JpaRepository<SizeModel, UUID> {

    @Query(value = "select tb_sizes.id,tb_sizes.quantity,tb_sizes.size from tb_sizes inner join tb_products_sizes on tb_sizes.id = tb_products_sizes.sizes_id where product_model_id = :productId", nativeQuery = true)
    Set<SizeModel> findAllByProductId(UUID productId);

    @Modifying
    @Query(value = "delete from tb_products_sizes where sizes_id = :sizeId", nativeQuery = true)
    void deleteRelationSizeProductBySizeId(UUID sizeId);
}
