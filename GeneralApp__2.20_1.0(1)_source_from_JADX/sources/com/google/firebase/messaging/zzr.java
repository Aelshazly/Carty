package com.google.firebase.messaging;

import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import okhttp3.internal.cache.DiskLruCache;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
public final class zzr {
    private final Bundle zza;

    public zzr(Bundle bundle) {
        if (bundle != null) {
            this.zza = new Bundle(bundle);
            return;
        }
        throw new NullPointerException("data");
    }

    public final String zza(String str) {
        Bundle bundle = this.zza;
        if (!bundle.containsKey(str) && str.startsWith("gcm.n.")) {
            String zzi = zzi(str);
            if (this.zza.containsKey(zzi)) {
                str = zzi;
            }
        }
        return bundle.getString(str);
    }

    public final boolean zzb(String str) {
        String zza2 = zza(str);
        return DiskLruCache.VERSION_1.equals(zza2) || Boolean.parseBoolean(zza2);
    }

    public final Integer zzc(String str) {
        String zza2 = zza(str);
        if (!TextUtils.isEmpty(zza2)) {
            try {
                return Integer.valueOf(Integer.parseInt(zza2));
            } catch (NumberFormatException e) {
                String zzh = zzh(str);
                StringBuilder sb = new StringBuilder(String.valueOf(zzh).length() + 38 + String.valueOf(zza2).length());
                sb.append("Couldn't parse value of ");
                sb.append(zzh);
                sb.append("(");
                sb.append(zza2);
                sb.append(") into an int");
                Log.w("NotificationParams", sb.toString());
            }
        }
        return null;
    }

    public final Long zzd(String str) {
        String zza2 = zza(str);
        if (!TextUtils.isEmpty(zza2)) {
            try {
                return Long.valueOf(Long.parseLong(zza2));
            } catch (NumberFormatException e) {
                String zzh = zzh(str);
                StringBuilder sb = new StringBuilder(String.valueOf(zzh).length() + 38 + String.valueOf(zza2).length());
                sb.append("Couldn't parse value of ");
                sb.append(zzh);
                sb.append("(");
                sb.append(zza2);
                sb.append(") into a long");
                Log.w("NotificationParams", sb.toString());
            }
        }
        return null;
    }

