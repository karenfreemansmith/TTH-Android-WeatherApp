package com.karenfreemansmith.yetanotherweatherapp.weather;

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
}
