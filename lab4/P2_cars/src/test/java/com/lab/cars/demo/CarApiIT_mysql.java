package com.lab.cars.demo;

import com.lab.cars.demo.entities.Car;
import com.lab.cars.demo.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CarApiIT_mysql {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar() {
        testRestTemplate.postForEntity("/car", new Car("audi", "Q7"), Car.class);

        List<Car> findAll = carRepository.findAll();
        assertThat(findAll).extracting(Car::getModel).containsOnly("Q7");
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200()  {
        carRepository.saveAndFlush(new Car("audi", "A4"));
        carRepository.saveAndFlush(new Car("tesla", "modelS"));


        ResponseEntity<List<Car>> response = testRestTemplate
                .exchange("/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("A4","modelS");

    }

}

