package com.alazz.weatherjo.Utils;


import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


@SuppressWarnings("unused")
public class ImmediateSchedulerProvider implements BaseSchedulerProvider {
    private static ImmediateSchedulerProvider INSTANCE;

    private ImmediateSchedulerProvider(){
    }

    public static synchronized ImmediateSchedulerProvider getInstance (){
        if (INSTANCE == null) {
            INSTANCE = new ImmediateSchedulerProvider();
        }
        return INSTANCE;
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
