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
        Optional<SizeModel> sizeModelOptional = sizeRepository.findById(sizeId);
        if(sizeModelOptional.isEmpty()){
            throw new SizeNotFoundException(sizeId);
        }
        return sizeModelOptional.get();
    }

    @Override
    public List<SizeModel> findAllSizeByProductId(UUID productId) {
        return sizeRepository.findAllByProductId(productId);
    }


}
