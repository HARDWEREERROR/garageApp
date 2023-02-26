package com.example.garageservice.garage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adress {

    private String city;
    private String street;
    private String numberOfBuilding;
    private String numberOfApartament;
    private String zipCode;

    public Adress(String city, String street, String numberOfBuilding, String numberOfApartament, String zipCode) {
        this.city = city;
        this.street = street;
        this.numberOfBuilding = numberOfBuilding;
        this.numberOfApartament = numberOfApartament;
        this.zipCode = zipCode;
    }

    public Adress(String city, String street, String numberOfBuilding, String zipCode) {
        this.city = city;
        this.street = street;
        this.numberOfBuilding = numberOfBuilding;
        this.zipCode = zipCode;
    }

    public Adress(String city, String numberOfBuilding, String zipCode) {
        this.city = city;
        this.numberOfBuilding = numberOfBuilding;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        if (numberOfApartament.equals(null) && street.equals(null)) {
            return city + " " + numberOfBuilding + " " + zipCode;
        }
        if (numberOfApartament.equals(null)) {
            return city + " " + street + " " + numberOfBuilding + " " + zipCode;
        } else {
            return city + " " + street + " " + numberOfBuilding + " " + numberOfApartament + " " + zipCode;
        }
    }
}


