package vn.edu.usth.weather;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForecastFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ForecastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForecastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForecastFragment newInstance(String param1, String param2) {
        ForecastFragment fragment = new ForecastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n", "RtlHardcoded"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.fragment_forecast, container, false);

        return view;
    }

    private LinearLayout setUpLinearV(FrameLayout view) {
        LinearLayout linearV = new LinearLayout(view.getContext());
        linearV.setOrientation(LinearLayout.VERTICAL);
        linearV.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return linearV;
    }

    @NonNull
    private LinearLayout setUpLinearH(@NonNull FrameLayout view) {
        LinearLayout linearH = new LinearLayout(view.getContext());
        linearH.setOrientation(LinearLayout.HORIZONTAL);
        linearH.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return linearH;
    }

    private TextView setUpTvThursday(FrameLayout view) {
        TextView tvThursday = new TextView(view.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200);
        layoutParams.gravity = Gravity.TOP;
        layoutParams.setMargins(20, 0, 0, 0);
        tvThursday.setText("Thursday");
        tvThursday.setTextSize(30);
        tvThursday.setLayoutParams(layoutParams);
        return tvThursday;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private ImageView setUpIvWeather(FrameLayout view) {
        ImageView ivWeatherIcon = new ImageView(view.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 120);
        layoutParams.gravity = Gravity.TOP;
        layoutParams.setMargins(0, 0, 0, 0);
        ivWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.weather_icon_21));
        ivWeatherIcon.setLayoutParams(layoutParams);
        return ivWeatherIcon;
    }


}
