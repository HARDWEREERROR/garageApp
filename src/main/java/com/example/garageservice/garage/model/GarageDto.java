package com.example.garageservice.garage.model;

import com.example.garageservice.car.model.Car;
import com.example.garageservice.common.Fuel;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class GarageDto {

    private int id;
    private String adress;
    private int capacity;
    private Set<Fuel> fuels;
    private int numOfCars;

    public static GarageDto fromEntity(Garage garage){
        return GarageDto.builder()
                .id(garage.getId())
                .adress(garage.getAdress())
                .capacity(garage.getCapacity())
                .fuels(garage.getFuels())
                .numOfCars(garage.getCars().size())
                .build();
    }

}
