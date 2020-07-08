package com.navibees.core.model.metadata.json;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import androidx.preference.PreferenceManager;
import org.json.JSONException;
import org.json.JSONObject;

public class MonitoredRegion implements Parcelable {
    public static final Creator<MonitoredRegion> CREATOR = new Creator<MonitoredRegion>() {
        public MonitoredRegion createFromParcel(Parcel parcel) {
            return new MonitoredRegion(parcel);
        }

        public MonitoredRegion[] newArray(int i) {
            return new MonitoredRegion[i];
        }
    };
    private static final String JSON_KEY_ID = "id";
    private static final String JSON_KEY_IDENTIFIER = "identifier";
    private static final String JSON_KEY_INTERVAL = "interval";
    private static final String JSON_KEY_IS_VENUE = "is_venue";
    private static final String JSON_KEY_MAJOR = "major";
    private static final String JSON_KEY_MESSAGE = "message";
    private static final String JSON_KEY_MINOR = "minor";
    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_NOTIFICATION = "notification";
    private static final String JSON_KEY_TRACKING = "tracking";
    private static final String JSON_KEY_TYPE = "monitor_type";
    private static final String JSON_KEY_UUID = "UUID";
    private static final String JSON_KEY_VENUE_ID = "venue_id";
    public String UUID;
    private boolean enteredState;

    /* renamed from: id */
    public String f1337id;
    public String identifier;
    public long interval;
    public boolean isVenue;
    private long lastSeen;
    public Integer major;
    public Text message;
    public Integer minor;
    public String name;
    public boolean notification;
    public boolean tracking;
    public RegionType type;
    public String venueId;

