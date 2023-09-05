package com.coralsoft.domproduct.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TB_BRANDS")
public class BrandModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;
}
