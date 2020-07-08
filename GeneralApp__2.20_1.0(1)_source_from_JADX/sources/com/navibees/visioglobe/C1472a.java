package com.navibees.visioglobe;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.navibees.visioglobe.a */
/* compiled from: VgMyConfigPreferences */
public class C1472a {

    /* renamed from: e */
    protected static String f715e = "configPreferencesKey";

    /* renamed from: f */
    protected static String f716f = "isSavedKey";

    /* renamed from: g */
    protected static String f717g = "configPathKey";

    /* renamed from: h */
    protected static String f718h = "secretCodeKey";

    /* renamed from: i */
    protected static String f719i = "licenseIdKey";

    /* renamed from: j */
    protected static String f720j = "versionKey";

    /* renamed from: a */
    public String f721a;

    /* renamed from: b */
    public Long f722b = Long.valueOf(0);

    /* renamed from: c */
    public String f723c;

    /* renamed from: d */
    public String f724d;

    public C1472a() {
        String str = "";
        this.f721a = str;
        this.f723c = str;
        this.f724d = str;
    }

    /* renamed from: b */
    public static C1472a m438b(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(f715e);
        sb.append("-");
        sb.append(str);
        SharedPreferences sharedPreferences = context.getSharedPreferences(sb.toString(), 0);
        if (!sharedPreferences.getBoolean(f716f, false)) {
            return null;
        }
        C1472a aVar = new C1472a();
        String str2 = "";
        aVar.f721a = sharedPreferences.getString(f717g, str2);
        aVar.f723c = sharedPreferences.getString(f719i, str2);
        aVar.f722b = Long.valueOf(sharedPreferences.getLong(f718h, 0));
        aVar.f724d = sharedPreferences.getString(f720j, str2);
        return aVar;
    }

    /* renamed from: a */
    public void mo28551a(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(f715e);
        sb.append("-");
        sb.append(str);
        context.getSharedPreferences(sb.toString(), 0).edit().putString(f717g, this.f721a).putString(f719i, this.f723c).putLong(f718h, this.f722b.longValue()).putString(f720j, this.f724d).putBoolean(f716f, true).commit();
    }
}
