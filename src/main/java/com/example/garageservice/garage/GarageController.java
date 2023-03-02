package com.example.garageservice.garage;

import com.example.garageservice.common.Fuel;
import com.example.garageservice.garage.model.Garage;
import com.example.garageservice.garage.model.GarageDto;
import lombok.AllArgsConstructor;
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
@RequestMapping("/garages")
public class GarageController {

    private final GarageService garageService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("garages", garageService.findAll());
        return "garage/list";
    }

    @GetMapping("/create")
    public String getGarageCreateForm(Model model) {
        model.addAttribute("fuels", Fuel.values());
        return "garage/form";
    }

    @PostMapping("/create")
    public String createGarage(Garage garage) {
        garageService.save(garage);
        return "redirect:/garages";
    }

    @GetMapping(params = "fuel")
    @ResponseBody
    public List<GarageDto> findAllByFuel(@RequestParam Fuel fuel) {
        return garageService.findAllByFuel(fuel).stream()
                .map(GarageDto::fromEntity)
                .toList();
    }

    @DeleteMapping(params = "garageId")
    @ResponseBody
    public void deleteGarage(@RequestParam int garageId) {
        garageService.deleteById(garageId);
    }
}
