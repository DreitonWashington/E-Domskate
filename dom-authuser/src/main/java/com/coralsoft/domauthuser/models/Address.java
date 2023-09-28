package com.coralsoft.domauthuser.models;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
    private String cep;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;
}
