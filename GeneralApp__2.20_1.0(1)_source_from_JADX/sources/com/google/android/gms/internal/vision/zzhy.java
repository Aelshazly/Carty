package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzhy implements zzhv {
    zzhy() {
    }

    public final Map<?, ?> zzl(Object obj) {
        return (zzhw) obj;
    }

    public final zzht<?, ?> zzq(Object obj) {
        zzhu zzhu = (zzhu) obj;
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzm(Object obj) {
        return (zzhw) obj;
    }

    public final boolean zzn(Object obj) {
        return !((zzhw) obj).isMutable();
    }

    public final Object zzo(Object obj) {
        ((zzhw) obj).zzdp();
        return obj;
    }

    public final Object zzp(Object obj) {
        return zzhw.zzhc().zzhd();
    }

    public final Object zzc(Object obj, Object obj2) {
        zzhw zzhw = (zzhw) obj;
        zzhw zzhw2 = (zzhw) obj2;
        if (!zzhw2.isEmpty()) {
            if (!zzhw.isMutable()) {
                zzhw = zzhw.zzhd();
            }
            zzhw.zza(zzhw2);
        }
        return zzhw;
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzhw zzhw = (zzhw) obj;
        zzhu zzhu = (zzhu) obj2;
        if (zzhw.isEmpty()) {
            return 0;
        }
        Iterator it = zzhw.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Entry entry = (Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
