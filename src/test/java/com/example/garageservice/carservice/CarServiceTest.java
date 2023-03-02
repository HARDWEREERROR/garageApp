package com.example.garageservice.carservice;

import com.example.garageservice.car.CarRepository;
import com.example.garageservice.car.CarService;
import com.example.garageservice.car.model.Car;
import com.example.garageservice.common.Fuel;
import com.example.garageservice.garage.GarageRepository;
import com.example.garageservice.garage.model.Garage;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @InjectMocks
    private CarService carService;
    @Mock
    private CarRepository carRepository;

    @Mock
    private GarageRepository garageRepository;

    @Mock
    private ArgumentCaptor<Car> carArgumentCaptor;


    @Test
    void testSave() {
        // Given
        int garageId = 1;
        Fuel fuelType = Fuel.PETROL;
        Garage garage = new Garage();
        garage.setId(garageId);
        garage.setCapacity(2);
        garage.setFuels(Set.of(fuelType));
        Car car = new Car();
        car.setFuel(fuelType);

        when(garageRepository.findWithLockingById(garageId)).thenReturn(Optional.of(garage));
        when(carRepository.save(car)).thenReturn(car);

        // When
        carService.save(car, garageId);

        // Then
        Assertions.assertEquals(garage, car.getGarage());
    }

    @Test
    void testSave_withGarageNotFound() {
        // Given
        int garageId = 1;
        Fuel fuelType = Fuel.PETROL;
        Garage garage = new Garage();
        garage.setId(garageId);
        garage.setCapacity(2);
        garage.setFuels(Set.of(fuelType));
        Car car = new Car();
        car.setFuel(fuelType);

        when(garageRepository.findWithLockingById(garageId)).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            carService.save(car, garageId);
        });
    }

    @Test
    void testSave_withGarageNotAllowFuelType() {
        // Given
        int garageId = 1;
        Fuel garageFuelType = Fuel.PETROL;
        Fuel carFuelType = Fuel.DIESEL;
        Garage garage = new Garage();
        garage.setId(garageId);
        garage.setCapacity(2);
        garage.setFuels(Set.of(garageFuelType));
        Car car = new Car();
        car.setFuel(carFuelType);

        when(garageRepository.findWithLockingById(garageId)).thenReturn(Optional.of(garage));

        // Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carService.save(car, garageId);
        });
    }

    @Test
    void testSave_withGarageCapacityFull() {
        // Given
        int garageId = 1;
        Fuel fuelType = Fuel.PETROL;
        Garage garage = new Garage();
        garage.setId(garageId);
        garage.setCapacity(1);
        garage.setFuels(Set.of(fuelType));
        Car car1 = new Car();
        car1.setFuel(fuelType);
        Car car2 = new Car();
        car2.setFuel(fuelType);
        garage.setCars(Set.of(car1, car2));
        Car car = new Car();
        car.setFuel(fuelType);

        when(garageRepository.findWithLockingById(garageId)).thenReturn(Optional.of(garage));

        // Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carService.save(car, garageId);
        });
    }

}
