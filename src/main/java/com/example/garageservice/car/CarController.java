package com.example.garageservice.car;


import com.example.garageservice.car.model.Car;
import com.example.garageservice.car.model.CarDto;
import com.example.garageservice.common.Fuel;
import com.example.garageservice.garage.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;


@GetMapping
   public String getAll(Model model){
       model.addAttribute("cars",carService.findAll());
       return "car/list";
   }

   @GetMapping("/create")
   public String getCarCreateForm(Model model){
    model.addAttribute("fuels", Fuel.values());
    return "car/form";
   }

   @PostMapping("/create")
   public String createCar(Car car){
     carService.save(car);
     return "redirect:/cars";
   }
    @GetMapping(params = "garageId")
    @ResponseBody
    public List<CarDto> findAllByTeacher(@RequestParam int garageId){
        return carService.findAllByGarage(garageId).stream()
                .map(CarDto::fromEntity)
                .toList();
    }

    @DeleteMapping(params = "carId")
    @ResponseBody
    public void deleteCar(@RequestParam int carId) {
        carService.deleteById(carId);
    }
}
