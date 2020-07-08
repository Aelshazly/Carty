package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class WorkingHours implements Parcelable {
    public static final Creator<WorkingHours> CREATOR = new Creator<WorkingHours>() {
        public WorkingHours createFromParcel(Parcel parcel) {
            return new WorkingHours(parcel);
        }

        public WorkingHours[] newArray(int i) {
            return new WorkingHours[i];
        }
    };
    public List<String> days = new ArrayList();
    public List<Period> periods = new ArrayList();

    public class Period {
        public String endDate = "6:30 PM";
        public String startTime = "10:00 AM";

        public Period() {
        }
    }

    protected WorkingHours(Parcel parcel) {
        if (parcel.readByte() == 1) {
            this.periods = new ArrayList();
            parcel.readList(this.periods, Period.class.getClassLoader());
        } else {
            this.periods = null;
        }
        if (parcel.readByte() == 1) {
            this.days = new ArrayList();
            parcel.readList(this.days, String.class.getClassLoader());
            return;
        }
        this.days = null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.periods == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.periods);
        }
        if (this.days == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeList(this.days);
    }
}
