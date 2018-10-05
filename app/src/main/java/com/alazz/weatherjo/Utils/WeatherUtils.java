package com.alazz.weatherjo.Utils;


import com.alazz.weatherjo.R;
import com.alazz.weatherjo.myApplication.WeatherJoApp;

public class WeatherUtils {



    public static String getHumidity(Double value) {

        return Math.round(value * 100) + "%";
    }


    public static String getTemperature(Double value) {

        return WeatherJoApp.getInstance().getString(R.string.degree, Math.round(value));
    }


    public static String getWindSpeed(Double value) {

        return WeatherJoApp.getInstance().getString(R.string.km_h, Math.round(value));

    }


    public static String getPressure(Double value) {

        return WeatherJoApp.getInstance().getString(R.string.hPa, Math.round(value));
    }

    public static String getPrecipitation(Double value) {

         return Math.round(value * 100) + "%";
    }

    public static String getClouds(Double value) {

        return Math.round(value * 100) + "%";


    }


    public static String getFahrenheitUnit(Double value) {

        return WeatherJoApp.getInstance().getString(R.string.degree, Math.round(value * 1.8 + 32));

    }



}
