package com.karenfreemansmith.yetanotherweatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.karenfreemansmith.yetanotherweatherapp.R;
import com.karenfreemansmith.yetanotherweatherapp.weather.Day;

/**
 * Created by Karen Freeman-Smith on 12/14/2016.
 */

public class DayAdapter extends BaseAdapter {
  private Context mContext;
  private Day[] mDays;

  public DayAdapter(Context context, Day[] days) {
    mContext = context;
    mDays = days;
  }

  @Override
  public int getCount() {
    return mDays.length;
  }

  @Override
  public Object getItem(int position) {
    return mDays[position];
  }

  @Override
  public long getItemId(int position) {
    return 0; // not being used...
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;

    if(convertView==null) {
      convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
      holder = new ViewHolder();
      holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);

      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    Day day = mDays[position];
    // **** NULL POINTER ERROR in this block of code - seems to be missing "day" information! ****
//    Drawable drawable = ContextCompat.getDrawable(this, day.getIconId());
//    holder.iconImageView.setImageDrawable(drawable);
//    holder.iconImageView.setImageResource(day.getIconId());
//    holder.tempText.setText(day.getTemperatureMax()+"");
//    holder.dayText.setText(day.getDayOfWeek());

    return convertView;
  }

  private static class ViewHolder {
    ImageView iconImageView;
    TextView tempText;
    TextView dayText;
  }
}
