package com.google.android.gms.internal.vision;

import android.content.Context;
import android.net.Uri;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzbk {
    final String zzgl;
    final Uri zzgm;
    final String zzgn;
    final String zzgo;
    final boolean zzgp;
    final boolean zzgq;
    final boolean zzgr;
    final boolean zzgs;
    @Nullable
    final zzcl<Context, Boolean> zzgt;

    public zzbk(Uri uri) {
        this(null, uri, "", "", false, false, false, false, null);
    }

    private zzbk(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, @Nullable zzcl<Context, Boolean> zzcl) {
        this.zzgl = str;
        this.zzgm = uri;
        this.zzgn = str2;
        this.zzgo = str3;
        this.zzgp = z;
        this.zzgq = z2;
        this.zzgr = z3;
        this.zzgs = z4;
        this.zzgt = zzcl;
    }

    public final zzbk zzf(String str) {
        boolean z = this.zzgp;
        if (!z) {
            zzbk zzbk = new zzbk(this.zzgl, this.zzgm, str, this.zzgo, z, this.zzgq, this.zzgr, this.zzgs, this.zzgt);
            return zzbk;
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    public final zzbe<Long> zza(String str, long j) {
        return zzbe.zza(this, str, j);
    }

    public final zzbe<Boolean> zza(String str, boolean z) {
        return zzbe.zza(this, str, z);
    }

    public final <T> zzbe<T> zza(String str, T t, zzbh<T> zzbh) {
        return zzbe.zza(this, str, t, zzbh);
    }
}
