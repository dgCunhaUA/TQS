package ua.tqs.airquality.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AmbeeRepositoryTest {

    @Mock
    private AmbeeRepository ambeeRepository;

    @Test
    public void whenGetCityAirQuality_thenReturnAirQuality() throws IOException, InterruptedException {

        String response_body = "{\"message\":\"success\",\"stations\":[{\"_id\":\"60363d6c8f2bb86af93ba8fb\",\"placeId\":\"591a22fa84590ec0d7381c22d8b2d0849e4c3dd7f645170b3f6bebe1fab31ba8\",\"CO\":0.20208333333333334,\"NO2\":7.836,\"OZONE\":3.449,\"PM10\":24.814,\"PM25\":6.8,\"SO2\":3.384,\"city\":\"Viseu\",\"countryCode\":\"PT\",\"division\":\"Viseu\",\"lat\":40.661,\"lng\":-7.9097,\"placeName\":\"Viseu\",\"postalCode\":\"3500-004\",\"state\":\"Viseu\",\"updatedAt\":\"2021-04-23 11:00:00\",\"AQI\":28,\"aqiInfo\":{\"pollutant\":\"PM2.5\",\"concentration\":6.8,\"category\":\"Good\"}}]}\n";

        when( ambeeRepository.getCurrentAirQualityByCity("Viseu")).thenReturn(response_body);

        assertEquals( ambeeRepository.getCurrentAirQualityByCity("Viseu"), response_body );
        assertThat( ambeeRepository.getCurrentAirQualityByCity("Viseu") ).isInstanceOf(String.class);
    }

    @Test
    public void whenGetWrongCityNameAirQuality_thenReturnError() throws IOException, InterruptedException {

        String response_body = "{\"message\":\"Sorry, we couldn't find the specified place\",\"data\":[]}";

        when( ambeeRepository.getCurrentAirQualityByCity("Error")).thenReturn(response_body);

        assertEquals( ambeeRepository.getCurrentAirQualityByCity("Error"), response_body );
        assertThat( ambeeRepository.getCurrentAirQualityByCity("Error") ).isInstanceOf(String.class);
    }
}