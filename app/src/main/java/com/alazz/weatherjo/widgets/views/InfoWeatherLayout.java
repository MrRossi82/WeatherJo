package com.alazz.weatherjo.widgets.views;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import com.alazz.weatherjo.R;

import static com.alazz.weatherjo.Utils.Constant.FONT_ICON;

public class InfoWeatherLayout extends ConstraintLayout {

    private String main;
    private String sub;
    private String ic;

    public InfoWeatherLayout(Context context) {
        super(context);
        initializeViews(context);
        if(!isInEditMode())
            initializeViews(context);
    }

    public InfoWeatherLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttr(context, attrs);
        initializeViews(context);
    }

    public InfoWeatherLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttr(context, attrs);
        initializeViews(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InfoWeatherLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        parseAttr(context, attrs);
        initializeViews(context);
    }

    public void setValue(String main) {
        ((TextView) findViewById(R.id.main)).setText(main);
    }


    @SuppressLint("CustomViewStyleable")
    private void parseAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InfoDisplayWidget, 0, 0);
        main = typedArray.getString(R.styleable.InfoDisplayWidget_main);
        sub = typedArray.getString(R.styleable.InfoDisplayWidget_sub);
        ic = typedArray.getString(R.styleable.InfoDisplayWidget_ic);
        typedArray.recycle();
    }

    private void initializeViews(Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), FONT_ICON);

        inflate(context, R.layout.info, this);
        TextView main = findViewById(R.id.main);
        TextView sub = findViewById(R.id.sub);
        main.setText(this.main);
        sub.setText(this.sub);

        TextView icon = findViewById(R.id.icon);
        icon.setText(this.ic);
        icon.setTypeface(font);
    }
}
