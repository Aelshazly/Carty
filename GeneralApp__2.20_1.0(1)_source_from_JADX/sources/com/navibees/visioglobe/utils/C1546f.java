package com.navibees.visioglobe.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONObject;

/* renamed from: com.navibees.visioglobe.utils.f */
/* compiled from: VgMyVenueLayout */
public class C1546f implements Parcelable {
    public static final Creator<C1546f> CREATOR = new C1547a();

    /* renamed from: a */
    public String f925a;

    /* renamed from: b */
    public String f926b;

    /* renamed from: c */
    public String f927c;

    /* renamed from: d */
    public String f928d;

    /* renamed from: e */
    public HashMap<String, C1548b> f929e = new HashMap<>();

    /* renamed from: com.navibees.visioglobe.utils.f$a */
    /* compiled from: VgMyVenueLayout */
    static class C1547a implements Creator<C1546f> {
        C1547a() {
        }

        public C1546f createFromParcel(Parcel parcel) {
            return new C1546f(parcel);
        }

        public C1546f[] newArray(int i) {
            return new C1546f[i];
        }
    }

    /* renamed from: com.navibees.visioglobe.utils.f$b */
    /* compiled from: VgMyVenueLayout */
    public static class C1548b implements Parcelable {
        public static final Creator<C1548b> CREATOR = new C1550b();

        /* renamed from: a */
        public String f930a;

        /* renamed from: b */
        public String f931b;

        /* renamed from: c */
        public String f932c;

        /* renamed from: d */
        public String f933d;

        /* renamed from: e */
        public String f934e;

        /* renamed from: f */
        public String f935f;

        /* renamed from: g */
        public int f936g;

        /* renamed from: h */
        public HashMap<String, C1551c> f937h = new HashMap<>();

        /* renamed from: com.navibees.visioglobe.utils.f$b$a */
        /* compiled from: VgMyVenueLayout */
        class C1549a implements Comparator<C1551c> {
            C1549a(C1548b bVar) {
            }

            /* renamed from: a */
            public int compare(C1551c cVar, C1551c cVar2) {
                int i = cVar.f943f;
                int i2 = cVar2.f943f;
                if (i < i2) {
                    return -1;
                }
                return i > i2 ? 1 : 0;
            }
        }

        /* renamed from: com.navibees.visioglobe.utils.f$b$b */
        /* compiled from: VgMyVenueLayout */
        static class C1550b implements Creator<C1548b> {
            C1550b() {
            }

            public C1548b createFromParcel(Parcel parcel) {
                return new C1548b(parcel);
            }

            public C1548b[] newArray(int i) {
                return new C1548b[i];
            }
        }

        public C1548b(String str) {
            this.f930a = str;
            this.f931b = str;
            this.f932c = str;
        }

        /* renamed from: a */
        public void mo28702a(JSONObject jSONObject) {
            JSONObject optJSONObject = jSONObject.optJSONObject(this.f930a);
            if (optJSONObject != null) {
                this.f931b = optJSONObject.optString("name", this.f931b);
                this.f932c = optJSONObject.optString("shortName", this.f932c);
                this.f933d = optJSONObject.optString("description", this.f933d);
            }
            for (Entry value : this.f937h.entrySet()) {
                ((C1551c) value.getValue()).mo28709a(jSONObject);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f930a);
            parcel.writeString(this.f931b);
            parcel.writeString(this.f932c);
            parcel.writeString(this.f933d);
            parcel.writeString(this.f934e);
            parcel.writeString(this.f935f);
            parcel.writeInt(this.f936g);
            parcel.writeSerializable(this.f937h);
        }

        /* renamed from: a */
        public List<C1551c> mo28700a() {
            LinkedList linkedList = new LinkedList();
            for (Entry value : this.f937h.entrySet()) {
                linkedList.add(value.getValue());
            }
            Collections.sort(linkedList, new C1549a(this));
            return linkedList;
        }

