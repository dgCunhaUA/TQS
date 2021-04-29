package ua.tqs.airquality.repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.tqs.airquality.entity.AirQuality;
import ua.tqs.airquality.service.AirQualityService;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AmbeeRepositoryTest {

    @Mock
    private AmbeeRepository ambeeRepository;


    /* Erro
        (GET https://api.ambeedata.com/latest/by-city?city=dfwerfewqrf) 400
        {"message":"Sorry, we couldn't find the specified place","data":[]}
        java.net.http.HttpHeaders@2ff56d4e { {access-control-allow-credentials=[true], access-control-allow-headers=[Origin, X-Requested-With, Content-Type, Accept], access-control-allow-origin=[*], connection=[keep-alive], content-length=[67], content-security-policy=[default-src 'self';base-uri 'self';block-all-mixed-content;font-src 'self' https: data:;frame-ancestors 'self';img-src 'self' data:;object-src 'none';script-src 'self';script-src-attr 'none';style-src 'self' https: 'unsafe-inline';upgrade-insecure-requests], content-type=[application/json; charset=utf-8], date=[Sat, 24 Apr 2021 15:52:50 GMT], etag=[W/"43-tUok3PAmBdjz+etCcp/Jo8rtQvk"], expect-ct=[max-age=0], keep-alive=[timeout=5], referrer-policy=[no-referrer], strict-transport-security=[max-age=15552000; includeSubDomains], vary=[Origin], x-content-type-options=[nosniff], x-dns-prefetch-control=[off], x-download-options=[noopen], x-permitted-cross-domain-policies=[none], x-xss-protection=[0]} }
        */

    /* Sucess
        (GET https://api.ambeedata.com/latest/by-city?city=Viseu) 200
        {"message":"success","stations":[{"_id":"60363d6c8f2bb86af93ba8fb","placeId":"591a22fa84590ec0d7381c22d8b2d0849e4c3dd7f645170b3f6bebe1fab31ba8","CO":0.20208333333333334,"NO2":7.836,"OZONE":3.449,"PM10":24.814,"PM25":6.8,"SO2":3.384,"city":"Viseu","countryCode":"PT","division":"Viseu","lat":40.661,"lng":-7.9097,"placeName":"Viseu","postalCode":"3500-004","state":"Viseu","updatedAt":"2021-04-23 11:00:00","AQI":28,"aqiInfo":{"pollutant":"PM2.5","concentration":6.8,"category":"Good"}}]}
        java.net.http.HttpHeaders@9376d3bd { {access-control-allow-credentials=[true], access-control-allow-headers=[Origin, X-Requested-With, Content-Type, Accept], access-control-allow-origin=[*], connection=[keep-alive], content-length=[487], content-security-policy=[default-src 'self';base-uri 'self';block-all-mixed-content;font-src 'self' https: data:;frame-ancestors 'self';img-src 'self' data:;object-src 'none';script-src 'self';script-src-attr 'none';style-src 'self' https: 'unsafe-inline';upgrade-insecure-requests], content-type=[application/json; charset=utf-8], date=[Sat, 24 Apr 2021 15:53:37 GMT], etag=[W/"1e7-OBS1/0IpSVOoBSdfY3iLYVCu32U"], expect-ct=[max-age=0], keep-alive=[timeout=5], referrer-policy=[no-referrer], strict-transport-security=[max-age=15552000; includeSubDomains], vary=[Origin], x-content-type-options=[nosniff], x-dns-prefetch-control=[off], x-download-options=[noopen], x-permitted-cross-domain-policies=[none], x-xss-protection=[0]} }
        */

    @Test
    public void whenGetCityAirQuality_thenReturnAirQuality() throws IOException, InterruptedException {

        String response_body = "{\"message\":\"success\",\"stations\":[{\"_id\":\"60363d6c8f2bb86af93ba8fb\",\"placeId\":\"591a22fa84590ec0d7381c22d8b2d0849e4c3dd7f645170b3f6bebe1fab31ba8\",\"CO\":0.20208333333333334,\"NO2\":7.836,\"OZONE\":3.449,\"PM10\":24.814,\"PM25\":6.8,\"SO2\":3.384,\"city\":\"Viseu\",\"countryCode\":\"PT\",\"division\":\"Viseu\",\"lat\":40.661,\"lng\":-7.9097,\"placeName\":\"Viseu\",\"postalCode\":\"3500-004\",\"state\":\"Viseu\",\"updatedAt\":\"2021-04-23 11:00:00\",\"AQI\":28,\"aqiInfo\":{\"pollutant\":\"PM2.5\",\"concentration\":6.8,\"category\":\"Good\"}}]}\n";

        when( ambeeRepository.getCurrentAirQualityByCity("Viseu")).thenReturn(response_body);

        assertEquals( ambeeRepository.getCurrentAirQualityByCity("Viseu"), response_body );

    }

    @Test
    public void whenGetWrongCityNameAirQuality_thenReturnError() throws IOException, InterruptedException {

        String response_body = "{\"message\":\"Sorry, we couldn't find the specified place\",\"data\":[]}";

        when( ambeeRepository.getCurrentAirQualityByCity("Error")).thenReturn(response_body);

        assertEquals( ambeeRepository.getCurrentAirQualityByCity("Error"), response_body );
    }
}