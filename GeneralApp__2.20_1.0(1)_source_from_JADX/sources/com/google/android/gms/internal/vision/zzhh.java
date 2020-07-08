package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public class zzhh {
    private static final zzgd zzrr = zzgd.zzfl();
    private zzfh zzyd;
    private volatile zzic zzye;
    private volatile zzfh zzyf;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhh)) {
            return false;
        }
        zzhh zzhh = (zzhh) obj;
        zzic zzic = this.zzye;
        zzic zzic2 = zzhh.zzye;
        if (zzic == null && zzic2 == null) {
            return zzdk().equals(zzhh.zzdk());
        }
        if (zzic != null && zzic2 != null) {
            return zzic.equals(zzic2);
        }
        if (zzic != null) {
            return zzic.equals(zzhh.zzh(zzic.zzgd()));
        }
        return zzh(zzic2.zzgd()).equals(zzic2);
    }

    public int hashCode() {
        return 1;
    }

    private final zzic zzh(zzic zzic) {
        if (this.zzye == null) {
            synchronized (this) {
                if (this.zzye == null) {
                    try {
                        this.zzye = zzic;
                        this.zzyf = zzfh.zzsd;
                    } catch (zzhc e) {
                        this.zzye = zzic;
                        this.zzyf = zzfh.zzsd;
                    }
                }
            }
        }
        return this.zzye;
    }

    public final zzic zzi(zzic zzic) {
        zzic zzic2 = this.zzye;
        this.zzyd = null;
        this.zzyf = null;
        this.zzye = zzic;
        return zzic2;
    }

    public final int zzgf() {
        if (this.zzyf != null) {
            return this.zzyf.size();
        }
        if (this.zzye != null) {
            return this.zzye.zzgf();
        }
        return 0;
    }

    public final zzfh zzdk() {
        if (this.zzyf != null) {
            return this.zzyf;
        }
        synchronized (this) {
            if (this.zzyf != null) {
                zzfh zzfh = this.zzyf;
                return zzfh;
            }
            if (this.zzye == null) {
                this.zzyf = zzfh.zzsd;
            } else {
                this.zzyf = this.zzye.zzdk();
            }
            zzfh zzfh2 = this.zzyf;
            return zzfh2;
        }
    }
}
