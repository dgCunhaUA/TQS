package ua.tqs.airquality.entity;

import java.util.HashMap;

public class Cache {

    private long timeToLive;
    private int numOfRequests = 0;
    private int numOfHits = 0;
    private int numOfMisses = 0;
    private HashMap<City, AirQuality> lastRequests = new HashMap<City, AirQuality>();


    public void storeRequest(City city, AirQuality airQuality) {
        this.lastRequests.put(city, airQuality);
        //this.requestsExpiration.put(name, getCurrentTimeInMillis() + this.timeToLive * 1000);
    }

    public void requestsIncr() { numOfRequests++; }

    public void hitsIncr() {
        numOfHits++;
    }

    public void missesIncr() {
        numOfMisses++;
    }

    public HashMap<City, AirQuality> exists(Cache cache, String city) {
        HashMap<City, AirQuality> data = new HashMap<City, AirQuality>();

        for (City i : cache.getLastRequests().keySet()) {
            if( i.getName().substring(1, i.getName().length() - 1).equals(city) ) {
                System.out.println("Found in Cache");
                data.put(i, cache.getLastRequests().get(i));

                return data;
            }
        }

        System.out.println("Not Found in Cache");
        return data;
    }


    public long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public int getNumOfRequests() {
        return numOfRequests;
    }

    public void setNumOfRequests(int numOfRequests) {
        this.numOfRequests = numOfRequests;
    }

    public int getNumOfHits() {
        return numOfHits;
    }

    public void setNumOfHits(int numOfHits) {
        this.numOfHits = numOfHits;
    }

    public int getNumOfMisses() {
        return numOfMisses;
    }

    public void setNumOfMisses(int numOfMisses) {
        this.numOfMisses = numOfMisses;
    }

    public HashMap<City, AirQuality> getLastRequests() {
        return lastRequests;
    }

    public void setLastRequests(HashMap<City, AirQuality> cache) {
        this.lastRequests = cache;
    }
}
