package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import com.google.android.material.C1124R;

class MonthsPagerAdapter extends Adapter<ViewHolder> {
    private final CalendarConstraints calendarConstraints;
    private final DateSelector<?> dateSelector;
    private final int itemHeight;
    /* access modifiers changed from: private */
    public final OnDayClickListener onDayClickListener;

    public static class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        final MaterialCalendarGridView monthGrid;
        final TextView monthTitle;

        ViewHolder(LinearLayout container, boolean showLabel) {
            super(container);
            this.monthTitle = (TextView) container.findViewById(C1124R.C1127id.month_title);
            ViewCompat.setAccessibilityHeading(this.monthTitle, true);
            this.monthGrid = (MaterialCalendarGridView) container.findViewById(C1124R.C1127id.month_grid);
            if (!showLabel) {
                this.monthTitle.setVisibility(8);
            }
        }
    }

    MonthsPagerAdapter(Context context, DateSelector<?> dateSelector2, CalendarConstraints calendarConstraints2, OnDayClickListener onDayClickListener2) {
        Month firstPage = calendarConstraints2.getStart();
        Month lastPage = calendarConstraints2.getEnd();
        Month currentPage = calendarConstraints2.getOpenAt();
        if (firstPage.compareTo(currentPage) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        } else if (currentPage.compareTo(lastPage) <= 0) {
            this.itemHeight = (MonthAdapter.MAXIMUM_WEEKS * MaterialCalendar.getDayHeight(context)) + (MaterialDatePicker.isFullscreen(context) ? MaterialCalendar.getDayHeight(context) : 0);
            this.calendarConstraints = calendarConstraints2;
            this.dateSelector = dateSelector2;
            this.onDayClickListener = onDayClickListener2;
            setHasStableIds(true);
        } else {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LinearLayout container = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(C1124R.layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (!MaterialDatePicker.isFullscreen(viewGroup.getContext())) {
            return new ViewHolder(container, false);
        }
        container.setLayoutParams(new LayoutParams(-1, this.itemHeight));
        return new ViewHolder(container, true);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Month month = this.calendarConstraints.getStart().monthsLater(position);
        viewHolder.monthTitle.setText(month.getLongName());
        final MaterialCalendarGridView monthGrid = (MaterialCalendarGridView) viewHolder.monthGrid.findViewById(C1124R.C1127id.month_grid);
        if (monthGrid.getAdapter() == null || !month.equals(monthGrid.getAdapter().month)) {
            MonthAdapter monthAdapter = new MonthAdapter(month, this.dateSelector, this.calendarConstraints);
            monthGrid.setNumColumns(month.daysInWeek);
            monthGrid.setAdapter((ListAdapter) monthAdapter);
        } else {
            monthGrid.getAdapter().notifyDataSetChanged();
        }
        monthGrid.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (monthGrid.getAdapter().withinMonth(position)) {
                    MonthsPagerAdapter.this.onDayClickListener.onDayClick(monthGrid.getAdapter().getItem(position).longValue());
                }
            }
        });
    }

    public long getItemId(int position) {
        return this.calendarConstraints.getStart().monthsLater(position).getStableId();
    }

    public int getItemCount() {
        return this.calendarConstraints.getMonthSpan();
    }

    /* access modifiers changed from: 0000 */
    public CharSequence getPageTitle(int position) {
        return getPageMonth(position).getLongName();
    }

    /* access modifiers changed from: 0000 */
    public Month getPageMonth(int position) {
        return this.calendarConstraints.getStart().monthsLater(position);
    }

    /* access modifiers changed from: 0000 */
    public int getPosition(Month month) {
        return this.calendarConstraints.getStart().monthsUntil(month);
    }
}
