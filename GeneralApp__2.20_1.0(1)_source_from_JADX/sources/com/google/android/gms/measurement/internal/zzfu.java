package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@17.3.0 */
final class zzfu implements Runnable {
    private final URL zza;
    private final byte[] zzb;
    private final zzfs zzc;
    private final String zzd;
    private final Map<String, String> zze;
    private final /* synthetic */ zzfq zzf;

    public zzfu(zzfq zzfq, String str, URL url, byte[] bArr, Map<String, String> map, zzfs zzfs) {
        this.zzf = zzfq;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzfs);
        this.zza = url;
        this.zzb = bArr;
        this.zzc = zzfs;
        this.zzd = str;
        this.zze = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ca A[SYNTHETIC, Splitter:B:44:0x00ca] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0105 A[SYNTHETIC, Splitter:B:57:0x0105] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x011f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r13 = this;
            java.lang.String r0 = "Error closing HTTP compressed POST connection output stream. appId"
            com.google.android.gms.measurement.internal.zzfq r1 = r13.zzf
            r1.zzc()
            r1 = 0
            r2 = 0
            com.google.android.gms.measurement.internal.zzfq r3 = r13.zzf     // Catch:{ IOException -> 0x00fe, all -> 0x00c4 }
            java.net.URL r4 = r13.zza     // Catch:{ IOException -> 0x00fe, all -> 0x00c4 }
            java.net.HttpURLConnection r3 = r3.zza(r4)     // Catch:{ IOException -> 0x00fe, all -> 0x00c4 }
            java.util.Map<java.lang.String, java.lang.String> r4 = r13.zze     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            if (r4 == 0) goto L_0x003f
            java.util.Map<java.lang.String, java.lang.String> r4 = r13.zze     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.util.Set r4 = r4.entrySet()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
        L_0x0023:
            boolean r5 = r4.hasNext()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            if (r5 == 0) goto L_0x003f
            java.lang.Object r5 = r4.next()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.lang.Object r6 = r5.getKey()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.lang.Object r5 = r5.getValue()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            r3.addRequestProperty(r6, r5)     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            goto L_0x0023
        L_0x003f:
            byte[] r4 = r13.zzb     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            if (r4 == 0) goto L_0x008a
            com.google.android.gms.measurement.internal.zzfq r4 = r13.zzf     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            com.google.android.gms.measurement.internal.zzkw r4 = r4.zzg()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            byte[] r5 = r13.zzb     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            byte[] r4 = r4.zzc(r5)     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            com.google.android.gms.measurement.internal.zzfq r5 = r13.zzf     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            com.google.android.gms.measurement.internal.zzfj r5 = r5.zzr()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            com.google.android.gms.measurement.internal.zzfl r5 = r5.zzx()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.lang.String r6 = "Uploading data. size"
            int r7 = r4.length     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            r5.zza(r6, r7)     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            r5 = 1
            r3.setDoOutput(r5)     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.lang.String r5 = "Content-Encoding"
            java.lang.String r6 = "gzip"
            r3.addRequestProperty(r5, r6)     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            int r5 = r4.length     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            r3.setFixedLengthStreamingMode(r5)     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            r3.connect()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.io.OutputStream r5 = r3.getOutputStream()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            r5.write(r4)     // Catch:{ IOException -> 0x0084, all -> 0x0080 }
            r5.close()     // Catch:{ IOException -> 0x0084, all -> 0x0080 }
            goto L_0x008a
        L_0x0080:
            r4 = move-exception
            r11 = r1
            r1 = r5
            goto L_0x00c7
        L_0x0084:
            r4 = move-exception
            r11 = r1
            r9 = r4
            r1 = r5
            goto L_0x0102
        L_0x008a:
            int r8 = r3.getResponseCode()     // Catch:{ IOException -> 0x00c1, all -> 0x00be }
            java.util.Map r11 = r3.getHeaderFields()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
            com.google.android.gms.measurement.internal.zzfq r2 = r13.zzf     // Catch:{ IOException -> 0x00b5, all -> 0x00b3 }
            byte[] r10 = com.google.android.gms.measurement.internal.zzfq.zza(r3)     // Catch:{ IOException -> 0x00b5, all -> 0x00b3 }
            if (r3 == 0) goto L_0x009d
            r3.disconnect()
        L_0x009d:
            com.google.android.gms.measurement.internal.zzfq r0 = r13.zzf
            com.google.android.gms.measurement.internal.zzgj r0 = r0.zzq()
            com.google.android.gms.measurement.internal.zzfr r1 = new com.google.android.gms.measurement.internal.zzfr
            java.lang.String r6 = r13.zzd
            com.google.android.gms.measurement.internal.zzfs r7 = r13.zzc
            r9 = 0
            r12 = 0
            r5 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r0.zza(r1)
            return
        L_0x00b3:
            r4 = move-exception
            goto L_0x00c8
        L_0x00b5:
            r4 = move-exception
            goto L_0x00bc
        L_0x00b7:
            r4 = move-exception
            r11 = r1
            goto L_0x00c8
        L_0x00ba:
            r4 = move-exception
            r11 = r1
        L_0x00bc:
            r9 = r4
            goto L_0x0103
        L_0x00be:
            r4 = move-exception
            r11 = r1
            goto L_0x00c7
        L_0x00c1:
            r4 = move-exception
            r11 = r1
            goto L_0x0101
        L_0x00c4:
            r4 = move-exception
            r3 = r1
            r11 = r3
        L_0x00c7:
            r8 = 0
        L_0x00c8:
            if (r1 == 0) goto L_0x00e2
            r1.close()     // Catch:{ IOException -> 0x00ce }
            goto L_0x00e2
        L_0x00ce:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzfq r2 = r13.zzf
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()
            java.lang.String r5 = r13.zzd
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfj.zza(r5)
            r2.zza(r0, r5, r1)
        L_0x00e2:
            if (r3 == 0) goto L_0x00e7
            r3.disconnect()
        L_0x00e7:
            com.google.android.gms.measurement.internal.zzfq r0 = r13.zzf
            com.google.android.gms.measurement.internal.zzgj r0 = r0.zzq()
            com.google.android.gms.measurement.internal.zzfr r1 = new com.google.android.gms.measurement.internal.zzfr
            java.lang.String r6 = r13.zzd
            com.google.android.gms.measurement.internal.zzfs r7 = r13.zzc
            r9 = 0
            r10 = 0
            r12 = 0
            r5 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r0.zza(r1)
            throw r4
        L_0x00fe:
            r4 = move-exception
            r3 = r1
            r11 = r3
        L_0x0101:
            r9 = r4
        L_0x0102:
            r8 = 0
        L_0x0103:
            if (r1 == 0) goto L_0x011d
            r1.close()     // Catch:{ IOException -> 0x0109 }
            goto L_0x011d
        L_0x0109:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzfq r2 = r13.zzf
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()
            java.lang.String r4 = r13.zzd
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfj.zza(r4)
            r2.zza(r0, r4, r1)
        L_0x011d:
            if (r3 == 0) goto L_0x0122
            r3.disconnect()
        L_0x0122:
            com.google.android.gms.measurement.internal.zzfq r0 = r13.zzf
            com.google.android.gms.measurement.internal.zzgj r0 = r0.zzq()
            com.google.android.gms.measurement.internal.zzfr r1 = new com.google.android.gms.measurement.internal.zzfr
            java.lang.String r6 = r13.zzd
            com.google.android.gms.measurement.internal.zzfs r7 = r13.zzc
            r10 = 0
            r12 = 0
            r5 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r0.zza(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfu.run():void");
    }
}
