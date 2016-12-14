package com.karenfreemansmith.yetanotherweatherapp.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.karenfreemansmith.yetanotherweatherapp.Constants;
import com.karenfreemansmith.yetanotherweatherapp.R;
import com.karenfreemansmith.yetanotherweatherapp.adapters.DayAdapter;
import com.karenfreemansmith.yetanotherweatherapp.weather.Day;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DailyForecastActivity extends AppCompatActivity {
  @Bind(R.id.dailyListView) ListView mDailyListView;
  Day[]  mDays;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_daily_forecast);
    ButterKnife.bind(this);

    Intent intent = getIntent();
    Parcelable[] parcelables = intent.getParcelableArrayExtra(Constants.DAILY_FORECAST);
    mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

    DayAdapter adapter = new DayAdapter(this, mDays);
    mDailyListView.setAdapter(adapter);
  }
}
