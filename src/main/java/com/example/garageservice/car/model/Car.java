package com.example.garageservice.car.model;


import com.example.garageservice.common.Fuel;
import com.example.garageservice.garage.model.Garage;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@SQLDelete(sql = "UPDATE car SET active = '0' WHERE id = ?")
@Where(clause = "active = 1")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mark;

    private String model;

    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    @ManyToOne
    private Garage garage;

    private boolean active = true;


    @Override
    public String toString() {
        return mark + " " + model;
    }
}
