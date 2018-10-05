package com.alazz.weatherjo.base;

import android.support.annotation.StringRes;


@SuppressWarnings("unused")
public interface BaseView<T> {

    void setPresenter(T presenter);

    void makeToast(@StringRes int stringId);

    void makeToast(String message);

}
