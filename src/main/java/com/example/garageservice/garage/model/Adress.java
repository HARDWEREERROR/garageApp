package com.example.garageservice.garage.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Adress implements Serializable {

    private String city;
    private String street;
    private String numberOfBuilding;
    private String numberOfApartament;
    private String zipCode;




}


