package ua.tqs.airquality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.tqs.airquality.model.AirQuality;
import ua.tqs.airquality.model.City;
import ua.tqs.airquality.service.AirQualityService;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class AirQualityController {

    @Autowired
    private AirQualityService airQualityService;

    private static final Logger logger
            = Logger.getLogger(
            AirQualityController.class.getName());

    @GetMapping("/")
    public String homePage(Model model) {

        model.addAttribute("city", new City());
        return "index";
    }

    @RequestMapping(value = "/air-quality", method = RequestMethod.POST)
    public String postCityName(@ModelAttribute City city, Model model) throws IOException, InterruptedException {
        logger.log(Level.INFO, "Posted city name to search for airquality: " + city.toString());

        logger.log(Level.INFO, "External API Request for " + city.getName());
        HashMap<City, AirQuality> response = airQualityService.getCurrentAirQualityByCity(city.getName());
        logger.log(Level.INFO, "Response: " + response.toString());

        for (City i : response.keySet()) {
            AirQuality airQuality = response.get(i);

            logger.log(Level.INFO, "Return template, infos: " + city.toString() + airQuality.toString());
            model.addAttribute("airQuality", airQuality);
            model.addAttribute("city", i);
            return "results";
        }

        return "results";
    }

}