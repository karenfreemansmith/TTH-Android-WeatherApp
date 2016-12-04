package com.karenfreemansmith.yetanotherweatherapp.ui;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.karenfreemansmith.yetanotherweatherapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HourlyForecastActivity extends AppCompatActivity {
  @Bind(R.id.hourlyListView)
  ListView mHourlyListView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hourly_forecast);
    ButterKnife.bind(this);
  }
}
