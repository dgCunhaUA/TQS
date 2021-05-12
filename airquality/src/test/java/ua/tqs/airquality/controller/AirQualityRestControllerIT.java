package ua.tqs.airquality.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.airquality.model.AirQuality;
import ua.tqs.airquality.model.City;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AirQualityRestController.class)
public class AirQualityRestControllerIT {

    @Autowired
    private MockMvc mvc;


    @Test
    public void whenGetAirQualityByCityName_thenReturnData( ) throws Exception {

        AirQuality airQuality = new AirQuality();
        airQuality.setCO("0.20208333333333334");
        airQuality.setNO2("7.836");
        airQuality.setOZONE("3.449");
        airQuality.setPM10("24.814");
        airQuality.setPM25("6.8");
        airQuality.setSO2("3.384");

        City cityObj = new City();
        cityObj.setName("\"Viseu\"");
        cityObj.setCountry("\"PT\"");
        cityObj.setLat("40.661");
        cityObj.setLng("-7.9097");
        cityObj.setPostalCode("\"3500-004\"");


        //TODO: Verify city information

        mvc.perform(get("/api/air-quality/Viseu").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()
                );

    }

    @Test
    public void whenGetAirQualityByWrongCityName_thenReturnData( ) throws Exception {

        AirQuality airQuality = new AirQuality();
        airQuality.setCO("-");
        airQuality.setNO2("-");
        airQuality.setOZONE("-");
        airQuality.setPM10("-");
        airQuality.setPM25("-");
        airQuality.setSO2("-");

        City cityObj = new City();
        cityObj.setName("City Not Found");
        cityObj.setPostalCode("-");
        cityObj.setCountry("-");
        cityObj.setLat("-");
        cityObj.setLng("-");


        //TODO: Verify city information
        mvc.perform(get("/api/air-quality/Errorrrrr").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"City{name='City Not Found', country='-', lat='-', lng='-', postalCode='-'}\":{\"co\":\"-\",\"no2\":\"-\",\"ozone\":\"-\",\"pm10\":\"-\",\"pm25\":\"-\",\"so2\":\"-\"}}")
                );

    }
}
