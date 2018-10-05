package com.alazz.weatherjo.Utils;

import java.util.Locale;
import java.util.UUID;

import static com.alazz.weatherjo.Utils.Constant.DISPLAY_LANGUAGE_ARABIC;


public class Utils {


    public static String getUniqueID() {
        return UUID.randomUUID().toString();
    }


    public static String getDisplayLanguage() {


        if (Locale.getDefault().getDisplayLanguage().equals(DISPLAY_LANGUAGE_ARABIC)){

            return "ar";
        }

        return "en";
    }






}
