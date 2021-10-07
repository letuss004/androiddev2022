package vn.edu.usth.weather;

import android.util.Log;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
        switch (position) {
            case 0:
                return "Hanoi, Vietnam";
            case 1:
                return "Paris, France";
            case 2:
                return "Toulouse, France";
        }
        return "fail";
    }
}
