package com.coralsoft.domorder.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class PurchaseItemDto {
     private UUID productId;
     private UUID sizeId;
     private int quantityId;
}
