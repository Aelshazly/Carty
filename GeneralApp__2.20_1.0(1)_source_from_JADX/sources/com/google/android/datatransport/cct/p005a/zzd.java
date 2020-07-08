package com.google.android.datatransport.cct.p005a;

import com.google.android.datatransport.cct.p005a.zza.C1733zza;

/* renamed from: com.google.android.datatransport.cct.a.zzd */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
final class zzd extends zza {
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;

    /* renamed from: com.google.android.datatransport.cct.a.zzd$zza */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    static final class zza extends C1733zza {
        private Integer zza;
        private String zzb;
        private String zzc;
        private String zzd;
        private String zze;
        private String zzf;
        private String zzg;
        private String zzh;

        zza() {
        }

        public C1733zza zza(int i) {
            this.zza = Integer.valueOf(i);
            return this;
        }

        public C1733zza zzb(String str) {
            this.zzh = str;
            return this;
        }

        public C1733zza zzc(String str) {
            this.zzc = str;
            return this;
        }

        public C1733zza zzd(String str) {
            this.zzg = str;
            return this;
        }

        public C1733zza zze(String str) {
            this.zzb = str;
            return this;
        }

        public C1733zza zzf(String str) {
            this.zzf = str;
            return this;
        }

        public C1733zza zzg(String str) {
            this.zze = str;
            return this;
        }

        public C1733zza zza(String str) {
            this.zzd = str;
            return this;
        }

        public zza zza() {
            String str = "";
            if (this.zza == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" sdkVersion");
                str = sb.toString();
            }
            if (str.isEmpty()) {
                zzd zzd2 = new zzd(this.zza.intValue(), this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, null);
                return zzd2;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Missing required properties:");
            sb2.append(str);
            throw new IllegalStateException(sb2.toString());
        }
    }

    /* synthetic */ zzd(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, zzc zzc2) {
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = str4;
        this.zzf = str5;
        this.zzg = str6;
        this.zzh = str7;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zza)) {
            return false;
        }
        zzd zzd2 = (zzd) ((zza) obj);
        if (this.zza == zzd2.zza) {
            String str = this.zzb;
            if (str != null ? str.equals(zzd2.zzb) : zzd2.zzb == null) {
                String str2 = this.zzc;
                if (str2 != null ? str2.equals(zzd2.zzc) : zzd2.zzc == null) {
                    String str3 = this.zzd;
                    if (str3 != null ? str3.equals(zzd2.zzd) : zzd2.zzd == null) {
                        String str4 = this.zze;
                        if (str4 != null ? str4.equals(zzd2.zze) : zzd2.zze == null) {
                            String str5 = this.zzf;
                            if (str5 != null ? str5.equals(zzd2.zzf) : zzd2.zzf == null) {
                                String str6 = this.zzg;
                                if (str6 != null ? str6.equals(zzd2.zzg) : zzd2.zzg == null) {
                                    String str7 = this.zzh;
                                    if (str7 != null) {
                                    }
                                }
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
        int i = (this.zza ^ 1000003) * 1000003;
        String str = this.zzb;
        int i2 = 0;
        int hashCode = (i ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.zzc;
        int hashCode2 = (hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.zzd;
        int hashCode3 = (hashCode2 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.zze;
        int hashCode4 = (hashCode3 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.zzf;
        int hashCode5 = (hashCode4 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.zzg;
        int hashCode6 = (hashCode5 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        String str7 = this.zzh;
        if (str7 != null) {
            i2 = str7.hashCode();
        }
        return hashCode6 ^ i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AndroidClientInfo{sdkVersion=");
        sb.append(this.zza);
        sb.append(", model=");
        sb.append(this.zzb);
        sb.append(", hardware=");
        sb.append(this.zzc);
        sb.append(", device=");
        sb.append(this.zzd);
        sb.append(", product=");
        sb.append(this.zze);
        sb.append(", osBuild=");
        sb.append(this.zzf);
        sb.append(", manufacturer=");
        sb.append(this.zzg);
        sb.append(", fingerprint=");
        sb.append(this.zzh);
        sb.append("}");
        return sb.toString();
    }

    public String zzb() {
        return this.zzd;
    }

    public String zzc() {
        return this.zzh;
    }

    public String zzd() {
        return this.zzc;
    }

    public String zze() {
        return this.zzg;
    }

    public String zzf() {
        return this.zzb;
    }

    public String zzg() {
        return this.zzf;
    }

    public String zzh() {
        return this.zze;
    }

    public int zzi() {
        return this.zza;
    }
}
