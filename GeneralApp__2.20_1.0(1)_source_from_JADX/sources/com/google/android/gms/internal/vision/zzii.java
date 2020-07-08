package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs.zze;
import com.google.android.gms.internal.vision.zzgs.zzg;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzii<T> implements zzir<T> {
    private final zzic zzze;
    private final boolean zzzf;
    private final zzjj<?, ?> zzzo;
    private final zzgf<?> zzzp;

    private zzii(zzjj<?, ?> zzjj, zzgf<?> zzgf, zzic zzic) {
        this.zzzo = zzjj;
        this.zzzf = zzgf.zze(zzic);
        this.zzzp = zzgf;
        this.zzze = zzic;
    }

    static <T> zzii<T> zza(zzjj<?, ?> zzjj, zzgf<?> zzgf, zzic zzic) {
        return new zzii<>(zzjj, zzgf, zzic);
    }

    public final T newInstance() {
        return this.zzze.zzgj().zzgb();
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzzo.zzw(t).equals(this.zzzo.zzw(t2))) {
            return false;
        }
        if (this.zzzf) {
            return this.zzzp.zzf(t).equals(this.zzzp.zzf(t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzzo.zzw(t).hashCode();
        if (this.zzzf) {
            return (hashCode * 53) + this.zzzp.zzf(t).hashCode();
        }
        return hashCode;
    }

    public final void zzd(T t, T t2) {
        zzit.zza(this.zzzo, t, t2);
        if (this.zzzf) {
            zzit.zza(this.zzzp, t, t2);
        }
    }

    public final void zza(T t, zzkg zzkg) throws IOException {
        Iterator it = this.zzzp.zzf(t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzgk zzgk = (zzgk) entry.getKey();
            if (zzgk.zzft() != zzkd.MESSAGE || zzgk.zzfu() || zzgk.zzfv()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzhf) {
                zzkg.zza(zzgk.zzag(), (Object) ((zzhf) entry).zzgw().zzdk());
            } else {
                zzkg.zza(zzgk.zzag(), entry.getValue());
            }
        }
        zzjj<?, ?> zzjj = this.zzzo;
        zzjj.zzc(zzjj.zzw(t), zzkg);
    }

    public final void zza(T t, byte[] bArr, int i, int i2, zzfb zzfb) throws IOException {
        zzgs zzgs = (zzgs) t;
        zzjm zzjm = zzgs.zzwj;
        if (zzjm == zzjm.zzig()) {
            zzjm = zzjm.zzih();
            zzgs.zzwj = zzjm;
        }
        zzgi zzgk = ((zze) t).zzgk();
        zzg zzg = null;
        while (i < i2) {
            int zza = zzez.zza(bArr, i, zzfb);
            int i3 = zzfb.zzru;
            if (i3 == 11) {
                int i4 = 0;
                Object obj = null;
                while (zza < i2) {
                    zza = zzez.zza(bArr, zza, zzfb);
                    int i5 = zzfb.zzru;
                    int i6 = i5 >>> 3;
                    int i7 = i5 & 7;
                    if (i6 != 2) {
                        if (i6 == 3) {
                            if (zzg != null) {
                                zza = zzez.zza(zzin.zzho().zzf(zzg.zzxg.getClass()), bArr, zza, i2, zzfb);
                                zzgk.zza(zzg.zzxh, zzfb.zzrw);
                            } else if (i7 == 2) {
                                zza = zzez.zze(bArr, zza, zzfb);
                                obj = (zzfh) zzfb.zzrw;
                            }
                        }
                    } else if (i7 == 0) {
                        zza = zzez.zza(bArr, zza, zzfb);
                        i4 = zzfb.zzru;
                        zzg = (zzg) this.zzzp.zza(zzfb.zzcn, this.zzze, i4);
                    }
                    if (i5 == 12) {
                        break;
                    }
                    zza = zzez.zza(i5, bArr, zza, i2, zzfb);
                }
                if (obj != null) {
                    zzjm.zzb((i4 << 3) | 2, obj);
                }
                i = zza;
            } else if ((i3 & 7) == 2) {
                zzg zzg2 = (zzg) this.zzzp.zza(zzfb.zzcn, this.zzze, i3 >>> 3);
                if (zzg2 != null) {
                    i = zzez.zza(zzin.zzho().zzf(zzg2.zzxg.getClass()), bArr, zza, i2, zzfb);
                    zzgk.zza(zzg2.zzxh, zzfb.zzrw);
                    zzg = zzg2;
                } else {
                    i = zzez.zza(i3, bArr, zza, i2, zzjm, zzfb);
                    zzg = zzg2;
                }
            } else {
                i = zzez.zza(i3, bArr, zza, i2, zzfb);
            }
        }
        if (i != i2) {
            throw zzhc.zzgs();
        }
    }

    public final void zza(T t, zzis zzis, zzgd zzgd) throws IOException {
        boolean z;
        zzjj<?, ?> zzjj = this.zzzo;
        zzgf<?> zzgf = this.zzzp;
        Object zzx = zzjj.zzx(t);
        zzgi zzg = zzgf.zzg(t);
        do {
            try {
                if (zzis.zzdu() == Integer.MAX_VALUE) {
                    zzjj.zzg(t, zzx);
                    return;
                }
                int tag = zzis.getTag();
                if (tag == 11) {
                    int i = 0;
                    Object obj = null;
                    zzfh zzfh = null;
                    while (zzis.zzdu() != Integer.MAX_VALUE) {
                        int tag2 = zzis.getTag();
                        if (tag2 == 16) {
                            i = zzis.zzee();
                            obj = zzgf.zza(zzgd, this.zzze, i);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzgf.zza(zzis, obj, zzgd, zzg);
                            } else {
                                zzfh = zzis.zzed();
                            }
                        } else if (!zzis.zzdv()) {
                            break;
                        }
                    }
                    if (zzis.getTag() != 12) {
                        throw zzhc.zzgq();
                    } else if (zzfh != null) {
                        if (obj != null) {
                            zzgf.zza(zzfh, obj, zzgd, zzg);
                        } else {
                            zzjj.zza(zzx, i, zzfh);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object zza = zzgf.zza(zzgd, this.zzze, tag >>> 3);
                    if (zza != null) {
                        zzgf.zza(zzis, zza, zzgd, zzg);
                    } else {
                        z = zzjj.zza(zzx, zzis);
                        continue;
                    }
                } else {
                    z = zzis.zzdv();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzjj.zzg(t, zzx);
            }
        } while (z);
    }

    public final void zzh(T t) {
        this.zzzo.zzh(t);
        this.zzzp.zzh(t);
    }

    public final boolean zzu(T t) {
        return this.zzzp.zzf(t).isInitialized();
    }

    public final int zzs(T t) {
        zzjj<?, ?> zzjj = this.zzzo;
        int zzy = zzjj.zzy(zzjj.zzw(t)) + 0;
        if (this.zzzf) {
            return zzy + this.zzzp.zzf(t).zzfo();
        }
        return zzy;
    }
}
