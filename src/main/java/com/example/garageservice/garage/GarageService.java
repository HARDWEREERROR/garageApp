package com.example.garageservice.garage;

import com.example.garageservice.car.model.Car;
import com.example.garageservice.common.Fuel;
import com.example.garageservice.garage.model.Garage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GarageService {

    private final GarageRepository garageRepository;

    public List<Garage> findAll() {
        return garageRepository.findAll();
    }


    public void save(Garage garage) {
        garageRepository.save(garage);
    }

    public List<Garage> findAllByFuel(Fuel fuel) {
        return garageRepository.findAllByFuelsContaining(fuel);
    }

    @Transactional
    public void deleteById(int garageId) {
        Garage garage = garageRepository.findWithLockingById(garageId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Garage with id={0} has not been found", garageId)));

        for (Car car : garage.getCars()){
            car.setGarage(null);
        }

        garageRepository.deleteById(garageId);
    }
}
