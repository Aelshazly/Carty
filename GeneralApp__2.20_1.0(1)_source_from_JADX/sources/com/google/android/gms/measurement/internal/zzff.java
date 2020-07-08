package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.util.Clock;
import okhttp3.internal.cache.DiskLruCache;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzff extends zze {
    private final zzfi zza = new zzfi(this, zzn(), "google_app_measurement_local.db");
    private boolean zzb;

    zzff(zzgq zzgq) {
        super(zzgq);
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    public final void zzab() {
        zzb();
        zzd();
        try {
            int delete = zzae().delete("messages", null, null) + 0;
            if (delete > 0) {
                zzr().zzx().zza("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error resetting local analytics data. error", e);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r12v0, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r9v1 */
    /* JADX WARNING: type inference failed for: r7v1 */
    /* JADX WARNING: type inference failed for: r12v1 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r9v2, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r7v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v3 */
    /* JADX WARNING: type inference failed for: r9v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v5 */
    /* JADX WARNING: type inference failed for: r12v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r9v6 */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r9v7 */
    /* JADX WARNING: type inference failed for: r12v4 */
    /* JADX WARNING: type inference failed for: r9v8, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r12v7, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r2v14 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: type inference failed for: r7v11 */
    /* JADX WARNING: type inference failed for: r7v12 */
    /* JADX WARNING: type inference failed for: r9v9 */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* JADX WARNING: type inference failed for: r9v10 */
    /* JADX WARNING: type inference failed for: r9v11 */
    /* JADX WARNING: type inference failed for: r7v13 */
    /* JADX WARNING: type inference failed for: r7v14 */
    /* JADX WARNING: type inference failed for: r9v12 */
    /* JADX WARNING: type inference failed for: r9v13 */
    /* JADX WARNING: type inference failed for: r7v15 */
    /* JADX WARNING: type inference failed for: r7v16 */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r9v14 */
    /* JADX WARNING: type inference failed for: r9v15 */
    /* JADX WARNING: type inference failed for: r9v16 */
    /* JADX WARNING: type inference failed for: r9v17 */
    /* JADX WARNING: type inference failed for: r9v18 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r12v11 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v1, types: [boolean, int]
      assigns: []
      uses: [?[int, short, byte, char], int, boolean]
      mth insns count: 160
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00cc A[SYNTHETIC, Splitter:B:50:0x00cc] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0122 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0122 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0122 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 19 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(int r17, byte[] r18) {
        /*
            r16 = this;
            r1 = r16
            r16.zzb()
            r16.zzd()
            boolean r0 = r1.zzb
            r2 = 0
            if (r0 == 0) goto L_0x000e
            return r2
        L_0x000e:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r17)
            java.lang.String r4 = "type"
            r3.put(r4, r0)
            java.lang.String r0 = "entry"
            r4 = r18
            r3.put(r0, r4)
            r4 = 5
            r5 = 0
            r6 = 5
        L_0x0027:
            if (r5 >= r4) goto L_0x0135
            r7 = 0
            r8 = 1
            android.database.sqlite.SQLiteDatabase r9 = r16.zzae()     // Catch:{ SQLiteFullException -> 0x0106, SQLiteDatabaseLockedException -> 0x00f3, SQLiteException -> 0x00c8, all -> 0x00c4 }
            if (r9 != 0) goto L_0x003b
            r1.zzb = r8     // Catch:{ SQLiteFullException -> 0x00c2, SQLiteDatabaseLockedException -> 0x00c0, SQLiteException -> 0x00bc }
            if (r9 == 0) goto L_0x003a
            r9.close()
        L_0x003a:
            return r2
        L_0x003b:
            r9.beginTransaction()     // Catch:{ SQLiteFullException -> 0x00c2, SQLiteDatabaseLockedException -> 0x00c0, SQLiteException -> 0x00bc }
            r10 = 0
            java.lang.String r0 = "select count(1) from messages"
            android.database.Cursor r12 = r9.rawQuery(r0, r7)     // Catch:{ SQLiteFullException -> 0x00c2, SQLiteDatabaseLockedException -> 0x00c0, SQLiteException -> 0x00bc }
            if (r12 == 0) goto L_0x005e
            boolean r0 = r12.moveToFirst()     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            if (r0 == 0) goto L_0x005e
            long r10 = r12.getLong(r2)     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            goto L_0x005e
        L_0x0053:
            r0 = move-exception
            goto L_0x012a
        L_0x0056:
            r0 = move-exception
            goto L_0x00be
        L_0x0058:
            r0 = move-exception
            goto L_0x00ba
        L_0x005a:
            r0 = move-exception
            r7 = r12
            goto L_0x0108
        L_0x005e:
            java.lang.String r0 = "messages"
            r13 = 100000(0x186a0, double:4.94066E-319)
            int r15 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r15 < 0) goto L_0x00a5
            com.google.android.gms.measurement.internal.zzfj r15 = r16.zzr()     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            com.google.android.gms.measurement.internal.zzfl r15 = r15.zzf()     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            java.lang.String r4 = "Data loss, local db full"
            r15.zza(r4)     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            long r13 = r13 - r10
            r10 = 1
            long r13 = r13 + r10
            java.lang.String r4 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            java.lang.String[] r10 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            java.lang.String r11 = java.lang.Long.toString(r13)     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            r10[r2] = r11     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            int r4 = r9.delete(r0, r4, r10)     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            long r10 = (long) r4     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            int r4 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x00a5
            com.google.android.gms.measurement.internal.zzfj r4 = r16.zzr()     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            com.google.android.gms.measurement.internal.zzfl r4 = r4.zzf()     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            java.lang.String r15 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r2 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            java.lang.Long r8 = java.lang.Long.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            long r13 = r13 - r10
            java.lang.Long r10 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            r4.zza(r15, r2, r8, r10)     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
        L_0x00a5:
            r9.insertOrThrow(r0, r7, r3)     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            r9.endTransaction()     // Catch:{ SQLiteFullException -> 0x005a, SQLiteDatabaseLockedException -> 0x0058, SQLiteException -> 0x0056, all -> 0x0053 }
            if (r12 == 0) goto L_0x00b3
            r12.close()
        L_0x00b3:
            if (r9 == 0) goto L_0x00b8
            r9.close()
        L_0x00b8:
            r2 = 1
            return r2
        L_0x00ba:
            r7 = r12
            goto L_0x00f5
        L_0x00bc:
            r0 = move-exception
            r12 = r7
        L_0x00be:
            r7 = r9
            goto L_0x00ca
        L_0x00c0:
            r0 = move-exception
            goto L_0x00f5
        L_0x00c2:
            r0 = move-exception
            goto L_0x0108
        L_0x00c4:
            r0 = move-exception
            r9 = r7
            r12 = r9
            goto L_0x012a
        L_0x00c8:
            r0 = move-exception
            r12 = r7
        L_0x00ca:
            if (r7 == 0) goto L_0x00d5
            boolean r2 = r7.inTransaction()     // Catch:{ all -> 0x00f0 }
            if (r2 == 0) goto L_0x00d5
            r7.endTransaction()     // Catch:{ all -> 0x00f0 }
        L_0x00d5:
            com.google.android.gms.measurement.internal.zzfj r2 = r16.zzr()     // Catch:{ all -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()     // Catch:{ all -> 0x00f0 }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zza(r4, r0)     // Catch:{ all -> 0x00f0 }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x00f0 }
            if (r12 == 0) goto L_0x00ea
            r12.close()
        L_0x00ea:
            if (r7 == 0) goto L_0x0122
            r7.close()
            goto L_0x0122
        L_0x00f0:
            r0 = move-exception
            r9 = r7
            goto L_0x012a
        L_0x00f3:
            r0 = move-exception
            r9 = r7
        L_0x00f5:
            long r10 = (long) r6
            android.os.SystemClock.sleep(r10)     // Catch:{ all -> 0x0128 }
            int r6 = r6 + 20
            if (r7 == 0) goto L_0x0100
            r7.close()
        L_0x0100:
            if (r9 == 0) goto L_0x0122
            r9.close()
            goto L_0x0122
        L_0x0106:
            r0 = move-exception
            r9 = r7
        L_0x0108:
            com.google.android.gms.measurement.internal.zzfj r2 = r16.zzr()     // Catch:{ all -> 0x0128 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()     // Catch:{ all -> 0x0128 }
            java.lang.String r4 = "Error writing entry; local database full"
            r2.zza(r4, r0)     // Catch:{ all -> 0x0128 }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x0128 }
            if (r7 == 0) goto L_0x011d
            r7.close()
        L_0x011d:
            if (r9 == 0) goto L_0x0122
            r9.close()
        L_0x0122:
            int r5 = r5 + 1
            r2 = 0
            r4 = 5
            goto L_0x0027
        L_0x0128:
            r0 = move-exception
            r12 = r7
        L_0x012a:
            if (r12 == 0) goto L_0x012f
            r12.close()
        L_0x012f:
            if (r9 == 0) goto L_0x0134
            r9.close()
        L_0x0134:
            throw r0
        L_0x0135:
            com.google.android.gms.measurement.internal.zzfj r0 = r16.zzr()
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzx()
            java.lang.String r2 = "Failed to write entry to local database"
            r0.zza(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzff.zza(int, byte[]):boolean");
    }

    public final boolean zza(zzan zzan) {
        Parcel obtain = Parcel.obtain();
        zzan.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzr().zzg().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzkz zzkz) {
        Parcel obtain = Parcel.obtain();
        zzkz.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzr().zzg().zza("User property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zza(zzv zzv) {
        zzp();
        byte[] zza2 = zzla.zza((Parcelable) zzv);
        if (zza2.length <= 131072) {
            return zza(2, zza2);
        }
        zzr().zzg().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX WARNING: type inference failed for: r24v0, types: [int] */
    /* JADX WARNING: type inference failed for: r24v1 */
    /* JADX WARNING: type inference failed for: r13v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r13v1 */
    /* JADX WARNING: type inference failed for: r24v2 */
    /* JADX WARNING: type inference failed for: r24v3 */
    /* JADX WARNING: type inference failed for: r13v2, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r13v3 */
    /* JADX WARNING: type inference failed for: r24v4 */
    /* JADX WARNING: type inference failed for: r13v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r13v5 */
    /* JADX WARNING: type inference failed for: r24v5 */
    /* JADX WARNING: type inference failed for: r13v6, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r13v7 */
    /* JADX WARNING: type inference failed for: r13v8 */
    /* JADX WARNING: type inference failed for: r15v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r24v6 */
    /* JADX WARNING: type inference failed for: r13v9 */
    /* JADX WARNING: type inference failed for: r13v10 */
    /* JADX WARNING: type inference failed for: r24v7 */
    /* JADX WARNING: type inference failed for: r13v11 */
    /* JADX WARNING: type inference failed for: r13v12 */
    /* JADX WARNING: type inference failed for: r24v8 */
    /* JADX WARNING: type inference failed for: r13v13 */
    /* JADX WARNING: type inference failed for: r13v14 */
    /* JADX WARNING: type inference failed for: r13v15 */
    /* JADX WARNING: type inference failed for: r13v16 */
    /* JADX WARNING: type inference failed for: r24v9 */
    /* JADX WARNING: type inference failed for: r13v18 */
    /* JADX WARNING: type inference failed for: r13v19 */
    /* JADX WARNING: type inference failed for: r13v20 */
    /* JADX WARNING: type inference failed for: r13v21 */
    /* JADX WARNING: type inference failed for: r13v23, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r24v10 */
    /* JADX WARNING: type inference failed for: r10v16, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r24v11 */
    /* JADX WARNING: type inference failed for: r13v25 */
    /* JADX WARNING: type inference failed for: r24v12 */
    /* JADX WARNING: type inference failed for: r24v13 */
    /* JADX WARNING: type inference failed for: r13v26 */
    /* JADX WARNING: type inference failed for: r24v14 */
    /* JADX WARNING: type inference failed for: r13v27 */
    /* JADX WARNING: type inference failed for: r24v15 */
    /* JADX WARNING: type inference failed for: r24v16 */
    /* JADX WARNING: type inference failed for: r13v28 */
    /* JADX WARNING: type inference failed for: r24v17 */
    /* JADX WARNING: type inference failed for: r24v18 */
    /* JADX WARNING: type inference failed for: r24v19 */
    /* JADX WARNING: type inference failed for: r24v20 */
    /* JADX WARNING: type inference failed for: r10v18, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r24v21 */
    /* JADX WARNING: type inference failed for: r13v30 */
    /* JADX WARNING: type inference failed for: r13v31 */
    /* JADX WARNING: type inference failed for: r13v32 */
    /* JADX WARNING: type inference failed for: r24v22 */
    /* JADX WARNING: type inference failed for: r24v23 */
    /* JADX WARNING: type inference failed for: r24v24 */
    /* JADX WARNING: type inference failed for: r24v25 */
    /* JADX WARNING: type inference failed for: r24v26 */
    /* JADX WARNING: type inference failed for: r24v27 */
    /* JADX WARNING: type inference failed for: r24v28 */
    /* JADX WARNING: type inference failed for: r24v29 */
    /* JADX WARNING: type inference failed for: r24v30 */
    /* JADX WARNING: type inference failed for: r24v31 */
    /* JADX WARNING: type inference failed for: r24v32 */
    /* JADX WARNING: type inference failed for: r24v33 */
    /* JADX WARNING: type inference failed for: r24v34 */
    /* JADX WARNING: type inference failed for: r13v33 */
    /* JADX WARNING: type inference failed for: r24v35 */
    /* JADX WARNING: type inference failed for: r24v36 */
    /* JADX WARNING: type inference failed for: r24v37 */
    /* JADX WARNING: type inference failed for: r13v34 */
    /* JADX WARNING: type inference failed for: r13v35 */
    /* JADX WARNING: type inference failed for: r24v38 */
    /* JADX WARNING: type inference failed for: r24v39 */
    /* JADX WARNING: type inference failed for: r13v36 */
    /* JADX WARNING: type inference failed for: r13v37 */
    /* JADX WARNING: type inference failed for: r24v40 */
    /* JADX WARNING: type inference failed for: r24v41 */
    /* JADX WARNING: type inference failed for: r13v38 */
    /* JADX WARNING: type inference failed for: r24v42 */
    /* JADX WARNING: type inference failed for: r13v39 */
    /* JADX WARNING: type inference failed for: r24v43 */
    /* JADX WARNING: type inference failed for: r13v40 */
    /* JADX WARNING: type inference failed for: r24v44 */
    /* JADX WARNING: type inference failed for: r13v41 */
    /* JADX WARNING: type inference failed for: r13v42 */
    /* JADX WARNING: type inference failed for: r24v45 */
    /* JADX WARNING: type inference failed for: r24v46 */
    /* JADX WARNING: type inference failed for: r24v47 */
    /* JADX WARNING: type inference failed for: r24v48 */
    /* JADX WARNING: type inference failed for: r24v49 */
    /* JADX WARNING: type inference failed for: r24v50 */
    /* JADX WARNING: type inference failed for: r13v43 */
    /* JADX WARNING: type inference failed for: r13v44 */
    /* JADX WARNING: type inference failed for: r13v45 */
    /* JADX WARNING: type inference failed for: r24v51 */
    /* JADX WARNING: type inference failed for: r24v52 */
    /* JADX WARNING: type inference failed for: r24v53 */
    /* JADX WARNING: type inference failed for: r24v54 */
    /* JADX WARNING: type inference failed for: r24v55 */
    /* JADX WARNING: type inference failed for: r24v56 */
    /* JADX WARNING: type inference failed for: r24v57 */
    /* JADX WARNING: type inference failed for: r24v58 */
    /* JADX WARNING: type inference failed for: r24v59 */
    /* JADX WARNING: type inference failed for: r24v60 */
    /* JADX WARNING: type inference failed for: r24v61 */
    /* JADX WARNING: type inference failed for: r24v62 */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0200, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0201, code lost:
        r13 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0205, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0206, code lost:
        r13 = r15;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x020a, code lost:
        r13 = r15;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x020d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x020e, code lost:
        r13 = r15;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x021a, code lost:
        r13 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x021e, code lost:
        if (r13.inTransaction() != false) goto L_0x0220;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0220, code lost:
        r13.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0232, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0237, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0246, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x024b, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0261, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0266, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x026d, code lost:
        r0 = th;
        r13 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0270, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0275, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0269, code lost:
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0269, code lost:
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0269, code lost:
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0269, code lost:
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0269, code lost:
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0269, code lost:
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0097, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0098, code lost:
        r24 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009d, code lost:
        r24 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a1, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a2, code lost:
        r24 = r15;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=null, for r24v0, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r24v1
      assigns: []
      uses: []
      mth insns count: 323
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0209 A[ExcHandler: SQLiteDatabaseLockedException (e android.database.sqlite.SQLiteDatabaseLockedException), Splitter:B:12:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x021a A[SYNTHETIC, Splitter:B:155:0x021a] */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0237  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0246  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x024b  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0270  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x0269 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x0269 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0269 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 35 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zza(int r24) {
        /*
            r23 = this;
            r1 = r23
            java.lang.String r2 = "Error reading entries from local database"
            r23.zzd()
            r23.zzb()
            boolean r0 = r1.zzb
            r3 = 0
            if (r0 == 0) goto L_0x0010
            return r3
        L_0x0010:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            boolean r0 = r23.zzaf()
            if (r0 != 0) goto L_0x001c
            return r4
        L_0x001c:
            r5 = 5
            r6 = 0
            r7 = 0
            r8 = 5
        L_0x0021:
            if (r7 >= r5) goto L_0x0279
            r9 = 1
            android.database.sqlite.SQLiteDatabase r15 = r23.zzae()     // Catch:{ SQLiteFullException -> 0x024f, SQLiteDatabaseLockedException -> 0x023b, SQLiteException -> 0x0215, all -> 0x0211 }
            if (r15 != 0) goto L_0x0043
            r1.zzb = r9     // Catch:{ SQLiteFullException -> 0x003e, SQLiteDatabaseLockedException -> 0x0209, SQLiteException -> 0x0039, all -> 0x0034 }
            if (r15 == 0) goto L_0x0033
            r15.close()
        L_0x0033:
            return r3
        L_0x0034:
            r0 = move-exception
            r10 = r3
            r13 = r15
            goto L_0x026e
        L_0x0039:
            r0 = move-exception
            r10 = r3
            r13 = r15
            goto L_0x0218
        L_0x003e:
            r0 = move-exception
            r10 = r3
            r13 = r15
            goto L_0x0252
        L_0x0043:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x020d, SQLiteDatabaseLockedException -> 0x0209, SQLiteException -> 0x0205, all -> 0x0200 }
            com.google.android.gms.measurement.internal.zzx r0 = r23.zzt()     // Catch:{ SQLiteFullException -> 0x020d, SQLiteDatabaseLockedException -> 0x0209, SQLiteException -> 0x0205, all -> 0x0200 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzap.zzbz     // Catch:{ SQLiteFullException -> 0x020d, SQLiteDatabaseLockedException -> 0x0209, SQLiteException -> 0x0205, all -> 0x0200 }
            boolean r0 = r0.zza(r10)     // Catch:{ SQLiteFullException -> 0x020d, SQLiteDatabaseLockedException -> 0x0209, SQLiteException -> 0x0205, all -> 0x0200 }
            r10 = 100
            java.lang.String r11 = "entry"
            java.lang.String r12 = "type"
            java.lang.String r13 = "rowid"
            r19 = -1
            if (r0 == 0) goto L_0x00a6
            long r16 = zza(r15)     // Catch:{ SQLiteFullException -> 0x00a1, SQLiteDatabaseLockedException -> 0x0209, SQLiteException -> 0x009c, all -> 0x0097 }
            int r0 = (r16 > r19 ? 1 : (r16 == r19 ? 0 : -1))
            if (r0 == 0) goto L_0x0071
            java.lang.String r0 = "rowid<?"
            java.lang.String[] r14 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x003e, SQLiteDatabaseLockedException -> 0x0209, SQLiteException -> 0x0039, all -> 0x0034 }
            java.lang.String r16 = java.lang.String.valueOf(r16)     // Catch:{ SQLiteFullException -> 0x003e, SQLiteDatabaseLockedException -> 0x0209, SQLiteException -> 0x0039, all -> 0x0034 }
            r14[r6] = r16     // Catch:{ SQLiteFullException -> 0x003e, SQLiteDatabaseLockedException -> 0x0209, SQLiteException -> 0x0039, all -> 0x0034 }
            goto L_0x0073
        L_0x0071:
            r0 = r3
            r14 = r0
        L_0x0073:
            java.lang.String r16 = "messages"
            java.lang.String[] r12 = new java.lang.String[]{r13, r12, r11}     // Catch:{ SQLiteFullException -> 0x00a1, SQLiteDatabaseLockedException -> 0x0209, SQLiteException -> 0x009c, all -> 0x0097 }
            r17 = 0
            r18 = 0
            java.lang.String r21 = "rowid asc"
            java.lang.String r22 = java.lang.Integer.toString(r10)     // Catch:{ SQLiteFullException -> 0x00a1, SQLiteDatabaseLockedException -> 0x0209, SQLiteException -> 0x009c, all -> 0x0097 }
            r10 = r15
            r11 = r16
            r13 = r0
            r24 = r15
            r15 = r17
            r16 = r18
            r17 = r21
            r18 = r22
            android.database.Cursor r0 = r10.query(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteFullException -> 0x01fc, SQLiteDatabaseLockedException -> 0x01f8, SQLiteException -> 0x01f4, all -> 0x01f0 }
            r10 = r0
            goto L_0x00c1
        L_0x0097:
            r0 = move-exception
            r24 = r15
            goto L_0x01f1
        L_0x009c:
            r0 = move-exception
            r24 = r15
            goto L_0x01f5
        L_0x00a1:
            r0 = move-exception
            r24 = r15
            goto L_0x01fd
        L_0x00a6:
            r24 = r15
            java.lang.String r0 = "messages"
            java.lang.String[] r12 = new java.lang.String[]{r13, r12, r11}     // Catch:{ SQLiteFullException -> 0x01fc, SQLiteDatabaseLockedException -> 0x01f8, SQLiteException -> 0x01f4, all -> 0x01f0 }
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            java.lang.String r17 = "rowid asc"
            java.lang.String r18 = java.lang.Integer.toString(r10)     // Catch:{ SQLiteFullException -> 0x01fc, SQLiteDatabaseLockedException -> 0x01f8, SQLiteException -> 0x01f4, all -> 0x01f0 }
            r10 = r24
            r11 = r0
            android.database.Cursor r0 = r10.query(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteFullException -> 0x01fc, SQLiteDatabaseLockedException -> 0x01f8, SQLiteException -> 0x01f4, all -> 0x01f0 }
            r10 = r0
        L_0x00c1:
        L_0x00c2:
            boolean r0 = r10.moveToNext()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            if (r0 == 0) goto L_0x019e
            long r19 = r10.getLong(r6)     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            int r0 = r10.getInt(r9)     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            r11 = 2
            byte[] r12 = r10.getBlob(r11)     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            if (r0 != 0) goto L_0x010c
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            int r0 = r12.length     // Catch:{ ParseException -> 0x00f6 }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x00f6 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x00f6 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzan> r0 = com.google.android.gms.measurement.internal.zzan.CREATOR     // Catch:{ ParseException -> 0x00f6 }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x00f6 }
            com.google.android.gms.measurement.internal.zzan r0 = (com.google.android.gms.measurement.internal.zzan) r0     // Catch:{ ParseException -> 0x00f6 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            if (r0 == 0) goto L_0x00f3
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
        L_0x00f3:
            goto L_0x00c2
        L_0x00f4:
            r0 = move-exception
            goto L_0x0108
        L_0x00f6:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfj r0 = r23.zzr()     // Catch:{ all -> 0x00f4 }
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzf()     // Catch:{ all -> 0x00f4 }
            java.lang.String r12 = "Failed to load event from local database"
            r0.zza(r12)     // Catch:{ all -> 0x00f4 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            goto L_0x00c2
        L_0x0108:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            throw r0     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
        L_0x010c:
            if (r0 != r9) goto L_0x0144
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            int r0 = r12.length     // Catch:{ ParseException -> 0x0128 }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x0128 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x0128 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzkz> r0 = com.google.android.gms.measurement.internal.zzkz.CREATOR     // Catch:{ ParseException -> 0x0128 }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x0128 }
            com.google.android.gms.measurement.internal.zzkz r0 = (com.google.android.gms.measurement.internal.zzkz) r0     // Catch:{ ParseException -> 0x0128 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            goto L_0x013a
        L_0x0126:
            r0 = move-exception
            goto L_0x0140
        L_0x0128:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfj r0 = r23.zzr()     // Catch:{ all -> 0x0126 }
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzf()     // Catch:{ all -> 0x0126 }
            java.lang.String r12 = "Failed to load user property from local database"
            r0.zza(r12)     // Catch:{ all -> 0x0126 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            r0 = r3
        L_0x013a:
            if (r0 == 0) goto L_0x013f
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
        L_0x013f:
            goto L_0x00c2
        L_0x0140:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            throw r0     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
        L_0x0144:
            if (r0 != r11) goto L_0x017d
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            int r0 = r12.length     // Catch:{ ParseException -> 0x0160 }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x0160 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x0160 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzv> r0 = com.google.android.gms.measurement.internal.zzv.CREATOR     // Catch:{ ParseException -> 0x0160 }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x0160 }
            com.google.android.gms.measurement.internal.zzv r0 = (com.google.android.gms.measurement.internal.zzv) r0     // Catch:{ ParseException -> 0x0160 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            goto L_0x0172
        L_0x015e:
            r0 = move-exception
            goto L_0x0179
        L_0x0160:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfj r0 = r23.zzr()     // Catch:{ all -> 0x015e }
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzf()     // Catch:{ all -> 0x015e }
            java.lang.String r12 = "Failed to load conditional user property from local database"
            r0.zza(r12)     // Catch:{ all -> 0x015e }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            r0 = r3
        L_0x0172:
            if (r0 == 0) goto L_0x0177
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
        L_0x0177:
            goto L_0x00c2
        L_0x0179:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            throw r0     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
        L_0x017d:
            r11 = 3
            if (r0 != r11) goto L_0x018f
            com.google.android.gms.measurement.internal.zzfj r0 = r23.zzr()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzi()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            java.lang.String r11 = "Skipping app launch break"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            goto L_0x00c2
        L_0x018f:
            com.google.android.gms.measurement.internal.zzfj r0 = r23.zzr()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzf()     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            java.lang.String r11 = "Unknown record type in local database"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            goto L_0x00c2
        L_0x019e:
            java.lang.String r0 = "messages"
            java.lang.String r11 = "rowid <= ?"
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            java.lang.String r13 = java.lang.Long.toString(r19)     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            r12[r6] = r13     // Catch:{ SQLiteFullException -> 0x01eb, SQLiteDatabaseLockedException -> 0x01e6, SQLiteException -> 0x01e2, all -> 0x01dd }
            r13 = r24
            int r0 = r13.delete(r0, r11, r12)     // Catch:{ SQLiteFullException -> 0x01da, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d5 }
            int r11 = r4.size()     // Catch:{ SQLiteFullException -> 0x01da, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d5 }
            if (r0 >= r11) goto L_0x01c3
            com.google.android.gms.measurement.internal.zzfj r0 = r23.zzr()     // Catch:{ SQLiteFullException -> 0x01da, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d5 }
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzf()     // Catch:{ SQLiteFullException -> 0x01da, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d5 }
            java.lang.String r11 = "Fewer entries removed from local database than expected"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x01da, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d5 }
        L_0x01c3:
            r13.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x01da, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d5 }
            r13.endTransaction()     // Catch:{ SQLiteFullException -> 0x01da, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d5 }
            if (r10 == 0) goto L_0x01cf
            r10.close()
        L_0x01cf:
            if (r13 == 0) goto L_0x01d4
            r13.close()
        L_0x01d4:
            return r4
        L_0x01d5:
            r0 = move-exception
            goto L_0x0218
        L_0x01d7:
            r0 = move-exception
            goto L_0x023e
        L_0x01da:
            r0 = move-exception
            goto L_0x0252
        L_0x01dd:
            r0 = move-exception
            r13 = r24
            goto L_0x026e
        L_0x01e2:
            r0 = move-exception
            r13 = r24
            goto L_0x0218
        L_0x01e6:
            r0 = move-exception
            r13 = r24
            goto L_0x023e
        L_0x01eb:
            r0 = move-exception
            r13 = r24
            goto L_0x0252
        L_0x01f0:
            r0 = move-exception
        L_0x01f1:
            r13 = r24
            goto L_0x0202
        L_0x01f4:
            r0 = move-exception
        L_0x01f5:
            r13 = r24
            goto L_0x0207
        L_0x01f8:
            r0 = move-exception
            r13 = r24
            goto L_0x020b
        L_0x01fc:
            r0 = move-exception
        L_0x01fd:
            r13 = r24
            goto L_0x020f
        L_0x0200:
            r0 = move-exception
            r13 = r15
        L_0x0202:
            r10 = r3
            goto L_0x026e
        L_0x0205:
            r0 = move-exception
            r13 = r15
        L_0x0207:
            r10 = r3
            goto L_0x0218
        L_0x0209:
            r0 = move-exception
            r13 = r15
        L_0x020b:
            r10 = r3
            goto L_0x023e
        L_0x020d:
            r0 = move-exception
            r13 = r15
        L_0x020f:
            r10 = r3
            goto L_0x0252
        L_0x0211:
            r0 = move-exception
            r10 = r3
            r13 = r10
            goto L_0x026e
        L_0x0215:
            r0 = move-exception
            r10 = r3
            r13 = r10
        L_0x0218:
            if (r13 == 0) goto L_0x0223
            boolean r11 = r13.inTransaction()     // Catch:{ all -> 0x026d }
            if (r11 == 0) goto L_0x0223
            r13.endTransaction()     // Catch:{ all -> 0x026d }
        L_0x0223:
            com.google.android.gms.measurement.internal.zzfj r11 = r23.zzr()     // Catch:{ all -> 0x026d }
            com.google.android.gms.measurement.internal.zzfl r11 = r11.zzf()     // Catch:{ all -> 0x026d }
            r11.zza(r2, r0)     // Catch:{ all -> 0x026d }
            r1.zzb = r9     // Catch:{ all -> 0x026d }
            if (r10 == 0) goto L_0x0235
            r10.close()
        L_0x0235:
            if (r13 == 0) goto L_0x0269
            r13.close()
            goto L_0x0269
        L_0x023b:
            r0 = move-exception
            r10 = r3
            r13 = r10
        L_0x023e:
            long r11 = (long) r8
            android.os.SystemClock.sleep(r11)     // Catch:{ all -> 0x026d }
            int r8 = r8 + 20
            if (r10 == 0) goto L_0x0249
            r10.close()
        L_0x0249:
            if (r13 == 0) goto L_0x0269
            r13.close()
            goto L_0x0269
        L_0x024f:
            r0 = move-exception
            r10 = r3
            r13 = r10
        L_0x0252:
            com.google.android.gms.measurement.internal.zzfj r11 = r23.zzr()     // Catch:{ all -> 0x026d }
            com.google.android.gms.measurement.internal.zzfl r11 = r11.zzf()     // Catch:{ all -> 0x026d }
            r11.zza(r2, r0)     // Catch:{ all -> 0x026d }
            r1.zzb = r9     // Catch:{ all -> 0x026d }
            if (r10 == 0) goto L_0x0264
            r10.close()
        L_0x0264:
            if (r13 == 0) goto L_0x0269
            r13.close()
        L_0x0269:
            int r7 = r7 + 1
            goto L_0x0021
        L_0x026d:
            r0 = move-exception
        L_0x026e:
            if (r10 == 0) goto L_0x0273
            r10.close()
        L_0x0273:
            if (r13 == 0) goto L_0x0278
            r13.close()
        L_0x0278:
            throw r0
        L_0x0279:
            com.google.android.gms.measurement.internal.zzfj r0 = r23.zzr()
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzi()
            java.lang.String r2 = "Failed to read events from database in reasonable time"
            r0.zza(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzff.zza(int):java.util.List");
    }

    public final boolean zzac() {
        return zza(3, new byte[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x008b, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzad() {
        /*
            r11 = this;
            java.lang.String r0 = "Error deleting app launch break from local database"
            r11.zzd()
            r11.zzb()
            boolean r1 = r11.zzb
            r2 = 0
            if (r1 == 0) goto L_0x000e
            return r2
        L_0x000e:
            boolean r1 = r11.zzaf()
            if (r1 != 0) goto L_0x0015
            return r2
        L_0x0015:
            r1 = 5
            r3 = 0
            r4 = 5
        L_0x0019:
            if (r3 >= r1) goto L_0x0094
            r5 = 0
            r6 = 1
            android.database.sqlite.SQLiteDatabase r5 = r11.zzae()     // Catch:{ SQLiteFullException -> 0x0078, SQLiteDatabaseLockedException -> 0x006b, SQLiteException -> 0x004c }
            if (r5 != 0) goto L_0x002b
            r11.zzb = r6     // Catch:{ SQLiteFullException -> 0x0078, SQLiteDatabaseLockedException -> 0x006b, SQLiteException -> 0x004c }
            if (r5 == 0) goto L_0x002a
            r5.close()
        L_0x002a:
            return r2
        L_0x002b:
            r5.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0078, SQLiteDatabaseLockedException -> 0x006b, SQLiteException -> 0x004c }
            java.lang.String r7 = "messages"
            java.lang.String r8 = "type == ?"
            java.lang.String[] r9 = new java.lang.String[r6]     // Catch:{ SQLiteFullException -> 0x0078, SQLiteDatabaseLockedException -> 0x006b, SQLiteException -> 0x004c }
            r10 = 3
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ SQLiteFullException -> 0x0078, SQLiteDatabaseLockedException -> 0x006b, SQLiteException -> 0x004c }
            r9[r2] = r10     // Catch:{ SQLiteFullException -> 0x0078, SQLiteDatabaseLockedException -> 0x006b, SQLiteException -> 0x004c }
            r5.delete(r7, r8, r9)     // Catch:{ SQLiteFullException -> 0x0078, SQLiteDatabaseLockedException -> 0x006b, SQLiteException -> 0x004c }
            r5.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0078, SQLiteDatabaseLockedException -> 0x006b, SQLiteException -> 0x004c }
            r5.endTransaction()     // Catch:{ SQLiteFullException -> 0x0078, SQLiteDatabaseLockedException -> 0x006b, SQLiteException -> 0x004c }
            if (r5 == 0) goto L_0x0049
            r5.close()
        L_0x0049:
            return r6
        L_0x004a:
            r0 = move-exception
            goto L_0x008e
        L_0x004c:
            r7 = move-exception
            if (r5 == 0) goto L_0x0058
            boolean r8 = r5.inTransaction()     // Catch:{ all -> 0x004a }
            if (r8 == 0) goto L_0x0058
            r5.endTransaction()     // Catch:{ all -> 0x004a }
        L_0x0058:
            com.google.android.gms.measurement.internal.zzfj r8 = r11.zzr()     // Catch:{ all -> 0x004a }
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzf()     // Catch:{ all -> 0x004a }
            r8.zza(r0, r7)     // Catch:{ all -> 0x004a }
            r11.zzb = r6     // Catch:{ all -> 0x004a }
            if (r5 == 0) goto L_0x008b
            r5.close()
            goto L_0x008b
        L_0x006b:
            r6 = move-exception
            long r6 = (long) r4
            android.os.SystemClock.sleep(r6)     // Catch:{ all -> 0x004a }
            int r4 = r4 + 20
            if (r5 == 0) goto L_0x008b
            r5.close()
            goto L_0x008b
        L_0x0078:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzfj r8 = r11.zzr()     // Catch:{ all -> 0x004a }
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzf()     // Catch:{ all -> 0x004a }
            r8.zza(r0, r7)     // Catch:{ all -> 0x004a }
            r11.zzb = r6     // Catch:{ all -> 0x004a }
            if (r5 == 0) goto L_0x008b
            r5.close()
        L_0x008b:
            int r3 = r3 + 1
            goto L_0x0019
        L_0x008e:
            if (r5 == 0) goto L_0x0093
            r5.close()
        L_0x0093:
            throw r0
        L_0x0094:
            com.google.android.gms.measurement.internal.zzfj r0 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzi()
            java.lang.String r1 = "Error deleting app launch break from local database in reasonable time"
            r0.zza(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzff.zzad():boolean");
    }

    /* JADX INFO: finally extract failed */
    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            Cursor query = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{"3"}, null, null, "rowid desc", DiskLruCache.VERSION_1);
            if (query.moveToFirst()) {
                long j = query.getLong(0);
                if (query != null) {
                    query.close();
                }
                return j;
            }
            if (query != null) {
                query.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final SQLiteDatabase zzae() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    private final boolean zzaf() {
        return zzn().getDatabasePath("google_app_measurement_local.db").exists();
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    public final /* bridge */ /* synthetic */ zzb zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzhr zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzfg zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zziz zzh() {
        return super.zzh();
    }

    public final /* bridge */ /* synthetic */ zziy zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzff zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ zzke zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ zzah zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzfh zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzla zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzgj zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzfj zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzfv zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    public final /* bridge */ /* synthetic */ zzw zzu() {
        return super.zzu();
    }
}
