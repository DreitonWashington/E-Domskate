package com.coralsoft.domauthuser.models;

import com.coralsoft.domauthuser.enums.RoleType;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_roles")
public class RoleModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleName;
}
