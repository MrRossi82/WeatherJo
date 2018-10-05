package com.alazz.weatherjo.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class CityWeather implements Serializable {

    @SerializedName("latitude")
    @Expose
    private Double Latitude;
    @SerializedName("longitude")
    @Expose
    private Double Longitude;
    @SerializedName("currently")
    @Expose
    private Currently Currently;
    private long FetchTime;

    public long getFetchTime() {
        return FetchTime;
    }

    public void setFetchTime(long fetchtime) {
        this.FetchTime = fetchtime;
    }


    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        this.Latitude = latitude;
    }


    public Double getLongitude() {
        return Longitude;
    }


    public void setLongitude(Double longitude) {
        this.Longitude = longitude;
    }


    public Currently getCurrently() {
        return Currently;
    }


    public void setCurrently(Currently currently) {
        this.Currently = currently;
    }



}
