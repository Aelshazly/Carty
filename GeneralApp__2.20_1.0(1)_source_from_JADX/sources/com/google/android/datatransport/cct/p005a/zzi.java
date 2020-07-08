package com.google.android.datatransport.cct.p005a;

import java.util.Arrays;

/* renamed from: com.google.android.datatransport.cct.a.zzi */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
final class zzi extends zzt {
    private final long zza;
    private final int zzb;
    private final long zzc;
    private final byte[] zzd;
    private final String zze;
    private final long zzf;
    private final zzy zzg;

    /* renamed from: com.google.android.datatransport.cct.a.zzi$zza */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    static final class zza extends com.google.android.datatransport.cct.p005a.zzt.zza {
        private Long zza;
        private Integer zzb;
        private Long zzc;
        private byte[] zzd;
        private String zze;
        private Long zzf;
        private zzy zzg;

        zza() {
        }

        public com.google.android.datatransport.cct.p005a.zzt.zza zza(long j) {
            this.zza = Long.valueOf(j);
            return this;
        }

        public com.google.android.datatransport.cct.p005a.zzt.zza zzb(long j) {
            this.zzc = Long.valueOf(j);
            return this;
        }

        public com.google.android.datatransport.cct.p005a.zzt.zza zzc(long j) {
            this.zzf = Long.valueOf(j);
            return this;
        }

        public com.google.android.datatransport.cct.p005a.zzt.zza zza(int i) {
            this.zzb = Integer.valueOf(i);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.google.android.datatransport.cct.p005a.zzt.zza zza(byte[] bArr) {
            this.zzd = bArr;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.google.android.datatransport.cct.p005a.zzt.zza zza(String str) {
            this.zze = str;
            return this;
        }

        public com.google.android.datatransport.cct.p005a.zzt.zza zza(zzy zzy) {
            this.zzg = zzy;
            return this;
        }

        public zzt zza() {
            String str = "";
            if (this.zza == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" eventTimeMs");
                str = sb.toString();
            }
            if (this.zzb == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" eventCode");
                str = sb2.toString();
            }
            if (this.zzc == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" eventUptimeMs");
                str = sb3.toString();
            }
            if (this.zzf == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" timezoneOffsetSeconds");
                str = sb4.toString();
            }
            if (str.isEmpty()) {
                zzi zzi = new zzi(this.zza.longValue(), this.zzb.intValue(), this.zzc.longValue(), this.zzd, this.zze, this.zzf.longValue(), this.zzg, null);
                return zzi;
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Missing required properties:");
            sb5.append(str);
            throw new IllegalStateException(sb5.toString());
        }
    }

    /* synthetic */ zzi(long j, int i, long j2, byte[] bArr, String str, long j3, zzy zzy, zzh zzh) {
        this.zza = j;
        this.zzb = i;
        this.zzc = j2;
        this.zzd = bArr;
        this.zze = str;
        this.zzf = j3;
        this.zzg = zzy;
    }

    public boolean equals(Object obj) {
        byte[] bArr;
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzt)) {
            return false;
        }
        zzt zzt = (zzt) obj;
        if (this.zza == zzt.zza()) {
            zzi zzi = (zzi) zzt;
            if (this.zzb == zzi.zzb && this.zzc == zzt.zzb()) {
                byte[] bArr2 = this.zzd;
                if (zzt instanceof zzi) {
                    bArr = zzi.zzd;
                } else {
                    bArr = zzi.zzd;
                }
                if (Arrays.equals(bArr2, bArr)) {
                    String str = this.zze;
                    if (str != null ? str.equals(zzi.zze) : zzi.zze == null) {
                        if (this.zzf == zzt.zzc()) {
                            zzy zzy = this.zzg;
                            if (zzy != null) {
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
        int i = (((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.zzb) * 1000003;
        long j2 = this.zzc;
        int hashCode = (((i ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.zzd)) * 1000003;
        String str = this.zze;
        int i2 = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        long j3 = this.zzf;
        int i3 = (hashCode2 ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003;
        zzy zzy = this.zzg;
        if (zzy != null) {
            i2 = zzy.hashCode();
        }
        return i3 ^ i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LogEvent{eventTimeMs=");
        sb.append(this.zza);
        sb.append(", eventCode=");
        sb.append(this.zzb);
        sb.append(", eventUptimeMs=");
        sb.append(this.zzc);
        sb.append(", sourceExtension=");
        sb.append(Arrays.toString(this.zzd));
        sb.append(", sourceExtensionJsonProto3=");
        sb.append(this.zze);
        sb.append(", timezoneOffsetSeconds=");
        sb.append(this.zzf);
        sb.append(", networkConnectionInfo=");
        sb.append(this.zzg);
        sb.append("}");
        return sb.toString();
    }

    public long zza() {
        return this.zza;
    }

    public long zzb() {
        return this.zzc;
    }

    public long zzc() {
        return this.zzf;
    }

    public int zzd() {
        return this.zzb;
    }

    public zzy zze() {
        return this.zzg;
    }

    public byte[] zzf() {
        return this.zzd;
    }

    public String zzg() {
        return this.zze;
    }
}
