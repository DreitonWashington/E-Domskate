package com.coralsoft.domorder.models;

import com.coralsoft.domorder.enums.OrderStatus;
import com.coralsoft.domorder.enums.PaymentMethod;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_orders")
public class OrderModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
    @OneToOne
    private UserModel user;
    @OneToOne
    private CartModel cart;
    private LocalDateTime orderTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}
