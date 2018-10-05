package com.alazz.weatherjo.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.alazz.weatherjo.R;
import com.alazz.weatherjo.Utils.IconManager;
import com.alazz.weatherjo.Utils.SharedPreferencesManager;
import com.alazz.weatherjo.Utils.TimeUtils;
import com.alazz.weatherjo.Utils.WeatherUtils;
import com.alazz.weatherjo.Utils.scheduler.SchedulerInjection;
import com.alazz.weatherjo.myApplication.WeatherJoApp;
import com.alazz.weatherjo.network.models.City;
import com.alazz.weatherjo.network.models.CityWeather;
import com.alazz.weatherjo.widgets.views.InfoWeatherLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.alazz.weatherjo.Utils.Constant.AMMAN_ID;
import static com.alazz.weatherjo.Utils.Constant.AQABA_ID;
import static com.alazz.weatherjo.Utils.Constant.CELSIUS;
import static com.alazz.weatherjo.Utils.Constant.IRBID_ID;
import static com.alazz.weatherjo.Utils.FontsUtils.getFontTypeFace;


@SuppressWarnings("WeakerAccess")
public class WeatherFragment extends Fragment implements WeatherContract.View {


    @BindView(R.id.textView_icon_summary)
    TextView mIconSummaryTextView;

    @BindView(R.id.textView_temperature)
    TextView mTemperatureTextView;

    @BindView(R.id.textView_summary)
    TextView mSummaryTextView;

    @BindView(R.id.InfoWeatherLayout_humidity)
    InfoWeatherLayout mHumidityInfoWeatherLayout;

    @BindView(R.id.InfoWeatherLayout_wind)
    InfoWeatherLayout mWindSpeedInfoWeatherLayout;

    @BindView(R.id.InfoWeatherLayout_pressure)
    InfoWeatherLayout mPressureInfoWeatherLayout;

    @BindView(R.id.InfoWeatherLayout_clouds)
    InfoWeatherLayout mCloudsInfoWeatherLayout;

    @BindView(R.id.InfoWeatherLayout_precipitation)
    InfoWeatherLayout mPrecipitationInfoWeatherLayout;

    @BindView(R.id.InfoWeatherLayout_feels_like)
    InfoWeatherLayout mFeelsLikeInfoWeatherLayout;

    @BindView(R.id.textView_updated_at)
    TextView mUpdateDateTextView;

    @BindView(R.id.view_empty)
    ConstraintLayout mEmptyView;

    @BindView(R.id.view_weather_content)
    ConstraintLayout mWeatherContentView;

    @BindView(R.id.view_main)
    ConstraintLayout mMainView;

    @BindView(R.id.loading_progress)
    LottieAnimationView mProgressBar;

    @BindView(R.id.swipeRefres)
    SwipeRefreshLayout mSwipeRefreshLayout;

    SharedPreferencesManager mSharedPreferencesManager;


    private WeatherContract.Presenter mPresenter;

    City mCity;

    public WeatherFragment() {


    }

    public static WeatherFragment newInstance(City city) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("city_bundle", city);
        fragment.setArguments(bundle);
        return fragment ;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCity =new City();
            mCity =(City) getArguments().getSerializable("city_bundle");
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView =inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this,mView);

        initView();
        return mView;
    }


    private void initView(){

        mSharedPreferencesManager  = new SharedPreferencesManager(WeatherJoApp.getInstance());
        mSwipeRefreshLayout.setOnRefreshListener(this::OnRefresh);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = new WeatherPresenter(this,
                    SchedulerInjection.provideSchedulerProvider());
        }


    }

    @Override
    public void getWeatherDetails(CityWeather cityWeather) {


        mIconSummaryTextView.setText(IconManager.getIconResource(cityWeather.getCurrently().getIcon(), getActivity()));

        mIconSummaryTextView.setTypeface(getFontTypeFace());

        mSummaryTextView.setText(cityWeather.getCurrently().getSummary());


        switch (mCity.getId()){

            case AMMAN_ID:
                mMainView.setBackgroundColor(IconManager.getColorResource(AMMAN_ID, getActivity()));
                break;
            case AQABA_ID:
                mMainView.setBackgroundColor(IconManager.getColorResource(AQABA_ID, getActivity()));
                break;
            case IRBID_ID:
                mMainView.setBackgroundColor(IconManager.getColorResource(IRBID_ID, getActivity()));
                break;
        }


        String Unit= mSharedPreferencesManager.getUnit();

        if (Unit.equals(CELSIUS)) {

            mTemperatureTextView.setText(WeatherUtils.getTemperature(cityWeather.getCurrently().getTemperature()));
            mFeelsLikeInfoWeatherLayout.setValue(WeatherUtils.getTemperature(cityWeather.getCurrently().getApparentTemperature()));

        } else {

            mTemperatureTextView.setText(WeatherUtils.getFahrenheitUnit(cityWeather.getCurrently().getTemperature()));
            mFeelsLikeInfoWeatherLayout.setValue(WeatherUtils.getFahrenheitUnit(cityWeather.getCurrently().getApparentTemperature()));
        }


        mHumidityInfoWeatherLayout.setValue(WeatherUtils.getHumidity(cityWeather.getCurrently().getHumidity()));

        mWindSpeedInfoWeatherLayout.setValue(WeatherUtils.getWindSpeed(cityWeather.getCurrently().getWindSpeed()));

        mPressureInfoWeatherLayout.setValue(WeatherUtils.getPressure(cityWeather.getCurrently().getPressure()));

        mPrecipitationInfoWeatherLayout.setValue(WeatherUtils.getPrecipitation(cityWeather.getCurrently().getPrecipProbability()));

        mCloudsInfoWeatherLayout.setValue(WeatherUtils.getClouds(cityWeather.getCurrently().getCloudCover()));


        mUpdateDateTextView.setText(TimeUtils.getTime(cityWeather.getFetchTime()));

    }




    @Override
    public void showProgressIndicator(boolean show) {


        if (show){
            mProgressBar.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
            mWeatherContentView.setVisibility(View.GONE);



        } else {
            mWeatherContentView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.GONE);

        }


    }

    @Override
    public void showEmptyView() {


        mEmptyView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mWeatherContentView.setVisibility(View.GONE);

        switch (mCity.getId()){

            case AMMAN_ID:
                mMainView.setBackgroundColor(IconManager.getColorResource(AMMAN_ID, getActivity()));
                break;
            case AQABA_ID:
                mMainView.setBackgroundColor(IconManager.getColorResource(AQABA_ID, getActivity()));
                break;
            case IRBID_ID:
                mMainView.setBackgroundColor(IconManager.getColorResource(IRBID_ID, getActivity()));
                break;
        }

    }

    public void OnRefresh(){


        mPresenter.fetchWeatherData(mCity);

        if (mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void makeToast(int stringId) {
        Toast.makeText(getActivity(),stringId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }




    @Override
    public void onStart() {
        super.onStart();
        mPresenter.fetchWeatherData(mCity);


    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.unsubscribe();
    }




}
