package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzfg extends zzfe {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private int tag;
    private final boolean zzsa = true;
    private final int zzsb;
    private int zzsc;

    public zzfg(ByteBuffer byteBuffer, boolean z) {
        super(null);
        this.buffer = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        this.pos = arrayOffset;
        this.zzsb = arrayOffset;
        this.limit = byteBuffer.arrayOffset() + byteBuffer.limit();
    }

    private final boolean zzdt() {
        return this.pos == this.limit;
    }

    public final int zzdu() throws IOException {
        if (zzdt()) {
            return Integer.MAX_VALUE;
        }
        this.tag = zzek();
        int i = this.tag;
        if (i == this.zzsc) {
            return Integer.MAX_VALUE;
        }
        return i >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final boolean zzdv() throws IOException {
        if (!zzdt()) {
            int i = this.tag;
            int i2 = this.zzsc;
            if (i != i2) {
                int i3 = i & 7;
                if (i3 == 0) {
                    int i4 = this.limit;
                    int i5 = this.pos;
                    if (i4 - i5 >= 10) {
                        byte[] bArr = this.buffer;
                        int i6 = i5;
                        int i7 = 0;
                        while (true) {
                            if (i7 >= 10) {
                                break;
                            }
                            int i8 = i6 + 1;
                            if (bArr[i6] >= 0) {
                                this.pos = i8;
                                break;
                            }
                            i7++;
                            i6 = i8;
                        }
                    }
                    for (int i9 = 0; i9 < 10; i9++) {
                        if (readByte() >= 0) {
                            return true;
                        }
                    }
                    throw zzhc.zzgo();
                } else if (i3 == 1) {
                    zzai(8);
                    return true;
                } else if (i3 == 2) {
                    zzai(zzek());
                    return true;
                } else if (i3 == 3) {
                    this.zzsc = ((i >>> 3) << 3) | 4;
                    while (zzdu() != Integer.MAX_VALUE) {
                        if (!zzdv()) {
                            break;
                        }
                    }
                    if (this.tag == this.zzsc) {
                        this.zzsc = i2;
                        return true;
                    }
                    throw zzhc.zzgs();
                } else if (i3 == 5) {
                    zzai(4);
                    return true;
                } else {
                    throw zzhc.zzgr();
                }
            }
        }
        return false;
    }

    public final double readDouble() throws IOException {
        zzak(1);
        return Double.longBitsToDouble(zzeo());
    }

    public final float readFloat() throws IOException {
        zzak(5);
        return Float.intBitsToFloat(zzen());
    }

    public final long zzdw() throws IOException {
        zzak(0);
        return zzel();
    }

    public final long zzdx() throws IOException {
        zzak(0);
        return zzel();
    }

    public final int zzdy() throws IOException {
        zzak(0);
        return zzek();
    }

    public final long zzdz() throws IOException {
        zzak(1);
        return zzeo();
    }

    public final int zzea() throws IOException {
        zzak(5);
        return zzen();
    }

    public final boolean zzeb() throws IOException {
        zzak(0);
        if (zzek() != 0) {
            return true;
        }
        return false;
    }

    public final String readString() throws IOException {
        return zzj(false);
    }

    public final String zzec() throws IOException {
        return zzj(true);
    }

    private final String zzj(boolean z) throws IOException {
        zzak(2);
        int zzek = zzek();
        if (zzek == 0) {
            return "";
        }
        zzaj(zzek);
        if (z) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            if (!zzjs.zzf(bArr, i, i + zzek)) {
                throw zzhc.zzgt();
            }
        }
        String str = new String(this.buffer, this.pos, zzek, zzgt.UTF_8);
        this.pos += zzek;
        return str;
    }

    public final <T> T zza(Class<T> cls, zzgd zzgd) throws IOException {
        zzak(2);
        return zzb(zzin.zzho().zzf(cls), zzgd);
    }

    public final <T> T zza(zzir<T> zzir, zzgd zzgd) throws IOException {
        zzak(2);
        return zzb(zzir, zzgd);
    }

    private final <T> T zzb(zzir<T> zzir, zzgd zzgd) throws IOException {
        int zzek = zzek();
        zzaj(zzek);
        int i = this.limit;
        int i2 = this.pos + zzek;
        this.limit = i2;
        try {
            T newInstance = zzir.newInstance();
            zzir.zza(newInstance, this, zzgd);
            zzir.zzh(newInstance);
            if (this.pos == i2) {
                return newInstance;
            }
            throw zzhc.zzgs();
        } finally {
            this.limit = i;
        }
    }

    public final <T> T zzb(Class<T> cls, zzgd zzgd) throws IOException {
        zzak(3);
        return zzd(zzin.zzho().zzf(cls), zzgd);
    }

    public final <T> T zzc(zzir<T> zzir, zzgd zzgd) throws IOException {
        zzak(3);
        return zzd(zzir, zzgd);
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
        zzfh zzfh;
        zzak(2);
        int zzek = zzek();
        if (zzek == 0) {
            return zzfh.zzsd;
        }
        zzaj(zzek);
        if (this.zzsa) {
            zzfh = zzfh.zzb(this.buffer, this.pos, zzek);
        } else {
            zzfh = zzfh.zza(this.buffer, this.pos, zzek);
        }
        this.pos += zzek;
        return zzfh;
    }

    public final int zzee() throws IOException {
        zzak(0);
        return zzek();
    }

    public final int zzef() throws IOException {
        zzak(0);
        return zzek();
    }

    public final int zzeg() throws IOException {
        zzak(5);
        return zzen();
    }

    public final long zzeh() throws IOException {
        zzak(1);
        return zzeo();
    }

    public final int zzei() throws IOException {
        zzak(0);
        return zzft.zzav(zzek());
    }

    public final long zzej() throws IOException {
        zzak(0);
        return zzft.zzr(zzel());
    }

    public final void zza(List<Double> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgb) {
            zzgb zzgb = (zzgb) list;
            int i3 = this.tag & 7;
            if (i3 == 1) {
                do {
                    zzgb.zzc(readDouble());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzek = zzek();
                zzal(zzek);
                int i4 = this.pos + zzek;
                while (this.pos < i4) {
                    zzgb.zzc(Double.longBitsToDouble(zzeq()));
                }
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 1) {
                do {
                    list.add(Double.valueOf(readDouble()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else if (i5 == 2) {
                int zzek2 = zzek();
                zzal(zzek2);
                int i6 = this.pos + zzek2;
                while (this.pos < i6) {
                    list.add(Double.valueOf(Double.longBitsToDouble(zzeq())));
                }
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzb(List<Float> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgo) {
            zzgo zzgo = (zzgo) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzek = zzek();
                zzam(zzek);
                int i4 = this.pos + zzek;
                while (this.pos < i4) {
                    zzgo.zzu(Float.intBitsToFloat(zzep()));
                }
            } else if (i3 == 5) {
                do {
                    zzgo.zzu(readFloat());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzek2 = zzek();
                zzam(zzek2);
                int i6 = this.pos + zzek2;
                while (this.pos < i6) {
                    list.add(Float.valueOf(Float.intBitsToFloat(zzep())));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Float.valueOf(readFloat()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzc(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzhq.zzac(zzdw());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzek = this.pos + zzek();
                while (this.pos < zzek) {
                    zzhq.zzac(zzel());
                }
                zzan(zzek);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzdw()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Long.valueOf(zzel()));
                }
                zzan(zzek2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzd(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzhq.zzac(zzdx());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzek = this.pos + zzek();
                while (this.pos < zzek) {
                    zzhq.zzac(zzel());
                }
                zzan(zzek);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzdx()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Long.valueOf(zzel()));
                }
                zzan(zzek2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zze(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgu.zzbm(zzdy());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzek = this.pos + zzek();
                while (this.pos < zzek) {
                    zzgu.zzbm(zzek());
                }
                zzan(zzek);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzdy()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Integer.valueOf(zzek()));
                }
                zzan(zzek2);
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzf(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            int i3 = this.tag & 7;
            if (i3 == 1) {
                do {
                    zzhq.zzac(zzdz());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzek = zzek();
                zzal(zzek);
                int i4 = this.pos + zzek;
                while (this.pos < i4) {
                    zzhq.zzac(zzeq());
                }
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 1) {
                do {
                    list.add(Long.valueOf(zzdz()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else if (i5 == 2) {
                int zzek2 = zzek();
                zzal(zzek2);
                int i6 = this.pos + zzek2;
                while (this.pos < i6) {
                    list.add(Long.valueOf(zzeq()));
                }
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzg(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzek = zzek();
                zzam(zzek);
                int i4 = this.pos + zzek;
                while (this.pos < i4) {
                    zzgu.zzbm(zzep());
                }
            } else if (i3 == 5) {
                do {
                    zzgu.zzbm(zzea());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzek2 = zzek();
                zzam(zzek2);
                int i6 = this.pos + zzek2;
                while (this.pos < i6) {
                    list.add(Integer.valueOf(zzep()));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Integer.valueOf(zzea()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzh(List<Boolean> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzff) {
            zzff zzff = (zzff) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzff.addBoolean(zzeb());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzek = this.pos + zzek();
                while (this.pos < zzek) {
                    zzff.addBoolean(zzek() != 0);
                }
                zzan(zzek);
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Boolean.valueOf(zzeb()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Boolean.valueOf(zzek() != 0));
                }
                zzan(zzek2);
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
        int i;
        int i2;
        if ((this.tag & 7) != 2) {
            throw zzhc.zzgr();
        } else if (!(list instanceof zzhj) || z) {
            do {
                list.add(zzj(z));
                if (!zzdt()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzek() == this.tag);
            this.pos = i;
        } else {
            zzhj zzhj = (zzhj) list;
            do {
                zzhj.zzc(zzed());
                if (!zzdt()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzek() == this.tag);
            this.pos = i2;
        }
    }

    public final <T> void zza(List<T> list, zzir<T> zzir, zzgd zzgd) throws IOException {
        int i;
        int i2 = this.tag;
        if ((i2 & 7) == 2) {
            do {
                list.add(zzb(zzir, zzgd));
                if (!zzdt()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzek() == i2);
            this.pos = i;
            return;
        }
        throw zzhc.zzgr();
    }

    public final <T> void zzb(List<T> list, zzir<T> zzir, zzgd zzgd) throws IOException {
        int i;
        int i2 = this.tag;
        if ((i2 & 7) == 3) {
            do {
                list.add(zzd(zzir, zzgd));
                if (!zzdt()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzek() == i2);
            this.pos = i;
            return;
        }
        throw zzhc.zzgr();
    }

    public final void zzj(List<zzfh> list) throws IOException {
        int i;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzed());
                if (!zzdt()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzek() == this.tag);
            this.pos = i;
            return;
        }
        throw zzhc.zzgr();
    }

    public final void zzk(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgu.zzbm(zzee());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzek = this.pos + zzek();
                while (this.pos < zzek) {
                    zzgu.zzbm(zzek());
                }
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzee()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Integer.valueOf(zzek()));
                }
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzl(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgu.zzbm(zzef());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzek = this.pos + zzek();
                while (this.pos < zzek) {
                    zzgu.zzbm(zzek());
                }
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzef()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Integer.valueOf(zzek()));
                }
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzm(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzek = zzek();
                zzam(zzek);
                int i4 = this.pos + zzek;
                while (this.pos < i4) {
                    zzgu.zzbm(zzep());
                }
            } else if (i3 == 5) {
                do {
                    zzgu.zzbm(zzeg());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzek2 = zzek();
                zzam(zzek2);
                int i6 = this.pos + zzek2;
                while (this.pos < i6) {
                    list.add(Integer.valueOf(zzep()));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Integer.valueOf(zzeg()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzn(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            int i3 = this.tag & 7;
            if (i3 == 1) {
                do {
                    zzhq.zzac(zzeh());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzek = zzek();
                zzal(zzek);
                int i4 = this.pos + zzek;
                while (this.pos < i4) {
                    zzhq.zzac(zzeq());
                }
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 1) {
                do {
                    list.add(Long.valueOf(zzeh()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else if (i5 == 2) {
                int zzek2 = zzek();
                zzal(zzek2);
                int i6 = this.pos + zzek2;
                while (this.pos < i6) {
                    list.add(Long.valueOf(zzeq()));
                }
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzo(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgu.zzbm(zzei());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzek = this.pos + zzek();
                while (this.pos < zzek) {
                    zzgu.zzbm(zzft.zzav(zzek()));
                }
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzei()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Integer.valueOf(zzft.zzav(zzek())));
                }
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final void zzp(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzhq.zzac(zzej());
                    if (!zzdt()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzek = this.pos + zzek();
                while (this.pos < zzek) {
                    zzhq.zzac(zzft.zzr(zzel()));
                }
            } else {
                throw zzhc.zzgr();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzej()));
                    if (!zzdt()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzek() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Long.valueOf(zzft.zzr(zzel())));
                }
            } else {
                throw zzhc.zzgr();
            }
        }
    }

    public final <K, V> void zza(Map<K, V> map, zzht<K, V> zzht, zzgd zzgd) throws IOException {
        String str;
        zzak(2);
        int zzek = zzek();
        zzaj(zzek);
        int i = this.limit;
        this.limit = this.pos + zzek;
        try {
            K k = zzht.zzyt;
            V v = zzht.zzgd;
            while (true) {
                int zzdu = zzdu();
                if (zzdu != Integer.MAX_VALUE) {
                    str = "Unable to parse map entry.";
                    if (zzdu == 1) {
                        k = zza(zzht.zzys, null, (zzgd) null);
                    } else if (zzdu == 2) {
                        v = zza(zzht.zzyu, zzht.zzgd.getClass(), zzgd);
                    } else if (!zzdv()) {
                        throw new zzhc(str);
                    }
                } else {
                    map.put(k, v);
                    this.limit = i;
                    return;
                }
            }
        } catch (zzhb e) {
            if (!zzdv()) {
                throw new zzhc(str);
            }
        } catch (Throwable th) {
            this.limit = i;
            throw th;
        }
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
                return zzj(true);
            case UINT32:
                return Integer.valueOf(zzee());
            case UINT64:
                return Long.valueOf(zzdw());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zzek() throws IOException {
        byte b;
        int i = this.pos;
        int i2 = this.limit;
        if (i2 != i) {
            byte[] bArr = this.buffer;
            int i3 = i + 1;
            byte b2 = bArr[i];
            if (b2 >= 0) {
                this.pos = i3;
                return b2;
            } else if (i2 - i3 < 9) {
                return (int) zzem();
            } else {
                int i4 = i3 + 1;
                byte b3 = b2 ^ (bArr[i3] << 7);
                if (b3 < 0) {
                    b = b3 ^ ByteCompanionObject.MIN_VALUE;
                } else {
                    int i5 = i4 + 1;
                    byte b4 = b3 ^ (bArr[i4] << 14);
                    if (b4 >= 0) {
                        b = b4 ^ ByteCompanionObject.MIN_VALUE;
                        i4 = i5;
                    } else {
                        i4 = i5 + 1;
                        byte b5 = b4 ^ (bArr[i5] << 21);
                        if (b5 < 0) {
                            b = b5 ^ ByteCompanionObject.MIN_VALUE;
                        } else {
                            int i6 = i4 + 1;
                            byte b6 = bArr[i4];
                            b = (b5 ^ (b6 << 28)) ^ ByteCompanionObject.MIN_VALUE;
                            if (b6 < 0) {
                                i4 = i6 + 1;
                                if (bArr[i6] < 0) {
                                    i6 = i4 + 1;
                                    if (bArr[i4] < 0) {
                                        i4 = i6 + 1;
                                        if (bArr[i6] < 0) {
                                            i6 = i4 + 1;
                                            if (bArr[i4] < 0) {
                                                i4 = i6 + 1;
                                                if (bArr[i6] < 0) {
                                                    throw zzhc.zzgo();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            i4 = i6;
                        }
                    }
                }
                this.pos = i4;
                return b;
            }
        } else {
            throw zzhc.zzgm();
        }
    }

    private final long zzel() throws IOException {
        long j;
        int i = this.pos;
        int i2 = this.limit;
        if (i2 != i) {
            byte[] bArr = this.buffer;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.pos = i3;
                return (long) b;
            } else if (i2 - i3 < 9) {
                return zzem();
            } else {
                int i4 = i3 + 1;
                byte b2 = b ^ (bArr[i3] << 7);
                if (b2 < 0) {
                    j = (long) (b2 ^ ByteCompanionObject.MIN_VALUE);
                } else {
                    int i5 = i4 + 1;
                    byte b3 = b2 ^ (bArr[i4] << 14);
                    if (b3 >= 0) {
                        i4 = i5;
                        j = (long) (b3 ^ ByteCompanionObject.MIN_VALUE);
                    } else {
                        i4 = i5 + 1;
                        byte b4 = b3 ^ (bArr[i5] << 21);
                        if (b4 < 0) {
                            j = (long) (b4 ^ ByteCompanionObject.MIN_VALUE);
                        } else {
                            long j2 = (long) b4;
                            int i6 = i4 + 1;
                            long j3 = j2 ^ (((long) bArr[i4]) << 28);
                            if (j3 >= 0) {
                                j = j3 ^ 266354560;
                                i4 = i6;
                            } else {
                                i4 = i6 + 1;
                                long j4 = j3 ^ (((long) bArr[i6]) << 35);
                                if (j4 < 0) {
                                    j = j4 ^ -34093383808L;
                                } else {
                                    int i7 = i4 + 1;
                                    long j5 = j4 ^ (((long) bArr[i4]) << 42);
                                    if (j5 >= 0) {
                                        j = j5 ^ 4363953127296L;
                                        i4 = i7;
                                    } else {
                                        i4 = i7 + 1;
                                        long j6 = j5 ^ (((long) bArr[i7]) << 49);
                                        if (j6 < 0) {
                                            j = j6 ^ -558586000294016L;
                                        } else {
                                            int i8 = i4 + 1;
                                            long j7 = (j6 ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                            if (j7 < 0) {
                                                i4 = i8 + 1;
                                                if (((long) bArr[i8]) >= 0) {
                                                    j = j7;
                                                } else {
                                                    throw zzhc.zzgo();
                                                }
                                            } else {
                                                i4 = i8;
                                                j = j7;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.pos = i4;
                return j;
            }
        } else {
            throw zzhc.zzgm();
        }
    }

    private final long zzem() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte readByte = readByte();
            j |= ((long) (readByte & ByteCompanionObject.MAX_VALUE)) << i;
            if ((readByte & ByteCompanionObject.MIN_VALUE) == 0) {
                return j;
            }
        }
        throw zzhc.zzgo();
    }

    private final byte readByte() throws IOException {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzhc.zzgm();
    }

    private final int zzen() throws IOException {
        zzaj(4);
        return zzep();
    }

    private final long zzeo() throws IOException {
        zzaj(8);
        return zzeq();
    }

    private final int zzep() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
    }

    private final long zzeq() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    private final void zzai(int i) throws IOException {
        zzaj(i);
        this.pos += i;
    }

    private final void zzaj(int i) throws IOException {
        if (i < 0 || i > this.limit - this.pos) {
            throw zzhc.zzgm();
        }
    }

    private final void zzak(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzhc.zzgr();
        }
    }

    private final void zzal(int i) throws IOException {
        zzaj(i);
        if ((i & 7) != 0) {
            throw zzhc.zzgs();
        }
    }

    private final void zzam(int i) throws IOException {
        zzaj(i);
        if ((i & 3) != 0) {
            throw zzhc.zzgs();
        }
    }

    private final void zzan(int i) throws IOException {
        if (this.pos != i) {
            throw zzhc.zzgm();
        }
    }
}
