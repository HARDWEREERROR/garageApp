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
    public void save(Car car) {
        carRepository.save(car);
    }

    public List<Car> findAllByGarage(int garageId) {
        return carRepository.findAllByGarageId(garageId);
    }

    @Transactional
    public void deleteById(int carId) {
        Car car = carRepository.findWithLockingById(carId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Car with id={0} has not been found", carId)));
        if (car.getGarage() != null) {
            car.getGarage().getCars().remove(car);
        }
        carRepository.deleteById(carId);
    }
}
