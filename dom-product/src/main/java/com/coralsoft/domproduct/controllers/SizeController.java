package com.coralsoft.domproduct.controllers;

import com.coralsoft.domproduct.dtos.SizeModelDto;
import com.coralsoft.domproduct.models.SizeModel;
import com.coralsoft.domproduct.servicies.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sizes")
public class SizeController {

    @Autowired
    SizeService sizeService;

    @PostMapping
    public ResponseEntity<SizeModel> saveSize(@RequestBody SizeModelDto sizeModelDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(sizeService.save(sizeModelDto));
    }

    @GetMapping
    public ResponseEntity<Page<SizeModel>> getAllSizes(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(sizeService.findAll(pageable));
    }

    @GetMapping("/{sizeId}")
    public ResponseEntity<Object> getSizeById(@PathVariable(value = "sizeId")UUID sizeId){
        return ResponseEntity.status(HttpStatus.OK).body(sizeService.findById(sizeId));
    }

    @DeleteMapping("/{sizeId}")
    public ResponseEntity<Object> deleteSizeById(@PathVariable(value = "sizeId")UUID sizeId){
        sizeService.deleteById(sizeId);
        return ResponseEntity.status(HttpStatus.OK).body("Size deleted successfully.");
    }


}
