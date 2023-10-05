package com.coralsoft.domorder.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_products")
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    private UUID productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;

    public ProductModel(UUID id, String name, double price) {
        this.productId = id;
        this.name = name;
        this.price = price;
    }
}
