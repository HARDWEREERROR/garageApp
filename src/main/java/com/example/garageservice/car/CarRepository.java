package com.example.garageservice.car;

import com.example.garageservice.car.model.Car;
import com.example.garageservice.garage.model.Garage;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findAllByGarageId(int garageId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Car> findWithLockingById(int id);
}
