package ua.tqs.airquality.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tqs.airquality.entity.AirQuality;
import ua.tqs.airquality.entity.Cache;
import ua.tqs.airquality.entity.City;
import ua.tqs.airquality.repository.AmbeeRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AirQualityService {

    @Autowired
    private AmbeeRepository ambeeRepository;

    private Cache cache = new Cache();


    public HashMap<City, AirQuality> getCurrentAirQualityByCity(String city) throws IOException, InterruptedException {

        // Verificar se ja existe em cache
        HashMap<City, AirQuality> data = cache.exists(cache, city);

        if( data.size() == 1 )
            return data;


        String response = ambeeRepository.getCurrentAirQualityByCity(city);

        // Incr number os Request
        cache.requestsIncr();

        var gson = new Gson();
        Map jsonMap = gson.fromJson(response, Map.class);

        if( jsonMap.get("message").equals("success") )
            cache.hitsIncr();
        else {
            cache.missesIncr();

            AirQuality airQuality = new AirQuality();
            airQuality.setCO("-");
            airQuality.setNO2("-");
            airQuality.setOZONE("-");
            airQuality.setPM10("-");
            airQuality.setPM25("-");
            airQuality.setSO2("-");

            City cityCached = new City();
            cityCached.setName("\"" + city + "\"");

            City cityObj = new City();
            cityObj.setName("City Not Found");

            cache.storeRequest(cityCached, airQuality);
            data.put(cityObj, airQuality);

            return data;
        }
        // Converter o http response to json
        ArrayList response_array = (ArrayList) jsonMap.get("stations");
        JsonObject jsonObject = gson.toJsonTree(response_array.get(0)).getAsJsonObject();


        // Recolher os valores
        AirQuality airQuality = new AirQuality();
        airQuality.setCO(jsonObject.get("CO").toString());
        airQuality.setNO2(jsonObject.get("NO2").toString());
        airQuality.setOZONE(jsonObject.get("OZONE").toString());
        airQuality.setPM10(jsonObject.get("PM10").toString());
        airQuality.setPM25(jsonObject.get("PM25").toString());
        airQuality.setSO2(jsonObject.get("SO2").toString());

        City cityObj = new City();
        cityObj.setName(jsonObject.get("city").toString());
        cityObj.setCountry(jsonObject.get("countryCode").toString());
        cityObj.setLat(jsonObject.get("lat").toString());
        cityObj.setLng(jsonObject.get("lng").toString());
        cityObj.setPostalCode(jsonObject.get("postalCode").toString());

        // Store Request Response In Cache
        cache.storeRequest(cityObj, airQuality);

        data.put(cityObj, airQuality);

        return data;
    }
}


