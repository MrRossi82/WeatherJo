package com.alazz.weatherjo.Test;


import android.content.Context;

import com.alazz.weatherjo.R;
import com.alazz.weatherjo.Utils.IconManager;
import com.alazz.weatherjo.Utils.scheduler.SchedulerInjection;
import com.alazz.weatherjo.home.WeatherContract;
import com.alazz.weatherjo.home.WeatherPresenter;
import com.alazz.weatherjo.network.models.City;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static com.alazz.weatherjo.Utils.Constant.AMMAN_ID;
import static com.alazz.weatherjo.Utils.Constant.AMMAN_LOCATION;
import static com.alazz.weatherjo.Utils.Constant.AQABA_NAME;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class WeatherPresenterTest {
    @Mock
    private WeatherContract.View view;
    @Mock
    private WeatherPresenter presenter;
    @Mock
    private Context mMockContext;

    @Before
    public void setUp() {
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
        presenter = new WeatherPresenter(view,SchedulerInjection.provideSchedulerProvider());

    }

    @Test
    public void fetchWeatherData() {

       City mCity =new City();

        mCity.setId(AMMAN_ID);
        mCity.setName(AQABA_NAME);
        mCity.setLocation(AMMAN_LOCATION);
        presenter.fetchWeatherData(mCity);

    }

    @Test
    public void getIconResource() {
        when(mMockContext.getString(R.string.rain))
                .thenReturn("&#xf019;");

        assertEquals(IconManager.getIconResource("rain", mMockContext), "&#xf019;");
    }


}