package com.karenfreemansmith.yetanotherweatherapp.weather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karen Freeman-Smith on 12/3/2016.
 */

public class Hour implements Parcelable {
  private long mTime;
  private String mSummary;
  private double mTemperature;
  private String mIcon;
  private String mTimezone;

  public Hour() {}

  public long getTime() {
    return mTime;
  }

  public void setTime(long time) {
    mTime = time;
  }

  public String getSummary() {
    return mSummary;
  }

  public void setSummary(String summary) {
    mSummary = summary;
  }

  public double getTemperature() {
    return mTemperature;
  }

  public void setTemperature(double temperature) {
    mTemperature = temperature;
  }

  public String getIcon() {
    return mIcon;
  }

  public void setIcon(String icon) {
    mIcon = icon;
  }

  public String getTimezone() {
    return mTimezone;
  }

  public void setTimezone(String timezone) {
    mTimezone = timezone;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeLong(mTime);
    parcel.writeString(mSummary);
    parcel.writeDouble(mTemperature);
    parcel.writeString(mIcon);
    parcel.writeString(mTimezone);
  }

  protected Hour(Parcel in) {
    mTime = in.readLong();
    mSummary = in.readString();
    mTemperature = in.readDouble();
    mIcon = in.readString();
    mTimezone = in.readString();
  }

  public static final Creator<Hour> CREATOR = new Creator<Hour>() {
    @Override
    public Hour createFromParcel(Parcel in) {
      return new Hour(in);
    }

    @Override
    public Hour[] newArray(int size) {
      return new Hour[size];
    }
  };

}
