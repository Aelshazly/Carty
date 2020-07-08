package com.navibees.core.model.analytics;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.navibees.core.C1556a;
import com.navibees.core.model.metadata.json.MonitoredRegion;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.navibees.core.model.analytics.b */
/* compiled from: RegionEvent */
public class C1656b implements Parcelable {
    public static final Creator<C1656b> CREATOR = new C1657a();

    /* renamed from: h */
    private static final String f1292h = "event_id";

    /* renamed from: i */
    private static final String f1293i = "agent_id";

    /* renamed from: j */
    private static final String f1294j = "state";

    /* renamed from: k */
    private static final String f1295k = "region";

    /* renamed from: l */
    private static final String f1296l = "enter_timestamp";

    /* renamed from: m */
    private static final String f1297m = "current_timestamp";

    /* renamed from: n */
    private static final String f1298n = "visit_duration";

    /* renamed from: a */
    public String f1299a;

    /* renamed from: b */
    public String f1300b;

    /* renamed from: c */
    public C1658c f1301c;

    /* renamed from: d */
    public MonitoredRegion f1302d;

    /* renamed from: e */
    public long f1303e;

    /* renamed from: f */
    public Long f1304f;

    /* renamed from: g */
    public Long f1305g;

    /* renamed from: com.navibees.core.model.analytics.b$a */
    /* compiled from: RegionEvent */
    static class C1657a implements Creator<C1656b> {
        C1657a() {
        }

        public C1656b createFromParcel(Parcel parcel) {
            return new C1656b(parcel);
        }

        public C1656b[] newArray(int i) {
            return new C1656b[i];
        }
    }

    public C1656b(Context context, MonitoredRegion monitoredRegion) {
        this.f1299a = UUID.randomUUID().toString();
        this.f1300b = C1556a.m635d(context);
        this.f1301c = C1658c.ENTERED;
        this.f1302d = monitoredRegion;
        this.f1303e = System.currentTimeMillis() / 1000;
    }

    /* renamed from: a */
    public JSONObject mo29041a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f1292h, this.f1299a);
            jSONObject.put(f1293i, this.f1300b);
            jSONObject.put(f1294j, this.f1301c.mo29046a());
            jSONObject.put(f1295k, this.f1302d.toJson());
            jSONObject.put(f1296l, this.f1303e);
            if (this.f1304f != null) {
                jSONObject.put(f1297m, this.f1304f);
            }
            if (this.f1305g != null) {
                jSONObject.put(f1298n, this.f1305g);
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1299a);
        parcel.writeString(this.f1300b);
        parcel.writeString(this.f1301c.mo29046a());
        parcel.writeValue(this.f1302d);
        parcel.writeLong(this.f1303e);
        if (this.f1304f == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeLong(this.f1304f.longValue());
        }
        if (this.f1305g == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeLong(this.f1305g.longValue());
    }

    public C1656b(JSONObject jSONObject) throws JSONException {
        this.f1299a = jSONObject.getString(f1292h);
        this.f1300b = jSONObject.getString(f1293i);
        this.f1301c = C1658c.m950a(jSONObject.getString(f1294j));
        this.f1302d = new MonitoredRegion(jSONObject.getJSONObject(f1295k));
        this.f1303e = jSONObject.getLong(f1296l);
        String str = f1297m;
        if (!jSONObject.isNull(str)) {
            this.f1304f = Long.valueOf(jSONObject.optLong(str));
        }
        String str2 = f1298n;
        if (!jSONObject.isNull(str2)) {
            this.f1305g = Long.valueOf(jSONObject.optLong(str2));
        }
    }

    protected C1656b(Parcel parcel) {
        this.f1299a = parcel.readString();
        this.f1300b = parcel.readString();
        this.f1301c = C1658c.m950a(parcel.readString());
        this.f1302d = (MonitoredRegion) parcel.readValue(MonitoredRegion.class.getClassLoader());
        this.f1303e = parcel.readLong();
        Long l = null;
        this.f1304f = parcel.readByte() == 0 ? null : Long.valueOf(parcel.readLong());
        if (parcel.readByte() != 0) {
            l = Long.valueOf(parcel.readLong());
        }
        this.f1305g = l;
    }
}