    public MonitoredRegion(JSONObject jSONObject) throws JSONException {
        this.notification = false;
        this.tracking = false;
        this.f1337id = jSONObject.getString(JSON_KEY_ID);
        this.identifier = jSONObject.getString(JSON_KEY_IDENTIFIER);
        this.UUID = jSONObject.getString(JSON_KEY_UUID);
        this.name = "<undefined>";
        String str = "name";
        if (!jSONObject.isNull(str)) {
            this.name = jSONObject.getString(str);
        }
        String str2 = JSON_KEY_MAJOR;
        if (!jSONObject.isNull(str2)) {
            this.major = Integer.valueOf(jSONObject.optInt(str2, -1));
        }
        String str3 = JSON_KEY_MINOR;
        if (!jSONObject.isNull(str3)) {
            this.minor = Integer.valueOf(jSONObject.optInt(str3, -1));
        }
        String str4 = JSON_KEY_NOTIFICATION;
        if (!jSONObject.isNull(str4)) {
            this.notification = jSONObject.optBoolean(str4);
        }
        if (this.notification) {
            String str5 = JSON_KEY_INTERVAL;
            if (!jSONObject.isNull(str5)) {
                this.interval = jSONObject.optLong(str5);
            }
            String str6 = JSON_KEY_TYPE;
            String str7 = "all";
            if (!jSONObject.isNull(str6)) {
                str7 = jSONObject.optString(str6, str7);
            }
            this.type = RegionType.getRegion(str7);
            String str8 = JSON_KEY_MESSAGE;
            if (!jSONObject.isNull(str8)) {
                try {
                    this.message = new Text(jSONObject.getJSONObject(str8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        String str9 = JSON_KEY_TRACKING;
        if (!jSONObject.isNull(str9)) {
            this.tracking = jSONObject.optBoolean(str9);
        }
        if (this.tracking) {
            String str10 = JSON_KEY_VENUE_ID;
            if (!jSONObject.isNull(str10)) {
                this.venueId = jSONObject.optString(str10);
            }
            String str11 = JSON_KEY_IS_VENUE;
            if (!jSONObject.isNull(str11)) {
                this.isVenue = jSONObject.optBoolean(str11);
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean getEnteredState() {
        return this.enteredState;
    }

    public long getLastSeen() {
        return this.lastSeen;
    }

    public void setEnteredState(Context context, boolean z) {
        this.enteredState = z;
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        StringBuilder sb = new StringBuilder();
        sb.append(this.identifier);
        sb.append("-entered-state");
        edit.putBoolean(sb.toString(), z);
        edit.apply();
    }

    public void setLastSeen(Context context, long j) {
        this.lastSeen = j;
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        StringBuilder sb = new StringBuilder();
        sb.append(this.identifier);
        sb.append("-last-seen");
        edit.putLong(sb.toString(), j);
        edit.apply();
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_ID, this.f1337id);
            jSONObject.put(JSON_KEY_IDENTIFIER, this.identifier);
            jSONObject.put("name", this.name);
            jSONObject.put(JSON_KEY_UUID, this.UUID);
            if (this.major != null) {
                jSONObject.put(JSON_KEY_MAJOR, this.major);
            }
            if (this.minor != null) {
                jSONObject.put(JSON_KEY_MINOR, this.minor);
            }
            jSONObject.put(JSON_KEY_NOTIFICATION, this.notification);
            if (this.notification) {
                jSONObject.put(JSON_KEY_INTERVAL, this.interval);
                if (this.type != null) {
                    jSONObject.put(JSON_KEY_TYPE, this.type.getString());
                }
                if (this.message != null) {
                    jSONObject.put(JSON_KEY_MESSAGE, this.message.toJson());
                }
            }
            jSONObject.put(JSON_KEY_TRACKING, this.tracking);
            if (this.tracking) {
                if (this.venueId != null) {
                    jSONObject.put(JSON_KEY_VENUE_ID, this.venueId);
                }
                jSONObject.put(JSON_KEY_IS_VENUE, this.isVenue);
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1337id);
        parcel.writeString(this.identifier);
        parcel.writeString(this.name);
        parcel.writeString(this.UUID);
        if (this.major == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeInt(this.major.intValue());
        }
        if (this.minor == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeInt(this.minor.intValue());
        }
        parcel.writeByte(this.notification ? (byte) 1 : 0);
        parcel.writeLong(this.interval);
        parcel.writeLong(this.lastSeen);
        parcel.writeByte(this.enteredState ? (byte) 1 : 0);
        if (this.type == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeString(this.type.getString());
        }
        if (this.message == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeValue(this.message);
        }
        parcel.writeByte(this.tracking ? (byte) 1 : 0);
        if (this.venueId == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeString(this.venueId);
        }
        parcel.writeByte(this.isVenue ? (byte) 1 : 0);
    }

    public MonitoredRegion(JSONObject jSONObject, Context context) throws JSONException {
        this(jSONObject);
        if (this.notification) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            StringBuilder sb = new StringBuilder();
            sb.append(this.identifier);
            sb.append("-last-seen");
            this.lastSeen = defaultSharedPreferences.getLong(sb.toString(), 0);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.identifier);
            sb2.append("-entered-state");
            this.enteredState = defaultSharedPreferences.getBoolean(sb2.toString(), false);
        }
    }

    protected MonitoredRegion(Parcel parcel) {
        boolean z = false;
        this.notification = false;
        this.tracking = false;
        this.f1337id = parcel.readString();
        this.identifier = parcel.readString();
        this.name = parcel.readString();
        this.UUID = parcel.readString();
        String str = null;
        this.major = parcel.readByte() == 0 ? null : Integer.valueOf(parcel.readInt());
        this.minor = parcel.readByte() == 0 ? null : Integer.valueOf(parcel.readInt());
        this.notification = parcel.readByte() != 0;
        this.interval = parcel.readLong();
        this.lastSeen = parcel.readLong();
        this.enteredState = parcel.readByte() != 0;
        this.type = parcel.readByte() == 0 ? null : RegionType.getRegion(parcel.readString());
        this.message = parcel.readByte() == 0 ? null : (Text) parcel.readValue(Text.class.getClassLoader());
        this.tracking = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            str = parcel.readString();
        }
        this.venueId = str;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.isVenue = z;
    }
}
