package com.karenfreemansmith.yetanotherweatherapp.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.karenfreemansmith.yetanotherweatherapp.weather.Current;
import com.karenfreemansmith.yetanotherweatherapp.R;
import com.karenfreemansmith.yetanotherweatherapp.weather.Day;
import com.karenfreemansmith.yetanotherweatherapp.weather.Forecast;
import com.karenfreemansmith.yetanotherweatherapp.weather.Hour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "Calling API: ";
  private Forecast mForecast;

  @Bind(R.id.temperatureLabel) TextView mTempLabel;
  @Bind(R.id.timeLabel) TextView mTimeLabel;
  @Bind(R.id.humidityAmount) TextView mHumLabel;
  @Bind(R.id.percipitationAmount) TextView mPercipLabel;
  @Bind(R.id.summaryLabel) TextView mSummaryLabel;
  @Bind(R.id.iconImage) ImageView mIconView;
  @Bind(R.id.refreshImageView) ImageView mRefreshView;
  @Bind(R.id.progressBar) ProgressBar mProgressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    mProgressBar.setVisibility(View.INVISIBLE);

    final double latitude = 45.4462;
    final double longitude = -122.6393;

    mRefreshView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getForcast(latitude, longitude);
      }
    });

    getForcast(latitude, longitude);
  }

  private void getForcast(double latitude, double longitude) {
    String apiKey = "e8567e4763c3878a56df623ac21a4b9f";
    String forcastUrl = "https://api.forecast.io/forecast/" + apiKey + "/" + latitude + ", " + longitude;
    if (isNetworkAvailable()) {
      toggleProgressBar();

      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
          .url(forcastUrl)
          .build();

      Call call = client.newCall(request);
      call.enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              toggleProgressBar();
            }
          });
          alertUserAboutError();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              toggleProgressBar();
            }
          });
          try {
            String jsonData = response.body().string();
            Log.v(TAG, jsonData);
            if (response.isSuccessful()) {
              mForecast = parseForcastDetails(jsonData);
              runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  updateDisplay();
                }
              });
            } else {
              alertUserAboutError();
            }
          } catch (IOException e) {
            Log.e(TAG, "Exception caught:", e);
          } catch (JSONException e) {
            Log.e(TAG, "Exception caught:", e);
          }
        }
      });
    } else {
      Toast.makeText(this, "Network is unavailable!", Toast.LENGTH_LONG).show();
    }
  }

  private void toggleProgressBar() {
    if(mProgressBar.getVisibility()==View.INVISIBLE) {
      mProgressBar.setVisibility(View.VISIBLE);
      mRefreshView.setVisibility(View.INVISIBLE);
    } else {
      mProgressBar.setVisibility(View.INVISIBLE);
      mRefreshView.setVisibility(View.VISIBLE);
    }

  }

  private Forecast parseForcastDetails(String jsonData) throws JSONException{
    Forecast forecast = new Forecast();

    forecast.setCurrent(getCurrentDetails(jsonData));
    forecast.setHourly(getHourly(jsonData));
    forecast.setDaily(getDaily(jsonData));

    return forecast;
  }

  private Day[] getDaily(String jsonData) throws JSONException {
    JSONObject forecast = new JSONObject(jsonData);
    String timezone = forecast.getString("timezone");
    JSONObject daily = forecast.getJSONObject("daily");
    JSONArray data = forecast.getJSONArray("data");

    Day[] days = new Day[data.length()];

    for(int i=0; i<data.length(); i++) {
      JSONObject jsonDay = data.getJSONObject(i);
      Day day = new Day();

      day.setSummary(jsonDay.getString("summary"));
      day.setIcon(jsonDay.getString("icon"));
      day.setTemperatureMax(jsonDay.getDouble("temperatureMax"));
      day.setTime(jsonDay.getLong("time"));
      day.setTimezone(jsonDay.getString("timezone"));

      days[i] = day;
    }
    return days;
  }

  private Hour[] getHourly(String jsonData) throws JSONException {
    JSONObject forecast = new JSONObject(jsonData);
    String timezone = forecast.getString("timezone");
    JSONObject hourly = forecast.getJSONObject("hourly");
    JSONArray data = hourly.getJSONArray("data");

    Hour[] hours = new Hour[data.length()];
    for(int i=0; i<data.length(); i++) {
      JSONObject jsonHour = data.getJSONObject(i);
      Hour hour = new Hour();
      hour.setSummary(jsonHour.getString("summary"));
      hour.setTemperature(jsonHour.getDouble("temperature"));
      hour.setIcon(jsonHour.getString("icon"));
      hour.setTime(jsonHour.getLong("time"));
      hour.setTimezone(jsonHour.getString("timezone"));
      hours[i] = hour;
    }
    return hours;
  }

  private Current getCurrentDetails(String jsonData) throws JSONException {
    JSONObject forecast = new JSONObject(jsonData);
    String timezone = forecast.getString("timezone");
    Log.i(TAG, "From JSON: " + timezone);
    JSONObject currently = forecast.getJSONObject("currently");

    Current current = new Current();
    current.setHumidity(currently.getDouble("humidity"));
    current.setTime(currently.getLong("time"));
    current.setIcon(currently.getString("icon"));
    current.setPercipChance(currently.getDouble("precipProbability"));
    current.setSummary(currently.getString("summary"));
    current.setTemperature(currently.getDouble("temperature"));
    current.setTimeZone(timezone);

    return current;
  }

  private boolean isNetworkAvailable() {
    ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = manager.getActiveNetworkInfo();
    boolean isAvailable = false;
    if (networkInfo != null && networkInfo.isConnected()) {
      isAvailable = true;
    }
    return isAvailable;
  }

  private void updateDisplay() {
    Current current = mForecast.getCurrent();
    mTempLabel.setText(current.getTemperature() + "");
    mTimeLabel.setText("At " + current.getFormattedTime() + " it will be: ");
    mHumLabel.setText(current.getHumidity() + "");
    mPercipLabel.setText(current.getPercipChance() + "%");
    mSummaryLabel.setText(current.getSummary());

    Drawable drawable = ContextCompat.getDrawable(this, current.getIconId());
    mIconView.setImageDrawable(drawable);
    //Drawable drawable = getResources().getDrawable(drawable, null);
  }

  private void alertUserAboutError() {
    AlertDialogFragment dialog = new AlertDialogFragment();
    //dialog.show(getFragmentManager(), "error_dialog");
  }
}