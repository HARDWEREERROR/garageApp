package com.example.garageservice.garage;

import com.example.garageservice.common.Fuel;
import com.example.garageservice.garage.model.Garage;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void deleteById(int garageId) {
        garageRepository.deleteById(garageId);
    }
}
