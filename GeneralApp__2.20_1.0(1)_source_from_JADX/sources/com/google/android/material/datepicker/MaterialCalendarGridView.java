package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.C1124R;
import java.util.Calendar;
import java.util.Iterator;

final class MaterialCalendarGridView extends GridView {
    private final Calendar dayCompute;

    public MaterialCalendarGridView(Context context) {
        this(context, null);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.dayCompute = UtcDates.getUtcCalendar();
        if (MaterialDatePicker.isFullscreen(getContext())) {
            setNextFocusLeftId(C1124R.C1127id.cancel_button);
            setNextFocusRightId(C1124R.C1127id.confirm_button);
        }
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCollectionInfo(null);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
    }

    public void setSelection(int position) {
        if (position < getAdapter().firstPositionInMonth()) {
            super.setSelection(getAdapter().firstPositionInMonth());
        } else {
            super.setSelection(position);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!super.onKeyDown(keyCode, event)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= getAdapter().firstPositionInMonth()) {
            return true;
        }
        if (19 != keyCode) {
            return false;
        }
        setSelection(getAdapter().firstPositionInMonth());
        return true;
    }

    public MonthAdapter getAdapter() {
        return (MonthAdapter) super.getAdapter();
    }

    public final void setAdapter(ListAdapter adapter) {
        if (adapter instanceof MonthAdapter) {
            super.setAdapter(adapter);
        } else {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", new Object[]{MaterialCalendarGridView.class.getCanonicalName(), MonthAdapter.class.getCanonicalName()}));
        }
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        int rangeHighlightStart;
        int firstHighlightPosition;
        int lastHighlightPosition;
        int rangeHighlightEnd;
        int i;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        MonthAdapter monthAdapter = getAdapter();
        DateSelector<?> dateSelector = monthAdapter.dateSelector;
        CalendarStyle calendarStyle = monthAdapter.calendarStyle;
        Long firstOfMonth = monthAdapter.getItem(monthAdapter.firstPositionInMonth());
        Long lastOfMonth = monthAdapter.getItem(monthAdapter.lastPositionInMonth());
        Iterator it = dateSelector.getSelectedRanges().iterator();
        while (it.hasNext()) {
            Pair<Long, Long> range = (Pair) it.next();
            if (range.first == null) {
                DateSelector<?> dateSelector2 = dateSelector;
                Long l = firstOfMonth;
                Long l2 = lastOfMonth;
                Iterator it2 = it;
                Pair pair = range;
                materialCalendarGridView = this;
            } else if (range.second == null) {
                continue;
            } else {
                long startItem = ((Long) range.first).longValue();
                long endItem = ((Long) range.second).longValue();
                if (!skipMonth(firstOfMonth, lastOfMonth, Long.valueOf(startItem), Long.valueOf(endItem))) {
                    if (startItem < firstOfMonth.longValue()) {
                        firstHighlightPosition = monthAdapter.firstPositionInMonth();
                        if (monthAdapter.isFirstInRow(firstHighlightPosition)) {
                            rangeHighlightStart = 0;
                        } else {
                            rangeHighlightStart = materialCalendarGridView.getChildAt(firstHighlightPosition - 1).getRight();
                        }
                    } else {
                        materialCalendarGridView.dayCompute.setTimeInMillis(startItem);
                        firstHighlightPosition = monthAdapter.dayToPosition(materialCalendarGridView.dayCompute.get(5));
                        rangeHighlightStart = horizontalMidPoint(materialCalendarGridView.getChildAt(firstHighlightPosition));
                    }
                    if (endItem > lastOfMonth.longValue()) {
                        lastHighlightPosition = monthAdapter.lastPositionInMonth();
                        if (monthAdapter.isLastInRow(lastHighlightPosition)) {
                            i = getWidth();
                        } else {
                            i = materialCalendarGridView.getChildAt(lastHighlightPosition).getRight();
                        }
                        rangeHighlightEnd = i;
                    } else {
                        materialCalendarGridView.dayCompute.setTimeInMillis(endItem);
                        lastHighlightPosition = monthAdapter.dayToPosition(materialCalendarGridView.dayCompute.get(5));
                        rangeHighlightEnd = horizontalMidPoint(materialCalendarGridView.getChildAt(lastHighlightPosition));
                    }
                    Long firstOfMonth2 = firstOfMonth;
                    Long lastOfMonth2 = lastOfMonth;
                    int firstRow = (int) monthAdapter.getItemId(firstHighlightPosition);
                    Iterator it3 = it;
                    Pair pair2 = range;
                    int lastRow = (int) monthAdapter.getItemId(lastHighlightPosition);
                    int row = firstRow;
                    while (row <= lastRow) {
                        MonthAdapter monthAdapter2 = monthAdapter;
                        int firstPositionInRow = row * getNumColumns();
                        DateSelector<?> dateSelector3 = dateSelector;
                        int lastPositionInRow = (firstPositionInRow + getNumColumns()) - 1;
                        View firstView = materialCalendarGridView.getChildAt(firstPositionInRow);
                        int top = firstView.getTop() + calendarStyle.day.getTopInset();
                        Iterator it4 = it3;
                        int bottom = firstView.getBottom() - calendarStyle.day.getBottomInset();
                        int right = lastHighlightPosition > lastPositionInRow ? getWidth() : rangeHighlightEnd;
                        int i2 = firstPositionInRow;
                        int left = firstPositionInRow > firstHighlightPosition ? 0 : rangeHighlightStart;
                        int left2 = lastPositionInRow;
                        float f = (float) left;
                        int i3 = left;
                        float f2 = (float) top;
                        int i4 = top;
                        int right2 = right;
                        int firstRow2 = firstRow;
                        int i5 = right2;
                        int i6 = bottom;
                        canvas.drawRect(f, f2, (float) right2, (float) bottom, calendarStyle.rangeFill);
                        row++;
                        materialCalendarGridView = this;
                        monthAdapter = monthAdapter2;
                        dateSelector = dateSelector3;
                        it3 = it4;
                        firstRow = firstRow2;
                    }
                    DateSelector<?> dateSelector4 = dateSelector;
                    Iterator it5 = it3;
                    int i7 = firstRow;
                    materialCalendarGridView = this;
                    firstOfMonth = firstOfMonth2;
                    lastOfMonth = lastOfMonth2;
                    it = it5;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        if (gainFocus) {
            gainFocus(direction, previouslyFocusedRect);
        } else {
            super.onFocusChanged(false, direction, previouslyFocusedRect);
        }
    }

    private void gainFocus(int direction, Rect previouslyFocusedRect) {
        if (direction == 33) {
            setSelection(getAdapter().lastPositionInMonth());
        } else if (direction == 130) {
            setSelection(getAdapter().firstPositionInMonth());
        } else {
            super.onFocusChanged(true, direction, previouslyFocusedRect);
        }
    }

    private static boolean skipMonth(Long firstOfMonth, Long lastOfMonth, Long startDay, Long endDay) {
        boolean z = true;
        if (firstOfMonth == null || lastOfMonth == null || startDay == null || endDay == null) {
            return true;
        }
        if (startDay.longValue() <= lastOfMonth.longValue() && endDay.longValue() >= firstOfMonth.longValue()) {
            z = false;
        }
        return z;
    }

    private static int horizontalMidPoint(View view) {
        return view.getLeft() + (view.getWidth() / 2);
    }
}
