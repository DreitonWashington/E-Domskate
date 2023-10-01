package com.coralsoft.domauthuser.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class AddressModel {
    private String cep;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;
}
