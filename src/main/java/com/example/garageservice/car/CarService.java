package com.example.garageservice.car;

import com.example.garageservice.car.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public void save(Car car){
        carRepository.save(car);
    }
}
