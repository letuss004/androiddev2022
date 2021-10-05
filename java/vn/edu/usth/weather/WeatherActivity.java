package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.Collection;
import java.util.Collections;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

//        ForecastFragment forecastFragment = new ForecastFragment();
//        WeatherFragment weatherFragment = new WeatherFragment();

        //TODO: WHY I CAN'T DO IT PROGRAMMATICALLY
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_tag, weatherFragment).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, forecastFragment).commit();

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}