package vn.edu.usth.weather.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Locale;

import vn.edu.usth.weather.view.FailFragment;
import vn.edu.usth.weather.view.ForecastFragment;
import vn.edu.usth.weather.view.WeatherAndForecastFragment;
import vn.edu.usth.weather.view.WeatherFragment;

public class ViewpagerAdapter extends FragmentStatePagerAdapter {
    private static final int PAGE_COUNT = 3;

    public ViewpagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return WeatherAndForecastFragment.newInstance("0", "0");
            case 1:
                return new ForecastFragment();
            case 2:
                return new WeatherFragment();
        }
        return new FailFragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Locale locale = Locale.getDefault();
        if (locale.toString().equals("vi")) {
            switch (position) {
                case 0:
                    return "Hà Nội, Việt Nam";
                case 1:
                    return "Paris, Pháp";
                case 2:
                    return "Toulouse, Pháp";
            }
        } else {
            switch (position) {
                case 0:
                    return "Hanoi, Vietnam";
                case 1:
                    return "Paris, France";
                case 2:
                    return "Toulouse, France";
            }
        }
        return "fail";
    }
}
