package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpeningPeriod implements Parcelable {
    public static final Creator<OpeningPeriod> CREATOR = new Creator<OpeningPeriod>() {
        public OpeningPeriod createFromParcel(Parcel parcel) {
            return new OpeningPeriod(parcel);
        }

        public OpeningPeriod[] newArray(int i) {
            return new OpeningPeriod[i];
        }
    };
    public List<String> days;
    public List<Period> periods;

    public class Period implements Parcelable {
        public final Creator<Period> CREATOR = new Creator<Period>() {
            public Period createFromParcel(Parcel parcel) {
                return new Period(parcel);
            }

            public Period[] newArray(int i) {
                return new Period[i];
            }
        };
        public String endDate;
        public String startTime;

        public Period() {
        }

        public int describeContents() {
            return 0;
        }

        public JSONObject toJson() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("start_time", this.startTime);
                jSONObject.put("end_time", this.endDate);
                return jSONObject;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.startTime);
            parcel.writeString(this.endDate);
        }

        protected Period(Parcel parcel) {
            this.startTime = parcel.readString();
            this.endDate = parcel.readString();
        }
    }

    public OpeningPeriod(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("week_days");
        JSONArray jSONArray2 = jSONObject.getJSONArray("periods");
        this.days = new ArrayList();
        this.periods = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!TextUtils.isEmpty(jSONArray.getString(i))) {
                this.days.add(jSONArray.getString(i));
            }
        }
        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
            String str = "start_time";
            if (!jSONArray2.getJSONObject(i2).isNull(str)) {
                String str2 = "end_time";
                if (!jSONArray2.getJSONObject(i2).isNull(str2)) {
                    Period period = new Period();
                    period.startTime = jSONArray2.getJSONObject(i2).getString(str);
                    period.endDate = jSONArray2.getJSONObject(i2).getString(str2);
                    this.periods.add(period);
                }
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (Period json : this.periods) {
                jSONArray.put(json.toJson());
            }
            jSONObject.put("periods", this.periods);
            JSONArray jSONArray2 = new JSONArray();
            for (String put : this.days) {
                jSONArray2.put(put);
            }
            jSONObject.put("week_days", jSONArray2);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    protected OpeningPeriod(Parcel parcel) {
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
}
