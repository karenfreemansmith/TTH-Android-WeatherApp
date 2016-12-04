package com.karenfreemansmith.yetanotherweatherapp.ui;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karenfreemansmith.yetanotherweatherapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DailyForecastActivity extends AppCompatActivity {
  @Bind(R.id.dailyListView)
  ListView mDailyListView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_daily_forecast);
    ButterKnife.bind(this);

    String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daysOfWeek);
    mDailyListView.setAdapter(adapter);
  }
}
