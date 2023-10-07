package com.coralsoft.domorder.repositories;

import com.coralsoft.domorder.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderModel, UUID> {

    @Modifying
    @Query(value = "update tb_orders set order_status = 'PAYMENT_CONFIRMED' where order_id = :orderId", nativeQuery = true)
    void updateToPaymentConfirmed(UUID orderId);

    @Query(value = "select order_status from tb_orders where order_id = :orderId", nativeQuery = true)
    String getCurrentOrderStatus(UUID orderId);
}
