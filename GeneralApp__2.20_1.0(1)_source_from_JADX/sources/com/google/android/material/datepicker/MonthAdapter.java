package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.C1124R;

class MonthAdapter extends BaseAdapter {
    static final int MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
    final CalendarConstraints calendarConstraints;
    CalendarStyle calendarStyle;
    final DateSelector<?> dateSelector;
    final Month month;

    MonthAdapter(Month month2, DateSelector<?> dateSelector2, CalendarConstraints calendarConstraints2) {
        this.month = month2;
        this.dateSelector = dateSelector2;
        this.calendarConstraints = calendarConstraints2;
    }

    public boolean hasStableIds() {
        return true;
    }

    public Long getItem(int position) {
        if (position < this.month.daysFromStartOfWeekToFirstOfMonth() || position > lastPositionInMonth()) {
            return null;
        }
        return Long.valueOf(this.month.getDay(positionToDay(position)));
    }

    public long getItemId(int position) {
        return (long) (position / this.month.daysInWeek);
    }

    public int getCount() {
        return this.month.daysInMonth + firstPositionInMonth();
    }

    public TextView getView(int position, View convertView, ViewGroup parent) {
        initializeStyles(parent.getContext());
        TextView day = (TextView) convertView;
        if (convertView == null) {
            day = (TextView) LayoutInflater.from(parent.getContext()).inflate(C1124R.layout.mtrl_calendar_day, parent, false);
        }
        int offsetPosition = position - firstPositionInMonth();
        if (offsetPosition < 0 || offsetPosition >= this.month.daysInMonth) {
            day.setVisibility(8);
            day.setEnabled(false);
        } else {
            int dayNumber = offsetPosition + 1;
            day.setTag(this.month);
            day.setText(String.valueOf(dayNumber));
            long dayInMillis = this.month.getDay(dayNumber);
            if (this.month.year == Month.today().year) {
                day.setContentDescription(DateStrings.getMonthDayOfWeekDay(dayInMillis));
            } else {
                day.setContentDescription(DateStrings.getYearMonthDayOfWeekDay(dayInMillis));
            }
            day.setVisibility(0);
            day.setEnabled(true);
        }
        Long date = getItem(position);
        if (date == null) {
            return day;
        }
        if (this.calendarConstraints.getDateValidator().isValid(date.longValue())) {
            day.setEnabled(true);
            for (Long longValue : this.dateSelector.getSelectedDays()) {
                if (UtcDates.canonicalYearMonthDay(date.longValue()) == UtcDates.canonicalYearMonthDay(longValue.longValue())) {
                    this.calendarStyle.selectedDay.styleItem(day);
                    return day;
                }
            }
            if (UtcDates.getTodayCalendar().getTimeInMillis() == date.longValue()) {
                this.calendarStyle.todayDay.styleItem(day);
                return day;
            }
            this.calendarStyle.day.styleItem(day);
            return day;
        }
        day.setEnabled(false);
        this.calendarStyle.invalidDay.styleItem(day);
        return day;
    }

    private void initializeStyles(Context context) {
        if (this.calendarStyle == null) {
            this.calendarStyle = new CalendarStyle(context);
        }
    }

    /* access modifiers changed from: 0000 */
    public int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth();
    }

    /* access modifiers changed from: 0000 */
    public int lastPositionInMonth() {
        return (this.month.daysFromStartOfWeekToFirstOfMonth() + this.month.daysInMonth) - 1;
    }

    /* access modifiers changed from: 0000 */
    public int positionToDay(int position) {
        return (position - this.month.daysFromStartOfWeekToFirstOfMonth()) + 1;
    }

    /* access modifiers changed from: 0000 */
    public int dayToPosition(int day) {
        return firstPositionInMonth() + (day - 1);
    }

    /* access modifiers changed from: 0000 */
    public boolean withinMonth(int position) {
        return position >= firstPositionInMonth() && position <= lastPositionInMonth();
    }

    /* access modifiers changed from: 0000 */
    public boolean isFirstInRow(int position) {
        return position % this.month.daysInWeek == 0;
    }

    /* access modifiers changed from: 0000 */
    public boolean isLastInRow(int position) {
        return (position + 1) % this.month.daysInWeek == 0;
    }
}
