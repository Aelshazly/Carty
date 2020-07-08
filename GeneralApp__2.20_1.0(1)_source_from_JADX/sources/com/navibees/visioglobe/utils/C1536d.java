package com.navibees.visioglobe.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* renamed from: com.navibees.visioglobe.utils.d */
/* compiled from: VgMyApplicationParameters */
public class C1536d implements Parcelable {
    public static final Creator<C1536d> CREATOR = new C1537a();

    /* renamed from: a */
    public C1543d f911a;

    /* renamed from: b */
    public C1538b f912b;

    /* renamed from: c */
    public C1541c f913c;

    /* renamed from: com.navibees.visioglobe.utils.d$a */
    /* compiled from: VgMyApplicationParameters */
    static class C1537a implements Creator<C1536d> {
        C1537a() {
        }

        public C1536d createFromParcel(Parcel parcel) {
            return new C1536d(parcel);
        }

        public C1536d[] newArray(int i) {
            return new C1536d[i];
        }
    }

    /* renamed from: com.navibees.visioglobe.utils.d$b */
    /* compiled from: VgMyApplicationParameters */
    public static class C1538b implements Parcelable {
        public static final Creator<C1538b> CREATOR = new C1539a();

        /* renamed from: a */
        public boolean f914a;

        /* renamed from: b */
        public double f915b;

        /* renamed from: c */
        public C1540b f916c;

        /* renamed from: com.navibees.visioglobe.utils.d$b$a */
        /* compiled from: VgMyApplicationParameters */
        static class C1539a implements Creator<C1538b> {
            C1539a() {
            }

            public C1538b createFromParcel(Parcel parcel) {
                return new C1538b(parcel);
            }

            public C1538b[] newArray(int i) {
                return new C1538b[i];
            }
        }

        /* renamed from: com.navibees.visioglobe.utils.d$b$b */
        /* compiled from: VgMyApplicationParameters */
        public enum C1540b {
            eLego,
            eSpaced
        }

        public C1538b() {
            this.f914a = true;
            this.f915b = -30.0d;
            this.f916c = C1540b.eLego;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBooleanArray(new boolean[]{this.f914a});
            parcel.writeDouble(this.f915b);
            parcel.writeSerializable(this.f916c);
        }

        public C1538b(Parcel parcel) {
            boolean[] zArr = new boolean[1];
            parcel.readBooleanArray(zArr);
            this.f914a = zArr[0];
            this.f915b = parcel.readDouble();
            this.f916c = (C1540b) parcel.readSerializable();
        }
    }

    /* renamed from: com.navibees.visioglobe.utils.d$c */
    /* compiled from: VgMyApplicationParameters */
    public static class C1541c implements Parcelable {
        public static final Creator<C1541c> CREATOR = new C1542a();

        /* renamed from: a */
        public boolean f920a;

        /* renamed from: b */
        public double f921b;

        /* renamed from: com.navibees.visioglobe.utils.d$c$a */
        /* compiled from: VgMyApplicationParameters */
        static class C1542a implements Creator<C1541c> {
            C1542a() {
            }

            public C1541c createFromParcel(Parcel parcel) {
                return new C1541c(parcel);
            }

            public C1541c[] newArray(int i) {
                return new C1541c[i];
            }
        }

        public C1541c() {
            this.f920a = true;
            this.f921b = -55.0d;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBooleanArray(new boolean[]{this.f920a});
            parcel.writeDouble(this.f921b);
        }

        public C1541c(Parcel parcel) {
            boolean[] zArr = new boolean[1];
            parcel.readBooleanArray(zArr);
            this.f920a = zArr[0];
            this.f921b = parcel.readDouble();
        }
    }

    /* renamed from: com.navibees.visioglobe.utils.d$d */
    /* compiled from: VgMyApplicationParameters */
    public static class C1543d implements Parcelable {
        public static final Creator<C1543d> CREATOR = new C1544a();

        /* renamed from: a */
        public boolean f922a;

        /* renamed from: b */
        public double f923b;

        /* renamed from: com.navibees.visioglobe.utils.d$d$a */
        /* compiled from: VgMyApplicationParameters */
        static class C1544a implements Creator<C1543d> {
            C1544a() {
            }

            public C1543d createFromParcel(Parcel parcel) {
                return new C1543d(parcel);
            }

            public C1543d[] newArray(int i) {
                return new C1543d[i];
            }
        }

        public C1543d() {
            this.f922a = true;
            this.f923b = -55.0d;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBooleanArray(new boolean[]{this.f922a});
            parcel.writeDouble(this.f923b);
        }

        public C1543d(Parcel parcel) {
            boolean[] zArr = new boolean[1];
            parcel.readBooleanArray(zArr);
            this.f922a = zArr[0];
            this.f923b = parcel.readDouble();
        }
    }

    public C1536d() {
        this.f911a = new C1543d();
        this.f912b = new C1538b();
        this.f913c = new C1541c();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f911a, i);
        parcel.writeParcelable(this.f912b, i);
        parcel.writeParcelable(this.f913c, i);
    }

    public C1536d(Parcel parcel) {
        this.f911a = (C1543d) parcel.readParcelable(null);
        this.f912b = (C1538b) parcel.readParcelable(null);
        this.f913c = (C1541c) parcel.readParcelable(null);
    }
}
