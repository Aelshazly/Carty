package com.google.android.datatransport.cct.p005a;

import com.google.android.datatransport.cct.p005a.zzq.zzb;

/* renamed from: com.google.android.datatransport.cct.a.zzg */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
final class zzg extends zzq {
    private final zzb zza;
    private final zza zzb;

    /* renamed from: com.google.android.datatransport.cct.a.zzg$zza */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    static final class zza extends com.google.android.datatransport.cct.p005a.zzq.zza {
        private zzb zza;
        private zza zzb;

        zza() {
        }

        public com.google.android.datatransport.cct.p005a.zzq.zza zza(zzb zzb2) {
            this.zza = zzb2;
            return this;
        }

        public com.google.android.datatransport.cct.p005a.zzq.zza zza(zza zza2) {
            this.zzb = zza2;
            return this;
        }

        public zzq zza() {
            return new zzg(this.zza, this.zzb, null);
        }
    }

    /* synthetic */ zzg(zzb zzb2, zza zza2, zzf zzf) {
        this.zza = zzb2;
        this.zzb = zza2;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzq)) {
            return false;
        }
        zzb zzb2 = this.zza;
        if (zzb2 != null ? zzb2.equals(((zzg) obj).zza) : ((zzg) obj).zza == null) {
            zza zza2 = this.zzb;
            if (zza2 != null) {
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        zzb zzb2 = this.zza;
        int i = 0;
        int hashCode = ((zzb2 == null ? 0 : zzb2.hashCode()) ^ 1000003) * 1000003;
        zza zza2 = this.zzb;
        if (zza2 != null) {
            i = zza2.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClientInfo{clientType=");
        sb.append(this.zza);
        sb.append(", androidClientInfo=");
        sb.append(this.zzb);
        sb.append("}");
        return sb.toString();
    }

    public zza zzb() {
        return this.zzb;
    }

    public zzb zzc() {
        return this.zza;
    }
}
