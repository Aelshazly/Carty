package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class BeaconNodeConfiguration implements Parcelable {
    public static final Creator<BeaconNodeConfiguration> CREATOR = new Creator<BeaconNodeConfiguration>() {
        public BeaconNodeConfiguration createFromParcel(Parcel parcel) {
            return new BeaconNodeConfiguration(parcel);
        }

        public BeaconNodeConfiguration[] newArray(int i) {
            return new BeaconNodeConfiguration[i];
        }
    };
    public boolean active;
    public int batteryStatus;
    public String buildingId;
    public int expectedLifeTime;

    /* renamed from: id */
    public String f1328id;
    public String lastSeenAt;
    public long lastTimeBatteryReported;
    public IndoorLocation location;
    public int major;
    public int minor;
    public int power;
    public String uuid;

    public BeaconNodeConfiguration(int i, int i2) {
        this.major = i;
        this.minor = i2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BeaconNodeConfiguration)) {
            return false;
        }
        BeaconNodeConfiguration beaconNodeConfiguration = (BeaconNodeConfiguration) obj;
        if (beaconNodeConfiguration.major != this.major || beaconNodeConfiguration.minor != this.minor) {
            return false;
        }
        if (TextUtils.isEmpty(this.uuid)) {
            TextUtils.isEmpty(beaconNodeConfiguration.uuid);
        }
        return true;
    }

    public String generateKey() {
        String str = "_";
        if (!TextUtils.isEmpty(this.uuid)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.uuid);
            sb.append(str);
            sb.append(this.major);
            sb.append(str);
            sb.append(this.minor);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.major);
        sb2.append(str);
        sb2.append(this.minor);
        return sb2.toString();
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.f1328id);
            jSONObject.put(Param.LOCATION, this.location.toJson());
            jSONObject.put("major", this.major);
            jSONObject.put("minor", this.minor);
            jSONObject.put("power", this.power);
            jSONObject.put("battery_status", this.batteryStatus);
            jSONObject.put("expected_life_time", this.expectedLifeTime);
            if (this.lastSeenAt != null) {
                jSONObject.put("last_seen_at", this.lastSeenAt);
            }
            jSONObject.put(ConditionalUserProperty.ACTIVE, this.active);
            if (this.uuid != null) {
                jSONObject.put("UUID", this.uuid);
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[major , minor]:[ ");
        sb.append(this.major);
        String str = " , ";
        sb.append(str);
        sb.append(this.minor);
        sb.append(" ] ,*, (location.X , location.Y) : ( ");
        sb.append(this.location.f1332x);
        sb.append(str);
        sb.append(this.location.f1333y);
        sb.append(" )[betteryStatus]:[ ");
        sb.append(this.batteryStatus);
        sb.append("] (objectId): ( ");
        sb.append(this.f1328id);
        sb.append(" ) ,*, (lastTimeBatteryReported):(");
        sb.append(new Date(this.lastSeenAt));
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    public BeaconNodeConfiguration(JSONObject jSONObject) throws JSONException {
        this.f1328id = jSONObject.getString("id");
        this.location = new IndoorLocation(jSONObject.getJSONObject(Param.LOCATION));
        this.major = jSONObject.getInt("major");
        this.minor = jSONObject.getInt("minor");
        this.power = jSONObject.optInt("power");
        this.batteryStatus = jSONObject.optInt("battery_status");
        this.expectedLifeTime = jSONObject.optInt("expected_life_time");
        this.lastSeenAt = jSONObject.optString("last_seen_at", null);
        String str = ConditionalUserProperty.ACTIVE;
        if (!jSONObject.isNull(str)) {
            this.active = jSONObject.optBoolean(str);
        } else {
            this.active = true;
        }
        String str2 = "UUID";
        if (!jSONObject.isNull(str2)) {
            this.uuid = jSONObject.optString(str2).toUpperCase();
        }
    }

    protected BeaconNodeConfiguration(Parcel parcel) {
        this.f1328id = parcel.readString();
        this.major = parcel.readInt();
        this.minor = parcel.readInt();
        this.power = parcel.readInt();
        this.batteryStatus = parcel.readInt();
    }
}
