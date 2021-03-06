package com.google.android.gms.internal.measurement;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
final class zzcq extends zzcl<Boolean> {
    zzcq(zzcr zzcr, String str, Boolean bool) {
        super(zzcr, str, bool, null);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzbw.zzb.matcher(str).matches()) {
                return Boolean.valueOf(true);
            }
            if (zzbw.zzc.matcher(str).matches()) {
                return Boolean.valueOf(false);
            }
        }
        String zzb = super.zzb();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zzb).length() + 28 + String.valueOf(valueOf).length());
        sb.append("Invalid boolean value for ");
        sb.append(zzb);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
