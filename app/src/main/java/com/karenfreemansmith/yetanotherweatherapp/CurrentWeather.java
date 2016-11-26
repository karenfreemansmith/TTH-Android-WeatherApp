package com.karenfreemansmith.yetanotherweatherapp;

/**
 * Created by Karen Freeman-Smith on 11/21/2016.
 */

public class CurrentWeather {
  private String mIcon;
  private long mTime;
  private double mTemperature;
  private double mHumidity;
  private double mPercipChance;
  private String mSummary;

  public String getIcon() {
    return mIcon;
  }

  public void setIcon(String icon) {
    mIcon = icon;
  }

  public long getTime() {
    return mTime;
  }

  public void setTime(long time) {
    mTime = time;
  }

  public double getTemperature() {
    return mTemperature;
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

  public double getPercipChance() {
    return mPercipChance;
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
