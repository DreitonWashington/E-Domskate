package com.coralsoft.domproduct.repositories;

import com.coralsoft.domproduct.models.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, UUID> {

    @Query(value = "select tb_images.id, tb_images.url from tb_images inner join tb_products_images on tb_images.id = tb_products_images.images_id where product_model_id = :productId", nativeQuery = true)
    Set<ImageModel> findAllImagesByProductId(UUID productId);
}
