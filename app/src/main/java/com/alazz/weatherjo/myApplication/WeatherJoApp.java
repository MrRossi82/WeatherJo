package com.alazz.weatherjo.myApplication;

import android.annotation.SuppressLint;
import android.app.Application;

import com.alazz.weatherjo.Utils.FontsUtils;

import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.alazz.weatherjo.Utils.Constant.DISPLAY_LANGUAGE_ARABIC;


public class WeatherJoApp extends Application {

    @SuppressLint("StaticFieldLeak")
    private static WeatherJoApp mDaesaApp;
    public static RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();

        mDaesaApp=this;


        Realm.init(this);
        realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);


        // Change Font app if Phone Language is arabic ///

        if (Locale.getDefault().getDisplayLanguage().equals(DISPLAY_LANGUAGE_ARABIC)){

            FontsUtils.setFontArabic();
        }


    }


    public static WeatherJoApp getInstance() {

        return mDaesaApp;
    }







}

