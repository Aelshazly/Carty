package com.google.android.gms.internal.vision;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public class zzal {
    public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static final Uri zzeu = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzev = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzew = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    /* access modifiers changed from: private */
    public static final AtomicBoolean zzex = new AtomicBoolean();
    private static HashMap<String, String> zzey;
    private static final HashMap<String, Boolean> zzez = new HashMap<>();
    private static final HashMap<String, Integer> zzfa = new HashMap<>();
    private static final HashMap<String, Long> zzfb = new HashMap<>();
    private static final HashMap<String, Float> zzfc = new HashMap<>();
    private static Object zzfd;
    private static boolean zzfe;
    private static String[] zzff = new String[0];

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006c, code lost:
        return r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a9, code lost:
        return r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ab, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b0, code lost:
        r10 = r10.query(CONTENT_URI, null, null, new java.lang.String[]{r11}, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00be, code lost:
        if (r10 != null) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c1, code lost:
        if (r10 == null) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c3, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c6, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00cb, code lost:
        if (r10.moveToFirst() != false) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00cd, code lost:
        zza(r0, r11, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d5, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r12 = r10.getString(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00da, code lost:
        if (r12 == null) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e0, code lost:
        if (r12.equals(null) == false) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e2, code lost:
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e3, code lost:
        zza(r0, r11, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e6, code lost:
        if (r12 == null) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e9, code lost:
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00ea, code lost:
        if (r10 == null) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ec, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ef, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00f0, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00f1, code lost:
        if (r10 != null) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00f3, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00f6, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String zza(android.content.ContentResolver r10, java.lang.String r11, java.lang.String r12) {
        /*
            java.lang.Class<com.google.android.gms.internal.vision.zzal> r12 = com.google.android.gms.internal.vision.zzal.class
            monitor-enter(r12)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzey     // Catch:{ all -> 0x00f7 }
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != 0) goto L_0x002b
            java.util.concurrent.atomic.AtomicBoolean r0 = zzex     // Catch:{ all -> 0x00f7 }
            r0.set(r2)     // Catch:{ all -> 0x00f7 }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x00f7 }
            r0.<init>()     // Catch:{ all -> 0x00f7 }
            zzey = r0     // Catch:{ all -> 0x00f7 }
            java.lang.Object r0 = new java.lang.Object     // Catch:{ all -> 0x00f7 }
            r0.<init>()     // Catch:{ all -> 0x00f7 }
            zzfd = r0     // Catch:{ all -> 0x00f7 }
            zzfe = r2     // Catch:{ all -> 0x00f7 }
            android.net.Uri r0 = CONTENT_URI     // Catch:{ all -> 0x00f7 }
            com.google.android.gms.internal.vision.zzao r4 = new com.google.android.gms.internal.vision.zzao     // Catch:{ all -> 0x00f7 }
            r4.<init>(r3)     // Catch:{ all -> 0x00f7 }
            r10.registerContentObserver(r0, r1, r4)     // Catch:{ all -> 0x00f7 }
            goto L_0x0055
        L_0x002b:
            java.util.concurrent.atomic.AtomicBoolean r0 = zzex     // Catch:{ all -> 0x00f7 }
            boolean r0 = r0.getAndSet(r2)     // Catch:{ all -> 0x00f7 }
            if (r0 == 0) goto L_0x0055
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzey     // Catch:{ all -> 0x00f7 }
            r0.clear()     // Catch:{ all -> 0x00f7 }
            java.util.HashMap<java.lang.String, java.lang.Boolean> r0 = zzez     // Catch:{ all -> 0x00f7 }
            r0.clear()     // Catch:{ all -> 0x00f7 }
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = zzfa     // Catch:{ all -> 0x00f7 }
            r0.clear()     // Catch:{ all -> 0x00f7 }
            java.util.HashMap<java.lang.String, java.lang.Long> r0 = zzfb     // Catch:{ all -> 0x00f7 }
            r0.clear()     // Catch:{ all -> 0x00f7 }
            java.util.HashMap<java.lang.String, java.lang.Float> r0 = zzfc     // Catch:{ all -> 0x00f7 }
            r0.clear()     // Catch:{ all -> 0x00f7 }
            java.lang.Object r0 = new java.lang.Object     // Catch:{ all -> 0x00f7 }
            r0.<init>()     // Catch:{ all -> 0x00f7 }
            zzfd = r0     // Catch:{ all -> 0x00f7 }
            zzfe = r2     // Catch:{ all -> 0x00f7 }
        L_0x0055:
            java.lang.Object r0 = zzfd     // Catch:{ all -> 0x00f7 }
            java.util.HashMap<java.lang.String, java.lang.String> r4 = zzey     // Catch:{ all -> 0x00f7 }
            boolean r4 = r4.containsKey(r11)     // Catch:{ all -> 0x00f7 }
            if (r4 == 0) goto L_0x006d
            java.util.HashMap<java.lang.String, java.lang.String> r10 = zzey     // Catch:{ all -> 0x00f7 }
            java.lang.Object r10 = r10.get(r11)     // Catch:{ all -> 0x00f7 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x00f7 }
            if (r10 == 0) goto L_0x006a
            goto L_0x006b
        L_0x006a:
            r10 = r3
        L_0x006b:
            monitor-exit(r12)     // Catch:{ all -> 0x00f7 }
            return r10
        L_0x006d:
            java.lang.String[] r4 = zzff     // Catch:{ all -> 0x00f7 }
            int r5 = r4.length     // Catch:{ all -> 0x00f7 }
            r6 = 0
        L_0x0071:
            if (r6 >= r5) goto L_0x00af
            r7 = r4[r6]     // Catch:{ all -> 0x00f7 }
            boolean r7 = r11.startsWith(r7)     // Catch:{ all -> 0x00f7 }
            if (r7 == 0) goto L_0x00ac
            boolean r0 = zzfe     // Catch:{ all -> 0x00f7 }
            if (r0 == 0) goto L_0x0087
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzey     // Catch:{ all -> 0x00f7 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00f7 }
            if (r0 == 0) goto L_0x00aa
        L_0x0087:
            java.lang.String[] r0 = zzff     // Catch:{ all -> 0x00f7 }
            java.util.HashMap<java.lang.String, java.lang.String> r2 = zzey     // Catch:{ all -> 0x00f7 }
            java.util.Map r10 = zza(r10, r0)     // Catch:{ all -> 0x00f7 }
            r2.putAll(r10)     // Catch:{ all -> 0x00f7 }
            zzfe = r1     // Catch:{ all -> 0x00f7 }
            java.util.HashMap<java.lang.String, java.lang.String> r10 = zzey     // Catch:{ all -> 0x00f7 }
            boolean r10 = r10.containsKey(r11)     // Catch:{ all -> 0x00f7 }
            if (r10 == 0) goto L_0x00aa
            java.util.HashMap<java.lang.String, java.lang.String> r10 = zzey     // Catch:{ all -> 0x00f7 }
            java.lang.Object r10 = r10.get(r11)     // Catch:{ all -> 0x00f7 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x00f7 }
            if (r10 == 0) goto L_0x00a7
            goto L_0x00a8
        L_0x00a7:
            r10 = r3
        L_0x00a8:
            monitor-exit(r12)     // Catch:{ all -> 0x00f7 }
            return r10
        L_0x00aa:
            monitor-exit(r12)     // Catch:{ all -> 0x00f7 }
            return r3
        L_0x00ac:
            int r6 = r6 + 1
            goto L_0x0071
        L_0x00af:
            monitor-exit(r12)     // Catch:{ all -> 0x00f7 }
            android.net.Uri r5 = CONTENT_URI
            r6 = 0
            r7 = 0
            java.lang.String[] r8 = new java.lang.String[r1]
            r8[r2] = r11
            r9 = 0
            r4 = r10
            android.database.Cursor r10 = r4.query(r5, r6, r7, r8, r9)
            if (r10 != 0) goto L_0x00c7
            if (r10 == 0) goto L_0x00c6
            r10.close()
        L_0x00c6:
            return r3
        L_0x00c7:
            boolean r12 = r10.moveToFirst()     // Catch:{ all -> 0x00f0 }
            if (r12 != 0) goto L_0x00d6
            zza(r0, r11, r3)     // Catch:{ all -> 0x00f0 }
            if (r10 == 0) goto L_0x00d5
            r10.close()
        L_0x00d5:
            return r3
        L_0x00d6:
            java.lang.String r12 = r10.getString(r1)     // Catch:{ all -> 0x00f0 }
            if (r12 == 0) goto L_0x00e3
            boolean r1 = r12.equals(r3)     // Catch:{ all -> 0x00f0 }
            if (r1 == 0) goto L_0x00e3
            r12 = r3
        L_0x00e3:
            zza(r0, r11, r12)     // Catch:{ all -> 0x00f0 }
            if (r12 == 0) goto L_0x00e9
            goto L_0x00ea
        L_0x00e9:
            r12 = r3
        L_0x00ea:
            if (r10 == 0) goto L_0x00ef
            r10.close()
        L_0x00ef:
            return r12
        L_0x00f0:
            r11 = move-exception
            if (r10 == 0) goto L_0x00f6
            r10.close()
        L_0x00f6:
            throw r11
        L_0x00f7:
            r10 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x00f7 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzal.zza(android.content.ContentResolver, java.lang.String, java.lang.String):java.lang.String");
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzal.class) {
            if (obj == zzfd) {
                zzey.put(str, str2);
            }
        }
    }

    private static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(zzeu, null, null, strArr, null);
        TreeMap treeMap = new TreeMap();
        if (query == null) {
            return treeMap;
        }
        while (query.moveToNext()) {
            try {
                treeMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return treeMap;
    }
}
