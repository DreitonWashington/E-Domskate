package com.coralsoft.domproduct.servicies.impl;

import com.coralsoft.domproduct.dtos.SizeModelDto;
import com.coralsoft.domproduct.exceptions.SizeNotFoundException;
import com.coralsoft.domproduct.models.SizeModel;
import com.coralsoft.domproduct.repositories.SizeRepository;
import com.coralsoft.domproduct.servicies.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepository sizeRepository;

    @Override
    public SizeModel save(SizeModelDto sizeModelDto) {
        var sizeModel = new SizeModel();
        sizeModel.setSize(sizeModelDto.getSize());
        sizeModel.setQuantity(sizeModelDto.getQuantity());
        return sizeRepository.save(sizeModel);
    }

    @Override
    public Page<SizeModel> findAll(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }

    @Override
    public Object findById(UUID sizeId) {
        SizeModel sizeModel = sizeRepository.findById(sizeId).orElseThrow(() -> new SizeNotFoundException(sizeId));
        return sizeModel;
    }

    @Override
    public List<SizeModel> findAllSizeByProductId(UUID productId) {
        return sizeRepository.findAllByProductId(productId);
    }

    @Transactional
    @Override
    public void deleteById(UUID sizeId) {
        sizeRepository.deleteRelationSizeProductBySizeId(sizeId);
        sizeRepository.deleteById(sizeId);
    }

    @Transactional
    @Override
    public void deleteSizes(List<SizeModel> sizes) {
        sizes.forEach(size -> sizeRepository.delete(size));
    }


}
