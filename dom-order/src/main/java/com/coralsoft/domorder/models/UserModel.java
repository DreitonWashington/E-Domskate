package com.coralsoft.domorder.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_users")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    private UUID userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Embedded
    private AddressModel address;
}
