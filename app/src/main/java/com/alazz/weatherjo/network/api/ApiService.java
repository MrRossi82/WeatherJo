package com.alazz.weatherjo.network.api;


import com.alazz.weatherjo.network.models.CityWeather;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.alazz.weatherjo.Utils.Constant.API_FORECAST;


public interface ApiService {

    @GET(API_FORECAST)
    Single<CityWeather> getForecast(@Path(value="latLon", encoded=true) String latLong, @Path("apiKey") String APIKEY, @Query("units") String units, @Query("lang") String lang);


}
