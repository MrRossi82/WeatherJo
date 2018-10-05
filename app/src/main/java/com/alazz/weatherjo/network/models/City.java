package com.alazz.weatherjo.network.models;

import java.io.Serializable;


 public class City implements Serializable {

    private String Id;
    private String Name;

     public String getLocation() {
         return Location;
     }

     public void setLocation(String location) {
         Location = location;
     }

     private String Location;


    private CityWeather CityWeather;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public CityWeather getCityWeather() {
        return CityWeather;
    }

    public void setCityWeather(CityWeather cityWeather) {
        this.CityWeather = cityWeather;
    }


}
