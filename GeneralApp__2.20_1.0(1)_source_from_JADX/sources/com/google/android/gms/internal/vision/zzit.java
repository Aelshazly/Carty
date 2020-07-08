package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzit {
    private static final zzjj<?, ?> zzaaa = new zzjl();
    private static final Class<?> zzzx = zzhv();
    private static final zzjj<?, ?> zzzy = zzn(false);
    private static final zzjj<?, ?> zzzz = zzn(true);

    public static void zzg(Class<?> cls) {
        if (!zzgs.class.isAssignableFrom(cls)) {
            Class<?> cls2 = zzzx;
            if (cls2 != null && !cls2.isAssignableFrom(cls)) {
                throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
            }
        }
    }

    public static void zza(int i, List<Double> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzkg zzkg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzkg zzkg) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzfh> list, zzkg zzkg) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzkg zzkg, zzir zzir) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zza(i, list, zzir);
        }
    }

    public static void zzb(int i, List<?> list, zzkg zzkg, zzir zzir) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkg.zzb(i, list, zzir);
        }
    }

    static int zzq(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzv(zzhq.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzga.zzv(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzq(list) + (list.size() * zzga.zzbb(i));
    }

    static int zzr(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzw(zzhq.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzga.zzw(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzga.zzbb(i));
    }

    static int zzs(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhq) {
            zzhq zzhq = (zzhq) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzx(zzhq.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzga.zzx(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzs(list) + (size * zzga.zzbb(i));
    }

    static int zzt(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzbh(zzgu.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzga.zzbh(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzga.zzbb(i));
    }

    static int zzu(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzbc(zzgu.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzga.zzbc(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzu(list) + (size * zzga.zzbb(i));
    }

    static int zzv(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzbd(zzgu.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzga.zzbd(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzv(list) + (size * zzga.zzbb(i));
    }

    static int zzw(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgu) {
            zzgu zzgu = (zzgu) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzbe(zzgu.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzga.zzbe(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzga.zzbb(i));
    }

    static int zzx(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzga.zzn(i, 0);
    }

    static int zzy(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzga.zzg(i, 0);
    }

    static int zzz(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzga.zzb(i, true);
    }

    static int zzc(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzbb = zzga.zzbb(i) * size;
        if (list instanceof zzhj) {
            zzhj zzhj = (zzhj) list;
            while (i4 < size) {
                Object raw = zzhj.getRaw(i4);
                if (raw instanceof zzfh) {
                    i3 = zzga.zzb((zzfh) raw);
                } else {
                    i3 = zzga.zzy((String) raw);
                }
                zzbb += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzfh) {
                    i2 = zzga.zzb((zzfh) obj);
                } else {
                    i2 = zzga.zzy((String) obj);
                }
                zzbb += i2;
                i4++;
            }
        }
        return zzbb;
    }

    static int zzc(int i, Object obj, zzir zzir) {
        if (obj instanceof zzhh) {
            return zzga.zza(i, (zzhh) obj);
        }
        return zzga.zzb(i, (zzic) obj, zzir);
    }

    static int zzc(int i, List<?> list, zzir zzir) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = zzga.zzbb(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzhh) {
                i2 = zzga.zza((zzhh) obj);
            } else {
                i2 = zzga.zza((zzic) obj, zzir);
            }
            zzbb += i2;
        }
        return zzbb;
    }

    static int zzd(int i, List<zzfh> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = size * zzga.zzbb(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzbb += zzga.zzb((zzfh) list.get(i2));
        }
        return zzbb;
    }

    static int zzd(int i, List<zzic> list, zzir zzir) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzga.zzc(i, (zzic) list.get(i3), zzir);
        }
        return i2;
    }

    public static zzjj<?, ?> zzhs() {
        return zzzy;
    }

    public static zzjj<?, ?> zzht() {
        return zzzz;
    }

    public static zzjj<?, ?> zzhu() {
        return zzaaa;
    }

    private static zzjj<?, ?> zzn(boolean z) {
        try {
            Class zzhw = zzhw();
            if (zzhw == null) {
                return null;
            }
            return (zzjj) zzhw.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzhv() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzhw() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static boolean zze(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zza(zzhv zzhv, T t, T t2, long j) {
        zzjp.zza((Object) t, j, zzhv.zzc(zzjp.zzp(t, j), zzjp.zzp(t2, j)));
    }

    static <T, FT extends zzgk<FT>> void zza(zzgf<FT> zzgf, T t, T t2) {
        zzgi zzf = zzgf.zzf(t2);
        if (!zzf.zzth.isEmpty()) {
            zzgf.zzg(t).zza(zzf);
        }
    }

    static <T, UT, UB> void zza(zzjj<UT, UB> zzjj, T t, T t2) {
        zzjj.zzf(t, zzjj.zzh(zzjj.zzw(t), zzjj.zzw(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzgv<?> zzgv, UB ub, zzjj<UT, UB> zzjj) {
        UB ub2;
        if (zzgv == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zzgv.zzh(intValue) != null) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub2 = zza(i, intValue, ub2, zzjj);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator it = list.iterator();
            ub2 = ub;
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (zzgv.zzh(intValue2) == null) {
                    UB zza = zza(i, intValue2, ub2, zzjj);
                    it.remove();
                    ub2 = zza;
                }
            }
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzgy zzgy, UB ub, zzjj<UT, UB> zzjj) {
        UB ub2;
        if (zzgy == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zzgy.zzg(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub2 = zza(i, intValue, ub2, zzjj);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator it = list.iterator();
            ub2 = ub;
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzgy.zzg(intValue2)) {
                    UB zza = zza(i, intValue2, ub2, zzjj);
                    it.remove();
                    ub2 = zza;
                }
            }
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzjj<UT, UB> zzjj) {
        if (ub == null) {
            ub = zzjj.zzif();
        }
        zzjj.zza(ub, i, (long) i2);
        return ub;
    }
}
