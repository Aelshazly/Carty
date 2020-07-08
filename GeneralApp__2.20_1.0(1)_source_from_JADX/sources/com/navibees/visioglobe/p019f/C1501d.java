package com.navibees.visioglobe.p019f;

import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;

/* renamed from: com.navibees.visioglobe.f.d */
/* compiled from: VgMyMapManagerListParser */
public class C1501d {

    /* renamed from: a */
    private int f807a;

    /* renamed from: b */
    private int f808b = 0;

    /* renamed from: c */
    private JSONArray f809c = null;

    /* renamed from: d */
    boolean f810d = false;

    /* renamed from: e */
    URL f811e;

    /* renamed from: f */
    String f812f = null;

    /* renamed from: g */
    String f813g = null;

    /* renamed from: com.navibees.visioglobe.f.d$a */
    /* compiled from: VgMyMapManagerListParser */
    public class C1502a {

        /* renamed from: a */
        public boolean f814a = false;

        /* renamed from: b */
        public String f815b;

        /* renamed from: c */
        public String f816c;

        /* renamed from: d */
        public String f817d = "";

        /* renamed from: e */
        public long f818e;

        /* renamed from: f */
        public String f819f;

        /* renamed from: g */
        public URL f820g;

        public C1502a(C1501d dVar) {
        }
    }

    /* renamed from: a */
    public boolean mo28604a(String str, URL url) {
        int i;
        boolean z = false;
        this.f810d = false;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f809c = jSONObject.getJSONArray("maps");
            Object opt = jSONObject.opt(ClientCookie.VERSION_ATTR);
            if (opt == null) {
                i = 0;
            } else {
                i = ((Integer) opt).intValue();
            }
            this.f807a = i;
            if (this.f809c.length() > 0) {
                if (this.f807a == 0) {
                    for (String split : url.getQuery().split("&")) {
                        String[] split2 = split.split("=");
                        String str2 = "Vg";
                        StringBuilder sb = new StringBuilder();
                        sb.append("name: ");
                        sb.append(split2[0]);
                        sb.append(" value ");
                        sb.append(split2[1]);
                        Log.d(str2, sb.toString());
                        if (split2[0].equals(Event.LOGIN)) {
                            this.f812f = split2[1];
                        } else if (split2[0].equals("password")) {
                            this.f813g = split2[1];
                        }
                    }
                    if (this.f812f != null) {
                        if (this.f813g != null) {
                            this.f810d = true;
                            this.f811e = url;
                        }
                    }
                    return false;
                } else if (this.f807a == 1) {
                    this.f810d = true;
                    this.f811e = url;
                }
                z = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return z;
    }

    /* renamed from: b */
    public C1502a mo28605b() {
        String str = "/";
        String str2 = "license_id";
        String str3 = "timestamp";
        String str4 = "id";
        String str5 = ClientCookie.VERSION_ATTR;
        C1502a aVar = new C1502a(this);
        if (!mo28603a()) {
            try {
                JSONObject jSONObject = this.f809c.getJSONObject(this.f808b);
                int i = this.f807a;
                String str6 = "secret_code";
                String str7 = "expiry_date";
                String str8 = "name";
                if (i != 0) {
                    if (i == 1) {
                        if (jSONObject.has(str8)) {
                            aVar.f816c = jSONObject.getString(str8);
                        }
                        if (aVar.f816c.isEmpty()) {
                            aVar.f814a = false;
                        } else {
                            if (jSONObject.has(str3)) {
                                aVar.f817d = jSONObject.getString(str3);
                            }
                            aVar.f815b = aVar.f816c.replace(str, ".");
                            if (jSONObject.has(str7)) {
                                jSONObject.getString(str7);
                            }
                            aVar.f819f = "";
                            if (jSONObject.has(str6)) {
                                aVar.f818e = Long.valueOf(jSONObject.getString(str6)).longValue();
                            }
                            aVar.f814a = true;
                            String optString = jSONObject.optString("zip_file");
                            if (!optString.isEmpty()) {
                                String optString2 = jSONObject.optString("base_url");
                                if (!optString2.isEmpty()) {
                                    try {
                                        URL url = this.f811e;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append(optString2);
                                        sb.append(str);
                                        aVar.f820g = new URL(new URL(url, sb.toString()), optString);
                                    } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                        aVar.f814a = false;
                                    }
                                } else {
                                    try {
                                        aVar.f820g = new URL(this.f811e, optString);
                                    } catch (MalformedURLException e2) {
                                        e2.printStackTrace();
                                        aVar.f814a = false;
                                    }
                                }
                            } else {
                                aVar.f814a = false;
                            }
                        }
                    }
                    this.f808b++;
                } else {
                    if (jSONObject.has(str5)) {
                        aVar.f817d = jSONObject.getString(str5);
                    }
                    if (jSONObject.has(str4)) {
                        aVar.f815b = jSONObject.getString(str4);
                    }
                    if (jSONObject.has(str7)) {
                        jSONObject.getString(str7);
                    }
                    if (jSONObject.has(str2)) {
                        aVar.f819f = jSONObject.getString(str2);
                    }
                    if (jSONObject.has(str6)) {
                        aVar.f818e = Long.valueOf(jSONObject.getString(str6)).longValue();
                    }
                    if (jSONObject.has(str8)) {
                        aVar.f816c = jSONObject.getString(str8);
                    }
                    try {
                        URL url2 = this.f811e;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("download?map_id=");
                        sb2.append(aVar.f815b);
                        sb2.append("&login=");
                        sb2.append(this.f812f);
                        sb2.append("+&password=");
                        sb2.append(this.f813g);
                        aVar.f820g = new URL(url2, sb2.toString());
                        aVar.f814a = true;
                    } catch (MalformedURLException e3) {
                        e3.printStackTrace();
                        aVar.f814a = false;
                    }
                    this.f808b++;
                }
            } catch (JSONException e4) {
                e4.printStackTrace();
            }
        }
        return aVar;
    }

    /* renamed from: c */
    public boolean mo28606c() {
        return this.f810d;
    }

    /* renamed from: a */
    public boolean mo28603a() {
        boolean z = true;
        if (!this.f810d) {
            return true;
        }
        if (this.f808b < this.f809c.length()) {
            z = false;
        }
        return z;
    }

    /* renamed from: a */
    static String m518a(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(file))));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
