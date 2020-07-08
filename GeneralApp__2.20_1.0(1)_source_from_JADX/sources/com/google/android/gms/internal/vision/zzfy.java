package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzfy implements zzis {
    private int tag;
    private int zzsc;
    private final zzft zzsv;
    private int zzsw = 0;

    public static zzfy zza(zzft zzft) {
        if (zzft.zzso != null) {
            return zzft.zzso;
        }
        return new zzfy(zzft);
    }

    private zzfy(zzft zzft) {
        this.zzsv = (zzft) zzgt.zza(zzft, "input");
        this.zzsv.zzso = this;
    }

    public final int zzdu() throws IOException {
        int i = this.zzsw;
        if (i != 0) {
            this.tag = i;
            this.zzsw = 0;
        } else {
            this.tag = this.zzsv.zzex();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzsc) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final boolean zzdv() throws IOException {
        if (!this.zzsv.zzdt()) {
            int i = this.tag;
            if (i != this.zzsc) {
                return this.zzsv.zzas(i);
            }
        }
        return false;
    }

    private final void zzak(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzhc.zzgr();
        }
    }

    public final double readDouble() throws IOException {
        zzak(1);
        return this.zzsv.readDouble();
    }

    public final float readFloat() throws IOException {
        zzak(5);
        return this.zzsv.readFloat();
    }

    public final long zzdw() throws IOException {
        zzak(0);
        return this.zzsv.zzdw();
    }

    public final long zzdx() throws IOException {
        zzak(0);
        return this.zzsv.zzdx();
    }

    public final int zzdy() throws IOException {
        zzak(0);
        return this.zzsv.zzdy();
    }

    public final long zzdz() throws IOException {
        zzak(1);
        return this.zzsv.zzdz();
    }

    public final int zzea() throws IOException {
        zzak(5);
        return this.zzsv.zzea();
    }

    public final boolean zzeb() throws IOException {
        zzak(0);
        return this.zzsv.zzeb();
    }

    public final String readString() throws IOException {
        zzak(2);
        return this.zzsv.readString();
    }

    public final String zzec() throws IOException {
        zzak(2);
        return this.zzsv.zzec();
    }

    public final <T> T zza(Class<T> cls, zzgd zzgd) throws IOException {
        zzak(2);
        return zzb(zzin.zzho().zzf(cls), zzgd);
    }

    public final <T> T zza(zzir<T> zzir, zzgd zzgd) throws IOException {
        zzak(2);
        return zzb(zzir, zzgd);
    }

    public final <T> T zzb(Class<T> cls, zzgd zzgd) throws IOException {
        zzak(3);
        return zzd(zzin.zzho().zzf(cls), zzgd);
    }

    public final <T> T zzc(zzir<T> zzir, zzgd zzgd) throws IOException {
        zzak(3);
        return zzd(zzir, zzgd);
    }

    private final <T> T zzb(zzir<T> zzir, zzgd zzgd) throws IOException {
        int zzee = this.zzsv.zzee();
        if (this.zzsv.zzsl < this.zzsv.zzsm) {
            int zzat = this.zzsv.zzat(zzee);
            T newInstance = zzir.newInstance();
            this.zzsv.zzsl++;
            zzir.zza(newInstance, this, zzgd);
            zzir.zzh(newInstance);
            this.zzsv.zzar(0);
            this.zzsv.zzsl--;
            this.zzsv.zzau(zzat);
            return newInstance;
        }
        throw new zzhc("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final <T> T zzd(zzir<T> zzir, zzgd zzgd) throws IOException {
        int i = this.zzsc;
        this.zzsc = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzir.newInstance();
            zzir.zza(newInstance, this, zzgd);
            zzir.zzh(newInstance);
            if (this.tag == this.zzsc) {
                return newInstance;
            }
            throw zzhc.zzgs();
        } finally {
            this.zzsc = i;
        }
    }

    public final zzfh zzed() throws IOException {
        zzak(2);
        return this.zzsv.zzed();
    }

    public final int zzee() throws IOException {
        zzak(0);
        return this.zzsv.zzee();
    }

    public final int zzef() throws IOException {
        zzak(0);
        return this.zzsv.zzef();
    }

    public final int zzeg() throws IOException {
        zzak(5);
        return this.zzsv.zzeg();
    }

    public final long zzeh() throws IOException {
        zzak(1);
        return this.zzsv.zzeh();
    }

    public final int zzei() throws IOException {
        zzak(0);
        return this.zzsv.zzei();
    }

    public final long zzej() throws IOException {
        zzak(0);
        return this.zzsv.zzej();
    }

    public final void zza(List<Double> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgb) {
            zzgb zzgb = (zzgb) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzgb.zzc(this.zzsv.readDouble());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else if (i == 2) {
                int zzee = this.zzsv.zzee();
                zzal(zzee);
                int zzez = this.zzsv.zzez() + zzee;
                do {
                    zzgb.zzc(this.zzsv.readDouble());
                } while (this.zzsv.zzez() < zzez);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Double.valueOf(this.zzsv.readDouble()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else if (i2 == 2) {
                int zzee2 = this.zzsv.zzee();
                zzal(zzee2);
                int zzez2 = this.zzsv.zzez() + zzee2;
                do {
                    list.add(Double.valueOf(this.zzsv.readDouble()));
                } while (this.zzsv.zzez() < zzez2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzb(List<Float> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgo) {
            zzgo zzgo = (zzgo) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzee = this.zzsv.zzee();
                zzam(zzee);
                int zzez = this.zzsv.zzez() + zzee;
                do {
                    zzgo.zzu(this.zzsv.readFloat());
                } while (this.zzsv.zzez() < zzez);
            } else if (i == 5) {
                do {
                    zzgo.zzu(this.zzsv.readFloat());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzee2 = this.zzsv.zzee();
                zzam(zzee2);
                int zzez2 = this.zzsv.zzez() + zzee2;
                do {
                    list.add(Float.valueOf(this.zzsv.readFloat()));
                } while (this.zzsv.zzez() < zzez2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(this.zzsv.readFloat()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzc(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzhq.zzac(this.zzsv.zzdw());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else if (i == 2) {
                int zzez = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    zzhq.zzac(this.zzsv.zzdw());
                } while (this.zzsv.zzez() < zzez);
                zzan(zzez);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzsv.zzdw()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else if (i2 == 2) {
                int zzez2 = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    list.add(Long.valueOf(this.zzsv.zzdw()));
                } while (this.zzsv.zzez() < zzez2);
                zzan(zzez2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzd(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzhq.zzac(this.zzsv.zzdx());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else if (i == 2) {
                int zzez = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    zzhq.zzac(this.zzsv.zzdx());
                } while (this.zzsv.zzez() < zzez);
                zzan(zzez);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzsv.zzdx()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else if (i2 == 2) {
                int zzez2 = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    list.add(Long.valueOf(this.zzsv.zzdx()));
                } while (this.zzsv.zzez() < zzez2);
                zzan(zzez2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zze(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgu.zzbm(this.zzsv.zzdy());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else if (i == 2) {
                int zzez = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    zzgu.zzbm(this.zzsv.zzdy());
                } while (this.zzsv.zzez() < zzez);
                zzan(zzez);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzsv.zzdy()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else if (i2 == 2) {
                int zzez2 = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    list.add(Integer.valueOf(this.zzsv.zzdy()));
                } while (this.zzsv.zzez() < zzez2);
                zzan(zzez2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzf(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzhq.zzac(this.zzsv.zzdz());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else if (i == 2) {
                int zzee = this.zzsv.zzee();
                zzal(zzee);
                int zzez = this.zzsv.zzez() + zzee;
                do {
                    zzhq.zzac(this.zzsv.zzdz());
                } while (this.zzsv.zzez() < zzez);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zzsv.zzdz()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else if (i2 == 2) {
                int zzee2 = this.zzsv.zzee();
                zzal(zzee2);
                int zzez2 = this.zzsv.zzez() + zzee2;
                do {
                    list.add(Long.valueOf(this.zzsv.zzdz()));
                } while (this.zzsv.zzez() < zzez2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzg(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzee = this.zzsv.zzee();
                zzam(zzee);
                int zzez = this.zzsv.zzez() + zzee;
                do {
                    zzgu.zzbm(this.zzsv.zzea());
                } while (this.zzsv.zzez() < zzez);
            } else if (i == 5) {
                do {
                    zzgu.zzbm(this.zzsv.zzea());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzee2 = this.zzsv.zzee();
                zzam(zzee2);
                int zzez2 = this.zzsv.zzez() + zzee2;
                do {
                    list.add(Integer.valueOf(this.zzsv.zzea()));
                } while (this.zzsv.zzez() < zzez2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzsv.zzea()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzh(List<Boolean> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzff) {
            zzff zzff = (zzff) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzff.addBoolean(this.zzsv.zzeb());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else if (i == 2) {
                int zzez = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    zzff.addBoolean(this.zzsv.zzeb());
                } while (this.zzsv.zzez() < zzez);
                zzan(zzez);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzsv.zzeb()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else if (i2 == 2) {
                int zzez2 = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    list.add(Boolean.valueOf(this.zzsv.zzeb()));
                } while (this.zzsv.zzez() < zzez2);
                zzan(zzez2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    public final void zzi(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int zzex;
        int zzex2;
        if ((this.tag & 7) != 2) {
            throw zzhc.zzgr();
        } else if (!(list instanceof zzhj) || z) {
            do {
                list.add(z ? zzec() : readString());
                if (!this.zzsv.zzdt()) {
                    zzex = this.zzsv.zzex();
                } else {
                    return;
                }
            } while (zzex == this.tag);
            this.zzsw = zzex;
        } else {
            zzhj zzhj = (zzhj) list;
            do {
                zzhj.zzc(zzed());
                if (!this.zzsv.zzdt()) {
                    zzex2 = this.zzsv.zzex();
                } else {
                    return;
                }
            } while (zzex2 == this.tag);
            this.zzsw = zzex2;
        }
    }

    public final <T> void zza(List<T> list, zzir<T> zzir, zzgd zzgd) throws IOException {
        int zzex;
        int i = this.tag;
        if ((i & 7) == 2) {
            do {
                list.add(zzb(zzir, zzgd));
                if (!this.zzsv.zzdt() && this.zzsw == 0) {
                    zzex = this.zzsv.zzex();
                } else {
                    return;
                }
            } while (zzex == i);
            this.zzsw = zzex;
            return;
        }
        throw zzhc.zzgr();
    }

    public final <T> void zzb(List<T> list, zzir<T> zzir, zzgd zzgd) throws IOException {
        int zzex;
        int i = this.tag;
        if ((i & 7) == 3) {
            do {
                list.add(zzd(zzir, zzgd));
                if (!this.zzsv.zzdt() && this.zzsw == 0) {
                    zzex = this.zzsv.zzex();
                } else {
                    return;
                }
            } while (zzex == i);
            this.zzsw = zzex;
            return;
        }
        throw zzhc.zzgr();
    }

    public final void zzj(List<zzfh> list) throws IOException {
        int zzex;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzed());
                if (!this.zzsv.zzdt()) {
                    zzex = this.zzsv.zzex();
                } else {
                    return;
                }
            } while (zzex == this.tag);
            this.zzsw = zzex;
            return;
        }
        throw zzhc.zzgr();
    }

    public final void zzk(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgu.zzbm(this.zzsv.zzee());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else if (i == 2) {
                int zzez = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    zzgu.zzbm(this.zzsv.zzee());
                } while (this.zzsv.zzez() < zzez);
                zzan(zzez);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzsv.zzee()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else if (i2 == 2) {
                int zzez2 = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    list.add(Integer.valueOf(this.zzsv.zzee()));
                } while (this.zzsv.zzez() < zzez2);
                zzan(zzez2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzl(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgu.zzbm(this.zzsv.zzef());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else if (i == 2) {
                int zzez = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    zzgu.zzbm(this.zzsv.zzef());
                } while (this.zzsv.zzez() < zzez);
                zzan(zzez);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzsv.zzef()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else if (i2 == 2) {
                int zzez2 = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    list.add(Integer.valueOf(this.zzsv.zzef()));
                } while (this.zzsv.zzez() < zzez2);
                zzan(zzez2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzm(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzee = this.zzsv.zzee();
                zzam(zzee);
                int zzez = this.zzsv.zzez() + zzee;
                do {
                    zzgu.zzbm(this.zzsv.zzeg());
                } while (this.zzsv.zzez() < zzez);
            } else if (i == 5) {
                do {
                    zzgu.zzbm(this.zzsv.zzeg());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzee2 = this.zzsv.zzee();
                zzam(zzee2);
                int zzez2 = this.zzsv.zzez() + zzee2;
                do {
                    list.add(Integer.valueOf(this.zzsv.zzeg()));
                } while (this.zzsv.zzez() < zzez2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzsv.zzeg()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzn(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzhq.zzac(this.zzsv.zzeh());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else if (i == 2) {
                int zzee = this.zzsv.zzee();
                zzal(zzee);
                int zzez = this.zzsv.zzez() + zzee;
                do {
                    zzhq.zzac(this.zzsv.zzeh());
                } while (this.zzsv.zzez() < zzez);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zzsv.zzeh()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else if (i2 == 2) {
                int zzee2 = this.zzsv.zzee();
                zzal(zzee2);
                int zzez2 = this.zzsv.zzez() + zzee2;
                do {
                    list.add(Long.valueOf(this.zzsv.zzeh()));
                } while (this.zzsv.zzez() < zzez2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzo(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgu.zzbm(this.zzsv.zzei());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else if (i == 2) {
                int zzez = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    zzgu.zzbm(this.zzsv.zzei());
                } while (this.zzsv.zzez() < zzez);
                zzan(zzez);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzsv.zzei()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else if (i2 == 2) {
                int zzez2 = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    list.add(Integer.valueOf(this.zzsv.zzei()));
                } while (this.zzsv.zzez() < zzez2);
                zzan(zzez2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzp(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzhq.zzac(this.zzsv.zzej());
                    if (!this.zzsv.zzdt()) {
                        zzex2 = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex2 == this.tag);
                this.zzsw = zzex2;
            } else if (i == 2) {
                int zzez = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    zzhq.zzac(this.zzsv.zzej());
                } while (this.zzsv.zzez() < zzez);
                zzan(zzez);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzsv.zzej()));
                    if (!this.zzsv.zzdt()) {
                        zzex = this.zzsv.zzex();
                    } else {
                        return;
                    }
                } while (zzex == this.tag);
                this.zzsw = zzex;
            } else if (i2 == 2) {
                int zzez2 = this.zzsv.zzez() + this.zzsv.zzee();
                do {
                    list.add(Long.valueOf(this.zzsv.zzej()));
                } while (this.zzsv.zzez() < zzez2);
                zzan(zzez2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    private static void zzal(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzhc.zzgs();
        }
    }

    public final <K, V> void zza(Map<K, V> map, zzht<K, V> zzht, zzgd zzgd) throws IOException {
        String str;
        zzak(2);
        int zzat = this.zzsv.zzat(this.zzsv.zzee());
        K k = zzht.zzyt;
        V v = zzht.zzgd;
        while (true) {
            try {
                int zzdu = zzdu();
                if (zzdu == Integer.MAX_VALUE || this.zzsv.zzdt()) {
                    map.put(k, v);
                } else {
                    str = "Unable to parse map entry.";
                    if (zzdu == 1) {
                        k = zza(zzht.zzys, null, (zzgd) null);
                    } else if (zzdu == 2) {
                        v = zza(zzht.zzyu, zzht.zzgd.getClass(), zzgd);
                    } else if (!zzdv()) {
                        throw new zzhc(str);
                    }
                }
            } catch (zzhb e) {
                if (!zzdv()) {
                    throw new zzhc(str);
                }
            } catch (Throwable th) {
                this.zzsv.zzau(zzat);
                throw th;
            }
        }
        map.put(k, v);
        this.zzsv.zzau(zzat);
    }

    private final Object zza(zzka zzka, Class<?> cls, zzgd zzgd) throws IOException {
        switch (zzka) {
            case BOOL:
                return Boolean.valueOf(zzeb());
            case BYTES:
                return zzed();
            case DOUBLE:
                return Double.valueOf(readDouble());
            case ENUM:
                return Integer.valueOf(zzef());
            case FIXED32:
                return Integer.valueOf(zzea());
            case FIXED64:
                return Long.valueOf(zzdz());
            case FLOAT:
                return Float.valueOf(readFloat());
            case INT32:
                return Integer.valueOf(zzdy());
            case INT64:
                return Long.valueOf(zzdx());
            case MESSAGE:
                return zza(cls, zzgd);
            case SFIXED32:
                return Integer.valueOf(zzeg());
            case SFIXED64:
                return Long.valueOf(zzeh());
            case SINT32:
                return Integer.valueOf(zzei());
            case SINT64:
                return Long.valueOf(zzej());
            case STRING:
                return zzec();
            case UINT32:
                return Integer.valueOf(zzee());
            case UINT64:
                return Long.valueOf(zzdw());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzam(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzhc.zzgs();
        }
    }

    private final void zzan(int i) throws IOException {
        if (this.zzsv.zzez() != i) {
            throw zzhc.zzgm();
        }
    }
}
