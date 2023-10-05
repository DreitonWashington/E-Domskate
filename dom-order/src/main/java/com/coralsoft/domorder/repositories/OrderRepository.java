package com.coralsoft.domorder.repositories;

import com.coralsoft.domorder.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderModel, UUID> {
}
