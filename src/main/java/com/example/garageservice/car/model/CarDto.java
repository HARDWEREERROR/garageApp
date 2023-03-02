package com.example.garageservice.car.model;

import com.example.garageservice.common.Fuel;
import com.example.garageservice.garage.model.Garage;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class CarDto {

    private int id;
    private String mark;
    private String model;
    private Fuel fuels;
    private String garageAdress;

    public static CarDto fromEntity(Car car){
        return CarDto.builder()
                .id(car.getId())
                .mark(car.getMark())
                .model(car.getModel())
                .fuels(car.getFuel())
                .garageAdress(car.getGarage().toString())
                .build();
    }

}
