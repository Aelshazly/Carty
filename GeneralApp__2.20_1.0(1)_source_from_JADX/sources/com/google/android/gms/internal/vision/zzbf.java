package com.google.android.gms.internal.vision;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzbf extends zzbe<Boolean> {
    zzbf(zzbk zzbk, String str, Boolean bool) {
        super(zzbk, str, bool, null);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzal.zzev.matcher(str).matches()) {
                return Boolean.valueOf(true);
            }
            if (zzal.zzew.matcher(str).matches()) {
                return Boolean.valueOf(false);
            }
        }
        String zzac = super.zzac();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zzac).length() + 28 + String.valueOf(valueOf).length());
        sb.append("Invalid boolean value for ");
        sb.append(zzac);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
