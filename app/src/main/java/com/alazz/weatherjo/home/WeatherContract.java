package com.alazz.weatherjo.home;


import com.alazz.weatherjo.base.BasePresenter;
import com.alazz.weatherjo.base.BaseView;
import com.alazz.weatherjo.network.models.City;
import com.alazz.weatherjo.network.models.CityWeather;

public interface WeatherContract {

    interface View extends BaseView<Presenter> {

        void setPresenter(Presenter presenter);

        void getWeatherDetails(CityWeather cityWeather);

        void showProgressIndicator(boolean show);

        void showEmptyView();

    }

    @SuppressWarnings("unused")
    interface Presenter extends BasePresenter {

        void fetchWeatherData(City city);

    }
}
