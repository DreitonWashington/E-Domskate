package com.coralsoft.domproduct.repositories;

import com.coralsoft.domproduct.models.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<BrandModel, UUID> {
}
