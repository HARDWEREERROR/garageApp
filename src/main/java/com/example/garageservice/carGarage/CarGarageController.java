package com.example.garageservice.carGarage;

import com.example.garageservice.car.CarService;
import com.example.garageservice.carGarage.model.CarGarage;
import com.example.garageservice.garage.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carGarages")
public class CarGarageController {

    private final CarService carService;

    private final GarageService garageService;

    private final CarGarageService carGarageService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("carGarages", carGarageService.findAll());
        return "carGarage/list";
    }

    @GetMapping("/create")
    public String getCarGarageCreateForm(Model model) {
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("garages", garageService.findAll());
        return "carGarage/form";
    }

    @PostMapping("/create")
    public String createCarGarage(CarGarage carGarage, @RequestParam("carId") int carId, @RequestParam("garageId") int garageId) {
        carGarageService.save(carGarage, carId, garageId);
        return "redirect:/carGarages";
    }

    @GetMapping("/unpark")
    public String getUnparkForm(Model model) {
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("garages", garageService.findAll());
        return "carGarage/unpark";
    }

    @PostMapping("/unpark")
    public String unpark(@RequestParam("carId") int carId, @RequestParam("garageId") int garageId) {
        carGarageService.unpark(carId, garageId);
        return "redirect:/carGarages";
    }
}
