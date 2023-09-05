package com.coralsoft.domproduct.servicies;

import com.coralsoft.domproduct.dtos.BrandModelDto;
import com.coralsoft.domproduct.models.BrandModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface BrandService {
    Page<BrandModel> findAll(Pageable pageable);

    BrandModel save(BrandModelDto brandModelDto);

    Optional<BrandModel> findById(UUID brandId);

    void delete(BrandModel brandModel);

    BrandModel update(BrandModel brandModel);
}
