package com.lab.cars.demo;

import com.lab.cars.demo.entities.Car;
import com.lab.cars.demo.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarApiIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    public void setUpTestCars() {
        carRepository.saveAndFlush(new Car("audi", "A4"));
        carRepository.saveAndFlush(new Car("tesla", "modelX"));
    }

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }


    @Test
    void getCar_returnsCarDetails() {
        ResponseEntity<Car> response = testRestTemplate.getForEntity("/car?carId=1", Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getModel()).isEqualTo("A4");
    }

    @Test
    void getAllCars_returnsCars() {

        ResponseEntity<List<Car>> response = testRestTemplate
                .exchange("/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("A4","modelX");
    }

    @Test
    void whenValidInput_thenCreateCar() {
        testRestTemplate.postForEntity("/car", new Car("audi", "Q7"), Car.class);

        List<Car> findAll = carRepository.findAll();
        assertThat(findAll).extracting(Car::getModel).containsExactly("A4", "modelX", "Q7");
    }

}