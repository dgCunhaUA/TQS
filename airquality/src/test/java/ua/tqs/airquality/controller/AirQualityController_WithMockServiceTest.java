package ua.tqs.airquality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.airquality.service.AirQualityService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AirQualityRestController.class)
public class AirQualityController_WithMockServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;


    //TODO: Post city name and return
}
