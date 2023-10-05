package com.coralsoft.domorder.repositories;

import com.coralsoft.domorder.models.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<CartModel, UUID> {
}
