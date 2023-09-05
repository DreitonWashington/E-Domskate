package com.coralsoft.domproduct.servicies;

import com.coralsoft.domproduct.dtos.SizeModelDto;
import com.coralsoft.domproduct.dtos.SizeProductDto;
import com.coralsoft.domproduct.models.SizeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SizeService {
    SizeModel save(SizeModelDto sizeModelDto);

    Page<SizeModel> findAll(Pageable pageable);

    Object findById(UUID sizeId);

    List<SizeModel> findAllSizeByProductId(UUID productId);
}
