package com.coralsoft.domproduct.dtos;

import com.coralsoft.domproduct.models.BrandModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
public class BrandModelDto {

    private UUID id;
    private String name;
}
