package com.karenfreemansmith.yetanotherweatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "Calling API: ";
  private CurrentWeather mCurrentWeather;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    String apiKey = "e8567e4763c3878a56df623ac21a4b9f";
    double latitude = 37.8267;
    double longitude = -122.423;
    String forcastUrl = "https://api.forecast.io/forecast/" + apiKey + "/" + latitude + ", " + longitude;
    if (isNetworkAvailable()) {
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
          .url(forcastUrl)
          .build();

      Call call = client.newCall(request);
      call.enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
          try {
            String jsonData = response.body().string();
            Log.v(TAG, jsonData);
            if (response.isSuccessful()) {
              mCurrentWeather = getCurrentDetails(jsonData);
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

  private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {
    JSONObject forecast = new JSONObject(jsonData);
    String timezone = forecast.getString("timezone");
    Log.i(TAG, "From JSON: " + timezone);
    JSONObject currently = forecast.getJSONObject("currently");

    CurrentWeather currentWeather = new CurrentWeather();
    currentWeather.setHumidity(currently.getDouble("humidity"));
    currentWeather.setTime(currently.getLong("time"));
    currentWeather.setIcon(currently.getString("icon"));
    currentWeather.setPercipChance(currently.getDouble("percipProbability"));
    currentWeather.setSummary(currently.getString("summary"));
    currentWeather.setTemperature(currently.getDouble("temperature"));
    currentWeather.setTimeZone(currently.getString("timeZone"));

    return currentWeather;
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

  private void alertUserAboutError() {
    AlertDialogFragment dialog = new AlertDialogFragment();
    //dialog.show(getFragmentManager(), "error_dialog");
  }
}