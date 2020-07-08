package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzbj.zzd.zza;
import com.google.android.gms.internal.measurement.zzbj.zzf.zzb;

/* compiled from: com.google.android.gms:play-services-measurement@@17.3.0 */
final /* synthetic */ class zzq {
    static final /* synthetic */ int[] zza = new int[zzb.values().length];
    static final /* synthetic */ int[] zzb = new int[zza.values().length];

    static {
        try {
            zzb[zza.LESS_THAN.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zzb[zza.GREATER_THAN.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zzb[zza.EQUAL.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzb[zza.BETWEEN.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zza[zzb.REGEXP.ordinal()] = 1;
        } catch (NoSuchFieldError e5) {
        }
        try {
            zza[zzb.BEGINS_WITH.ordinal()] = 2;
        } catch (NoSuchFieldError e6) {
        }
        try {
            zza[zzb.ENDS_WITH.ordinal()] = 3;
        } catch (NoSuchFieldError e7) {
        }
        try {
            zza[zzb.PARTIAL.ordinal()] = 4;
        } catch (NoSuchFieldError e8) {
        }
        try {
            zza[zzb.EXACT.ordinal()] = 5;
        } catch (NoSuchFieldError e9) {
        }
        try {
            zza[zzb.IN_LIST.ordinal()] = 6;
        } catch (NoSuchFieldError e10) {
        }
    }
}
