package vn.edu.usth.weather.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import vn.edu.usth.weather.R;
import vn.edu.usth.weather.controller.ViewpagerAdapter;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setVietnamese();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

//        setMp3Sound();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.usth_appbar, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // do st if app bar icon is touched
        switch (item.getItemId()) {
            case R.id.appbar_refresh:
                Toast toast = new Toast(this);
                toast.setText("Refreshing");
                toast.show();

            case R.id.appbar_settings:
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setMp3Sound() {
        Thread thread = new Thread(() -> {
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.bensound_betterdays);
            mediaPlayer.start();

            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mediaPlayer.stop();
        });
        thread.start();
    }

    private void setVietnamese() {
        Locale locale = new Locale("vi");
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My Lang", "vi");
        editor.apply();
    }

    private void setLang(String lang) {
        Locale locale = new Locale(lang);
        Resources resources = getBaseContext().getResources();
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
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