package com.alazz.weatherjo.home;


import android.os.Handler;

import com.alazz.weatherjo.Utils.BaseSchedulerProvider;
import com.alazz.weatherjo.Utils.Utils;
import com.alazz.weatherjo.database.realm.RealmController;
import com.alazz.weatherjo.network.api.ApiClient;
import com.alazz.weatherjo.network.models.City;
import com.alazz.weatherjo.network.models.CityWeather;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

import static com.alazz.weatherjo.Utils.Constant.API_KEY;
import static com.alazz.weatherjo.Utils.Constant.API_UNITS;
import static com.alazz.weatherjo.Utils.Constant.DELAY_GET_DATA;


public class WeatherPresenter implements WeatherContract.Presenter {

    private final BaseSchedulerProvider schedulerProvider;
    private final WeatherContract.View mView;
    private final CompositeDisposable disposableSubscriptions;

    public WeatherPresenter(WeatherContract.View view, BaseSchedulerProvider schedulerProvider) {


        mView = view;
        this.schedulerProvider = schedulerProvider;
        disposableSubscriptions = new CompositeDisposable();
        mView.setPresenter(this);
    }

    @Override
    public void fetchWeatherData(City city){

        // Fetch Weather Data From Api //


        disposableSubscriptions.add(

                    ApiClient.getClient().getForecast(city.getLocation(),API_KEY,API_UNITS,Utils.getDisplayLanguage())
                            .subscribeOn(schedulerProvider.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<CityWeather>() {

                                @Override
                                public void onSuccess(CityWeather cityWeather) {


                                    // Weather data successfully fetched from Api //

                                    if (cityWeather != null) {
                                        cityWeather.setFetchTime(System.currentTimeMillis());
                                        city.setCityWeather(cityWeather);
                                        city.setId(city.getId());


                                        /// Insert or Update Weather Data to Local Database(Realm)) ///

                                        InsertOrUpdateWeatherData(city);

                                    }

                                }

                                @Override
                                public void onError(Throwable e) {

                                    // If there is a problem fetching the latest data from api or , connection Problems,
                                    // show the latest weather data added from the database //

                                    getWeatherData(city.getId());

                                }

                            })

            );


        }

    private void InsertOrUpdateWeatherData(City city) {

        disposableSubscriptions.add(
                RealmController.with().insertWeatherCurrently(city)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(
                                new DisposableCompletableObserver() {
                                    @Override
                                    public void onComplete() {

                                        /// Insert weather information into databases successfully //

                                        getWeatherData(city.getId());
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                        /// Displays message to the user if the weather data fails to insert ///

                                        mView.makeToast(e.getMessage());

                                    }
                                })
        );

    }


    private void getWeatherData(String CityId) {

        // Get Weather Data From Local Database(Realm) //

        mView.showProgressIndicator(true);

        disposableSubscriptions.add(
                RealmController.with().getCityWeatherFromCityId(CityId)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(
                                new DisposableSingleObserver<CityWeather>() {
                                    @Override
                                    public void onSuccess(CityWeather cityWeather) {

                                        new Handler().postDelayed(() -> {

                                            mView.showProgressIndicator(false);
                                            mView.getWeatherDetails(cityWeather);

                                        }, DELAY_GET_DATA);

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                        // If no Weather Data is inside the Local Database , the user will be shown a Empty Page //

                                        mView.showEmptyView();

                                    }
                                })
        );


    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        disposableSubscriptions.clear();

    }
}
