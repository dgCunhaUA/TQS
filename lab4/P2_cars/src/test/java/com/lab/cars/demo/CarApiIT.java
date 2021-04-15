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
        ResponseEntity<Car> entity = testRestTemplate.getForEntity("/car?carId=1", Car.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().getModel()).isEqualTo("A4");
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
    void postCar_returnsCar() {
        // request url
        String url = "http://localhost:8080/car";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // request body parameters
        Map<String, Object> map = new HashMap<>();
        map.put("car", new Car("audi", "Q7"));

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        // send POST request
        //ResponseEntity<Car> response = restTemplate.postForEntity(url, entity, new ParameterizedTypeReference<ResponseEntity<Car>>());

        //assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(entity.getBody().getModel()).isEqualTo("Q7");
    }

}