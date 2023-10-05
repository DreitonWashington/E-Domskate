package com.coralsoft.domorder.services.impl;

import com.coralsoft.domorder.dtos.CartDto;
import com.coralsoft.domorder.dtos.PurchaseItemDto;
import com.coralsoft.domorder.models.CartModel;
import com.coralsoft.domorder.models.PurchaseItemModel;
import com.coralsoft.domorder.repositories.CartRepository;
import com.coralsoft.domorder.services.CartService;
import com.coralsoft.domorder.services.PurchaseItemService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    final CartRepository cartRepository;
    final PurchaseItemService purchaseItemService;

    public CartServiceImpl(CartRepository cartRepository, PurchaseItemService purchaseItemService){
        this.cartRepository = cartRepository;
        this.purchaseItemService = purchaseItemService;
    }

    @Transactional
    @Override
    public CartModel saveCart(CartDto cartDto) {
        CartModel cart = new CartModel();
        Set<PurchaseItemDto> products = cartDto.getProducts();
        Set<PurchaseItemModel> productsSaved = new HashSet<>();
        for(PurchaseItemDto purchaseItemDto : products){
            PurchaseItemModel purchaseItemModel = purchaseItemService.savePurchaseItem(purchaseItemDto);
            productsSaved.add(purchaseItemModel);
        }
        cart.setProducts(productsSaved);
        cart.setTotal(cartDto.getTotal());
        return cartRepository.save(cart);
    }
}
