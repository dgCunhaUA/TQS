package ua.tqs.airquality.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.tqs.airquality.entity.AirQuality;
import ua.tqs.airquality.entity.Cache;
import ua.tqs.airquality.entity.City;
import ua.tqs.airquality.service.AirQualityService;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AirQualityController {

    @Autowired
    private AirQualityService airQualityService;

    @GetMapping(value = "/")
    public String homePage(Model model) {

        //City city = new City();
        //city.setPostalCode("ola");

        //model.addAttribute("city", city);
        model.addAttribute("city", new City());
        return "index";
    }

    @GetMapping("/air-quality/{city}")
    public String getAirQualityByCity(@PathVariable("city") String city) throws IOException, InterruptedException {
        System.out.println(airQualityService.getCurrentAirQualityByCity(city));

        return "index";
    }

    @RequestMapping(value = "/air-quality", method = RequestMethod.POST)
    public String update(@ModelAttribute City city, Model model) throws IOException, InterruptedException {

        //HttpResponse response = airQualityService.getCurrentAirQualityByCity(city.getName());

        HashMap<City, AirQuality> response = airQualityService.getCurrentAirQualityByCity(city.getName());
        //HashMap response = airQualityService.getCurrentAirQualityByCity(city.getName());

        for (City i : response.keySet()) {
            AirQuality airQuality = response.get(i);
            City cityObj = i;

            model.addAttribute("airQuality", airQuality);
            model.addAttribute("city", cityObj);
            return "results";
        }

        return "results";
    }
}