        public C1548b(String str, JSONObject jSONObject) {
            this.f930a = str;
            this.f931b = jSONObject.optString("name", this.f930a);
            this.f932c = jSONObject.optString("shortName", this.f931b);
            this.f933d = jSONObject.optString("description", this.f931b);
            C1551c cVar = null;
            this.f934e = jSONObject.optString("defaultFloor", null);
            this.f936g = jSONObject.optInt("displayIndex");
            JSONObject optJSONObject = jSONObject.optJSONObject("floors");
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                C1551c cVar2 = new C1551c(str2, optJSONObject.optJSONObject(str2));
                this.f937h.put(str2, cVar2);
                int i = cVar2.f943f;
                if (i >= 0 && (cVar == null || i < cVar.f943f)) {
                    cVar = cVar2;
                }
            }
            if (cVar != null) {
                this.f935f = cVar.f938a;
                if (this.f934e == null) {
                    this.f934e = this.f935f;
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
            if (r1 < ((com.navibees.visioglobe.utils.C1546f.C1551c) r3.f937h.get(r0)).f943f) goto L_0x0014;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void mo28701a(com.navibees.visioglobe.utils.C1546f.C1551c r4) {
            /*
                r3 = this;
                java.lang.String r0 = r3.f935f
                if (r0 == 0) goto L_0x0014
                int r1 = r4.f943f
                if (r1 < 0) goto L_0x0018
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$c> r2 = r3.f937h
                java.lang.Object r0 = r2.get(r0)
                com.navibees.visioglobe.utils.f$c r0 = (com.navibees.visioglobe.utils.C1546f.C1551c) r0
                int r0 = r0.f943f
                if (r1 >= r0) goto L_0x0018
            L_0x0014:
                java.lang.String r0 = r4.f938a
                r3.f935f = r0
            L_0x0018:
                java.util.HashMap<java.lang.String, com.navibees.visioglobe.utils.f$c> r0 = r3.f937h
                java.lang.String r1 = r4.f938a
                r0.put(r1, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.navibees.visioglobe.utils.C1546f.C1548b.mo28701a(com.navibees.visioglobe.utils.f$c):void");
        }

        /* renamed from: a */
        public C1551c mo28699a(int i) {
            for (Entry entry : this.f937h.entrySet()) {
                if (((C1551c) entry.getValue()).f943f == i) {
                    return (C1551c) entry.getValue();
                }
            }
            return null;
        }

        public C1548b(Parcel parcel) {
            this.f930a = parcel.readString();
            this.f931b = parcel.readString();
            this.f932c = parcel.readString();
            this.f933d = parcel.readString();
            this.f934e = parcel.readString();
            this.f935f = parcel.readString();
            this.f936g = parcel.readInt();
            this.f937h = (HashMap) parcel.readSerializable();
        }
    }

    /* renamed from: com.navibees.visioglobe.utils.f$c */
    /* compiled from: VgMyVenueLayout */
    public static class C1551c implements Parcelable {
        public static final Creator<C1551c> CREATOR = new C1552a();

        /* renamed from: a */
        public String f938a;

        /* renamed from: b */
        public String f939b;

        /* renamed from: c */
        public String f940c;

        /* renamed from: d */
        public String f941d;

        /* renamed from: e */
        public String f942e;

        /* renamed from: f */
        public int f943f = 0;

        /* renamed from: com.navibees.visioglobe.utils.f$c$a */
        /* compiled from: VgMyVenueLayout */
        static class C1552a implements Creator<C1551c> {
            C1552a() {
            }

            public C1551c createFromParcel(Parcel parcel) {
                return new C1551c(parcel);
            }

            public C1551c[] newArray(int i) {
                return new C1551c[i];
            }
        }

        public C1551c(String str) {
            this.f938a = str;
            this.f940c = str;
            this.f941d = str;
            this.f942e = "";
        }

        /* renamed from: a */
        public void mo28709a(JSONObject jSONObject) {
            JSONObject optJSONObject = jSONObject.optJSONObject(this.f938a);
            if (optJSONObject != null) {
                this.f940c = optJSONObject.optString("name", this.f940c);
                this.f941d = optJSONObject.optString("shortName", this.f941d);
                this.f942e = optJSONObject.optString("description", this.f942e);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f938a);
            parcel.writeString(this.f939b);
            parcel.writeInt(this.f943f);
            parcel.writeString(this.f940c);
            parcel.writeString(this.f941d);
            parcel.writeString(this.f942e);
        }

        public C1551c(String str, JSONObject jSONObject) {
            this.f938a = str;
            this.f939b = jSONObject.optString("layer");
            this.f943f = jSONObject.optInt("levelIndex");
            this.f940c = jSONObject.optString("name", this.f938a);
            this.f941d = jSONObject.optString("shortName", this.f940c);
            this.f942e = jSONObject.optString("description", this.f940c);
        }

        public C1551c(Parcel parcel) {
            this.f938a = parcel.readString();
            this.f939b = parcel.readString();
            this.f943f = parcel.readInt();
            this.f940c = parcel.readString();
            this.f941d = parcel.readString();
            this.f942e = parcel.readString();
        }
    }

    public C1546f() {
    }

    /* renamed from: a */
    public void mo28694a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject(this.f927c);
        if (optJSONObject != null) {
            this.f925a = optJSONObject.optString("name", this.f925a);
            this.f926b = optJSONObject.optString("shortName", this.f926b);
        }
        for (Entry value : this.f929e.entrySet()) {
            ((C1548b) value.getValue()).mo28702a(jSONObject);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f925a);
        parcel.writeString(this.f926b);
        parcel.writeString(this.f927c);
        parcel.writeString(this.f928d);
        parcel.writeSerializable(this.f929e);
    }

    public C1546f(JSONObject jSONObject) {
        this.f925a = jSONObject.optString("name");
        this.f926b = jSONObject.optString("shortName");
        this.f927c = jSONObject.optString("layer");
        this.f928d = jSONObject.optString("defaultBuilding");
        JSONObject optJSONObject = jSONObject.optJSONObject("buildings");
        if (optJSONObject != null) {
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                this.f929e.put(str, new C1548b(str, optJSONObject.optJSONObject(str)));
            }
        }
    }

    public C1546f(Parcel parcel) {
        this.f925a = parcel.readString();
        this.f926b = parcel.readString();
        this.f927c = parcel.readString();
        this.f928d = parcel.readString();
        this.f929e = (HashMap) parcel.readSerializable();
    }
}
