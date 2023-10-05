package com.coralsoft.domorder.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_carts")
public class CartModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cartId;
    @OneToMany
    private Set<PurchaseItemModel> products;
}
