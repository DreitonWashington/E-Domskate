package com.coralsoft.domproduct.servicies.impl;

import com.coralsoft.domproduct.dtos.ImageModelDto;
import com.coralsoft.domproduct.dtos.ProductModelDto;
import com.coralsoft.domproduct.dtos.PublisherProductDto;
import com.coralsoft.domproduct.dtos.SizeModelDto;
import com.coralsoft.domproduct.enums.ActionType;
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
            productServicePublisher.publishProductToOrder(publisherProductDto, ActionType.CREATE);
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
            productRepository.save(shoesModel);

            PublisherProductDto publisherProductDto = new PublisherProductDto();
            publisherProductDto.convertProductModelToPublisherProductDto(shoesModel);
            productServicePublisher.publishProductToOrder(publisherProductDto, ActionType.CREATE);
            return shoesModel;
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
        var publisher = new PublisherProductDto().convertProductModelToPublisherProductDto(productModel);
        sizeService.deleteSizes(productModel.getSizes());
        imageService.deleteImages(productModel.getImages());
        productRepository.deleteById(productId);
        productServicePublisher.publishProductToOrder(publisher, ActionType.DELETE);
    }

    @Override
    public ClothesModel updateClothes(UUID productId, ClothesModel clothesModel) {
        ClothesModel clothesModelDb = (ClothesModel) productRepository.findById(productId).get();
        setProductValues(clothesModel, clothesModelDb);

        var publisher = new PublisherProductDto().convertProductModelToPublisherProductDto(clothesModelDb);
        productRepository.save(clothesModelDb);
        productServicePublisher.publishProductToOrder(publisher, ActionType.UPDATE);
        return clothesModelDb;
    }

    @Override
    public ShoesModel updateShoes(UUID productId, ShoesModel shoesModel) {
        ShoesModel shoesModelDb = (ShoesModel) productRepository.findById(productId).get();
        setProductValues(shoesModel, shoesModelDb);

        var publisher = new PublisherProductDto().convertProductModelToPublisherProductDto(shoesModelDb);
        productRepository.save(shoesModelDb);
        productServicePublisher.publishProductToOrder(publisher, ActionType.UPDATE);
        return shoesModelDb;
    }

    public Object setProductValues(ProductModel product, ProductModel productDb){
        if(product.getName() != null){
            productDb.setName(product.getName());
        }
        if(product.getBrand() != null){
            productDb.setBrand(product.getBrand());
        }
        if(product.getDescription() != null){
            productDb.setDescription(product.getDescription());
        }
        if(product.getPrice() != null){
            productDb.setPrice(product.getPrice());
        }
        if(product.getSizes() != null){
            productDb.setSizes(product.getSizes());
        }
        if(product.getImages() != null){
            productDb.setImages(product.getImages());
        }
        return productDb;
    }

}
