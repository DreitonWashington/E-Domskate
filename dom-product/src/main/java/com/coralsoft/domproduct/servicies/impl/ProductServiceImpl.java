package com.coralsoft.domproduct.servicies.impl;

import com.coralsoft.domproduct.dtos.ImageModelDto;
import com.coralsoft.domproduct.dtos.ProductModelDto;
import com.coralsoft.domproduct.dtos.PublisherProductDto;
import com.coralsoft.domproduct.dtos.SizeModelDto;
import com.coralsoft.domproduct.exceptions.ProductNotFoundException;
import com.coralsoft.domproduct.models.*;
import com.coralsoft.domproduct.publishers.ProductServicePublisher;
import com.coralsoft.domproduct.repositories.ProductRepository;
import com.coralsoft.domproduct.servicies.BrandService;
import com.coralsoft.domproduct.servicies.ImageService;
import com.coralsoft.domproduct.servicies.ProductService;
import com.coralsoft.domproduct.servicies.SizeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandService brandService;

    @Autowired
    SizeService sizeService;

    @Autowired
    ImageService imageService;

    @Autowired
    ProductServicePublisher productServicePublisher;

    @Transactional
    @Override
    public ProductModel save(ProductModelDto productModelDto) {
        if(productModelDto.getTypeClothes() != null){
            ClothesModel clothesModel = new ClothesModel();
            clothesModel.setName(productModelDto.getName());

            Optional<BrandModel> brandModelOptional = brandService.findById(productModelDto.getBrand().getId());
            clothesModel.setBrand(brandModelOptional.get());
            clothesModel.setDescription(productModelDto.getDescription());
            clothesModel.setPrice(productModelDto.getPrice());

            Set<SizeModel> sizesSaved = new HashSet<>();
            for(SizeModelDto size : productModelDto.getSizes()){
                sizesSaved.add(sizeService.save(size));
            }
            clothesModel.setSizes(sizesSaved);

            Set<ImageModel> imagesSaved = new HashSet<>();
            for(ImageModelDto image : productModelDto.getImages()){
                imagesSaved.add(imageService.save(image));
            };
            clothesModel.setImages(imagesSaved);

            clothesModel.setTypeClothes(productModelDto.getTypeClothes());
            productRepository.save(clothesModel);

            PublisherProductDto publisherProductDto = new PublisherProductDto();
            publisherProductDto.convertProductModelToPublisherProductDto(clothesModel);
            productServicePublisher.publishNewProductCreated(publisherProductDto);
            return clothesModel;
        }else{
            ShoesModel shoesModel = new ShoesModel();
            shoesModel.setName(productModelDto.getName());

            Optional<BrandModel> brandModelOptional = brandService.findById(productModelDto.getBrand().getId());
            shoesModel.setBrand(brandModelOptional.get());
            shoesModel.setDescription(productModelDto.getDescription());
            shoesModel.setPrice(productModelDto.getPrice());

            Set<SizeModel> sizesSaved = new HashSet<>();
            for(SizeModelDto size : productModelDto.getSizes()){
                sizesSaved.add(sizeService.save(size));
            }
            shoesModel.setSizes(sizesSaved);

            Set<ImageModel> imagesSaved = new HashSet<>();
            for(ImageModelDto image : productModelDto.getImages()){
                imagesSaved.add(imageService.save(image));
            };
            shoesModel.setImages(imagesSaved);
            shoesModel.setTypeShoes(productModelDto.getTypeShoes());
            return productRepository.save(shoesModel);
        }
    }

    @Override
    public Page<ProductModelDto> findAll(Specification<ProductModel> spec, Pageable pageable) {
        Page<ProductModel> pageProductModel = productRepository.findAll(spec,pageable);
        Page<ProductModelDto> pageProductDto = pageProductModel.map(new Function<ProductModel, ProductModelDto>() {
            @Override
            public ProductModelDto apply(ProductModel productModel) {
                ProductModelDto dto = new ProductModelDto();
                if(!productModel.getSizes().isEmpty()){
                    Set<SizeModel> l = sizeService.findAllSizeByProductId(productModel.getId());
                    productModel.setSizes(l);
                    Set<SizeModelDto> sizes = new HashSet<>();
                    for(SizeModel size : productModel.getSizes()){
                        SizeModelDto sizeModelDto = new SizeModelDto();
                        sizeModelDto.setSize(size.getSize());
                        sizeModelDto.setQuantity(size.getQuantity());
                        sizes.add(sizeModelDto);
                    }
                    dto.setSizes(sizes);
                }
                if(!productModel.getImages().isEmpty()){
                    Set<ImageModel> i = imageService.findAllImagesByProductId(productModel.getId());
                    productModel.setImages(i);
                    Set<ImageModelDto> images = new HashSet<>();
                    for(ImageModel image : productModel.getImages()){
                        ImageModelDto imageModelDto = new ImageModelDto();
                        imageModelDto.setUrl(image.getUrl());
                        images.add(imageModelDto);
                    }
                    dto.setImages(images);
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

    @Override
    public Optional<ProductModel> findById(UUID productId) {
        return Optional.ofNullable(productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId)));
    }

    @Transactional
    @Override
    public void deleteProductById(UUID productId) {
        var productModel = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        sizeService.deleteSizes(productModel.getSizes());
        imageService.deleteImages(productModel.getImages());
        productRepository.deleteById(productId);
    }

    @Override
    public ClothesModel updateClothes(UUID productId, ClothesModel clothesModel) {
        ClothesModel clothesModelDb = (ClothesModel) productRepository.findById(productId).get();

        if(clothesModel.getName() != null){
            clothesModelDb.setName(clothesModel.getName());
        }
        if(clothesModel.getBrand() != null){
            clothesModelDb.setBrand(clothesModel.getBrand());
        }
        if(clothesModel.getDescription() != null){
            clothesModelDb.setDescription(clothesModel.getDescription());
        }
        if(clothesModel.getPrice() != null){
            clothesModelDb.setPrice(clothesModel.getPrice());
        }
        if(clothesModel.getSizes() != null){
            clothesModelDb.setSizes(clothesModel.getSizes());
        }
        if(clothesModel.getImages() != null){
            clothesModelDb.setImages(clothesModel.getImages());
        }

        return productRepository.save(clothesModelDb);
    }

    @Override
    public ShoesModel updateShoes(UUID productId, ShoesModel shoesModel) {
        ShoesModel shoesModelDb = (ShoesModel) productRepository.findById(productId).get();

        if(shoesModel.getName() != null){
            shoesModelDb.setName(shoesModel.getName());
        }
        if(shoesModel.getBrand() != null){
            shoesModelDb.setBrand(shoesModel.getBrand());
        }
        if(shoesModel.getDescription() != null){
            shoesModelDb.setDescription(shoesModel.getDescription());
        }
        if(shoesModel.getPrice() != null){
            shoesModelDb.setPrice(shoesModel.getPrice());
        }
        if(shoesModel.getSizes() != null){
            shoesModelDb.setSizes(shoesModel.getSizes());
        }
        if(shoesModel.getImages() != null){
            shoesModelDb.setImages(shoesModel.getImages());
        }

        return productRepository.save(shoesModelDb);
    }

}
