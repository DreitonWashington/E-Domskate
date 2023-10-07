package com.coralsoft.domorder.repositories;

import com.coralsoft.domorder.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderModel, UUID> {

    @Modifying
    @Query(value = "update tb_orders set order_status = 'PAYMENT_CONFIRMED' where order_id = :orderId", nativeQuery = true)
    void updateToPaymentConfirmed(UUID orderId);

    @Modifying
    @Query(value = "update tb_orders set order_status = 'SEPARATING_IN_STOCK' where order_id = :orderId", nativeQuery = true)
    void updateToSeparatingInStock(UUID orderId);

    @Modifying
    @Query(value = "update tb_orders set order_status = 'SHIPPED_OUT' where order_id = :orderId", nativeQuery = true)
    void updateToShippedOut(UUID orderId);

    @Modifying
    @Query(value = "update tb_orders set order_status = 'CONCLUDED' where order_id = :orderId", nativeQuery = true)
    void updateToConcluded(UUID orderId);

    @Modifying
    @Query(value = "update tb_orders set order_status = 'CANCELED' where order_id = :orderId", nativeQuery = true)
    void updateToCanceled(UUID orderId);

    @Query(value = "select order_status from tb_orders where order_id = :orderId", nativeQuery = true)
    String getCurrentOrderStatus(UUID orderId);

    @Modifying
    @Query(value = "update tb_orders set last_update = :time where order_id = :orderId", nativeQuery = true)
    void updateLastUpdate(LocalDateTime time, UUID orderId);
}
