package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
abstract class zzjj<T, B> {
    zzjj() {
    }

    /* access modifiers changed from: 0000 */
    public abstract void zza(B b, int i, long j);

    /* access modifiers changed from: 0000 */
    public abstract void zza(B b, int i, zzfh zzfh);

    /* access modifiers changed from: 0000 */
    public abstract void zza(B b, int i, T t);

    /* access modifiers changed from: 0000 */
    public abstract void zza(T t, zzkg zzkg) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract boolean zza(zzis zzis);

    /* access modifiers changed from: 0000 */
    public abstract void zzb(B b, int i, long j);

    /* access modifiers changed from: 0000 */
    public abstract void zzc(B b, int i, int i2);

    /* access modifiers changed from: 0000 */
    public abstract void zzc(T t, zzkg zzkg) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zzf(Object obj, T t);

    /* access modifiers changed from: 0000 */
    public abstract void zzg(Object obj, B b);

    /* access modifiers changed from: 0000 */
    public abstract T zzh(T t, T t2);

    /* access modifiers changed from: 0000 */
    public abstract void zzh(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract B zzif();

    /* access modifiers changed from: 0000 */
    public abstract T zzo(B b);

    /* access modifiers changed from: 0000 */
    public abstract int zzs(T t);

    /* access modifiers changed from: 0000 */
    public abstract T zzw(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract B zzx(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract int zzy(T t);

    /* access modifiers changed from: 0000 */
    public final boolean zza(B b, zzis zzis) throws IOException {
        int tag = zzis.getTag();
        int i = tag >>> 3;
        int i2 = tag & 7;
        if (i2 == 0) {
            zza(b, i, zzis.zzdx());
            return true;
        } else if (i2 == 1) {
            zzb(b, i, zzis.zzdz());
            return true;
        } else if (i2 == 2) {
            zza(b, i, zzis.zzed());
            return true;
        } else if (i2 == 3) {
            Object zzif = zzif();
            int i3 = 4 | (i << 3);
            while (zzis.zzdu() != Integer.MAX_VALUE) {
                if (!zza((B) zzif, zzis)) {
                    break;
                }
            }
            if (i3 == zzis.getTag()) {
                zza(b, i, (T) zzo(zzif));
                return true;
            }
            throw zzhc.zzgq();
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzc(b, i, zzis.zzea());
                return true;
            }
            throw zzhc.zzgr();
        }
    }
}
