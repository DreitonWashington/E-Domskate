package com.coralsoft.domorder.services;

import com.coralsoft.domorder.dtos.CartDto;
import com.coralsoft.domorder.models.CartModel;

public interface CartService {

    CartModel saveCart(CartDto cartDto);
}
