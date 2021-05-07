package com.lab.cars.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lab.cars.demo.entities.Car;
import com.lab.cars.demo.services.CarManagerService;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarManagerService carManagerService;


    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carManagerService.save(car);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/car")
    public Car getCarById(@RequestParam(name = "carId") Long carId){
        return carManagerService.getCarDetails(carId);
    }
}
