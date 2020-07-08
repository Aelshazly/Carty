package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
final class zzir implements Runnable {
    private final URL zza;
    private final byte[] zzb = null;
    private final zzis zzc;
    private final String zzd;
    private final Map<String, String> zze;
    private final /* synthetic */ zzip zzf;

    public zzir(zzip zzip, String str, URL url, byte[] bArr, Map<String, String> map, zzis zzis) {
        this.zzf = zzip;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzis);
        this.zza = url;
        this.zzc = zzis;
        this.zzd = str;
        this.zze = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r7 = this;
            com.google.android.gms.measurement.internal.zzip r0 = r7.zzf
            r0.zzc()
            r0 = 0
            r1 = 0
            com.google.android.gms.measurement.internal.zzip r2 = r7.zzf     // Catch:{ IOException -> 0x007a, all -> 0x006d }
            java.net.URL r3 = r7.zza     // Catch:{ IOException -> 0x007a, all -> 0x006d }
            java.net.HttpURLConnection r2 = r2.zza(r3)     // Catch:{ IOException -> 0x007a, all -> 0x006d }
            java.util.Map<java.lang.String, java.lang.String> r3 = r7.zze     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            if (r3 == 0) goto L_0x003c
            java.util.Map<java.lang.String, java.lang.String> r3 = r7.zze     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            java.util.Set r3 = r3.entrySet()     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
        L_0x0020:
            boolean r4 = r3.hasNext()     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            if (r4 == 0) goto L_0x003c
            java.lang.Object r4 = r3.next()     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            java.lang.Object r5 = r4.getKey()     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            r2.addRequestProperty(r5, r4)     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            goto L_0x0020
        L_0x003c:
            int r1 = r2.getResponseCode()     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            java.util.Map r3 = r2.getHeaderFields()     // Catch:{ IOException -> 0x0063, all -> 0x005f }
            com.google.android.gms.measurement.internal.zzip r4 = r7.zzf     // Catch:{ IOException -> 0x0059, all -> 0x0053 }
            byte[] r4 = com.google.android.gms.measurement.internal.zzip.zza(r2)     // Catch:{ IOException -> 0x0059, all -> 0x0053 }
            if (r2 == 0) goto L_0x004f
            r2.disconnect()
        L_0x004f:
            r7.zzb(r1, r0, r4, r3)
            return
        L_0x0053:
            r4 = move-exception
            r6 = r4
            r4 = r1
            r1 = r3
            r3 = r6
            goto L_0x0071
        L_0x0059:
            r4 = move-exception
            r6 = r4
            r4 = r1
            r1 = r3
            r3 = r6
            goto L_0x007e
        L_0x005f:
            r3 = move-exception
            r4 = r1
            r1 = r0
            goto L_0x0071
        L_0x0063:
            r3 = move-exception
            r4 = r1
            r1 = r0
            goto L_0x007e
        L_0x0067:
            r3 = move-exception
            r1 = r0
            goto L_0x0070
        L_0x006a:
            r3 = move-exception
            r1 = r0
            goto L_0x007d
        L_0x006d:
            r3 = move-exception
            r1 = r0
            r2 = r1
        L_0x0070:
            r4 = 0
        L_0x0071:
            if (r2 == 0) goto L_0x0076
            r2.disconnect()
        L_0x0076:
            r7.zzb(r4, r0, r0, r1)
            throw r3
        L_0x007a:
            r3 = move-exception
            r1 = r0
            r2 = r1
        L_0x007d:
            r4 = 0
        L_0x007e:
            if (r2 == 0) goto L_0x0083
            r2.disconnect()
        L_0x0083:
            r7.zzb(r4, r3, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzir.run():void");
    }

    private final void zzb(int i, Exception exc, byte[] bArr, Map<String, List<String>> map) {
        zzgj zzq = this.zzf.zzq();
        zziu zziu = new zziu(this, i, exc, bArr, map);
        zzq.zza((Runnable) zziu);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(int i, Exception exc, byte[] bArr, Map map) {
        this.zzc.zza(this.zzd, i, exc, bArr, map);
    }
}
