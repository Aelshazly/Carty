package com.loopj.android.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import p008cz.msebera.android.httpclient.client.CookieStore;
import p008cz.msebera.android.httpclient.cookie.Cookie;

public class PersistentCookieStore implements CookieStore {
    private static final String COOKIE_NAME_PREFIX = "cookie_";
    private static final String COOKIE_NAME_STORE = "names";
    private static final String COOKIE_PREFS = "CookiePrefsFile";
    private static final String LOG_TAG = "PersistentCookieStore";
    private final SharedPreferences cookiePrefs;
    private final ConcurrentHashMap<String, Cookie> cookies;
    private boolean omitNonPersistentCookies = false;

    public PersistentCookieStore(Context context) {
        String[] cookieNames;
        this.cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        this.cookies = new ConcurrentHashMap<>();
        String storedCookieNames = this.cookiePrefs.getString(COOKIE_NAME_STORE, null);
        if (storedCookieNames != null) {
            for (String name : TextUtils.split(storedCookieNames, ",")) {
                SharedPreferences sharedPreferences = this.cookiePrefs;
                StringBuilder sb = new StringBuilder();
                sb.append(COOKIE_NAME_PREFIX);
                sb.append(name);
                String encodedCookie = sharedPreferences.getString(sb.toString(), null);
                if (encodedCookie != null) {
                    Cookie decodedCookie = decodeCookie(encodedCookie);
                    if (decodedCookie != null) {
                        this.cookies.put(name, decodedCookie);
                    }
                }
            }
            clearExpired(new Date());
        }
    }

    public void addCookie(Cookie cookie) {
        if (!this.omitNonPersistentCookies || cookie.isPersistent()) {
            StringBuilder sb = new StringBuilder();
            sb.append(cookie.getName());
            sb.append(cookie.getDomain());
            String name = sb.toString();
            if (!cookie.isExpired(new Date())) {
                this.cookies.put(name, cookie);
            } else {
                this.cookies.remove(name);
            }
            Editor prefsWriter = this.cookiePrefs.edit();
            prefsWriter.putString(COOKIE_NAME_STORE, TextUtils.join(",", this.cookies.keySet()));
            StringBuilder sb2 = new StringBuilder();
            sb2.append(COOKIE_NAME_PREFIX);
            sb2.append(name);
            prefsWriter.putString(sb2.toString(), encodeCookie(new SerializableCookie(cookie)));
            prefsWriter.commit();
        }
    }

    public void clear() {
        Editor prefsWriter = this.cookiePrefs.edit();
        for (String name : this.cookies.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(COOKIE_NAME_PREFIX);
            sb.append(name);
            prefsWriter.remove(sb.toString());
        }
        prefsWriter.remove(COOKIE_NAME_STORE);
        prefsWriter.commit();
        this.cookies.clear();
    }

    public boolean clearExpired(Date date) {
        boolean clearedAny = false;
        Editor prefsWriter = this.cookiePrefs.edit();
        for (Entry<String, Cookie> entry : this.cookies.entrySet()) {
            String name = (String) entry.getKey();
            if (((Cookie) entry.getValue()).isExpired(date)) {
                this.cookies.remove(name);
                StringBuilder sb = new StringBuilder();
                sb.append(COOKIE_NAME_PREFIX);
                sb.append(name);
                prefsWriter.remove(sb.toString());
                clearedAny = true;
            }
        }
        if (clearedAny) {
            prefsWriter.putString(COOKIE_NAME_STORE, TextUtils.join(",", this.cookies.keySet()));
        }
        prefsWriter.commit();
        return clearedAny;
    }

    public List<Cookie> getCookies() {
        return new ArrayList(this.cookies.values());
    }

    public void setOmitNonPersistentCookies(boolean omitNonPersistentCookies2) {
        this.omitNonPersistentCookies = omitNonPersistentCookies2;
    }

    public void deleteCookie(Cookie cookie) {
        StringBuilder sb = new StringBuilder();
        sb.append(cookie.getName());
        sb.append(cookie.getDomain());
        String name = sb.toString();
        this.cookies.remove(name);
        Editor prefsWriter = this.cookiePrefs.edit();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(COOKIE_NAME_PREFIX);
        sb2.append(name);
        prefsWriter.remove(sb2.toString());
        prefsWriter.commit();
    }

    /* access modifiers changed from: protected */
    public String encodeCookie(SerializableCookie cookie) {
        if (cookie == null) {
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(os).writeObject(cookie);
            return byteArrayToHexString(os.toByteArray());
        } catch (IOException e) {
            AsyncHttpClient.log.mo22448d(LOG_TAG, "IOException in encodeCookie", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Cookie decodeCookie(String cookieString) {
        String str = LOG_TAG;
        try {
            return ((SerializableCookie) new ObjectInputStream(new ByteArrayInputStream(hexStringToByteArray(cookieString))).readObject()).getCookie();
        } catch (IOException e) {
            AsyncHttpClient.log.mo22448d(str, "IOException in decodeCookie", e);
            return null;
        } catch (ClassNotFoundException e2) {
            AsyncHttpClient.log.mo22448d(str, "ClassNotFoundException in decodeCookie", e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 255;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    /* access modifiers changed from: protected */
    public byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[(len / 2)];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}
