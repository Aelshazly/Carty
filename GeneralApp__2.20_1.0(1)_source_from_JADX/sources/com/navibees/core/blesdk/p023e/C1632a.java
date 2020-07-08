package com.navibees.core.blesdk.p023e;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.neovisionaries.bluetooth.ble.advertising.ADPayloadParser;
import com.neovisionaries.bluetooth.ble.advertising.ADStructure;
import com.neovisionaries.bluetooth.ble.advertising.IBeacon;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.navibees.core.blesdk.e.a */
/* compiled from: Beacon */
public class C1632a implements Parcelable {
    public static final Creator<C1632a> CREATOR = new C1633a();

    /* renamed from: a */
    protected String f1224a;

    /* renamed from: b */
    protected String f1225b;

    /* renamed from: c */
    protected int f1226c;

    /* renamed from: d */
    protected int f1227d;

    /* renamed from: e */
    protected int f1228e;

    /* renamed from: f */
    protected int f1229f;

    /* renamed from: g */
    protected int f1230g;

    /* renamed from: com.navibees.core.blesdk.e.a$a */
    /* compiled from: Beacon */
    static class C1633a implements Creator<C1632a> {
        C1633a() {
        }

        public C1632a createFromParcel(Parcel parcel) {
            return new C1632a(parcel);
        }

        public C1632a[] newArray(int i) {
            return new C1632a[i];
        }
    }

    protected C1632a(Parcel parcel) {
        this.f1224a = parcel.readString();
        this.f1225b = parcel.readString();
        this.f1226c = parcel.readInt();
        this.f1227d = parcel.readInt();
        this.f1228e = parcel.readInt();
        this.f1230g = parcel.readInt();
        this.f1229f = parcel.readInt();
    }

    /* renamed from: a */
    public void mo28969a(String str) {
        this.f1224a = str;
    }

    /* renamed from: b */
    public String mo28970b() {
        return this.f1224a;
    }

    /* renamed from: c */
    public int mo28973c() {
        return this.f1226c;
    }

    /* renamed from: d */
    public int mo28975d() {
        return this.f1227d;
    }

    public int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public int mo28978e() {
        return this.f1228e;
    }

    /* renamed from: f */
    public int mo28980f() {
        return this.f1229f;
    }

    /* renamed from: g */
    public String mo28981g() {
        return this.f1225b;
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("mac", this.f1224a);
            jSONObject.put("uuid", this.f1225b);
            jSONObject.put("major", this.f1226c);
            jSONObject.put("minor", this.f1227d);
            jSONObject.put("rssi", this.f1228e);
            jSONObject.put("batteryStatus", this.f1230g);
            jSONObject.put("tx", this.f1229f);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1224a);
        parcel.writeString(this.f1225b);
        parcel.writeInt(this.f1226c);
        parcel.writeInt(this.f1227d);
        parcel.writeInt(this.f1228e);
        parcel.writeInt(this.f1230g);
        parcel.writeInt(this.f1229f);
    }

    /* renamed from: a */
    public int mo28967a() {
        return this.f1230g;
    }

    /* renamed from: b */
    public void mo28972b(String str) {
        this.f1225b = str;
    }

    /* renamed from: c */
    public void mo28974c(int i) {
        this.f1227d = i;
    }

    /* renamed from: d */
    public void mo28976d(int i) {
        this.f1228e = i;
    }

    /* renamed from: e */
    public void mo28979e(int i) {
        this.f1229f = i;
    }

    /* renamed from: a */
    public void mo28968a(int i) {
        this.f1230g = i;
    }

    /* renamed from: b */
    public void mo28971b(int i) {
        this.f1226c = i;
    }

    public C1632a() {
    }

    public C1632a(String str, int i, byte[] bArr) {
        for (ADStructure aDStructure : ADPayloadParser.getInstance().parse(bArr)) {
            if (aDStructure instanceof IBeacon) {
                IBeacon iBeacon = (IBeacon) aDStructure;
                mo28969a(str);
                mo28976d(i);
                mo28972b(iBeacon.getUUID().toString());
                mo28971b(iBeacon.getMajor());
                mo28974c(iBeacon.getMinor());
                mo28968a(iBeacon.getPower());
                mo28979e(iBeacon.getPower());
            }
        }
    }
}
