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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import vn.edu.usth.weather.R;
import vn.edu.usth.weather.controller.ViewpagerAdapter;

public class WeatherActivity extends AppCompatActivity {
    private final Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            String content = msg.getData().getString("simulate");
            Toast.makeText(WeatherActivity.this, content, Toast.LENGTH_SHORT).show();
        }
    };


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

    public interface ToastCallBack {
        void callBack(String json);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // do st if app bar icon is touched
        int id = item.getItemId();

        switch (id) {

            case R.id.appbar_refresh:
//                System.out.println("clickkkkkkkkkkkkkkkkkkkkkk");
//                networkSimulate(json -> Toast.makeText(WeatherActivity.this, json, Toast.LENGTH_SHORT).show()); // pw13
//                ToastHandler(); // pw13

//                Toast toast = new Toast(this);
//                Thread thread = new Thread(() -> {
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    String json = "Some simulate api will be here but i lazy";
//                    toast.setText(json);
//                    toast.show();
//
//                });
//                thread.start();
//                backgroundThreadUpgrade(); // PW14
//                asyncTaskNetwork(); // Pw15
                asyncTaskVolley();
                break;

            case R.id.appbar_settings:
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("StaticFieldLeak")
    private void asyncTaskVolley() {

        AsyncTask<String, String, Bitmap> asyncTask = new AsyncTask<String, String, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                RequestQueue queue = Volley.newRequestQueue(getBaseContext());
                Response.Listener<Bitmap> listener = response -> {
                    ImageView iv = (ImageView) findViewById(R.id.logo);
                    iv.setImageBitmap(response);
                };
                ImageRequest imageRequest = new ImageRequest("https://www.usth.edu.vn/uploads/logo_moi-eng.png",
                        listener, 0, 0, ImageView.ScaleType.CENTER,
                        Bitmap.Config.ARGB_8888, null);
                queue.add(imageRequest);
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
            }
        };

        asyncTask.execute();
    }


    @SuppressLint("StaticFieldLeak")
    private void asyncTaskNetwork() {

        AsyncTask<String, Integer, Bitmap> asyncTask = new AsyncTask<String, Integer, Bitmap>() {
            private Bitmap bitmap;
            private URL url;
            private HttpURLConnection connection;

            @Override
            protected void onPreExecute() {
                try {
                    url = new URL("https://www.usth.edu.vn/uploads/logo_moi-eng.png");
                    System.out.println("URL ready");

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                ImageView logo = (ImageView) findViewById(R.id.logo);
                logo.setImageBitmap(bitmap);
                System.out.println("Post posted");
            }

            @Override
            protected void onProgressUpdate(Integer... response) {
                super.onProgressUpdate(response);
            }

            InputStream is;

            @Override
            protected Bitmap doInBackground(String... strings) {

                try {
                    System.out.println("start try catch");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    // allow reading response code and response data connection.
                    connection.connect();
                    System.out.println("after connect");
                    // Receive response
                    int response = connection.getResponseCode();
                    Log.i("USTHWeather", "The response is: " + response);
                    // Process image response
                    is = connection.getInputStream();
                    System.out.println("end try catch");

                } catch (IOException e) {
                    e.printStackTrace();
                }

                bitmap = BitmapFactory.decodeStream(is);

                connection.disconnect();
                System.out.println("Disconnected");
                return bitmap;
            }
        };
        asyncTask.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void backgroundThreadUpgrade() {
        System.out.println("clicked");
        AsyncTask<String, String, String> asyncTask = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {

            }

            @Override
            protected void onPostExecute(String s) {
                String json = "simulate weather: [" +
                        " {" +
                        "   id: 800," +
                        "   main: Clear," +
                        "   description: clear sky," +
                        "   icon: 01d" +
                        " }" +
                        "  ]";
                System.out.println("OnProgress ");
                Toast.makeText(getBaseContext(), json, Toast.LENGTH_SHORT).show();
            }
        };

        asyncTask.execute();
    }

    private void ToastHandler() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Bundle bundle = new Bundle();
            bundle.putString("simulate", "weather: [" +
                    " {" +
                    "   id: 800," +
                    "   main: Clear," +
                    "   description: clear sky," +
                    "   icon: 01d" +
                    " }" +
                    "  ]");

            Message msg = new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);
        });
        thread.start();
    }

    @Deprecated
    private void networkSimulate(ToastCallBack toastCallBack) {
        AtomicReference<String> json = new AtomicReference<>("");
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Looper.prepare();
            json.set("Some simulate api will be here but i lazy");
            toastCallBack.callBack(json.get());
        });
        thread.start();

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