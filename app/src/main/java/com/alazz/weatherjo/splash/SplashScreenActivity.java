package com.alazz.weatherjo.splash;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.alazz.weatherjo.R;
import com.alazz.weatherjo.Utils.ActivityUtils;
import com.alazz.weatherjo.home.HomeActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.alazz.weatherjo.Utils.Constant.DELAY_SPLASH_SCREEN;


public class SplashScreenActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        startHomeActivity();

    }


    private void startHomeActivity() {

        new Handler().postDelayed(() -> ActivityUtils.onNavigateToActivity(this, HomeActivity.class, true), DELAY_SPLASH_SCREEN);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
