package com.alazz.weatherjo.database.realm;

import com.alazz.weatherjo.Utils.Utils;
import com.alazz.weatherjo.database.realm.models.WeatherCurrently;
import com.alazz.weatherjo.myApplication.WeatherJoApp;
import com.alazz.weatherjo.network.models.City;
import com.alazz.weatherjo.network.models.CityWeather;
import com.alazz.weatherjo.network.models.Currently;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.realm.Realm;

import static com.alazz.weatherjo.Utils.Constant.CITY_ID;


public class RealmController {

    private static RealmController instance;
    private Realm mRealm;

    private RealmController() {

        mRealm = Realm.getInstance(WeatherJoApp.realmConfiguration);

    }

    public static RealmController with() {
        if (instance == null) instance = new RealmController();
        return instance;
    }


    public Completable insertWeatherCurrently(final City city) {


        return Completable.create(


                e -> mRealm.executeTransactionAsync(realm -> {

                    WeatherCurrently old = realm.where(WeatherCurrently.class)
                            .equalTo(CITY_ID, city.getId()).findFirst();
                    if (old != null)
                        old.deleteFromRealm();

                    WeatherCurrently c = realm.createObject(WeatherCurrently.class,Utils.getUniqueID());

                    c.setCityId(city.getId());
                    c.setFetchtime(city.getCityWeather().getFetchTime());
                    c.setTime(city.getCityWeather().getCurrently().getTime());
                    c.setSummary(city.getCityWeather().getCurrently().getSummary());
                    c.setIcon(city.getCityWeather().getCurrently().getIcon());
                    c.setPrecipIntensity(city.getCityWeather().getCurrently().getPrecipIntensity());
                    c.setPrecipProbability(city.getCityWeather().getCurrently().getPrecipProbability());
                    c.setTemperature(city.getCityWeather().getCurrently().getTemperature());
                    c.setApparentTemperature(city.getCityWeather().getCurrently().getApparentTemperature());
                    c.setDewPoint(city.getCityWeather().getCurrently().getDewPoint());
                    c.setHumidity(city.getCityWeather().getCurrently().getHumidity());
                    c.setWindSpeed(city.getCityWeather().getCurrently().getWindSpeed());
                    c.setCloudCover(city.getCityWeather().getCurrently().getCloudCover());
                    c.setPressure(city.getCityWeather().getCurrently().getPressure());
                    c.setOzone(city.getCityWeather().getCurrently().getOzone());


                }, e::onComplete, e::onError)
        );


    }



    public Single<CityWeather> getCityWeatherFromCityId(String cityId) {

        return Single.create(emitter -> {

            if (mRealm.isClosed()){

                mRealm = Realm.getInstance(WeatherJoApp.realmConfiguration);

            }

            if (!mRealm.isClosed() || !mRealm.isEmpty()) {


                CityWeather cityWeather = new CityWeather();


                WeatherCurrently currentlyTable = mRealm.where(WeatherCurrently.class).equalTo(CITY_ID, cityId).findFirst();


                if (currentlyTable != null) {

                    cityWeather.setFetchTime(currentlyTable.getFetchtime());
                    Currently currently = new Currently();
                    currently.setTime(currentlyTable.getTime());
                    currently.setSummary(currentlyTable.getSummary());
                    currently.setIcon(currentlyTable.getIcon());
                    currently.setPrecipIntensity(currentlyTable.getPrecipIntensity());
                    currently.setPrecipProbability(currentlyTable.getPrecipProbability());
                    currently.setTemperature(currentlyTable.getTemperature());
                    currently.setApparentTemperature(currentlyTable.getApparentTemperature());
                    currently.setDewPoint(currentlyTable.getDewPoint());
                    currently.setHumidity(currentlyTable.getHumidity());
                    currently.setWindSpeed(currentlyTable.getWindSpeed());
                    currently.setCloudCover(currentlyTable.getCloudCover());
                    currently.setPressure(currentlyTable.getPressure());
                    currently.setOzone(currentlyTable.getOzone());
                    cityWeather.setCurrently(currently);

                }


                if (currentlyTable !=null){

                    emitter.onSuccess(cityWeather);

                } else {

                    emitter.onError(new Exception());
                }


            } else {

                emitter.onError(new Exception("Realm is Closed"));
            }


        });

    }




}


