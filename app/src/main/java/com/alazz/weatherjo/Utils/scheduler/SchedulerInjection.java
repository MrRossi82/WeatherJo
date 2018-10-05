package com.alazz.weatherjo.Utils.scheduler;


import com.alazz.weatherjo.Utils.BaseSchedulerProvider;
import com.alazz.weatherjo.Utils.SchedulerProvider;

public class SchedulerInjection {

    public static BaseSchedulerProvider provideSchedulerProvider(){
        return SchedulerProvider.getInstance();
    }
}
