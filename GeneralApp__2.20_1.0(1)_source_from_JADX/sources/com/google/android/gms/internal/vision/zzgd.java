package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs.zzg;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public class zzgd {
    private static volatile boolean zztb = false;
    private static boolean zztc = true;
    private static volatile zzgd zztd;
    private static volatile zzgd zzte;
    private static final zzgd zztf = new zzgd(true);
    private final Map<zza, zzg<?, ?>> zztg;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    static final class zza {
        private final int number;
        private final Object object;

        zza(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.object == zza.object && this.number == zza.number) {
                return true;
            }
            return false;
        }
    }

    public static zzgd zzfk() {
        return new zzgd();
    }

    public static zzgd zzfl() {
        zzgd zzgd = zztd;
        if (zzgd == null) {
            synchronized (zzgd.class) {
                zzgd = zztd;
                if (zzgd == null) {
                    zzgd = zztf;
                    zztd = zzgd;
                }
            }
        }
        return zzgd;
    }

    public static zzgd zzfm() {
        Class<zzgd> cls = zzgd.class;
        zzgd zzgd = zzte;
        if (zzgd != null) {
            return zzgd;
        }
        synchronized (cls) {
            zzgd zzgd2 = zzte;
            if (zzgd2 != null) {
                return zzgd2;
            }
            zzgd zzc = zzgr.zzc(cls);
            zzte = zzc;
            return zzc;
        }
    }

    public final <ContainingType extends zzic> zzg<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzg) this.zztg.get(new zza(containingtype, i));
    }

    public final void zza(zzg<?, ?> zzg) {
        this.zztg.put(new zza(zzg.zzxf, zzg.zzxh.number), zzg);
    }

    zzgd() {
        this.zztg = new HashMap();
    }

    private zzgd(boolean z) {
        this.zztg = Collections.emptyMap();
    }
}
