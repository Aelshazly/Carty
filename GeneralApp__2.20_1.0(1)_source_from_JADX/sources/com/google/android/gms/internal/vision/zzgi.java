package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzgi<T extends zzgk<T>> {
    private static final zzgi zztk = new zzgi(true);
    final zziw<T, Object> zzth;
    private boolean zzti;
    private boolean zztj;

    private zzgi() {
        this.zzth = zziw.zzbu(16);
    }

    private zzgi(boolean z) {
        this(zziw.zzbu(0));
        zzdp();
    }

    private zzgi(zziw<T, Object> zziw) {
        this.zzth = zziw;
        zzdp();
    }

    public static <T extends zzgk<T>> zzgi<T> zzfn() {
        return zztk;
    }

    public final void zzdp() {
        if (!this.zzti) {
            this.zzth.zzdp();
            this.zzti = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzti;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgi)) {
            return false;
        }
        return this.zzth.equals(((zzgi) obj).zzth);
    }

    public final int hashCode() {
        return this.zzth.hashCode();
    }

    public final Iterator<Entry<T, Object>> iterator() {
        if (this.zztj) {
            return new zzhi(this.zzth.entrySet().iterator());
        }
        return this.zzth.entrySet().iterator();
    }

    /* access modifiers changed from: 0000 */
    public final Iterator<Entry<T, Object>> descendingIterator() {
        if (this.zztj) {
            return new zzhi(this.zzth.zzhz().iterator());
        }
        return this.zzth.zzhz().iterator();
    }

    public final Object zza(T t) {
        Object obj = this.zzth.get(t);
        if (!(obj instanceof zzhd)) {
            return obj;
        }
        zzhd zzhd = (zzhd) obj;
        return zzhd.zzgu();
    }

    public final void zza(T t, Object obj) {
        if (!t.zzfu()) {
            zza(t.zzfs(), obj);
            r7 = obj;
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(t.zzfs(), obj2);
            }
            r7 = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (r7 instanceof zzhd) {
            this.zztj = true;
        }
        this.zzth.put(t, r7);
    }

    public final void zzb(T t, Object obj) {
        List list;
        if (t.zzfu()) {
            zza(t.zzfs(), obj);
            Object zza = zza(t);
            if (zza == null) {
                list = new ArrayList();
                this.zzth.put(t, list);
            } else {
                list = (List) zza;
            }
            list.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    private static void zza(zzka zzka, Object obj) {
        zzgt.checkNotNull(obj);
        boolean z = true;
        switch (zzgl.zztn[zzka.zzip().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if (!(obj instanceof zzfh) && !(obj instanceof byte[])) {
                    z = false;
                    break;
                }
            case 8:
                if (!(obj instanceof Integer) && !(obj instanceof zzgw)) {
                    z = false;
                    break;
                }
            case 9:
                if (!(obj instanceof zzic) && !(obj instanceof zzhd)) {
                    z = false;
                    break;
                }
            default:
                z = false;
                break;
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzth.zzhx(); i++) {
            if (!zzb(this.zzth.zzbv(i))) {
                return false;
            }
        }
        for (Entry zzb : this.zzth.zzhy()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzgk<T>> boolean zzb(Entry<T, Object> entry) {
        zzgk zzgk = (zzgk) entry.getKey();
        if (zzgk.zzft() == zzkd.MESSAGE) {
            if (zzgk.zzfu()) {
                for (zzic isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzic) {
                    if (!((zzic) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzhd) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzgi<T> zzgi) {
        for (int i = 0; i < zzgi.zzth.zzhx(); i++) {
            zzc(zzgi.zzth.zzbv(i));
        }
        for (Entry zzc : zzgi.zzth.zzhy()) {
            zzc(zzc);
        }
    }

    private static Object zzi(Object obj) {
        if (obj instanceof zzih) {
            return ((zzih) obj).zzdm();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzc(Entry<T, Object> entry) {
        Object obj;
        zzgk zzgk = (zzgk) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzhd) {
            zzhd zzhd = (zzhd) value;
            value = zzhd.zzgu();
        }
        if (zzgk.zzfu()) {
            Object zza = zza((T) zzgk);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzi : (List) value) {
                ((List) zza).add(zzi(zzi));
            }
            this.zzth.put(zzgk, zza);
        } else if (zzgk.zzft() == zzkd.MESSAGE) {
            Object zza2 = zza((T) zzgk);
            if (zza2 == null) {
                this.zzth.put(zzgk, zzi(value));
                return;
            }
            if (zza2 instanceof zzih) {
                obj = zzgk.zza((zzih) zza2, (zzih) value);
            } else {
                obj = zzgk.zza(((zzic) zza2).zzgi(), (zzic) value).zzgc();
            }
            this.zzth.put(zzgk, obj);
        } else {
            this.zzth.put(zzgk, zzi(value));
        }
    }

    static void zza(zzga zzga, zzka zzka, int i, Object obj) throws IOException {
        if (zzka == zzka.GROUP) {
            zzic zzic = (zzic) obj;
            zzgt.zzf(zzic);
            zzga.writeTag(i, 3);
            zzic.zzb(zzga);
            zzga.writeTag(i, 4);
            return;
        }
        zzga.writeTag(i, zzka.zziq());
        switch (zzgl.zzrx[zzka.ordinal()]) {
            case 1:
                zzga.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzga.zzs(((Float) obj).floatValue());
                return;
            case 3:
                zzga.zzs(((Long) obj).longValue());
                return;
            case 4:
                zzga.zzs(((Long) obj).longValue());
                return;
            case 5:
                zzga.zzax(((Integer) obj).intValue());
                return;
            case 6:
                zzga.zzu(((Long) obj).longValue());
                return;
            case 7:
                zzga.zzba(((Integer) obj).intValue());
                return;
            case 8:
                zzga.zzk(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzic) obj).zzb(zzga);
                return;
            case 10:
                zzga.zzb((zzic) obj);
                return;
            case 11:
                if (obj instanceof zzfh) {
                    zzga.zza((zzfh) obj);
                    return;
                } else {
                    zzga.zzx((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzfh) {
                    zzga.zza((zzfh) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzga.zze(bArr, 0, bArr.length);
                return;
            case 13:
                zzga.zzay(((Integer) obj).intValue());
                return;
            case 14:
                zzga.zzba(((Integer) obj).intValue());
                return;
            case 15:
                zzga.zzu(((Long) obj).longValue());
                return;
            case 16:
                zzga.zzaz(((Integer) obj).intValue());
                return;
            case 17:
                zzga.zzt(((Long) obj).longValue());
                return;
            case 18:
                if (!(obj instanceof zzgw)) {
                    zzga.zzax(((Integer) obj).intValue());
                    break;
                } else {
                    zzga.zzax(((zzgw) obj).zzag());
                    return;
                }
        }
    }

    public final int zzfo() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzth.zzhx(); i2++) {
            i += zzd(this.zzth.zzbv(i2));
        }
        for (Entry zzd : this.zzth.zzhy()) {
            i += zzd(zzd);
        }
        return i;
    }

    private static int zzd(Entry<T, Object> entry) {
        zzgk zzgk = (zzgk) entry.getKey();
        Object value = entry.getValue();
        if (zzgk.zzft() != zzkd.MESSAGE || zzgk.zzfu() || zzgk.zzfv()) {
            return zzc(zzgk, value);
        }
        if (value instanceof zzhd) {
            return zzga.zzb(((zzgk) entry.getKey()).zzag(), (zzhh) (zzhd) value);
        }
        return zzga.zzb(((zzgk) entry.getKey()).zzag(), (zzic) value);
    }

    static int zza(zzka zzka, int i, Object obj) {
        int zzbb = zzga.zzbb(i);
        if (zzka == zzka.GROUP) {
            zzgt.zzf((zzic) obj);
            zzbb <<= 1;
        }
        return zzbb + zzb(zzka, obj);
    }

    private static int zzb(zzka zzka, Object obj) {
        switch (zzgl.zzrx[zzka.ordinal()]) {
            case 1:
                return zzga.zzb(((Double) obj).doubleValue());
            case 2:
                return zzga.zzt(((Float) obj).floatValue());
            case 3:
                return zzga.zzv(((Long) obj).longValue());
            case 4:
                return zzga.zzw(((Long) obj).longValue());
            case 5:
                return zzga.zzbc(((Integer) obj).intValue());
            case 6:
                return zzga.zzy(((Long) obj).longValue());
            case 7:
                return zzga.zzbf(((Integer) obj).intValue());
            case 8:
                return zzga.zzl(((Boolean) obj).booleanValue());
            case 9:
                return zzga.zzd((zzic) obj);
            case 10:
                if (obj instanceof zzhd) {
                    return zzga.zza((zzhh) (zzhd) obj);
                }
                return zzga.zzc((zzic) obj);
            case 11:
                if (obj instanceof zzfh) {
                    return zzga.zzb((zzfh) obj);
                }
                return zzga.zzy((String) obj);
            case 12:
                if (obj instanceof zzfh) {
                    return zzga.zzb((zzfh) obj);
                }
                return zzga.zzf((byte[]) obj);
            case 13:
                return zzga.zzbd(((Integer) obj).intValue());
            case 14:
                return zzga.zzbg(((Integer) obj).intValue());
            case 15:
                return zzga.zzz(((Long) obj).longValue());
            case 16:
                return zzga.zzbe(((Integer) obj).intValue());
            case 17:
                return zzga.zzx(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzgw) {
                    return zzga.zzbh(((zzgw) obj).zzag());
                }
                return zzga.zzbh(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zzc(zzgk<?> zzgk, Object obj) {
        zzka zzfs = zzgk.zzfs();
        int zzag = zzgk.zzag();
        if (!zzgk.zzfu()) {
            return zza(zzfs, zzag, obj);
        }
        int i = 0;
        if (zzgk.zzfv()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzfs, zzb);
            }
            return zzga.zzbb(zzag) + i + zzga.zzbj(i);
        }
        for (Object zza : (List) obj) {
            i += zza(zzfs, zzag, zza);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzgi zzgi = new zzgi();
        for (int i = 0; i < this.zzth.zzhx(); i++) {
            Entry zzbv = this.zzth.zzbv(i);
            zzgi.zza((T) (zzgk) zzbv.getKey(), zzbv.getValue());
        }
        for (Entry entry : this.zzth.zzhy()) {
            zzgi.zza((T) (zzgk) entry.getKey(), entry.getValue());
        }
        zzgi.zztj = this.zztj;
        return zzgi;
    }
}
