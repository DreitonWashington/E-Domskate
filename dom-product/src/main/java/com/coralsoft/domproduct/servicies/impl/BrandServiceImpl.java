package com.coralsoft.domproduct.servicies.impl;

import com.coralsoft.domproduct.dtos.BrandModelDto;
import com.coralsoft.domproduct.exceptions.BrandNotFoundException;
import com.coralsoft.domproduct.models.BrandModel;
import com.coralsoft.domproduct.repositories.BrandRepository;
import com.coralsoft.domproduct.servicies.BrandService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public Page<BrandModel> findAll(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public BrandModel save(BrandModelDto brandModelDto) {
        var brandModel = new BrandModel();
        brandModel.setName(brandModelDto.getName());
        return brandRepository.save(brandModel);
    }

    public BrandModel update(BrandModel brandModel){
        return brandRepository.save(brandModel);
    }

    @Override
    public Optional<BrandModel> findById(UUID brandId) {
        Optional<BrandModel> brandModelOptional = brandRepository.findById(brandId);
        if(brandModelOptional.isEmpty()){
            log.debug("BrandModel ID {} not found", brandId);
            log.debug("Throwing exception BrandNotFoundException");
            throw new BrandNotFoundException(brandId);
        }
        return brandModelOptional;
    }

    @Transactional
    @Override
    public void delete(BrandModel brandModel) {
        brandRepository.delete(brandModel);
    }

}
