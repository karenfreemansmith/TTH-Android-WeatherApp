package com.karenfreemansmith.yetanotherweatherapp.weather;

import com.karenfreemansmith.yetanotherweatherapp.R;

/**
 * Created by Karen Freeman-Smith on 12/3/2016.
 */

public class Forecast {
  private Current mCurrent;
  private Hour[] mHourly;
  private Day[] mDaily;

  public Current getCurrent() {
    return mCurrent;
  }

  public void setCurrent(Current current) {
    mCurrent = current;
  }

  public Hour[] getHourly() {
    return mHourly;
  }

  public void setHourly(Hour[] hourly) {
    mHourly = hourly;
  }

  public Day[] getDaily() {
    return mDaily;
  }

  public void setDaily(Day[] daily) {
    mDaily = daily;
  }

  public static int getIconId(String iconString) {
    int iconId = R.drawable.clear_day;

    if(iconString.equals("clear-day")) {
      iconId = R.drawable.clear_day;
    } else if (iconString.equals("clear-night")) {
      iconId = R.drawable.clear_night;
    } else if (iconString.equals("clear-night")) {
      iconId = R.drawable.clear_night;
    } else if (iconString.equals("cloudy")) {
      iconId = R.drawable.cloudy;
    } else if (iconString.equals("cloudy-night")) {
      iconId = R.drawable.cloudy_night;
    } else if (iconString.equals("partly-cloudy-day")) {
      iconId = R.drawable.partly_cloudy;
    } else if (iconString.equals("partly-cloudy-night")) {
      iconId = R.drawable.partly_cloudy;
    } else if (iconString.equals("rain")) {
      iconId = R.drawable.rain;
    } else if (iconString.equals("sleet")) {
      iconId = R.drawable.sleet;
    } else if (iconString.equals("snow")) {
      iconId = R.drawable.snow;
    } else if (iconString.equals("sunny")) {
      iconId = R.drawable.sunny;
    } else if (iconString.equals("fog")) {
      iconId = R.drawable.fog;
    } else if (iconString.equals("wind")) {
      iconId = R.drawable.wind;
    }

    return iconId;
  }
}
