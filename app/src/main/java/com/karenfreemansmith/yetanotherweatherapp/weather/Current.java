package com.karenfreemansmith.yetanotherweatherapp.weather;

import android.content.Loader;

import com.karenfreemansmith.yetanotherweatherapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Karen Freeman-Smith on 11/21/2016.
 */

public class Current {
  private String mIcon;
  private long mTime;
  private double mTemperature;
  private double mHumidity;
  private double mPercipChance;
  private String mSummary;
  private String mTimeZone;

  public String getTimeZone() {
    return mTimeZone;
  }

  public void setTimeZone(String timeZone) {
    mTimeZone = timeZone;
  }

  public String getIcon() {
    return mIcon;
  }

  public int getIconId() {
    return Forecast.getIconId(mIcon);
  }


  public void setIcon(String icon) {
    mIcon = icon;
  }

  public long getTime() {
    return mTime;
  }

  public String getFormattedTime() {
    SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
    formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
    Date dateTime = new Date(getTime() * 1000);
    String timeString = formatter.format(dateTime);

    return timeString;
  }

  public void setTime(long time) {
    mTime = time;
  }

  public int getTemperature() {
    return (int) Math.round(mTemperature);
  }

  public void setTemperature(double temperature) {
    mTemperature = temperature;
  }

  public double getHumidity() {
    return mHumidity;
  }

  public void setHumidity(double humidity) {
    mHumidity = humidity;
  }

  public int getPercipChance() {
    double percipPercentage = mPercipChance * 100;
    return (int) Math.round(percipPercentage);
  }

  public void setPercipChance(double percipChance) {
    mPercipChance = percipChance;
  }

  public String getSummary() {
    return mSummary;
  }

  public void setSummary(String summary) {
    mSummary = summary;
  }
}
