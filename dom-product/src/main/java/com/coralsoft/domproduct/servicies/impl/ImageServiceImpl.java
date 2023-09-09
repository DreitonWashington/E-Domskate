package com.coralsoft.domproduct.servicies.impl;

import com.coralsoft.domproduct.dtos.ImageModelDto;
import com.coralsoft.domproduct.models.ImageModel;
import com.coralsoft.domproduct.repositories.ImageRepository;
import com.coralsoft.domproduct.servicies.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public ImageModel save(ImageModelDto imageModelDto) {
        ImageModel imageModel = new ImageModel();
        imageModel.setUrl(imageModelDto.getUrl());
        return imageRepository.save(imageModel);
    }

    @Override
    public Set<ImageModel> findAllImagesByProductId(UUID id) {
        return imageRepository.findAllImagesByProductId(id);
    }

    @Override
    public void deleteImages(Set<ImageModel> images) {
        images.forEach(image -> imageRepository.delete(image));
    }
}
