package com.example.garageservice.car;


import com.example.garageservice.car.model.Car;
import com.example.garageservice.common.Fuel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

   public String createCar(Car car){
     carService.save(car);
     return "redirect:/cars";
   }
}
