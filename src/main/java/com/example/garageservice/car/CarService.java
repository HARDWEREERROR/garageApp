package com.example.garageservice.car;

import com.example.garageservice.car.model.Car;
import com.example.garageservice.garage.GarageRepository;
import com.example.garageservice.garage.model.Garage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    private final GarageRepository garageRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Transactional
    public void save(Car car, int garageId) {
        Garage garage = garageRepository.findWithLockingById(garageId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Garage with id={0} has not been found", garageId)));
        if (!garage.getFuels().contains(car.getFuel())) {
            throw new IllegalArgumentException("Garage does NOT allow to park car with this type of fuel");
        }
        if (garage.getCars().size() >= garage.getCapacity()) {
            throw new IllegalArgumentException("Capacity of this Garage is full");
        }
        car.setGarage(garage);
        carRepository.save(car);
    }

    public List<Car> findAllByGarage(int garageId) {
        return carRepository.findAllByGarageId(garageId);
    }

    public void deleteById(int carId) {
        carRepository.deleteById(carId);
    }
}
