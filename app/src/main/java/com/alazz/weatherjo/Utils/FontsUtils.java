package com.alazz.weatherjo.Utils;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alazz.weatherjo.myApplication.WeatherJoApp;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.alazz.weatherjo.Utils.Constant.FONT_ICON;
import static com.alazz.weatherjo.Utils.Constant.FONT_PATH;

public class FontsUtils {


    public static void setFontArabic() {

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(FONT_PATH)
                .build());

    }

    public static Typeface getFontTypeFace() {

        return Typeface.createFromAsset(WeatherJoApp.getInstance().getAssets(), FONT_ICON);

    }



    public static void changeTabsFont(ViewGroup viewGroup) {

        Typeface type = Typeface.createFromAsset(WeatherJoApp.getInstance().getAssets(), FONT_PATH);

        ViewGroup vg = (ViewGroup) viewGroup.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(type);
                }
            }
        }
    }


}
