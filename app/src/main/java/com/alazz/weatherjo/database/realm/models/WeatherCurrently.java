package com.alazz.weatherjo.database.realm.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@SuppressWarnings({"WeakerAccess", "unused"})
public class WeatherCurrently extends RealmObject {

    @PrimaryKey
    private String id;
    String cityId;
    private String timezone;
    private Double offset;
    private long fetchtime;
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
    private Double windBearing;
    private Double cloudCover;
    private Double pressure;
    private Double ozone;



    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public void setCityId(String cityId) {
        this.cityId=cityId;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public long getFetchtime() {
        return fetchtime;
    }

    public void setFetchtime(long fetchtime) {
        this.fetchtime = fetchtime;
    }


    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getOffset() {
        return offset;
    }

    public void setOffset(Double offset) {
        this.offset = offset;
    }

    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
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

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Double getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(Double windBearing) {
        this.windBearing = windBearing;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }


//    public RealmList<com.alazz.weatherjo.database.realm.models.WeatherDaily> getWeatherDaily() {
//        return WeatherDaily;
//    }
//
//    public void setWeatherDaily(RealmList<com.alazz.weatherjo.database.realm.models.WeatherDaily> weatherDaily) {
//        WeatherDaily = weatherDaily;
//    }


}
