package com.coralsoft.domorder.dtos;

import com.coralsoft.domorder.models.PurchaseItemModel;
import lombok.Data;

import java.util.Set;

@Data
public class CartDto {

    private Set<PurchaseItemDto> products;
    private float total;
}
