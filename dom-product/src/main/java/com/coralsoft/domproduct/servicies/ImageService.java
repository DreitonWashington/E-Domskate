package com.coralsoft.domproduct.servicies;

import com.coralsoft.domproduct.dtos.ImageModelDto;
import com.coralsoft.domproduct.models.ImageModel;

import java.util.Set;
import java.util.UUID;

public interface ImageService {

    ImageModel save(ImageModelDto imageModelDto);

    Set<ImageModel> findAllImagesByProductId(UUID id);

    void deleteImages(Set<ImageModel> images);
}
