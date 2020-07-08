package com.google.android.datatransport.cct.p005a;

import com.google.android.datatransport.cct.p005a.zzy.zzb;
import com.google.android.datatransport.cct.p005a.zzy.zzc;

/* renamed from: com.google.android.datatransport.cct.a.zzn */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
final class zzn extends zzy {
    private final zzc zza;
    private final zzb zzb;

    /* renamed from: com.google.android.datatransport.cct.a.zzn$zza */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    static final class zza extends com.google.android.datatransport.cct.p005a.zzy.zza {
        private zzc zza;
        private zzb zzb;

        zza() {
        }

        public com.google.android.datatransport.cct.p005a.zzy.zza zza(zzc zzc) {
            this.zza = zzc;
            return this;
        }

        public com.google.android.datatransport.cct.p005a.zzy.zza zza(zzb zzb2) {
            this.zzb = zzb2;
            return this;
        }

        public zzy zza() {
            return new zzn(this.zza, this.zzb, null);
        }
    }

    /* synthetic */ zzn(zzc zzc, zzb zzb2, zzm zzm) {
        this.zza = zzc;
        this.zzb = zzb2;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzy)) {
            return false;
        }
        zzc zzc = this.zza;
        if (zzc != null ? zzc.equals(((zzn) obj).zza) : ((zzn) obj).zza == null) {
            zzb zzb2 = this.zzb;
            if (zzb2 != null) {
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        zzc zzc = this.zza;
        int i = 0;
        int hashCode = ((zzc == null ? 0 : zzc.hashCode()) ^ 1000003) * 1000003;
        zzb zzb2 = this.zzb;
        if (zzb2 != null) {
            i = zzb2.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NetworkConnectionInfo{networkType=");
        sb.append(this.zza);
        sb.append(", mobileSubtype=");
        sb.append(this.zzb);
        sb.append("}");
        return sb.toString();
    }

    public zzb zzb() {
        return this.zzb;
    }

    public zzc zzc() {
        return this.zza;
    }
}