    public final String zze(String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        return zza(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    public final Object[] zzf(String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_args");
        JSONArray zzg = zzg(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (zzg == null) {
            return null;
        }
        String[] strArr = new String[zzg.length()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = zzg.optString(i);
        }
        return strArr;
    }

    private final JSONArray zzg(String str) {
        String zza2 = zza(str);
        if (!TextUtils.isEmpty(zza2)) {
            try {
                return new JSONArray(zza2);
            } catch (JSONException e) {
                String zzh = zzh(str);
                StringBuilder sb = new StringBuilder(String.valueOf(zzh).length() + 50 + String.valueOf(zza2).length());
                sb.append("Malformed JSON for key ");
                sb.append(zzh);
                sb.append(": ");
                sb.append(zza2);
                sb.append(", falling back to default");
                Log.w("NotificationParams", sb.toString());
            }
        }
        return null;
    }

    private static String zzh(String str) {
        if (str.startsWith("gcm.n.")) {
            return str.substring(6);
        }
        return str;
    }

    public final Uri zza() {
        String zza2 = zza("gcm.n.link_android");
        if (TextUtils.isEmpty(zza2)) {
            zza2 = zza("gcm.n.link");
        }
        if (!TextUtils.isEmpty(zza2)) {
            return Uri.parse(zza2);
        }
        return null;
    }

    public final String zzb() {
        String zza2 = zza("gcm.n.sound2");
        if (TextUtils.isEmpty(zza2)) {
            return zza("gcm.n.sound");
        }
        return zza2;
    }

    public final long[] zzc() {
        JSONArray zzg = zzg("gcm.n.vibrate_timings");
        if (zzg == null) {
            return null;
        }
        try {
            if (zzg.length() > 1) {
                long[] jArr = new long[zzg.length()];
                for (int i = 0; i < jArr.length; i++) {
                    jArr[i] = zzg.optLong(i);
                }
                return jArr;
            }
            throw new JSONException("vibrateTimings have invalid length");
        } catch (NumberFormatException | JSONException e) {
            String valueOf = String.valueOf(zzg);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 74);
            sb.append("User defined vibrateTimings is invalid: ");
            sb.append(valueOf);
            sb.append(". Skipping setting vibrateTimings.");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final int[] zzd() {
        String str = ". Skipping setting LightSettings";
        String str2 = "LightSettings is invalid: ";
        String str3 = "NotificationParams";
        JSONArray zzg = zzg("gcm.n.light_settings");
        if (zzg == null) {
            return null;
        }
        int[] iArr = new int[3];
        try {
            if (zzg.length() == 3) {
                int parseColor = Color.parseColor(zzg.optString(0));
                if (parseColor != -16777216) {
                    iArr[0] = parseColor;
                    iArr[1] = zzg.optInt(1);
                    iArr[2] = zzg.optInt(2);
                    return iArr;
                }
                throw new IllegalArgumentException("Transparent color is invalid");
            }
            throw new JSONException("lightSettings don't have all three fields");
        } catch (JSONException e) {
            String valueOf = String.valueOf(zzg);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 58);
            sb.append(str2);
            sb.append(valueOf);
            sb.append(str);
            Log.w(str3, sb.toString());
            return null;
        } catch (IllegalArgumentException e2) {
            String valueOf2 = String.valueOf(zzg);
            String message = e2.getMessage();
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 60 + String.valueOf(message).length());
            sb2.append(str2);
            sb2.append(valueOf2);
            sb2.append(". ");
            sb2.append(message);
            sb2.append(str);
            Log.w(str3, sb2.toString());
            return null;
        }
    }

    public final Bundle zze() {
        Bundle bundle = new Bundle(this.zza);
        for (String str : this.zza.keySet()) {
            if (str.startsWith("google.c.") || str.startsWith("gcm.n.") || str.startsWith("gcm.notification.")) {
                bundle.remove(str);
            }
        }
        return bundle;
    }

    public final Bundle zzf() {
        Bundle bundle = new Bundle(this.zza);
        for (String str : this.zza.keySet()) {
            if (!(str.startsWith("google.c.a.") || str.equals("from"))) {
                bundle.remove(str);
            }
        }
        return bundle;
    }

    private final String zzb(Resources resources, String str, String str2) {
        String zze = zze(str2);
        if (TextUtils.isEmpty(zze)) {
            return null;
        }
        int identifier = resources.getIdentifier(zze, "string", str);
        String str3 = " Default value will be used.";
        String str4 = "NotificationParams";
        if (identifier == 0) {
            String valueOf = String.valueOf(str2);
            String valueOf2 = String.valueOf("_loc_key");
            String zzh = zzh(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
            StringBuilder sb = new StringBuilder(String.valueOf(zzh).length() + 49 + String.valueOf(str2).length());
            sb.append(zzh);
            sb.append(" resource not found: ");
            sb.append(str2);
            sb.append(str3);
            Log.w(str4, sb.toString());
            return null;
        }
        Object[] zzf = zzf(str2);
        if (zzf == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, zzf);
        } catch (MissingFormatArgumentException e) {
            String zzh2 = zzh(str2);
            String arrays = Arrays.toString(zzf);
            StringBuilder sb2 = new StringBuilder(String.valueOf(zzh2).length() + 58 + String.valueOf(arrays).length());
            sb2.append("Missing format argument for ");
            sb2.append(zzh2);
            sb2.append(": ");
            sb2.append(arrays);
            sb2.append(str3);
            Log.w(str4, sb2.toString(), e);
            return null;
        }
    }

    public final String zza(Resources resources, String str, String str2) {
        String zza2 = zza(str2);
        if (!TextUtils.isEmpty(zza2)) {
            return zza2;
        }
        return zzb(resources, str, str2);
    }

    public static boolean zza(Bundle bundle) {
        String str = "gcm.n.e";
        String string = bundle.getString(str);
        String str2 = DiskLruCache.VERSION_1;
        return str2.equals(string) || str2.equals(bundle.getString(zzi(str)));
    }

    private static String zzi(String str) {
        String str2 = "gcm.n.";
        if (!str.startsWith(str2)) {
            return str;
        }
        return str.replace(str2, "gcm.notification.");
    }
}
