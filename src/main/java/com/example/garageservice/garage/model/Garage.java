package com.example.garageservice.garage.model;


import com.example.garageservice.car.model.Car;
import com.example.garageservice.carGarage.CarGarage;
import com.example.garageservice.common.Fuel;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String adress;

    private int capacity;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "garage_fuel", joinColumns = @JoinColumn(name = "garage_id"))
    @Column(name = "fuel")
    private Set<Fuel> fuels;

    @OneToMany(mappedBy = "garage")
    private Set<Car> cars;

    @OneToMany(mappedBy = "garage")
    private Set<CarGarage> carGarageSet;

    @Override
    public String toString() {
        return adress + " ";
    }
}
