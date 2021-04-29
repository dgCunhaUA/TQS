package ua.tqs.airquality.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    @Autowired
    Cache cache;

    @Autowired
    City city;

    @Autowired
    AirQuality airQuality;

    @BeforeEach
    void setUp() {
        cache = new Cache();

        assertEquals(cache.getLastRequests().size(), 0);
        assertEquals(cache.getNumOfRequests(), 0);
        assertEquals(cache.getNumOfHits(), 0);
        assertEquals(cache.getNumOfMisses(), 0);

        city = new City();
        airQuality = new AirQuality();
    }

    @AfterEach
    void tearDown() {
        cache.getLastRequests().clear();
        cache.setNumOfRequests(0);
        cache.setNumOfHits(0);
        cache.setNumOfMisses(0);
    }

    @Test
    void storeRequest() {
        cache.storeRequest(city, airQuality);

        assertEquals(cache.getLastRequests().size(), 1);
    }

    @Test
    void requestsIncr() {
        cache.requestsIncr();

        assertEquals(cache.getNumOfRequests(), 1);
    }

    @Test
    void hitsIncr() {
        cache.hitsIncr();

        assertEquals(cache.getNumOfHits(), 1);
    }

    @Test
    void missesIncr() {
        cache.missesIncr();

        assertEquals(cache.getNumOfMisses(), 1);
    }

    @Test
    void exists() {
        String search_city = "Test";
        String search_city2 = "Test2";

        city.setName("\"Test\"");
        airQuality.setCO("10");

        cache.storeRequest(city, airQuality);
        assertEquals(cache.getLastRequests().size(), 1);

        HashMap<City, AirQuality> data = new HashMap<City, AirQuality>();
        data.put(city, airQuality);

        assertEquals(cache.exists(cache, search_city), data);
        assertEquals(data.size(), 1);


        HashMap<City, AirQuality> data2 = new HashMap<City, AirQuality>();

        assertEquals(cache.exists(cache, search_city2), data2);
        assertEquals(data2.size(), 0);
    }
}