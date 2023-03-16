package com.example.garageservice.carGarage;

import com.example.garageservice.carGarage.model.CarGarage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarGarageRepository extends JpaRepository<CarGarage, Integer> {
}
