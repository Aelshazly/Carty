package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzaa extends zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Context zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ zzx zzg;

    zzaa(zzx zzx, String str, String str2, Context context, Bundle bundle) {
        this.zzg = zzx;
        this.zzc = str;
        this.zzd = str2;
        this.zze = context;
        this.zzf = bundle;
        super(zzx);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0057 A[Catch:{ Exception -> 0x00a4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0063 A[Catch:{ Exception -> 0x00a4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza() {
        /*
            r14 = this;
            r0 = 0
            r1 = 1
            com.google.android.gms.internal.measurement.zzx r2 = r14.zzg     // Catch:{ Exception -> 0x00a4 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x00a4 }
            r3.<init>()     // Catch:{ Exception -> 0x00a4 }
            r2.zzf = r3     // Catch:{ Exception -> 0x00a4 }
            com.google.android.gms.internal.measurement.zzx r2 = r14.zzg     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r3 = r14.zzc     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r4 = r14.zzd     // Catch:{ Exception -> 0x00a4 }
            boolean r2 = com.google.android.gms.internal.measurement.zzx.zzc(r3, r4)     // Catch:{ Exception -> 0x00a4 }
            r3 = 0
            if (r2 == 0) goto L_0x002a
            java.lang.String r3 = r14.zzd     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r2 = r14.zzc     // Catch:{ Exception -> 0x00a4 }
            com.google.android.gms.internal.measurement.zzx r4 = r14.zzg     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r4 = r4.zzc     // Catch:{ Exception -> 0x00a4 }
            r10 = r2
            r11 = r3
            r9 = r4
            goto L_0x002d
        L_0x002a:
            r9 = r3
            r10 = r9
            r11 = r10
        L_0x002d:
            android.content.Context r2 = r14.zze     // Catch:{ Exception -> 0x00a4 }
            com.google.android.gms.internal.measurement.zzx.zzi(r2)     // Catch:{ Exception -> 0x00a4 }
            java.lang.Boolean r2 = com.google.android.gms.internal.measurement.zzx.zzi     // Catch:{ Exception -> 0x00a4 }
            boolean r2 = r2.booleanValue()     // Catch:{ Exception -> 0x00a4 }
            if (r2 != 0) goto L_0x0041
            if (r10 == 0) goto L_0x003f
            goto L_0x0041
        L_0x003f:
            r2 = 0
            goto L_0x0042
        L_0x0041:
            r2 = 1
        L_0x0042:
            com.google.android.gms.internal.measurement.zzx r3 = r14.zzg     // Catch:{ Exception -> 0x00a4 }
            com.google.android.gms.internal.measurement.zzx r4 = r14.zzg     // Catch:{ Exception -> 0x00a4 }
            android.content.Context r5 = r14.zze     // Catch:{ Exception -> 0x00a4 }
            com.google.android.gms.internal.measurement.zzm r4 = r4.zza(r5, r2)     // Catch:{ Exception -> 0x00a4 }
            r3.zzr = r4     // Catch:{ Exception -> 0x00a4 }
            com.google.android.gms.internal.measurement.zzx r3 = r14.zzg     // Catch:{ Exception -> 0x00a4 }
            com.google.android.gms.internal.measurement.zzm r3 = r3.zzr     // Catch:{ Exception -> 0x00a4 }
            if (r3 != 0) goto L_0x0063
            com.google.android.gms.internal.measurement.zzx r2 = r14.zzg     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r2 = r2.zzc     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r3 = "Failed to connect to measurement client."
            android.util.Log.w(r2, r3)     // Catch:{ Exception -> 0x00a4 }
            return
        L_0x0063:
            android.content.Context r3 = r14.zze     // Catch:{ Exception -> 0x00a4 }
            int r3 = com.google.android.gms.internal.measurement.zzx.zzh(r3)     // Catch:{ Exception -> 0x00a4 }
            android.content.Context r4 = r14.zze     // Catch:{ Exception -> 0x00a4 }
            int r4 = com.google.android.gms.internal.measurement.zzx.zzg(r4)     // Catch:{ Exception -> 0x00a4 }
            if (r2 == 0) goto L_0x007c
            int r2 = java.lang.Math.max(r3, r4)     // Catch:{ Exception -> 0x00a4 }
            if (r4 >= r3) goto L_0x0079
            r3 = 1
            goto L_0x007a
        L_0x0079:
            r3 = 0
        L_0x007a:
            r8 = r3
            goto L_0x0087
        L_0x007c:
            if (r3 <= 0) goto L_0x0080
            r2 = r3
            goto L_0x0081
        L_0x0080:
            r2 = r4
        L_0x0081:
            if (r3 <= 0) goto L_0x0085
            r3 = 1
            goto L_0x0086
        L_0x0085:
            r3 = 0
        L_0x0086:
            r8 = r3
        L_0x0087:
            com.google.android.gms.internal.measurement.zzv r13 = new com.google.android.gms.internal.measurement.zzv     // Catch:{ Exception -> 0x00a4 }
            r4 = 25001(0x61a9, double:1.2352E-319)
            long r6 = (long) r2     // Catch:{ Exception -> 0x00a4 }
            android.os.Bundle r12 = r14.zzf     // Catch:{ Exception -> 0x00a4 }
            r3 = r13
            r3.<init>(r4, r6, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x00a4 }
            com.google.android.gms.internal.measurement.zzx r2 = r14.zzg     // Catch:{ Exception -> 0x00a4 }
            com.google.android.gms.internal.measurement.zzm r2 = r2.zzr     // Catch:{ Exception -> 0x00a4 }
            android.content.Context r3 = r14.zze     // Catch:{ Exception -> 0x00a4 }
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r3)     // Catch:{ Exception -> 0x00a4 }
            long r4 = r14.zza     // Catch:{ Exception -> 0x00a4 }
            r2.initialize(r3, r13, r4)     // Catch:{ Exception -> 0x00a4 }
            return
        L_0x00a4:
            r2 = move-exception
            com.google.android.gms.internal.measurement.zzx r3 = r14.zzg
            r3.zza(r2, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzaa.zza():void");
    }
}
