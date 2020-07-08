package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzfc<V> {
    private static final Object zzf = new Object();
    private final String zza;
    private final zzfa<V> zzb;
    private final V zzc;
    private final V zzd;
    private final Object zze;
    private volatile V zzg;
    private volatile V zzh;

    private zzfc(String str, V v, V v2, zzfa<V> zzfa) {
        this.zze = new Object();
        this.zzg = null;
        this.zzh = null;
        this.zza = str;
        this.zzc = v;
        this.zzd = v2;
        this.zzb = zzfa;
    }

    public final String zza() {
        return this.zza;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0025, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r4 = com.google.android.gms.measurement.internal.zzap.zzdi.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0033, code lost:
        if (r4.hasNext() == false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0035, code lost:
        r0 = (com.google.android.gms.measurement.internal.zzfc) r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0040, code lost:
        if (com.google.android.gms.measurement.internal.zzw.zza() != false) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0042, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0045, code lost:
        if (r0.zzb == null) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0047, code lost:
        r1 = r0.zzb.zza();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0061, code lost:
        throw new java.lang.IllegalStateException("Refreshing flag cache must be done on a worker thread.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V zza(V r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zze
            monitor-enter(r0)
            V r1 = r3.zzg     // Catch:{ all -> 0x0084 }
            monitor-exit(r0)     // Catch:{ all -> 0x0084 }
            if (r4 == 0) goto L_0x0009
            return r4
        L_0x0009:
            com.google.android.gms.measurement.internal.zzw r4 = com.google.android.gms.measurement.internal.zzez.zza
            if (r4 != 0) goto L_0x0010
            V r4 = r3.zzc
            return r4
        L_0x0010:
            com.google.android.gms.measurement.internal.zzw r4 = com.google.android.gms.measurement.internal.zzez.zza
            java.lang.Object r4 = zzf
            monitor-enter(r4)
            boolean r0 = com.google.android.gms.measurement.internal.zzw.zza()     // Catch:{ all -> 0x0081 }
            if (r0 == 0) goto L_0x0026
            V r0 = r3.zzh     // Catch:{ all -> 0x0081 }
            if (r0 != 0) goto L_0x0022
            V r0 = r3.zzc     // Catch:{ all -> 0x0081 }
            goto L_0x0024
        L_0x0022:
            V r0 = r3.zzh     // Catch:{ all -> 0x0081 }
        L_0x0024:
            monitor-exit(r4)     // Catch:{ all -> 0x0081 }
            return r0
        L_0x0026:
            monitor-exit(r4)     // Catch:{ all -> 0x0081 }
            java.util.List r4 = com.google.android.gms.measurement.internal.zzap.zzdi     // Catch:{ SecurityException -> 0x0063 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ SecurityException -> 0x0063 }
        L_0x002f:
            boolean r0 = r4.hasNext()     // Catch:{ SecurityException -> 0x0063 }
            if (r0 == 0) goto L_0x0062
            java.lang.Object r0 = r4.next()     // Catch:{ SecurityException -> 0x0063 }
            com.google.android.gms.measurement.internal.zzfc r0 = (com.google.android.gms.measurement.internal.zzfc) r0     // Catch:{ SecurityException -> 0x0063 }
            boolean r1 = com.google.android.gms.measurement.internal.zzw.zza()     // Catch:{ SecurityException -> 0x0063 }
            if (r1 != 0) goto L_0x005a
            r1 = 0
            com.google.android.gms.measurement.internal.zzfa<V> r2 = r0.zzb     // Catch:{ IllegalStateException -> 0x004e }
            if (r2 == 0) goto L_0x004d
            com.google.android.gms.measurement.internal.zzfa<V> r2 = r0.zzb     // Catch:{ IllegalStateException -> 0x004e }
            java.lang.Object r1 = r2.zza()     // Catch:{ IllegalStateException -> 0x004e }
        L_0x004d:
            goto L_0x0050
        L_0x004e:
            r2 = move-exception
        L_0x0050:
            java.lang.Object r2 = zzf     // Catch:{ SecurityException -> 0x0063 }
            monitor-enter(r2)     // Catch:{ SecurityException -> 0x0063 }
            r0.zzh = r1     // Catch:{ all -> 0x0057 }
            monitor-exit(r2)     // Catch:{ all -> 0x0057 }
            goto L_0x002f
        L_0x0057:
            r4 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0057 }
            throw r4     // Catch:{ SecurityException -> 0x0063 }
        L_0x005a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ SecurityException -> 0x0063 }
            java.lang.String r0 = "Refreshing flag cache must be done on a worker thread."
            r4.<init>(r0)     // Catch:{ SecurityException -> 0x0063 }
            throw r4     // Catch:{ SecurityException -> 0x0063 }
        L_0x0062:
            goto L_0x0064
        L_0x0063:
            r4 = move-exception
        L_0x0064:
            com.google.android.gms.measurement.internal.zzfa<V> r4 = r3.zzb
            if (r4 != 0) goto L_0x006e
            com.google.android.gms.measurement.internal.zzw r4 = com.google.android.gms.measurement.internal.zzez.zza
            V r4 = r3.zzc
            return r4
        L_0x006e:
            java.lang.Object r4 = r4.zza()     // Catch:{ SecurityException -> 0x007a, IllegalStateException -> 0x0073 }
            return r4
        L_0x0073:
            r4 = move-exception
            com.google.android.gms.measurement.internal.zzw r4 = com.google.android.gms.measurement.internal.zzez.zza
            V r4 = r3.zzc
            return r4
        L_0x007a:
            r4 = move-exception
            com.google.android.gms.measurement.internal.zzw r4 = com.google.android.gms.measurement.internal.zzez.zza
            V r4 = r3.zzc
            return r4
        L_0x0081:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0081 }
            throw r0
        L_0x0084:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0084 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfc.zza(java.lang.Object):java.lang.Object");
    }
}
