package com.google.android.material.datepicker;

import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.C1124R;
import java.util.Calendar;
import java.util.Locale;

class DaysOfWeekAdapter extends BaseAdapter {
    private static final int CALENDAR_DAY_STYLE = (VERSION.SDK_INT >= 26 ? 4 : 1);
    private static final int NARROW_FORMAT = 4;
    private final Calendar calendar = UtcDates.getUtcCalendar();
    private final int daysInWeek = this.calendar.getMaximum(7);
    private final int firstDayOfWeek = this.calendar.getFirstDayOfWeek();

    public Integer getItem(int position) {
        if (position >= this.daysInWeek) {
            return null;
        }
        return Integer.valueOf(positionToDayOfWeek(position));
    }

    public long getItemId(int position) {
        return 0;
    }

    public int getCount() {
        return this.daysInWeek;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView dayOfWeek = (TextView) convertView;
        if (convertView == null) {
            dayOfWeek = (TextView) LayoutInflater.from(parent.getContext()).inflate(C1124R.layout.mtrl_calendar_day_of_week, parent, false);
        }
        this.calendar.set(7, positionToDayOfWeek(position));
        dayOfWeek.setText(this.calendar.getDisplayName(7, CALENDAR_DAY_STYLE, Locale.getDefault()));
        dayOfWeek.setContentDescription(String.format(parent.getContext().getString(C1124R.string.mtrl_picker_day_of_week_column_header), new Object[]{this.calendar.getDisplayName(7, 2, Locale.getDefault())}));
        return dayOfWeek;
    }

    private int positionToDayOfWeek(int position) {
        int dayConstant = this.firstDayOfWeek + position;
        int i = this.daysInWeek;
        if (dayConstant > i) {
            return dayConstant - i;
        }
        return dayConstant;
    }
}
