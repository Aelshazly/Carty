package com.google.android.datatransport.cct.p005a;

import java.util.List;

/* renamed from: com.google.android.datatransport.cct.a.zzk */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
final class zzk extends zzv {
    private final long zza;
    private final long zzb;
    private final zzq zzc;
    private final int zzd;
    private final String zze;
    private final List<zzt> zzf;
    private final zzaa zzg;

    /* renamed from: com.google.android.datatransport.cct.a.zzk$zza */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    static final class zza extends com.google.android.datatransport.cct.p005a.zzv.zza {
        private Long zza;
        private Long zzb;
        private zzq zzc;
        private Integer zzd;
        private String zze;
        private List<zzt> zzf;
        private zzaa zzg;

        zza() {
        }

        public com.google.android.datatransport.cct.p005a.zzv.zza zza(long j) {
            this.zza = Long.valueOf(j);
            return this;
        }

        public com.google.android.datatransport.cct.p005a.zzv.zza zzb(long j) {
            this.zzb = Long.valueOf(j);
            return this;
        }

        public com.google.android.datatransport.cct.p005a.zzv.zza zza(zzq zzq) {
            this.zzc = zzq;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.google.android.datatransport.cct.p005a.zzv.zza zza(int i) {
            this.zzd = Integer.valueOf(i);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.google.android.datatransport.cct.p005a.zzv.zza zza(String str) {
            this.zze = str;
            return this;
        }

        public com.google.android.datatransport.cct.p005a.zzv.zza zza(List<zzt> list) {
            this.zzf = list;
            return this;
        }

        public com.google.android.datatransport.cct.p005a.zzv.zza zza(zzaa zzaa) {
            this.zzg = zzaa;
            return this;
        }

        public zzv zza() {
            String str = "";
            if (this.zza == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" requestTimeMs");
                str = sb.toString();
            }
            if (this.zzb == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" requestUptimeMs");
                str = sb2.toString();
            }
            if (this.zzd == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" logSource");
                str = sb3.toString();
            }
            if (str.isEmpty()) {
                zzk zzk = new zzk(this.zza.longValue(), this.zzb.longValue(), this.zzc, this.zzd.intValue(), this.zze, this.zzf, this.zzg, null);
                return zzk;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Missing required properties:");
            sb4.append(str);
            throw new IllegalStateException(sb4.toString());
        }
    }

    /* synthetic */ zzk(long j, long j2, zzq zzq, int i, String str, List list, zzaa zzaa, zzj zzj) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = zzq;
        this.zzd = i;
        this.zze = str;
        this.zzf = list;
        this.zzg = zzaa;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzv)) {
            return false;
        }
        zzk zzk = (zzk) ((zzv) obj);
        if (this.zza == zzk.zza && this.zzb == zzk.zzb) {
            zzq zzq = this.zzc;
            if (zzq != null ? zzq.equals(zzk.zzc) : zzk.zzc == null) {
                if (this.zzd == zzk.zzd) {
                    String str = this.zze;
                    if (str != null ? str.equals(zzk.zze) : zzk.zze == null) {
                        List<zzt> list = this.zzf;
                        if (list != null ? list.equals(zzk.zzf) : zzk.zzf == null) {
                            zzaa zzaa = this.zzg;
                            if (zzaa != null) {
                            }
                        }
                    }
                }
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        long j = this.zza;
        int i = (((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003;
        long j2 = this.zzb;
        int i2 = (i ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003;
        zzq zzq = this.zzc;
        int i3 = 0;
        int hashCode = (((i2 ^ (zzq == null ? 0 : zzq.hashCode())) * 1000003) ^ this.zzd) * 1000003;
        String str = this.zze;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List<zzt> list = this.zzf;
        int hashCode3 = (hashCode2 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        zzaa zzaa = this.zzg;
        if (zzaa != null) {
            i3 = zzaa.hashCode();
        }
        return hashCode3 ^ i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LogRequest{requestTimeMs=");
        sb.append(this.zza);
        sb.append(", requestUptimeMs=");
        sb.append(this.zzb);
        sb.append(", clientInfo=");
        sb.append(this.zzc);
        sb.append(", logSource=");
        sb.append(this.zzd);
        sb.append(", logSourceName=");
        sb.append(this.zze);
        sb.append(", logEvents=");
        sb.append(this.zzf);
        sb.append(", qosTier=");
        sb.append(this.zzg);
        sb.append("}");
        return sb.toString();
    }

    public zzq zzb() {
        return this.zzc;
    }

    public List<zzt> zzc() {
        return this.zzf;
    }

    public int zzd() {
        return this.zzd;
    }

    public String zze() {
        return this.zze;
    }

    public long zzf() {
        return this.zza;
    }

    public long zzg() {
        return this.zzb;
    }
}
