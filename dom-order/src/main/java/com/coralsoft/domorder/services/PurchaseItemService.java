package com.coralsoft.domorder.services;

import com.coralsoft.domorder.dtos.PurchaseItemDto;
import com.coralsoft.domorder.models.PurchaseItemModel;

public interface PurchaseItemService {

    PurchaseItemModel savePurchaseItem(PurchaseItemDto purchaseItemDto);
}
