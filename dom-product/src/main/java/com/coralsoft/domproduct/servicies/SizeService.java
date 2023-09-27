package com.coralsoft.domproduct.servicies;

import com.coralsoft.domproduct.dtos.SizeModelDto;
import com.coralsoft.domproduct.models.SizeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface SizeService {
    SizeModel save(SizeModelDto sizeModelDto);

    Page<SizeModel> findAll(Pageable pageable);

    Object findById(UUID sizeId);

    Set<SizeModel> findAllSizeByProductId(UUID productId);

    void deleteById(UUID sizeId);

    void deleteSizes(Set<SizeModel> sizes);
}
