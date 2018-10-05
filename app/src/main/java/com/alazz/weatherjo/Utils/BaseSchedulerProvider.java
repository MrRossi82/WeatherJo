package com.alazz.weatherjo.Utils;

import io.reactivex.Scheduler;


@SuppressWarnings("unused")
public interface BaseSchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
