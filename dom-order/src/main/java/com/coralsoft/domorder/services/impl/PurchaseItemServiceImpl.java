package com.coralsoft.domorder.services.impl;

import com.coralsoft.domorder.dtos.PurchaseItemDto;
import com.coralsoft.domorder.models.ProductModel;
import com.coralsoft.domorder.models.PurchaseItemModel;
import com.coralsoft.domorder.repositories.PurchaseItemRepository;
import com.coralsoft.domorder.services.ProductService;
import com.coralsoft.domorder.services.PurchaseItemService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseItemServiceImpl implements PurchaseItemService {

    final PurchaseItemRepository purchaseItemRepository;
    final ProductService productService;

    public PurchaseItemServiceImpl(PurchaseItemRepository purchaseItemRepository, ProductService productService){
        this.purchaseItemRepository = purchaseItemRepository;
        this.productService = productService;
    }

    @Override
    public PurchaseItemModel savePurchaseItem(PurchaseItemDto purchaseItemDto) {
        ProductModel productModel = productService.findProductById(purchaseItemDto.getProductId());

        PurchaseItemModel purchaseItemModel = new PurchaseItemModel();
        purchaseItemModel.setSizeId(purchaseItemDto.getSizeId());
        purchaseItemModel.setQuantity(purchaseItemDto.getQuantity());
        purchaseItemModel.setProduct(productModel);
        return purchaseItemRepository.save(purchaseItemModel);
    }
}
