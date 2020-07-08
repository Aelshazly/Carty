package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONException;
import org.json.JSONObject;

public class IndoorLocation implements Parcelable {
    public static final Creator<IndoorLocation> CREATOR = new Creator<IndoorLocation>() {
        public IndoorLocation createFromParcel(Parcel parcel) {
            return new IndoorLocation(parcel);
        }

        public IndoorLocation[] newArray(int i) {
            return new IndoorLocation[i];
        }
    };
    public String buildingId;
    public IndoorLocationConfidence confidence;
    public int floor;
    public long timestamp;
    public String venueId;

    /* renamed from: x */
    public double f1332x;

    /* renamed from: y */
    public double f1333y;

    public IndoorLocation(double d, double d2) {
        this.f1332x = d;
        this.f1333y = d2;
    }

    public int describeContents() {
        return 0;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("x", this.f1332x);
            jSONObject.put("y", this.f1333y);
            jSONObject.put("floor_index", this.floor);
            if (this.buildingId != null) {
                jSONObject.put("building_id", this.buildingId);
            }
            if (this.venueId != null) {
                jSONObject.put("venue_id", this.venueId);
            }
            return jSONObject;
        } catch (Exception e) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.f1332x);
        parcel.writeDouble(this.f1333y);
        parcel.writeInt(this.floor);
        parcel.writeValue(this.confidence);
        parcel.writeString(this.buildingId);
        parcel.writeString(this.venueId);
    }

    public IndoorLocation(double d, double d2, int i) {
        this.f1332x = d;
        this.f1333y = d2;
        this.floor = i;
    }

    public IndoorLocation(JSONObject jSONObject) throws JSONException {
        this.f1332x = jSONObject.getDouble("x");
        this.f1333y = jSONObject.getDouble("y");
        this.floor = jSONObject.optInt("floor_index", -1);
        this.buildingId = jSONObject.optString("building_id");
        this.venueId = jSONObject.optString("venue_id");
    }

    protected IndoorLocation(Parcel parcel) {
        this.f1332x = parcel.readDouble();
        this.f1333y = parcel.readDouble();
        this.floor = parcel.readInt();
        this.confidence = (IndoorLocationConfidence) parcel.readValue(IndoorLocationConfidence.class.getClassLoader());
        this.buildingId = parcel.readString();
        this.venueId = parcel.readString();
    }
}
