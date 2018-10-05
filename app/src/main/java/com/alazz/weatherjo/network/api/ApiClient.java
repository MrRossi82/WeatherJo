package com.alazz.weatherjo.network.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.alazz.weatherjo.Utils.Constant.BASE_URL;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static ApiService getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(createClient(context))
                    .build();
        }


        return retrofit.create(ApiService.class);
    }



}