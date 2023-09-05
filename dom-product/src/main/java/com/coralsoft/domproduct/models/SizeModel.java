package com.coralsoft.domproduct.models;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_SIZES")
public class SizeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 4)
    private String size;

    @Column(nullable = false)
    private Integer quantity;

}
