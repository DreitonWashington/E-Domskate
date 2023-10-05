package com.coralsoft.domorder.models;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class AddressModel {

    private String cep;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;
}
