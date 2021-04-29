package ua.tqs.airquality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.tqs.airquality.service.AirQualityService;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AirQualityRestController {

    @Autowired
    private AirQualityService airQualityService;

    @GetMapping("/air-quality/{city}")
    public void getAirQualityByCity(@PathVariable("city") String city) throws IOException, InterruptedException {
        System.out.println(airQualityService.getCurrentAirQualityByCity(city));
    }

    /*@PostMapping("/air-quality/{city}")
    public void airQualityByCity(@PathVariable("city") String city) throws IOException, InterruptedException {
        System.out.println(airQualityService.getCurrentAirQualityByCity(city));
    }
     */

}
