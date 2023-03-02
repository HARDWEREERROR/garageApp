package com.example.garageservice.garage;

import com.example.garageservice.car.model.Car;
import com.example.garageservice.common.Fuel;
import com.example.garageservice.garage.model.Garage;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface GarageRepository extends JpaRepository<Garage, Integer> {

    List<Garage> findAllByFuelsContaining(Fuel fuel);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Garage> findWithLockingById(int id);



}
