package com.example.garageservice.carGarage;

import com.example.garageservice.car.CarRepository;
import com.example.garageservice.car.model.Car;
import com.example.garageservice.garage.GarageRepository;
import com.example.garageservice.garage.GarageService;
import com.example.garageservice.garage.model.Garage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarGarageService {

    private final CarGarageRepository carGarageRepository;

    private final CarRepository carRepository;

    private final GarageRepository garageRepository;

    public List<CarGarage> findAll() {
        return carGarageRepository.findAll();
    }

    @Transactional
    public void save(CarGarage carGarage, int carId, int garageId) {

        Car car = carRepository.findWithLockingById(carId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Car with id={0} has not been found", carId)));
        if (car.getGarage() != null) {
            throw new IllegalArgumentException("Car is already in the parking lot");
        }
        Garage garage = garageRepository.findWithLockingById(garageId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Garage with id={0} has not been found", garageId)));
        if (garage.getCars().size() >= garage.getCapacity()) {
            throw new IllegalArgumentException("There is no free parking space");
        }
        if (!garage.getFuels().contains(car.getFuel())) {
            throw new IllegalArgumentException("This fuel is NOT ALLOWED in this garage");
        }
        carGarage.setGarage(garage);
        carGarage.setCar(car);
        car.setGarage(garage);
        garage.getCars().add(car);
        garage.getCarGarageSet().add(carGarage);
        carGarageRepository.save(carGarage);
    }

    @Transactional
    public void unpark(@RequestParam("carId") int carId, @RequestParam("garageId") int garageId) {
        Car car = carRepository.findWithLockingById(carId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Car with id={0} has not been found", carId)));
        Garage garage = garageRepository.findWithLockingById(garageId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Garage with id={0} has not been found", garageId)));
        car.setGarage(null);
        garage.getCars().remove(car);
    }
}
