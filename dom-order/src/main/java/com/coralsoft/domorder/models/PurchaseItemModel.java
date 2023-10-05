package com.coralsoft.domorder.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_purchaseItem")
public class PurchaseItemModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID purchaseItemId;
    @OneToOne
    private ProductModel product;
    private UUID sizeId;
    private int quantity;
}
