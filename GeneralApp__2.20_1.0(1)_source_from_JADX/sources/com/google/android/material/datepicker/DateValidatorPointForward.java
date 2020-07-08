package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.material.datepicker.CalendarConstraints.DateValidator;
import java.util.Arrays;

public class DateValidatorPointForward implements DateValidator {
    public static final Creator<DateValidatorPointForward> CREATOR = new Creator<DateValidatorPointForward>() {
        public DateValidatorPointForward createFromParcel(Parcel source) {
            return new DateValidatorPointForward(source.readLong());
        }

        public DateValidatorPointForward[] newArray(int size) {
            return new DateValidatorPointForward[size];
        }
    };
    private final long point;

    private DateValidatorPointForward(long point2) {
        this.point = point2;
    }

    public static DateValidatorPointForward from(long point2) {
        return new DateValidatorPointForward(point2);
    }

    public static DateValidatorPointForward now() {
        return from(UtcDates.getTodayCalendar().getTimeInMillis());
    }

    public boolean isValid(long date) {
        return date >= this.point;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.point);
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (!(o instanceof DateValidatorPointForward)) {
            return false;
        }
        if (this.point != ((DateValidatorPointForward) o).point) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.point)});
    }
}
