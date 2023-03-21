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

import java.util.ArrayList;
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



}
