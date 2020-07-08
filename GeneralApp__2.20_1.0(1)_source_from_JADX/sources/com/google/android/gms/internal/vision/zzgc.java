package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs.zzf;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzgc implements zzkg {
    private final zzga zzsj;

    public static zzgc zza(zzga zzga) {
        if (zzga.zzsy != null) {
            return zzga.zzsy;
        }
        return new zzgc(zzga);
    }

    private zzgc(zzga zzga) {
        this.zzsj = (zzga) zzgt.zza(zzga, "output");
    }

    public final int zzfj() {
        return zzf.zzxc;
    }

    public final void zzq(int i, int i2) throws IOException {
        this.zzsj.zzj(i, i2);
    }

    public final void zzi(int i, long j) throws IOException {
        this.zzsj.zza(i, j);
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzsj.zzc(i, j);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzsj.zza(i, f);
    }

    public final void zza(int i, double d) throws IOException {
        this.zzsj.zza(i, d);
    }

    public final void zzr(int i, int i2) throws IOException {
        this.zzsj.zzg(i, i2);
    }

    public final void zza(int i, long j) throws IOException {
        this.zzsj.zza(i, j);
    }

    public final void zzg(int i, int i2) throws IOException {
        this.zzsj.zzg(i, i2);
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzsj.zzc(i, j);
    }

    public final void zzj(int i, int i2) throws IOException {
        this.zzsj.zzj(i, i2);
    }

    public final void zza(int i, boolean z) throws IOException {
        this.zzsj.zza(i, z);
    }

    public final void zza(int i, String str) throws IOException {
        this.zzsj.zza(i, str);
    }

    public final void zza(int i, zzfh zzfh) throws IOException {
        this.zzsj.zza(i, zzfh);
    }

    public final void zzh(int i, int i2) throws IOException {
        this.zzsj.zzh(i, i2);
    }

    public final void zzi(int i, int i2) throws IOException {
        this.zzsj.zzi(i, i2);
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzsj.zzb(i, j);
    }

    public final void zza(int i, Object obj, zzir zzir) throws IOException {
        this.zzsj.zza(i, (zzic) obj, zzir);
    }

    public final void zzb(int i, Object obj, zzir zzir) throws IOException {
        zzga zzga = this.zzsj;
        zzic zzic = (zzic) obj;
        zzga.writeTag(i, 3);
        zzir.zza(zzic, zzga.zzsy);
        zzga.writeTag(i, 4);
    }

    public final void zzbk(int i) throws IOException {
        this.zzsj.writeTag(i, 3);
    }

    public final void zzbl(int i) throws IOException {
        this.zzsj.writeTag(i, 4);
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzfh) {
            this.zzsj.zzb(i, (zzfh) obj);
        } else {
            this.zzsj.zza(i, (zzic) obj);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbc(((Integer) list.get(i4)).intValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzax(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zzg(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbf(((Integer) list.get(i4)).intValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzba(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zzj(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzv(((Long) list.get(i4)).longValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzs(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzw(((Long) list.get(i4)).longValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzs(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzy(((Long) list.get(i4)).longValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzu(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzt(((Float) list.get(i4)).floatValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzs(((Float) list.get(i2)).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zza(i, ((Float) list.get(i2)).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzb(((Double) list.get(i4)).doubleValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zza(((Double) list.get(i2)).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zza(i, ((Double) list.get(i2)).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbh(((Integer) list.get(i4)).intValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzax(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zzg(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzl(((Boolean) list.get(i4)).booleanValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzk(((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zza(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzhj) {
            zzhj zzhj = (zzhj) list;
            while (i2 < list.size()) {
                Object raw = zzhj.getRaw(i2);
                if (raw instanceof String) {
                    this.zzsj.zza(i, (String) raw);
                } else {
                    this.zzsj.zza(i, (zzfh) raw);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zza(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zzb(int i, List<zzfh> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzsj.zza(i, (zzfh) list.get(i2));
        }
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbd(((Integer) list.get(i4)).intValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzay(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zzh(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbg(((Integer) list.get(i4)).intValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzba(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zzj(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzz(((Long) list.get(i4)).longValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzu(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzbe(((Integer) list.get(i4)).intValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzaz(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zzi(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsj.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzga.zzx(((Long) list.get(i4)).longValue());
            }
            this.zzsj.zzay(i3);
            while (i2 < list.size()) {
                this.zzsj.zzt(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsj.zzb(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzir zzir) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzir);
        }
    }

    public final void zzb(int i, List<?> list, zzir zzir) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzir);
        }
    }

    public final <K, V> void zza(int i, zzht<K, V> zzht, Map<K, V> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            this.zzsj.writeTag(i, 2);
            this.zzsj.zzay(zzhu.zza(zzht, entry.getKey(), entry.getValue()));
            zzhu.zza(this.zzsj, zzht, entry.getKey(), entry.getValue());
        }
    }
}
