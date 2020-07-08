package com.navibees.core.blesdk.p023e;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.navibees.core.model.metadata.json.MonitoredRegion;
import com.neovisionaries.bluetooth.ble.advertising.ADPayloadParser;
import com.neovisionaries.bluetooth.ble.advertising.ADStructure;
import com.neovisionaries.bluetooth.ble.advertising.IBeacon;

/* renamed from: com.navibees.core.blesdk.e.b */
/* compiled from: Region */
public class C1634b implements Parcelable {
    public static final Creator<C1634b> CREATOR = new C1635a();

    /* renamed from: a */
    private String f1231a;

    /* renamed from: b */
    private String f1232b;

    /* renamed from: c */
    private int f1233c;

    /* renamed from: d */
    private int f1234d;

    /* renamed from: e */
    private String f1235e;

    /* renamed from: com.navibees.core.blesdk.e.b$a */
    /* compiled from: Region */
    static class C1635a implements Creator<C1634b> {
        C1635a() {
        }

        public C1634b createFromParcel(Parcel parcel) {
            return new C1634b(parcel);
        }

        public C1634b[] newArray(int i) {
            return new C1634b[i];
        }
    }

    public C1634b(String str) {
        this.f1232b = str;
    }

    /* renamed from: f */
    private void m898f() {
    }

    /* renamed from: a */
    public void mo28987a(int i) {
        this.f1233c = i;
    }

    /* renamed from: b */
    public void mo28991b(String str) {
        this.f1231a = str;
    }

    /* renamed from: c */
    public void mo28993c(String str) {
        this.f1232b = str;
    }

    /* renamed from: d */
    public String mo28994d() {
        return this.f1231a;
    }

    public int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public String mo28996e() {
        return this.f1232b;
    }

    public String toString() {
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    /* renamed from: a */
    public String mo28986a() {
        return this.f1235e;
    }

    /* renamed from: b */
    public int mo28989b() {
        return this.f1233c;
    }

    /* renamed from: c */
    public int mo28992c() {
        return this.f1234d;
    }

    public C1634b(String str, String str2) {
        this.f1231a = str;
        this.f1232b = str2;
    }

    /* renamed from: a */
    public void mo28988a(String str) {
        this.f1235e = str;
    }

    /* renamed from: b */
    public void mo28990b(int i) {
        this.f1234d = i;
    }

    public C1634b(String str, String str2, int i) {
        this.f1231a = str;
        this.f1232b = str2;
        this.f1233c = i;
    }

    public C1634b(String str, String str2, int i, int i2) {
        this.f1231a = str;
        this.f1232b = str2;
        this.f1233c = i;
        this.f1234d = i2;
    }

    public C1634b(String str, String str2, int i, int i2, String str3) {
        this.f1231a = str;
        this.f1232b = str2;
        this.f1233c = i;
        this.f1234d = i2;
        this.f1235e = str3;
    }

    protected C1634b(Parcel parcel) {
    }

    public C1634b(byte[] bArr) {
        for (ADStructure aDStructure : ADPayloadParser.getInstance().parse(bArr)) {
            if (aDStructure instanceof IBeacon) {
                IBeacon iBeacon = (IBeacon) aDStructure;
                mo28993c(iBeacon.getUUID().toString());
                mo28987a(iBeacon.getMajor());
                mo28990b(iBeacon.getMinor());
            }
        }
    }

    public C1634b(String str, byte[] bArr) {
        for (ADStructure aDStructure : ADPayloadParser.getInstance().parse(bArr)) {
            if (aDStructure instanceof IBeacon) {
                IBeacon iBeacon = (IBeacon) aDStructure;
                mo28988a(str);
                mo28993c(iBeacon.getUUID().toString());
                mo28987a(iBeacon.getMajor());
                mo28990b(iBeacon.getMinor());
            }
        }
    }

    public C1634b(String str, byte[] bArr, MonitoredRegion monitoredRegion) {
        for (ADStructure aDStructure : ADPayloadParser.getInstance().parse(bArr)) {
            if (aDStructure instanceof IBeacon) {
                IBeacon iBeacon = (IBeacon) aDStructure;
                mo28988a(str);
                mo28993c(iBeacon.getUUID().toString());
                mo28987a(iBeacon.getMajor());
                mo28990b(iBeacon.getMinor());
            }
        }
    }
}
