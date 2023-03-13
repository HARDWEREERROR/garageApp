package com.example.garageservice.carGarage;

import com.example.garageservice.car.model.Car;
import com.example.garageservice.garage.model.Garage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class CarGarage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @OneToOne
    private Car car;

    @ManyToOne
    private Garage garage;

}
