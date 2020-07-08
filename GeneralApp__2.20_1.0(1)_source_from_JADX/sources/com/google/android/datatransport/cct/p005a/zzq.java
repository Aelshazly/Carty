package com.google.android.datatransport.cct.p005a;

/* renamed from: com.google.android.datatransport.cct.a.zzq */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
public abstract class zzq {

    /* renamed from: com.google.android.datatransport.cct.a.zzq$zza */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public static abstract class zza {
        public abstract zza zza(zza zza);

        public abstract zza zza(zzb zzb);

        public abstract zzq zza();
    }

    /* renamed from: com.google.android.datatransport.cct.a.zzq$zzb */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public enum zzb {
        UNKNOWN(0),
        ANDROID(4);

        static {
            UNKNOWN = new zzb("UNKNOWN", 0, 0);
            ANDROID = new zzb("ANDROID", 1, 4);
            zzb[] zzbArr = {UNKNOWN, ANDROID};
        }

        private zzb(int i) {
        }
    }

    public static zza zza() {
        return new zza();
    }
}
