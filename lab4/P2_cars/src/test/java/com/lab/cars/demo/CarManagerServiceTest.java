package com.lab.cars.demo;

import com.lab.cars.demo.entities.Car;
import com.lab.cars.demo.repository.CarRepository;
import com.lab.cars.demo.services.CarManagerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;
    

    @Test
    public void getAllCars_returnsAllCarsInfo() {
        Car newCar = new Car("tesla", "modelX");
        Car newCar2 = new Car("tesla", "modelS");

        List<Car> allCars = Arrays.asList(newCar, newCar2);
        given(carRepository.findAll()).willReturn(allCars);

        List<Car> cars = carManagerService.getAllCars();
        Assertions.assertThat(cars.get(0).getModel()).isEqualTo("modelX");
        Assertions.assertThat(cars.get(1).getModel()).isEqualTo("modelS");
    }

    @Test
    public void getCarDetails_returnsCarInfo() {
        Car newCar = new Car("tesla", "modelX");

        given(carRepository.findByCarId(1L)).willReturn(newCar);
        Car car = carManagerService.getCarDetails(1L);
        Assertions.assertThat(car.getModel()).isEqualTo("modelX");
    }



}
