package com.lab.cars.demo;

import com.lab.cars.demo.controller.CarController;
import com.lab.cars.demo.entities.Car;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.lab.cars.demo.services.CarManagerService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.mockito.internal.verification.VerificationModeFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    MockMvc servlet;

    @MockBean
    CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(servlet);
    }

    @Test
    public void whenGetCar_thenReturnCar() throws Exception {
        //Create a car
        Car newCar = new Car("tesla", "modelX");
        given(carManagerService.getCarDetails(anyLong())).willReturn(newCar);

        servlet.perform(MockMvcRequestBuilders.get("/car?carId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("model").value("modelX"));

    }


    @Test
    public void givenListCars_whenGetAllCars_thenReturnJsonArray() throws Exception {
        Car newCar = new Car("tesla", "modelX");
        Car newCar2 = new Car("tesla", "modelS");


        List<Car> allCars = Arrays.asList(newCar, newCar2);
        given(carManagerService.getAllCars()).willReturn(allCars);

        servlet.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].model").value("modelX"))
                .andExpect(jsonPath("$[1].model").value("modelS"));

        verify(carManagerService, VerificationModeFactory.times(1)).getAllCars();

    }

}
