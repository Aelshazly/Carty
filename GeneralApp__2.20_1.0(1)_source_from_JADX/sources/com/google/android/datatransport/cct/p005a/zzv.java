package com.google.android.datatransport.cct.p005a;

import java.util.List;

/* renamed from: com.google.android.datatransport.cct.a.zzv */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
public abstract class zzv {

    /* renamed from: com.google.android.datatransport.cct.a.zzv$zza */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public static abstract class zza {
        /* access modifiers changed from: 0000 */
        public abstract zza zza(int i);

        public abstract zza zza(long j);

        public abstract zza zza(zzaa zzaa);

        public abstract zza zza(zzq zzq);

        /* access modifiers changed from: 0000 */
        public abstract zza zza(String str);

        public abstract zza zza(List<zzt> list);

        public abstract zzv zza();

        public zza zzb(int i) {
            return zza(i);
        }

        public abstract zza zzb(long j);

        public zza zzb(String str) {
            return zza(str);
        }
    }

    public static zza zza() {
        return new zza().zza(Integer.MIN_VALUE);
    }
}
