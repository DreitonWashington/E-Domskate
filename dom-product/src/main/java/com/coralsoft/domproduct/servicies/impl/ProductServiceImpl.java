package com.coralsoft.domproduct.servicies.impl;

import com.coralsoft.domproduct.dtos.ProductModelDto;
import com.coralsoft.domproduct.dtos.SizeModelDto;
import com.coralsoft.domproduct.models.*;
import com.coralsoft.domproduct.repositories.ProductRepository;
import com.coralsoft.domproduct.servicies.BrandService;
import com.coralsoft.domproduct.servicies.ProductService;
import com.coralsoft.domproduct.servicies.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandService brandService;

    @Autowired
    SizeService sizeService;

    @Override
    public ProductModel save(ProductModelDto productModelDto) {
        if(productModelDto.getTypeClothes() != null){
            ClothesModel clothesModel = new ClothesModel();
            clothesModel.setName(productModelDto.getName());

            Optional<BrandModel> brandModelOptional = brandService.findById(productModelDto.getBrand().getId());
            clothesModel.setBrand(brandModelOptional.get());
            clothesModel.setDescription(productModelDto.getDescription());
            clothesModel.setPrice(productModelDto.getPrice());

            List<SizeModel> sizesSaved = new ArrayList<SizeModel>();
            for(SizeModelDto size : productModelDto.getSizes()){
                sizesSaved.add(sizeService.save(size));
            }
            clothesModel.setSizes(sizesSaved);
            clothesModel.setTypeClothes(productModelDto.getTypeClothes());
            return productRepository.save(clothesModel);
        }else{
            ShoesModel shoesModel = new ShoesModel();
            shoesModel.setName(productModelDto.getName());

            Optional<BrandModel> brandModelOptional = brandService.findById(productModelDto.getBrand().getId());
            shoesModel.setBrand(brandModelOptional.get());
            shoesModel.setDescription(productModelDto.getDescription());
            shoesModel.setPrice(productModelDto.getPrice());

            List<SizeModel> sizesSaved = new ArrayList<SizeModel>();
            for(SizeModelDto size : productModelDto.getSizes()){
                sizesSaved.add(sizeService.save(size));
            }
            shoesModel.setSizes(sizesSaved);
            shoesModel.setTypeShoes(productModelDto.getTypeShoes());
            return productRepository.save(shoesModel);
        }
    }

    @Override
    public Page<ProductModelDto> findAll(Pageable pageable) {
        Page<ProductModel> pageProductModel = productRepository.findAll(pageable);
        Page<ProductModelDto> pageProductDto = pageProductModel.map(new Function<ProductModel, ProductModelDto>() {
            @Override
            public ProductModelDto apply(ProductModel productModel) {
                ProductModelDto dto = new ProductModelDto();
                if(!productModel.getSizes().isEmpty()){
                    List<SizeModel> l = sizeService.findAllSizeByProductId(productModel.getId());
                    productModel.setSizes(l);
                    List<SizeModelDto> sizes = new ArrayList<>();
                    for(SizeModel size : productModel.getSizes()){
                        SizeModelDto sizeModelDto = new SizeModelDto();
                        sizeModelDto.setSize(size.getSize());
                        sizeModelDto.setQuantity(size.getQuantity());
                        sizes.add(sizeModelDto);
                    }
                    dto.setSizes(sizes);
                }

                dto.setId(productModel.getId());
                dto.setName(productModel.getName());
                dto.setBrand(productModel.getBrand());
                dto.setDescription(productModel.getDescription());
                dto.setPrice(productModel.getPrice());
                return dto;
            }
        });
        return pageProductDto;
    }


}
