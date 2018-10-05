package com.alazz.weatherjo.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alazz.weatherjo.R;
public class SharedPreferencesManager {

    private final Context mContext;
    private final SharedPreferences default_prefence;

    public SharedPreferencesManager(Context context) {
        this.mContext = context;
        default_prefence = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private String str() {
        return mContext.getString(R.string.pref_key_text_unit);
    }



    public String getUnit(){

        return default_prefence.getString(str(), "C");
    }


    }


