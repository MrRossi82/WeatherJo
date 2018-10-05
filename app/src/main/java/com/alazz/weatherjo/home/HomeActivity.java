package com.alazz.weatherjo.home;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alazz.weatherjo.R;
import com.alazz.weatherjo.Utils.ActivityUtils;
import com.alazz.weatherjo.Utils.FontsUtils;
import com.alazz.weatherjo.network.models.City;
import com.alazz.weatherjo.settings.SettingsActivity;
import com.alazz.weatherjo.widgets.ViewPagerAdapter;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.alazz.weatherjo.Utils.Constant.AMMAN_ID;
import static com.alazz.weatherjo.Utils.Constant.AMMAN_LOCATION;
import static com.alazz.weatherjo.Utils.Constant.AMMAN_NAME;
import static com.alazz.weatherjo.Utils.Constant.AQABA_ID;
import static com.alazz.weatherjo.Utils.Constant.AQABA_LOCATION;
import static com.alazz.weatherjo.Utils.Constant.AQABA_NAME;
import static com.alazz.weatherjo.Utils.Constant.IRBID_ID;
import static com.alazz.weatherjo.Utils.Constant.IRBID_NAME;
import static com.alazz.weatherjo.Utils.Constant.IRBID__LOCATION;

@SuppressWarnings("WeakerAccess")
public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.home_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.home_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.home_tabs)
    TabLayout mTabLayout;

    @BindView(R.id.app_bar_home)
    AppBarLayout mAppBarLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        initView();


    }

    private void initView() {

        //  init Toolbar //

        setSupportActionBar(mToolbar);
        setTitle(null);


        //  init ViewPager & TabLayout //

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        Objects.requireNonNull(mTabLayout.getTabAt(1)).select();
        FontsUtils.changeTabsFont(mTabLayout);


    }

    private void setupViewPager(ViewPager viewPager ) {


        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFrag(WeatherFragment.newInstance(getCityAqabaInfo()),getString(R.string.home_aqaba_city_title));
        mViewPagerAdapter.addFrag(WeatherFragment.newInstance(getCityAmmanInfo()),getString(R.string.home_amman_city_title));
        mViewPagerAdapter.addFrag(WeatherFragment.newInstance(getCityIrbidInfo()),getString(R.string.home_irbid_city_title));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.setCurrentItem(1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                mIndicatorView.setSelection(position);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){

                    case 0:
                        mAppBarLayout.setBackgroundColor(getResources().getColor(R.color.ColorAqaba));
                        setStatusBarColor(R.color.ColorAqaba);

                          break;
                    case 1:
                        mAppBarLayout.setBackgroundColor(getResources().getColor(R.color.colorAmman));
                        setStatusBarColor(R.color.colorAmman);

                        break;
                    case 2:
                        mAppBarLayout.setBackgroundColor(getResources().getColor(R.color.colorIrbid));
                        setStatusBarColor(R.color.colorIrbid);

                        break;


                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void setStatusBarColor(int color){

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(color));
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.action_settings) {

            ActivityUtils.onNavigateToActivity(this, SettingsActivity.class, false);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private City getCityAmmanInfo(){

        City mCity =new City();

        mCity.setId(AMMAN_ID);
        mCity.setName(AMMAN_NAME);
        mCity.setLocation(AMMAN_LOCATION);

        return mCity;
    }

    private City getCityAqabaInfo(){

        City mCity =new City();

        mCity.setId(AQABA_ID);
        mCity.setName(AQABA_NAME);
        mCity.setLocation(AQABA_LOCATION);

        return mCity;
    }

    private City getCityIrbidInfo(){

        City mCity =new City();

        mCity.setId(IRBID_ID);
        mCity.setName(IRBID_NAME);
        mCity.setLocation(IRBID__LOCATION);

        return mCity;
    }




    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
