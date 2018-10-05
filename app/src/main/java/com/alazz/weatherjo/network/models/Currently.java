package com.alazz.weatherjo.network.models;


import java.io.Serializable;

public class Currently implements Serializable {

    private Integer time;
    private String summary;
    private String icon;
    private Double precipIntensity;
    private Double precipProbability;
    private Double temperature;
    private Double apparentTemperature;
    private Double dewPoint;
    private Double humidity;
    private Double windSpeed;
    private Double cloudCover;
    private Double pressure;
    private Double ozone;


    public Integer getTime() {
        return time;
    }


    public void setTime(Integer time) {
        this.time = time;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public String getIcon() {
        return icon;
    }



    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getPrecipIntensity() {
        return precipIntensity;
    }


    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }


    public Double getPrecipProbability() {
        return precipProbability;
    }


    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }


    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }


    public Double getApparentTemperature() {
        return apparentTemperature;
    }


    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }


    public Double getDewPoint() {
        return dewPoint;
    }


    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }


    public Double getHumidity() {
        return humidity;
    }


    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }


    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }



    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }


    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

}
