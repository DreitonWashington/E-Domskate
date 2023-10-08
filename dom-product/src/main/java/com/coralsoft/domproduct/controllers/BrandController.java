package com.coralsoft.domproduct.controllers;

import com.coralsoft.domproduct.dtos.BrandModelDto;
import com.coralsoft.domproduct.models.BrandModel;
import com.coralsoft.domproduct.servicies.BrandService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping
    public ResponseEntity<Page<BrandModel>> getAllBrands(Pageable pageable){
        log.info("Getting all brands");
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findAll(pageable));
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<Object> getBrandById(@PathVariable(value = "brandId") UUID brandId){
        log.info("Getting brand with ID {}", brandId);
        Optional<BrandModel> brandModelOptional = brandService.findById(brandId);
        log.debug("GET BrandModel {}", brandModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(brandModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<BrandModel> saveBrand(@RequestBody BrandModelDto brandModelDto){
        log.info("Saving Brand");
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.save(brandModelDto));
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<Object> deleteBrand(@PathVariable(value = "brandId") UUID brandId){
        Optional<BrandModel> brandModelOptional = brandService.findById(brandId);
        brandService.delete(brandModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Brand deleted successfully.");
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<Object> updateBrand(@PathVariable(value = "brandId") UUID brandId, @RequestBody BrandModelDto brandModelDto){
        Optional<BrandModel> brandModelOptional = brandService.findById(brandId);
        brandModelOptional.get().setName(brandModelDto.getName());
        return ResponseEntity.status(HttpStatus.OK).body(brandService.update(brandModelOptional.get()));
    }


}
