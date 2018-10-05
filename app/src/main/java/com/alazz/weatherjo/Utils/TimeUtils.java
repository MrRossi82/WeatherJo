package com.alazz.weatherjo.Utils;

import com.alazz.weatherjo.R;
import com.alazz.weatherjo.myApplication.WeatherJoApp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.alazz.weatherjo.Utils.Constant.TIME_PATTERN;

public class TimeUtils {


    public static String getTime(long value) {

        return WeatherJoApp.getInstance().getString(R.string.updated_at,new SimpleDateFormat(TIME_PATTERN,
                Locale.getDefault()).format(new Date(value)).toUpperCase());
    }

}
