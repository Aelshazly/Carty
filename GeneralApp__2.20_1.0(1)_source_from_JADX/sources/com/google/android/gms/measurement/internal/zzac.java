package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import androidx.collection.ArrayMap;
import bolts.MeasurementEvent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzbj;
import com.google.android.gms.internal.measurement.zzbj.zza.C1741zza;
import com.google.android.gms.internal.measurement.zzbj.zzb;
import com.google.android.gms.internal.measurement.zzbj.zze;
import com.google.android.gms.internal.measurement.zzbr;
import com.google.android.gms.internal.measurement.zzbr.zzc;
import com.google.android.gms.internal.measurement.zzbr.zzg;
import com.google.android.gms.internal.measurement.zzbr.zzg.zza;
import com.google.android.gms.internal.measurement.zzfd;
import com.google.android.gms.internal.measurement.zzkz;
import com.google.android.gms.internal.measurement.zzll;
import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@17.3.0 */
final class zzac extends zzkp {
    /* access modifiers changed from: private */
    public static final String[] zzb = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzc = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzd = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zze = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzf = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzg;
    /* access modifiers changed from: private */
    public static final String[] zzh;
    /* access modifiers changed from: private */
    public static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzad zzj = new zzad(this, zzn(), "google_app_measurement.db");
    /* access modifiers changed from: private */
    public final zzkl zzk = new zzkl(zzm());

    zzac(zzks zzks) {
        super(zzks);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return false;
    }

    public final void zzf() {
        zzak();
        mo16207c_().beginTransaction();
    }

    /* renamed from: b_ */
    public final void mo16206b_() {
        zzak();
        mo16207c_().setTransactionSuccessful();
    }

    public final void zzh() {
        zzak();
        mo16207c_().endTransaction();
    }

    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = mo16207c_().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzr().zzf().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = mo16207c_().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                long j2 = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j2;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c_ */
    public final SQLiteDatabase mo16207c_() {
        zzd();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e) {
            zzr().zzi().zza("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0161  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzaj zza(java.lang.String r27, java.lang.String r28) {
        /*
            r26 = this;
            r15 = r27
            r14 = r28
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r27)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r28)
            r26.zzd()
            r26.zzak()
            com.google.android.gms.measurement.internal.zzx r0 = r26.zzt()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzap.zzbn
            boolean r0 = r0.zze(r15, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            java.lang.String r2 = "lifetime_count"
            java.lang.String r3 = "current_bundle_count"
            java.lang.String r4 = "last_fire_timestamp"
            java.lang.String r5 = "last_bundled_timestamp"
            java.lang.String r6 = "last_bundled_day"
            java.lang.String r7 = "last_sampled_complex_event_id"
            java.lang.String r8 = "last_sampling_rate"
            java.lang.String r9 = "last_exempt_from_sampling"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3, r4, r5, r6, r7, r8, r9}
            java.util.List r2 = java.util.Arrays.asList(r2)
            r1.<init>(r2)
            if (r0 == 0) goto L_0x003f
            java.lang.String r2 = "current_session_count"
            r1.add(r2)
        L_0x003f:
            r18 = 0
            android.database.sqlite.SQLiteDatabase r2 = r26.mo16207c_()     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            java.lang.String r3 = "events"
            r10 = 0
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            java.lang.Object[] r1 = r1.toArray(r4)     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            r4 = r1
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            java.lang.String r5 = "app_id=? and name=?"
            r1 = 2
            java.lang.String[] r6 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            r6[r10] = r15     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            r11 = 1
            r6[r11] = r14     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            boolean r2 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            if (r2 != 0) goto L_0x0070
            if (r12 == 0) goto L_0x006f
            r12.close()
        L_0x006f:
            return r18
        L_0x0070:
            long r4 = r12.getLong(r10)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            long r6 = r12.getLong(r11)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            long r16 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            r1 = 3
            boolean r2 = r12.isNull(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            r8 = 0
            if (r2 == 0) goto L_0x0088
            r19 = r8
            goto L_0x008e
        L_0x0088:
            long r1 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            r19 = r1
        L_0x008e:
            r1 = 4
            boolean r2 = r12.isNull(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            if (r2 == 0) goto L_0x0098
            r21 = r18
            goto L_0x00a2
        L_0x0098:
            long r1 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            r21 = r1
        L_0x00a2:
            r1 = 5
            boolean r2 = r12.isNull(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            if (r2 == 0) goto L_0x00ac
            r22 = r18
            goto L_0x00b6
        L_0x00ac:
            long r1 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            r22 = r1
        L_0x00b6:
            r1 = 6
            boolean r2 = r12.isNull(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            if (r2 == 0) goto L_0x00c0
            r23 = r18
            goto L_0x00ca
        L_0x00c0:
            long r1 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            r23 = r1
        L_0x00ca:
            r1 = 7
            boolean r2 = r12.isNull(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            if (r2 != 0) goto L_0x00e4
            long r1 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            r24 = 1
            int r3 = (r1 > r24 ? 1 : (r1 == r24 ? 0 : -1))
            if (r3 != 0) goto L_0x00dd
            r10 = 1
        L_0x00dd:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r10)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            r24 = r1
            goto L_0x00e6
        L_0x00e4:
            r24 = r18
        L_0x00e6:
            if (r0 == 0) goto L_0x00f6
            r0 = 8
            boolean r1 = r12.isNull(r0)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            if (r1 != 0) goto L_0x00f6
            long r0 = r12.getLong(r0)     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            r8 = r0
        L_0x00f6:
            com.google.android.gms.measurement.internal.zzaj r0 = new com.google.android.gms.measurement.internal.zzaj     // Catch:{ SQLiteException -> 0x0132, all -> 0x012e }
            r1 = r0
            r2 = r27
            r3 = r28
            r10 = r16
            r25 = r12
            r12 = r19
            r14 = r21
            r15 = r22
            r16 = r23
            r17 = r24
            r1.<init>(r2, r3, r4, r6, r8, r10, r12, r14, r15, r16, r17)     // Catch:{ SQLiteException -> 0x012c }
            boolean r1 = r25.moveToNext()     // Catch:{ SQLiteException -> 0x012c }
            if (r1 == 0) goto L_0x0125
            com.google.android.gms.measurement.internal.zzfj r1 = r26.zzr()     // Catch:{ SQLiteException -> 0x012c }
            com.google.android.gms.measurement.internal.zzfl r1 = r1.zzf()     // Catch:{ SQLiteException -> 0x012c }
            java.lang.String r2 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfj.zza(r27)     // Catch:{ SQLiteException -> 0x012c }
            r1.zza(r2, r3)     // Catch:{ SQLiteException -> 0x012c }
        L_0x0125:
            if (r25 == 0) goto L_0x012b
            r25.close()
        L_0x012b:
            return r0
        L_0x012c:
            r0 = move-exception
            goto L_0x013d
        L_0x012e:
            r0 = move-exception
            r25 = r12
            goto L_0x015f
        L_0x0132:
            r0 = move-exception
            r25 = r12
            goto L_0x013d
        L_0x0136:
            r0 = move-exception
            r25 = r18
            goto L_0x015f
        L_0x013a:
            r0 = move-exception
            r25 = r18
        L_0x013d:
            com.google.android.gms.measurement.internal.zzfj r1 = r26.zzr()     // Catch:{ all -> 0x015e }
            com.google.android.gms.measurement.internal.zzfl r1 = r1.zzf()     // Catch:{ all -> 0x015e }
            java.lang.String r2 = "Error querying events. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfj.zza(r27)     // Catch:{ all -> 0x015e }
            com.google.android.gms.measurement.internal.zzfh r4 = r26.zzo()     // Catch:{ all -> 0x015e }
            r5 = r28
            java.lang.String r4 = r4.zza(r5)     // Catch:{ all -> 0x015e }
            r1.zza(r2, r3, r4, r0)     // Catch:{ all -> 0x015e }
            if (r25 == 0) goto L_0x015d
            r25.close()
        L_0x015d:
            return r18
        L_0x015e:
            r0 = move-exception
        L_0x015f:
            if (r25 == 0) goto L_0x0164
            r25.close()
        L_0x0164:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzaj");
    }

    public final void zza(zzaj zzaj) {
        Long l;
        Preconditions.checkNotNull(zzaj);
        zzd();
        zzak();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzaj.zza);
        contentValues.put("name", zzaj.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzaj.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzaj.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzaj.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzaj.zzg));
        contentValues.put("last_bundled_day", zzaj.zzh);
        contentValues.put("last_sampled_complex_event_id", zzaj.zzi);
        contentValues.put("last_sampling_rate", zzaj.zzj);
        if (zzt().zze(zzaj.zza, zzap.zzbn)) {
            contentValues.put("current_session_count", Long.valueOf(zzaj.zze));
        }
        if (zzaj.zzk == null || !zzaj.zzk.booleanValue()) {
            l = null;
        } else {
            l = Long.valueOf(1);
        }
        contentValues.put("last_exempt_from_sampling", l);
        try {
            if (mo16207c_().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update event aggregates (got -1). appId", zzfj.zza(zzaj.zza));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing event aggregates. appId", zzfj.zza(zzaj.zza), e);
        }
    }

    public final void zzb(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzd();
        zzak();
        try {
            int delete = mo16207c_().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
            if (!zzkz.zzb() || !this.zza.zzb().zze(str, zzap.zzcy)) {
                zzr().zzx().zza("Deleted user attribute rows", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            if (!zzkz.zzb() || !this.zza.zzb().zze(str, zzap.zzcy)) {
                zzr().zzf().zza("Error deleting user attribute. appId", zzfj.zza(str), zzo().zzc(str2), e);
                return;
            }
            zzr().zzf().zza("Error deleting user property. appId", zzfj.zza(str), zzo().zzc(str2), e);
        }
    }

    public final boolean zza(zzlb zzlb) {
        Preconditions.checkNotNull(zzlb);
        zzd();
        zzak();
        if (zzc(zzlb.zza, zzlb.zzc) == null) {
            if (zzla.zza(zzlb.zzc)) {
                if (zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzlb.zza}) >= ((long) zzt().zzc(zzlb.zza))) {
                    return false;
                }
            } else {
                String str = "select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'";
                if (zzt().zze(zzlb.zza, zzap.zzbd)) {
                    if (!"_npa".equals(zzlb.zzc)) {
                        if (zzb(str, new String[]{zzlb.zza, zzlb.zzb}) >= 25) {
                            return false;
                        }
                    }
                } else {
                    if (zzb(str, new String[]{zzlb.zza, zzlb.zzb}) >= 25) {
                        return false;
                    }
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzlb.zza);
        contentValues.put("origin", zzlb.zzb);
        contentValues.put("name", zzlb.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzlb.zzd));
        zza(contentValues, "value", zzlb.zze);
        try {
            if (mo16207c_().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update user property (got -1). appId", zzfj.zza(zzlb.zza));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing user property. appId", zzfj.zza(zzlb.zza), e);
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzlb zzc(java.lang.String r19, java.lang.String r20) {
        /*
            r18 = this;
            r8 = r20
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r19)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            r18.zzd()
            r18.zzak()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r18.mo16207c_()     // Catch:{ SQLiteException -> 0x0086, all -> 0x0081 }
            java.lang.String r11 = "user_attributes"
            java.lang.String r0 = "set_timestamp"
            java.lang.String r1 = "value"
            java.lang.String r2 = "origin"
            java.lang.String[] r12 = new java.lang.String[]{r0, r1, r2}     // Catch:{ SQLiteException -> 0x0086, all -> 0x0081 }
            java.lang.String r13 = "app_id=? and name=?"
            r0 = 2
            java.lang.String[] r14 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0086, all -> 0x0081 }
            r1 = 0
            r14[r1] = r19     // Catch:{ SQLiteException -> 0x0086, all -> 0x0081 }
            r2 = 1
            r14[r2] = r8     // Catch:{ SQLiteException -> 0x0086, all -> 0x0081 }
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteException -> 0x0086, all -> 0x0081 }
            boolean r3 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x007d, all -> 0x0079 }
            if (r3 != 0) goto L_0x0041
            if (r10 == 0) goto L_0x0040
            r10.close()
        L_0x0040:
            return r9
        L_0x0041:
            long r5 = r10.getLong(r1)     // Catch:{ SQLiteException -> 0x007d, all -> 0x0079 }
            r11 = r18
            java.lang.Object r7 = r11.zza(r10, r2)     // Catch:{ SQLiteException -> 0x0077 }
            java.lang.String r3 = r10.getString(r0)     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.measurement.internal.zzlb r0 = new com.google.android.gms.measurement.internal.zzlb     // Catch:{ SQLiteException -> 0x0077 }
            r1 = r0
            r2 = r19
            r4 = r20
            r1.<init>(r2, r3, r4, r5, r7)     // Catch:{ SQLiteException -> 0x0077 }
            boolean r1 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0077 }
            if (r1 == 0) goto L_0x0070
            com.google.android.gms.measurement.internal.zzfj r1 = r18.zzr()     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.measurement.internal.zzfl r1 = r1.zzf()     // Catch:{ SQLiteException -> 0x0077 }
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfj.zza(r19)     // Catch:{ SQLiteException -> 0x0077 }
            r1.zza(r2, r3)     // Catch:{ SQLiteException -> 0x0077 }
        L_0x0070:
            if (r10 == 0) goto L_0x0076
            r10.close()
        L_0x0076:
            return r0
        L_0x0077:
            r0 = move-exception
            goto L_0x008a
        L_0x0079:
            r0 = move-exception
            r11 = r18
            goto L_0x00aa
        L_0x007d:
            r0 = move-exception
            r11 = r18
            goto L_0x008a
        L_0x0081:
            r0 = move-exception
            r11 = r18
            r10 = r9
            goto L_0x00aa
        L_0x0086:
            r0 = move-exception
            r11 = r18
            r10 = r9
        L_0x008a:
            com.google.android.gms.measurement.internal.zzfj r1 = r18.zzr()     // Catch:{ all -> 0x00a9 }
            com.google.android.gms.measurement.internal.zzfl r1 = r1.zzf()     // Catch:{ all -> 0x00a9 }
            java.lang.String r2 = "Error querying user property. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfj.zza(r19)     // Catch:{ all -> 0x00a9 }
            com.google.android.gms.measurement.internal.zzfh r4 = r18.zzo()     // Catch:{ all -> 0x00a9 }
            java.lang.String r4 = r4.zzc(r8)     // Catch:{ all -> 0x00a9 }
            r1.zza(r2, r3, r4, r0)     // Catch:{ all -> 0x00a9 }
            if (r10 == 0) goto L_0x00a8
            r10.close()
        L_0x00a8:
            return r9
        L_0x00a9:
            r0 = move-exception
        L_0x00aa:
            if (r10 == 0) goto L_0x00af
            r10.close()
        L_0x00af:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzc(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzlb");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzlb> zza(java.lang.String r14) {
        /*
            r13 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            r13.zzd()
            r13.zzak()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r13.mo16207c_()     // Catch:{ SQLiteException -> 0x008a, all -> 0x0087 }
            java.lang.String r3 = "user_attributes"
            java.lang.String r4 = "name"
            java.lang.String r5 = "origin"
            java.lang.String r6 = "set_timestamp"
            java.lang.String r7 = "value"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6, r7}     // Catch:{ SQLiteException -> 0x008a, all -> 0x0087 }
            java.lang.String r5 = "app_id=?"
            r11 = 1
            java.lang.String[] r6 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x008a, all -> 0x0087 }
            r12 = 0
            r6[r12] = r14     // Catch:{ SQLiteException -> 0x008a, all -> 0x0087 }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "1000"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x008a, all -> 0x0087 }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0085 }
            if (r3 != 0) goto L_0x0041
            if (r2 == 0) goto L_0x0040
            r2.close()
        L_0x0040:
            return r0
        L_0x0041:
            java.lang.String r7 = r2.getString(r12)     // Catch:{ SQLiteException -> 0x0085 }
            java.lang.String r3 = r2.getString(r11)     // Catch:{ SQLiteException -> 0x0085 }
            if (r3 != 0) goto L_0x004f
            java.lang.String r3 = ""
            r6 = r3
            goto L_0x0050
        L_0x004f:
            r6 = r3
        L_0x0050:
            r3 = 2
            long r8 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0085 }
            r3 = 3
            java.lang.Object r10 = r13.zza(r2, r3)     // Catch:{ SQLiteException -> 0x0085 }
            if (r10 != 0) goto L_0x006e
            com.google.android.gms.measurement.internal.zzfj r3 = r13.zzr()     // Catch:{ SQLiteException -> 0x0085 }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()     // Catch:{ SQLiteException -> 0x0085 }
            java.lang.String r4 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfj.zza(r14)     // Catch:{ SQLiteException -> 0x0085 }
            r3.zza(r4, r5)     // Catch:{ SQLiteException -> 0x0085 }
            goto L_0x0078
        L_0x006e:
            com.google.android.gms.measurement.internal.zzlb r3 = new com.google.android.gms.measurement.internal.zzlb     // Catch:{ SQLiteException -> 0x0085 }
            r4 = r3
            r5 = r14
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ SQLiteException -> 0x0085 }
            r0.add(r3)     // Catch:{ SQLiteException -> 0x0085 }
        L_0x0078:
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0085 }
            if (r3 != 0) goto L_0x0041
            if (r2 == 0) goto L_0x0084
            r2.close()
        L_0x0084:
            return r0
        L_0x0085:
            r0 = move-exception
            goto L_0x008c
        L_0x0087:
            r14 = move-exception
            r2 = r1
            goto L_0x00a4
        L_0x008a:
            r0 = move-exception
            r2 = r1
        L_0x008c:
            com.google.android.gms.measurement.internal.zzfj r3 = r13.zzr()     // Catch:{ all -> 0x00a3 }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()     // Catch:{ all -> 0x00a3 }
            java.lang.String r4 = "Error querying user properties. appId"
            java.lang.Object r14 = com.google.android.gms.measurement.internal.zzfj.zza(r14)     // Catch:{ all -> 0x00a3 }
            r3.zza(r4, r14, r0)     // Catch:{ all -> 0x00a3 }
            if (r2 == 0) goto L_0x00a2
            r2.close()
        L_0x00a2:
            return r1
        L_0x00a3:
            r14 = move-exception
        L_0x00a4:
            if (r2 == 0) goto L_0x00a9
            r2.close()
        L_0x00a9:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00fb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00fc, code lost:
        r12 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0103, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0104, code lost:
        r12 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0107, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0108, code lost:
        r12 = r21;
        r11 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0122, code lost:
        r2.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0103 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0010] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x012a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzlb> zza(java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
            r21 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r22)
            r21.zzd()
            r21.zzak()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0107, all -> 0x0103 }
            r3 = 3
            r2.<init>(r3)     // Catch:{ SQLiteException -> 0x0107, all -> 0x0103 }
            r11 = r22
            r2.add(r11)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            java.lang.String r5 = "app_id=?"
            r4.<init>(r5)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            boolean r5 = android.text.TextUtils.isEmpty(r23)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            if (r5 != 0) goto L_0x0033
            r5 = r23
            r2.add(r5)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            java.lang.String r6 = " and origin=?"
            r4.append(r6)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            goto L_0x0035
        L_0x0033:
            r5 = r23
        L_0x0035:
            boolean r6 = android.text.TextUtils.isEmpty(r24)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            if (r6 != 0) goto L_0x004d
            java.lang.String r6 = java.lang.String.valueOf(r24)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            java.lang.String r7 = "*"
            java.lang.String r6 = r6.concat(r7)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            r2.add(r6)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            java.lang.String r6 = " and name glob ?"
            r4.append(r6)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
        L_0x004d:
            int r6 = r2.size()     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            java.lang.Object[] r2 = r2.toArray(r6)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            r16 = r2
            java.lang.String[] r16 = (java.lang.String[]) r16     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            android.database.sqlite.SQLiteDatabase r12 = r21.mo16207c_()     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            java.lang.String r13 = "user_attributes"
            java.lang.String r2 = "name"
            java.lang.String r6 = "set_timestamp"
            java.lang.String r7 = "value"
            java.lang.String r8 = "origin"
            java.lang.String[] r14 = new java.lang.String[]{r2, r6, r7, r8}     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            java.lang.String r15 = r4.toString()     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            r17 = 0
            r18 = 0
            java.lang.String r19 = "rowid"
            java.lang.String r20 = "1001"
            android.database.Cursor r2 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x0103 }
            boolean r4 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f3 }
            if (r4 != 0) goto L_0x008a
            if (r2 == 0) goto L_0x0089
            r2.close()
        L_0x0089:
            return r0
        L_0x008a:
            int r4 = r0.size()     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f3 }
            r6 = 1000(0x3e8, float:1.401E-42)
            if (r4 < r6) goto L_0x00a6
            com.google.android.gms.measurement.internal.zzfj r3 = r21.zzr()     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f3 }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f3 }
            java.lang.String r4 = "Read more than the max allowed user properties, ignoring excess"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f3 }
            r3.zza(r4, r6)     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f3 }
            r12 = r21
            goto L_0x00e5
        L_0x00a6:
            r4 = 0
            java.lang.String r7 = r2.getString(r4)     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f3 }
            r4 = 1
            long r8 = r2.getLong(r4)     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f3 }
            r4 = 2
            r12 = r21
            java.lang.Object r10 = r12.zza(r2, r4)     // Catch:{ SQLiteException -> 0x00f1 }
            java.lang.String r13 = r2.getString(r3)     // Catch:{ SQLiteException -> 0x00f1 }
            if (r10 != 0) goto L_0x00d1
            com.google.android.gms.measurement.internal.zzfj r4 = r21.zzr()     // Catch:{ SQLiteException -> 0x00ee }
            com.google.android.gms.measurement.internal.zzfl r4 = r4.zzf()     // Catch:{ SQLiteException -> 0x00ee }
            java.lang.String r5 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfj.zza(r22)     // Catch:{ SQLiteException -> 0x00ee }
            r14 = r24
            r4.zza(r5, r6, r13, r14)     // Catch:{ SQLiteException -> 0x00ee }
            goto L_0x00df
        L_0x00d1:
            r14 = r24
            com.google.android.gms.measurement.internal.zzlb r15 = new com.google.android.gms.measurement.internal.zzlb     // Catch:{ SQLiteException -> 0x00ee }
            r4 = r15
            r5 = r22
            r6 = r13
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ SQLiteException -> 0x00ee }
            r0.add(r15)     // Catch:{ SQLiteException -> 0x00ee }
        L_0x00df:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00ee }
            if (r4 != 0) goto L_0x00ec
        L_0x00e5:
            if (r2 == 0) goto L_0x00eb
            r2.close()
        L_0x00eb:
            return r0
        L_0x00ec:
            r5 = r13
            goto L_0x008a
        L_0x00ee:
            r0 = move-exception
            r5 = r13
            goto L_0x010f
        L_0x00f1:
            r0 = move-exception
            goto L_0x010f
        L_0x00f3:
            r0 = move-exception
            r12 = r21
            goto L_0x0127
        L_0x00f7:
            r0 = move-exception
            r12 = r21
            goto L_0x010f
        L_0x00fb:
            r0 = move-exception
            r12 = r21
            goto L_0x010e
        L_0x00ff:
            r0 = move-exception
            r12 = r21
            goto L_0x010c
        L_0x0103:
            r0 = move-exception
            r12 = r21
            goto L_0x0128
        L_0x0107:
            r0 = move-exception
            r12 = r21
            r11 = r22
        L_0x010c:
            r5 = r23
        L_0x010e:
            r2 = r1
        L_0x010f:
            com.google.android.gms.measurement.internal.zzfj r3 = r21.zzr()     // Catch:{ all -> 0x0126 }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()     // Catch:{ all -> 0x0126 }
            java.lang.String r4 = "(2)Error querying user properties"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfj.zza(r22)     // Catch:{ all -> 0x0126 }
            r3.zza(r4, r6, r5, r0)     // Catch:{ all -> 0x0126 }
            if (r2 == 0) goto L_0x0125
            r2.close()
        L_0x0125:
            return r1
        L_0x0126:
            r0 = move-exception
        L_0x0127:
            r1 = r2
        L_0x0128:
            if (r1 == 0) goto L_0x012d
            r1.close()
        L_0x012d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public final boolean zza(zzv zzv) {
        Preconditions.checkNotNull(zzv);
        zzd();
        zzak();
        if (zzc(zzv.zza, zzv.zzc.zza) == null) {
            if (zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzv.zza}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzv.zza);
        contentValues.put("origin", zzv.zzb);
        contentValues.put("name", zzv.zzc.zza);
        zza(contentValues, "value", zzv.zzc.zza());
        contentValues.put(ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzv.zze));
        contentValues.put(ConditionalUserProperty.TRIGGER_EVENT_NAME, zzv.zzf);
        contentValues.put(ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzv.zzh));
        zzp();
        contentValues.put("timed_out_event", zzla.zza((Parcelable) zzv.zzg));
        contentValues.put(ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzv.zzd));
        zzp();
        contentValues.put("triggered_event", zzla.zza((Parcelable) zzv.zzi));
        contentValues.put(ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzv.zzc.zzb));
        contentValues.put(ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzv.zzj));
        zzp();
        contentValues.put("expired_event", zzla.zza((Parcelable) zzv.zzk));
        try {
            if (mo16207c_().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update conditional user property (got -1)", zzfj.zza(zzv.zza));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing conditional user property", zzfj.zza(zzv.zza), e);
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x012b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzv zzd(java.lang.String r30, java.lang.String r31) {
        /*
            r29 = this;
            r7 = r31
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r30)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r31)
            r29.zzd()
            r29.zzak()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r29.mo16207c_()     // Catch:{ SQLiteException -> 0x0105, all -> 0x0100 }
            java.lang.String r10 = "conditional_properties"
            java.lang.String r11 = "origin"
            java.lang.String r12 = "value"
            java.lang.String r13 = "active"
            java.lang.String r14 = "trigger_event_name"
            java.lang.String r15 = "trigger_timeout"
            java.lang.String r16 = "timed_out_event"
            java.lang.String r17 = "creation_timestamp"
            java.lang.String r18 = "triggered_event"
            java.lang.String r19 = "triggered_timestamp"
            java.lang.String r20 = "time_to_live"
            java.lang.String r21 = "expired_event"
            java.lang.String[] r11 = new java.lang.String[]{r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21}     // Catch:{ SQLiteException -> 0x0105, all -> 0x0100 }
            java.lang.String r12 = "app_id=? and name=?"
            r0 = 2
            java.lang.String[] r13 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0105, all -> 0x0100 }
            r1 = 0
            r13[r1] = r30     // Catch:{ SQLiteException -> 0x0105, all -> 0x0100 }
            r2 = 1
            r13[r2] = r7     // Catch:{ SQLiteException -> 0x0105, all -> 0x0100 }
            r14 = 0
            r15 = 0
            r16 = 0
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x0105, all -> 0x0100 }
            boolean r3 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f8 }
            if (r3 != 0) goto L_0x0050
            if (r9 == 0) goto L_0x004f
            r9.close()
        L_0x004f:
            return r8
        L_0x0050:
            java.lang.String r16 = r9.getString(r1)     // Catch:{ SQLiteException -> 0x00fc, all -> 0x00f8 }
            r10 = r29
            java.lang.Object r5 = r10.zza(r9, r2)     // Catch:{ SQLiteException -> 0x00f6 }
            int r0 = r9.getInt(r0)     // Catch:{ SQLiteException -> 0x00f6 }
            if (r0 == 0) goto L_0x0063
            r20 = 1
            goto L_0x0065
        L_0x0063:
            r20 = 0
        L_0x0065:
            r0 = 3
            java.lang.String r21 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00f6 }
            r0 = 4
            long r23 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f6 }
            com.google.android.gms.measurement.internal.zzkw r0 = r29.zzg()     // Catch:{ SQLiteException -> 0x00f6 }
            r1 = 5
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f6 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzan> r2 = com.google.android.gms.measurement.internal.zzan.CREATOR     // Catch:{ SQLiteException -> 0x00f6 }
            android.os.Parcelable r0 = r0.zza(r1, r2)     // Catch:{ SQLiteException -> 0x00f6 }
            r22 = r0
            com.google.android.gms.measurement.internal.zzan r22 = (com.google.android.gms.measurement.internal.zzan) r22     // Catch:{ SQLiteException -> 0x00f6 }
            r0 = 6
            long r18 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f6 }
            com.google.android.gms.measurement.internal.zzkw r0 = r29.zzg()     // Catch:{ SQLiteException -> 0x00f6 }
            r1 = 7
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f6 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzan> r2 = com.google.android.gms.measurement.internal.zzan.CREATOR     // Catch:{ SQLiteException -> 0x00f6 }
            android.os.Parcelable r0 = r0.zza(r1, r2)     // Catch:{ SQLiteException -> 0x00f6 }
            r25 = r0
            com.google.android.gms.measurement.internal.zzan r25 = (com.google.android.gms.measurement.internal.zzan) r25     // Catch:{ SQLiteException -> 0x00f6 }
            r0 = 8
            long r3 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f6 }
            r0 = 9
            long r26 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f6 }
            com.google.android.gms.measurement.internal.zzkw r0 = r29.zzg()     // Catch:{ SQLiteException -> 0x00f6 }
            r1 = 10
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f6 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzan> r2 = com.google.android.gms.measurement.internal.zzan.CREATOR     // Catch:{ SQLiteException -> 0x00f6 }
            android.os.Parcelable r0 = r0.zza(r1, r2)     // Catch:{ SQLiteException -> 0x00f6 }
            r28 = r0
            com.google.android.gms.measurement.internal.zzan r28 = (com.google.android.gms.measurement.internal.zzan) r28     // Catch:{ SQLiteException -> 0x00f6 }
            com.google.android.gms.measurement.internal.zzkz r17 = new com.google.android.gms.measurement.internal.zzkz     // Catch:{ SQLiteException -> 0x00f6 }
            r1 = r17
            r2 = r31
            r6 = r16
            r1.<init>(r2, r3, r5, r6)     // Catch:{ SQLiteException -> 0x00f6 }
            com.google.android.gms.measurement.internal.zzv r0 = new com.google.android.gms.measurement.internal.zzv     // Catch:{ SQLiteException -> 0x00f6 }
            r14 = r0
            r15 = r30
            r14.<init>(r15, r16, r17, r18, r20, r21, r22, r23, r25, r26, r28)     // Catch:{ SQLiteException -> 0x00f6 }
            boolean r1 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x00f6 }
            if (r1 == 0) goto L_0x00ef
            com.google.android.gms.measurement.internal.zzfj r1 = r29.zzr()     // Catch:{ SQLiteException -> 0x00f6 }
            com.google.android.gms.measurement.internal.zzfl r1 = r1.zzf()     // Catch:{ SQLiteException -> 0x00f6 }
            java.lang.String r2 = "Got multiple records for conditional property, expected one"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfj.zza(r30)     // Catch:{ SQLiteException -> 0x00f6 }
            com.google.android.gms.measurement.internal.zzfh r4 = r29.zzo()     // Catch:{ SQLiteException -> 0x00f6 }
            java.lang.String r4 = r4.zzc(r7)     // Catch:{ SQLiteException -> 0x00f6 }
            r1.zza(r2, r3, r4)     // Catch:{ SQLiteException -> 0x00f6 }
        L_0x00ef:
            if (r9 == 0) goto L_0x00f5
            r9.close()
        L_0x00f5:
            return r0
        L_0x00f6:
            r0 = move-exception
            goto L_0x0109
        L_0x00f8:
            r0 = move-exception
            r10 = r29
            goto L_0x0129
        L_0x00fc:
            r0 = move-exception
            r10 = r29
            goto L_0x0109
        L_0x0100:
            r0 = move-exception
            r10 = r29
            r9 = r8
            goto L_0x0129
        L_0x0105:
            r0 = move-exception
            r10 = r29
            r9 = r8
        L_0x0109:
            com.google.android.gms.measurement.internal.zzfj r1 = r29.zzr()     // Catch:{ all -> 0x0128 }
            com.google.android.gms.measurement.internal.zzfl r1 = r1.zzf()     // Catch:{ all -> 0x0128 }
            java.lang.String r2 = "Error querying conditional property"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfj.zza(r30)     // Catch:{ all -> 0x0128 }
            com.google.android.gms.measurement.internal.zzfh r4 = r29.zzo()     // Catch:{ all -> 0x0128 }
            java.lang.String r4 = r4.zzc(r7)     // Catch:{ all -> 0x0128 }
            r1.zza(r2, r3, r4, r0)     // Catch:{ all -> 0x0128 }
            if (r9 == 0) goto L_0x0127
            r9.close()
        L_0x0127:
            return r8
        L_0x0128:
            r0 = move-exception
        L_0x0129:
            if (r9 == 0) goto L_0x012e
            r9.close()
        L_0x012e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzd(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzv");
    }

    public final int zze(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzd();
        zzak();
        try {
            return mo16207c_().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error deleting conditional property", zzfj.zza(str), zzo().zzc(str2), e);
            return 0;
        }
    }

    public final List<zzv> zzb(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzd();
        zzak();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zza(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final List<zzv> zza(String str, String[] strArr) {
        zzd();
        zzak();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = mo16207c_().query("conditional_properties", new String[]{"app_id", "origin", "name", "value", ConditionalUserProperty.ACTIVE, ConditionalUserProperty.TRIGGER_EVENT_NAME, ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", ConditionalUserProperty.TRIGGERED_TIMESTAMP, ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, str, strArr, null, null, "rowid", "1001");
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            }
            while (true) {
                if (arrayList.size() < 1000) {
                    boolean z = false;
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    String string3 = cursor.getString(2);
                    Object zza = zza(cursor, 3);
                    if (cursor.getInt(4) != 0) {
                        z = true;
                    }
                    String string4 = cursor.getString(5);
                    long j = cursor.getLong(6);
                    zzan zzan = (zzan) zzg().zza(cursor.getBlob(7), zzan.CREATOR);
                    long j2 = cursor.getLong(8);
                    zzan zzan2 = (zzan) zzg().zza(cursor.getBlob(9), zzan.CREATOR);
                    long j3 = cursor.getLong(10);
                    long j4 = cursor.getLong(11);
                    zzan zzan3 = (zzan) zzg().zza(cursor.getBlob(12), zzan.CREATOR);
                    zzkz zzkz = new zzkz(string3, j3, zza, string2);
                    boolean z2 = z;
                    zzv zzv = r3;
                    zzv zzv2 = new zzv(string, string2, zzkz, j2, z2, string4, zzan, j, zzan2, j4, zzan3);
                    arrayList.add(zzv);
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } else {
                    zzr().zzf().zza("Read more than the max allowed conditional properties, ignoring extra", Integer.valueOf(1000));
                    break;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error querying conditional user property value", e);
            List<zzv> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x011d A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0121 A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x015b A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0174 A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0189 A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x01a5 A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x01a6 A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01b5 A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01ec A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x022a  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0231  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzg zzb(java.lang.String r35) {
        /*
            r34 = this;
            r1 = r35
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r35)
            r34.zzd()
            r34.zzak()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r34.mo16207c_()     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r4 = "apps"
            java.lang.String r5 = "app_instance_id"
            java.lang.String r6 = "gmp_app_id"
            java.lang.String r7 = "resettable_device_id_hash"
            java.lang.String r8 = "last_bundle_index"
            java.lang.String r9 = "last_bundle_start_timestamp"
            java.lang.String r10 = "last_bundle_end_timestamp"
            java.lang.String r11 = "app_version"
            java.lang.String r12 = "app_store"
            java.lang.String r13 = "gmp_version"
            java.lang.String r14 = "dev_cert_hash"
            java.lang.String r15 = "measurement_enabled"
            java.lang.String r16 = "day"
            java.lang.String r17 = "daily_public_events_count"
            java.lang.String r18 = "daily_events_count"
            java.lang.String r19 = "daily_conversions_count"
            java.lang.String r20 = "config_fetched_time"
            java.lang.String r21 = "failed_config_fetch_time"
            java.lang.String r22 = "app_version_int"
            java.lang.String r23 = "firebase_instance_id"
            java.lang.String r24 = "daily_error_events_count"
            java.lang.String r25 = "daily_realtime_events_count"
            java.lang.String r26 = "health_monitor_sample"
            java.lang.String r27 = "android_id"
            java.lang.String r28 = "adid_reporting_enabled"
            java.lang.String r29 = "ssaid_reporting_enabled"
            java.lang.String r30 = "admob_app_id"
            java.lang.String r31 = "dynamite_version"
            java.lang.String r32 = "safelisted_events"
            java.lang.String r33 = "ga_app_id"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33}     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r6 = "app_id=?"
            r0 = 1
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r11 = 0
            r7[r11] = r1     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x020a, all -> 0x0206 }
            if (r4 != 0) goto L_0x006d
            if (r3 == 0) goto L_0x006c
            r3.close()
        L_0x006c:
            return r2
        L_0x006d:
            com.google.android.gms.measurement.internal.zzg r4 = new com.google.android.gms.measurement.internal.zzg     // Catch:{ SQLiteException -> 0x020a, all -> 0x0206 }
            r5 = r34
            com.google.android.gms.measurement.internal.zzks r6 = r5.zza     // Catch:{ SQLiteException -> 0x0204 }
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzs()     // Catch:{ SQLiteException -> 0x0204 }
            r4.<init>(r6, r1)     // Catch:{ SQLiteException -> 0x0204 }
            java.lang.String r6 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zza(r6)     // Catch:{ SQLiteException -> 0x0204 }
            java.lang.String r6 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzb(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 2
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zze(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 3
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzg(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 4
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zza(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 5
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzb(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 6
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzg(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 7
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzh(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 8
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzd(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 9
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zze(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 10
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204 }
            if (r7 != 0) goto L_0x00db
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204 }
            if (r6 == 0) goto L_0x00d9
            goto L_0x00db
        L_0x00d9:
            r6 = 0
            goto L_0x00dc
        L_0x00db:
            r6 = 1
        L_0x00dc:
            r4.zza(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 11
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzj(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 12
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzk(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 13
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzl(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 14
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzm(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 15
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzh(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 16
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzi(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 17
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204 }
            if (r7 == 0) goto L_0x0121
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x0126
        L_0x0121:
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204 }
            long r6 = (long) r6     // Catch:{ SQLiteException -> 0x0204 }
        L_0x0126:
            r4.zzc(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 18
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzf(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 19
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzo(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 20
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzn(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 21
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzi(r6)     // Catch:{ SQLiteException -> 0x0204 }
            com.google.android.gms.measurement.internal.zzx r6 = r34.zzt()     // Catch:{ SQLiteException -> 0x0204 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzap.zzdh     // Catch:{ SQLiteException -> 0x0204 }
            boolean r6 = r6.zza(r7)     // Catch:{ SQLiteException -> 0x0204 }
            r7 = 0
            if (r6 != 0) goto L_0x016c
            r6 = 22
            boolean r9 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204 }
            if (r9 == 0) goto L_0x0165
            r9 = r7
            goto L_0x0169
        L_0x0165:
            long r9 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
        L_0x0169:
            r4.zzp(r9)     // Catch:{ SQLiteException -> 0x0204 }
        L_0x016c:
            r6 = 23
            boolean r9 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204 }
            if (r9 != 0) goto L_0x017d
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204 }
            if (r6 == 0) goto L_0x017b
            goto L_0x017d
        L_0x017b:
            r6 = 0
            goto L_0x017e
        L_0x017d:
            r6 = 1
        L_0x017e:
            r4.zzb(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 24
            boolean r9 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0204 }
            if (r9 != 0) goto L_0x0191
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0204 }
            if (r6 == 0) goto L_0x0190
            goto L_0x0191
        L_0x0190:
            r0 = 0
        L_0x0191:
            r4.zzc(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 25
            java.lang.String r0 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzc(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 26
            boolean r6 = r3.isNull(r0)     // Catch:{ SQLiteException -> 0x0204 }
            if (r6 == 0) goto L_0x01a6
            goto L_0x01aa
        L_0x01a6:
            long r7 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
        L_0x01aa:
            r4.zzf(r7)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 27
            boolean r6 = r3.isNull(r0)     // Catch:{ SQLiteException -> 0x0204 }
            if (r6 != 0) goto L_0x01c8
            java.lang.String r0 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x0204 }
            java.lang.String r6 = ","
            r7 = -1
            java.lang.String[] r0 = r0.split(r6, r7)     // Catch:{ SQLiteException -> 0x0204 }
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zza(r0)     // Catch:{ SQLiteException -> 0x0204 }
        L_0x01c8:
            boolean r0 = com.google.android.gms.internal.measurement.zzll.zzb()     // Catch:{ SQLiteException -> 0x0204 }
            if (r0 == 0) goto L_0x01e3
            com.google.android.gms.measurement.internal.zzx r0 = r34.zzt()     // Catch:{ SQLiteException -> 0x0204 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzap.zzch     // Catch:{ SQLiteException -> 0x0204 }
            boolean r0 = r0.zze(r1, r6)     // Catch:{ SQLiteException -> 0x0204 }
            if (r0 == 0) goto L_0x01e3
            r0 = 28
            java.lang.String r0 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzd(r0)     // Catch:{ SQLiteException -> 0x0204 }
        L_0x01e3:
            r4.zzb()     // Catch:{ SQLiteException -> 0x0204 }
            boolean r0 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0204 }
            if (r0 == 0) goto L_0x01fd
            com.google.android.gms.measurement.internal.zzfj r0 = r34.zzr()     // Catch:{ SQLiteException -> 0x0204 }
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzf()     // Catch:{ SQLiteException -> 0x0204 }
            java.lang.String r6 = "Got multiple records for app, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfj.zza(r35)     // Catch:{ SQLiteException -> 0x0204 }
            r0.zza(r6, r7)     // Catch:{ SQLiteException -> 0x0204 }
        L_0x01fd:
            if (r3 == 0) goto L_0x0203
            r3.close()
        L_0x0203:
            return r4
        L_0x0204:
            r0 = move-exception
            goto L_0x0217
        L_0x0206:
            r0 = move-exception
            r5 = r34
            goto L_0x022f
        L_0x020a:
            r0 = move-exception
            r5 = r34
            goto L_0x0217
        L_0x020e:
            r0 = move-exception
            r5 = r34
            r3 = r2
            goto L_0x022f
        L_0x0213:
            r0 = move-exception
            r5 = r34
            r3 = r2
        L_0x0217:
            com.google.android.gms.measurement.internal.zzfj r4 = r34.zzr()     // Catch:{ all -> 0x022e }
            com.google.android.gms.measurement.internal.zzfl r4 = r4.zzf()     // Catch:{ all -> 0x022e }
            java.lang.String r6 = "Error querying app. appId"
            java.lang.Object r1 = com.google.android.gms.measurement.internal.zzfj.zza(r35)     // Catch:{ all -> 0x022e }
            r4.zza(r6, r1, r0)     // Catch:{ all -> 0x022e }
            if (r3 == 0) goto L_0x022d
            r3.close()
        L_0x022d:
            return r2
        L_0x022e:
            r0 = move-exception
        L_0x022f:
            if (r3 == 0) goto L_0x0234
            r3.close()
        L_0x0234:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzb(java.lang.String):com.google.android.gms.measurement.internal.zzg");
    }

    public final void zza(zzg zzg2) {
        String str = "apps";
        Preconditions.checkNotNull(zzg2);
        zzd();
        zzak();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzg2.zzc());
        contentValues.put("app_instance_id", zzg2.zzd());
        contentValues.put("gmp_app_id", zzg2.zze());
        contentValues.put("resettable_device_id_hash", zzg2.zzh());
        contentValues.put("last_bundle_index", Long.valueOf(zzg2.zzs()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzg2.zzj()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzg2.zzk()));
        contentValues.put("app_version", zzg2.zzl());
        contentValues.put("app_store", zzg2.zzn());
        contentValues.put("gmp_version", Long.valueOf(zzg2.zzo()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzg2.zzp()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzg2.zzr()));
        contentValues.put("day", Long.valueOf(zzg2.zzw()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzg2.zzx()));
        contentValues.put("daily_events_count", Long.valueOf(zzg2.zzy()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzg2.zzz()));
        contentValues.put("config_fetched_time", Long.valueOf(zzg2.zzt()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzg2.zzu()));
        contentValues.put("app_version_int", Long.valueOf(zzg2.zzm()));
        contentValues.put("firebase_instance_id", zzg2.zzi());
        contentValues.put("daily_error_events_count", Long.valueOf(zzg2.zzab()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzg2.zzaa()));
        contentValues.put("health_monitor_sample", zzg2.zzac());
        contentValues.put("android_id", Long.valueOf(zzg2.zzae()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzg2.zzaf()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzg2.zzag()));
        contentValues.put("admob_app_id", zzg2.zzf());
        contentValues.put("dynamite_version", Long.valueOf(zzg2.zzq()));
        if (zzg2.zzai() != null) {
            if (zzg2.zzai().size() == 0) {
                zzr().zzi().zza("Safelisted events should not be an empty list. appId", zzg2.zzc());
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzg2.zzai()));
            }
        }
        if (zzll.zzb() && zzt().zze(zzg2.zzc(), zzap.zzch)) {
            contentValues.put("ga_app_id", zzg2.zzg());
        }
        try {
            SQLiteDatabase c_ = mo16207c_();
            if (((long) c_.update(str, contentValues, "app_id = ?", new String[]{zzg2.zzc()})) == 0 && c_.insertWithOnConflict(str, null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update app (got -1). appId", zzfj.zza(zzg2.zzc()));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing app. appId", zzfj.zza(zzg2.zzc()), e);
        }
    }

    public final long zzc(String str) {
        Preconditions.checkNotEmpty(str);
        zzd();
        zzak();
        try {
            return (long) mo16207c_().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzt().zzb(str, zzap.zzo))))});
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error deleting over the limit events. appId", zzfj.zza(str), e);
            return 0;
        }
    }

    public final zzab zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return zza(j, str, 1, false, false, z3, false, z5);
    }

    public final zzab zza(long j, String str, long j2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        zzd();
        zzak();
        String[] strArr = {str};
        zzab zzab = new zzab();
        Cursor cursor = null;
        try {
            SQLiteDatabase c_ = mo16207c_();
            SQLiteDatabase sQLiteDatabase = c_;
            cursor = sQLiteDatabase.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
            if (!cursor.moveToFirst()) {
                zzr().zzi().zza("Not updating daily counts, app is not known. appId", zzfj.zza(str));
                if (cursor != null) {
                    cursor.close();
                }
                return zzab;
            }
            if (cursor.getLong(0) == j) {
                zzab.zzb = cursor.getLong(1);
                zzab.zza = cursor.getLong(2);
                zzab.zzc = cursor.getLong(3);
                zzab.zzd = cursor.getLong(4);
                zzab.zze = cursor.getLong(5);
            }
            if (z) {
                zzab.zzb += j2;
            }
            if (z2) {
                zzab.zza += j2;
            }
            if (z3) {
                zzab.zzc += j2;
            }
            if (z4) {
                zzab.zzd += j2;
            }
            if (z5) {
                zzab.zze += j2;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("day", Long.valueOf(j));
            contentValues.put("daily_public_events_count", Long.valueOf(zzab.zza));
            contentValues.put("daily_events_count", Long.valueOf(zzab.zzb));
            contentValues.put("daily_conversions_count", Long.valueOf(zzab.zzc));
            contentValues.put("daily_error_events_count", Long.valueOf(zzab.zzd));
            contentValues.put("daily_realtime_events_count", Long.valueOf(zzab.zze));
            c_.update("apps", contentValues, "app_id=?", strArr);
            if (cursor != null) {
                cursor.close();
            }
            return zzab;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error updating daily counts. appId", zzfj.zza(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return zzab;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zzd(java.lang.String r11) {
        /*
            r10 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            r10.zzd()
            r10.zzak()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.mo16207c_()     // Catch:{ SQLiteException -> 0x005a, all -> 0x0057 }
            java.lang.String r2 = "apps"
            java.lang.String r3 = "remote_config"
            java.lang.String[] r3 = new java.lang.String[]{r3}     // Catch:{ SQLiteException -> 0x005a, all -> 0x0057 }
            java.lang.String r4 = "app_id=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x005a, all -> 0x0057 }
            r9 = 0
            r5[r9] = r11     // Catch:{ SQLiteException -> 0x005a, all -> 0x0057 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x005a, all -> 0x0057 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0055 }
            if (r2 != 0) goto L_0x0033
            if (r1 == 0) goto L_0x0032
            r1.close()
        L_0x0032:
            return r0
        L_0x0033:
            byte[] r2 = r1.getBlob(r9)     // Catch:{ SQLiteException -> 0x0055 }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0055 }
            if (r3 == 0) goto L_0x004e
            com.google.android.gms.measurement.internal.zzfj r3 = r10.zzr()     // Catch:{ SQLiteException -> 0x0055 }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()     // Catch:{ SQLiteException -> 0x0055 }
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfj.zza(r11)     // Catch:{ SQLiteException -> 0x0055 }
            r3.zza(r4, r5)     // Catch:{ SQLiteException -> 0x0055 }
        L_0x004e:
            if (r1 == 0) goto L_0x0054
            r1.close()
        L_0x0054:
            return r2
        L_0x0055:
            r2 = move-exception
            goto L_0x005c
        L_0x0057:
            r11 = move-exception
            r1 = r0
            goto L_0x0074
        L_0x005a:
            r2 = move-exception
            r1 = r0
        L_0x005c:
            com.google.android.gms.measurement.internal.zzfj r3 = r10.zzr()     // Catch:{ all -> 0x0073 }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()     // Catch:{ all -> 0x0073 }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzfj.zza(r11)     // Catch:{ all -> 0x0073 }
            r3.zza(r4, r11, r2)     // Catch:{ all -> 0x0073 }
            if (r1 == 0) goto L_0x0072
            r1.close()
        L_0x0072:
            return r0
        L_0x0073:
            r11 = move-exception
        L_0x0074:
            if (r1 == 0) goto L_0x0079
            r1.close()
        L_0x0079:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzd(java.lang.String):byte[]");
    }

    public final boolean zza(zzg zzg2, boolean z) {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzg2);
        Preconditions.checkNotEmpty(zzg2.zzx());
        Preconditions.checkState(zzg2.zzk());
        zzv();
        long currentTimeMillis = zzm().currentTimeMillis();
        if (zzg2.zzl() < currentTimeMillis - zzx.zzk() || zzg2.zzl() > zzx.zzk() + currentTimeMillis) {
            zzr().zzi().zza("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzfj.zza(zzg2.zzx()), Long.valueOf(currentTimeMillis), Long.valueOf(zzg2.zzl()));
        }
        try {
            byte[] zzc2 = zzg().zzc(zzg2.zzbi());
            zzr().zzx().zza("Saving bundle, size", Integer.valueOf(zzc2.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzg2.zzx());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzg2.zzl()));
            contentValues.put("data", zzc2);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzg2.zzaz()) {
                contentValues.put("retry_count", Integer.valueOf(zzg2.zzba()));
            }
            try {
                if (mo16207c_().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzr().zzf().zza("Failed to insert bundle (got -1). appId", zzfj.zza(zzg2.zzx()));
                return false;
            } catch (SQLiteException e) {
                zzr().zzf().zza("Error storing bundle. appId", zzfj.zza(zzg2.zzx()), e);
                return false;
            }
        } catch (IOException e2) {
            zzr().zzf().zza("Data loss. Failed to serialize bundle. appId", zzfj.zza(zzg2.zzx()), e2);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0043  */
    /* renamed from: d_ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String mo16208d_() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.mo16207c_()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x002b, all -> 0x0026 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0024 }
            if (r2 == 0) goto L_0x001d
            r2 = 0
            java.lang.String r1 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0024 }
            if (r0 == 0) goto L_0x001c
            r0.close()
        L_0x001c:
            return r1
        L_0x001d:
            if (r0 == 0) goto L_0x0023
            r0.close()
        L_0x0023:
            return r1
        L_0x0024:
            r2 = move-exception
            goto L_0x002d
        L_0x0026:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0041
        L_0x002b:
            r2 = move-exception
            r0 = r1
        L_0x002d:
            com.google.android.gms.measurement.internal.zzfj r3 = r6.zzr()     // Catch:{ all -> 0x0040 }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()     // Catch:{ all -> 0x0040 }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zza(r4, r2)     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x003f
            r0.close()
        L_0x003f:
            return r1
        L_0x0040:
            r1 = move-exception
        L_0x0041:
            if (r0 == 0) goto L_0x0046
            r0.close()
        L_0x0046:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.mo16208d_():java.lang.String");
    }

    public final boolean zzk() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    public final List<Pair<zzg, Long>> zza(String str, int i, int i2) {
        zzd();
        zzak();
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i2 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            cursor = mo16207c_().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
            if (!cursor.moveToFirst()) {
                List<Pair<zzg, Long>> emptyList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyList;
            }
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            do {
                long j = cursor.getLong(0);
                try {
                    byte[] zzb2 = zzg().zzb(cursor.getBlob(1));
                    if (!arrayList.isEmpty() && zzb2.length + i3 > i2) {
                        break;
                    }
                    try {
                        zza zza = (zza) zzkw.zza(zzg.zzbf(), zzb2);
                        if (!cursor.isNull(2)) {
                            zza.zzi(cursor.getInt(2));
                        }
                        i3 += zzb2.length;
                        arrayList.add(Pair.create((zzg) ((zzfd) zza.zzu()), Long.valueOf(j)));
                    } catch (IOException e) {
                        zzr().zzf().zza("Failed to merge queued bundle. appId", zzfj.zza(str), e);
                    }
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } catch (IOException e2) {
                    zzr().zzf().zza("Failed to unzip queued bundle. appId", zzfj.zza(str), e2);
                }
            } while (i3 <= i2);
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e3) {
            zzr().zzf().zza("Error querying bundles. appId", zzfj.zza(str), e3);
            List<Pair<zzg, Long>> emptyList2 = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzv() {
        zzd();
        zzak();
        if (zzam()) {
            long zza = zzs().zzf.zza();
            long elapsedRealtime = zzm().elapsedRealtime();
            if (Math.abs(elapsedRealtime - zza) > ((Long) zzap.zzx.zza(null)).longValue()) {
                zzs().zzf.zza(elapsedRealtime);
                zzd();
                zzak();
                if (zzam()) {
                    int delete = mo16207c_().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzm().currentTimeMillis()), String.valueOf(zzx.zzk())});
                    if (delete > 0) {
                        zzr().zzx().zza("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(List<Long> list) {
        zzd();
        zzak();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzam()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zzb(sb3.toString(), (String[]) null) > 0) {
                zzr().zzi().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase c_ = mo16207c_();
                StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + 127);
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                c_.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                zzr().zzf().zza("Error incrementing retry count. error", e);
            }
        }
    }

    private final boolean zza(String str, int i, zzb zzb2) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzb2);
        Integer num = null;
        if (TextUtils.isEmpty(zzb2.zzc())) {
            zzfl zzi2 = zzr().zzi();
            Object zza = zzfj.zza(str);
            Integer valueOf = Integer.valueOf(i);
            if (zzb2.zza()) {
                num = Integer.valueOf(zzb2.zzb());
            }
            zzi2.zza("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zza, valueOf, String.valueOf(num));
            return false;
        }
        byte[] zzbi = zzb2.zzbi();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zzb2.zza() ? Integer.valueOf(zzb2.zzb()) : null);
        contentValues.put(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, zzb2.zzc());
        if (zzt().zze(str, zzap.zzbm)) {
            contentValues.put("session_scoped", zzb2.zzj() ? Boolean.valueOf(zzb2.zzk()) : null);
        }
        contentValues.put("data", zzbi);
        try {
            if (mo16207c_().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert event filter (got -1). appId", zzfj.zza(str));
            }
            return true;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing event filter. appId", zzfj.zza(str), e);
            return false;
        }
    }

    private final boolean zza(String str, int i, zze zze2) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zze2);
        Integer num = null;
        if (TextUtils.isEmpty(zze2.zzc())) {
            zzfl zzi2 = zzr().zzi();
            Object zza = zzfj.zza(str);
            Integer valueOf = Integer.valueOf(i);
            if (zze2.zza()) {
                num = Integer.valueOf(zze2.zzb());
            }
            zzi2.zza("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zza, valueOf, String.valueOf(num));
            return false;
        }
        byte[] zzbi = zze2.zzbi();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zze2.zza() ? Integer.valueOf(zze2.zzb()) : null);
        contentValues.put("property_name", zze2.zzc());
        if (zzt().zze(str, zzap.zzbm)) {
            contentValues.put("session_scoped", zze2.zzg() ? Boolean.valueOf(zze2.zzh()) : null);
        }
        contentValues.put("data", zzbi);
        try {
            if (mo16207c_().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert property filter (got -1). appId", zzfj.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing property filter. appId", zzfj.zza(str), e);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzbj.zzb>> zzf(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzak()
            r12.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.mo16207c_()
            r9 = 0
            java.lang.String r2 = "event_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            java.lang.String r4 = "app_id=? AND event_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            r10 = 0
            r5[r10] = r13     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            r11 = 1
            r5[r11] = r14     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0099 }
            if (r1 != 0) goto L_0x0043
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0099 }
            if (r14 == 0) goto L_0x0042
            r14.close()
        L_0x0042:
            return r13
        L_0x0043:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0099 }
            com.google.android.gms.internal.measurement.zzbj$zzb$zza r2 = com.google.android.gms.internal.measurement.zzbj.zzb.zzl()     // Catch:{ IOException -> 0x007a }
            com.google.android.gms.internal.measurement.zzgn r1 = com.google.android.gms.measurement.internal.zzkw.zza(r2, r1)     // Catch:{ IOException -> 0x007a }
            com.google.android.gms.internal.measurement.zzbj$zzb$zza r1 = (com.google.android.gms.internal.measurement.zzbj.zzb.zza) r1     // Catch:{ IOException -> 0x007a }
            com.google.android.gms.internal.measurement.zzgo r1 = r1.zzu()     // Catch:{ IOException -> 0x007a }
            com.google.android.gms.internal.measurement.zzfd r1 = (com.google.android.gms.internal.measurement.zzfd) r1     // Catch:{ IOException -> 0x007a }
            com.google.android.gms.internal.measurement.zzbj$zzb r1 = (com.google.android.gms.internal.measurement.zzbj.zzb) r1     // Catch:{ IOException -> 0x007a }
            int r2 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0099 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0099 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0099 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0099 }
            if (r3 != 0) goto L_0x0076
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0099 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0099 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0099 }
            r0.put(r2, r3)     // Catch:{ SQLiteException -> 0x0099 }
        L_0x0076:
            r3.add(r1)     // Catch:{ SQLiteException -> 0x0099 }
            goto L_0x008d
        L_0x007a:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzfj r2 = r12.zzr()     // Catch:{ SQLiteException -> 0x0099 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()     // Catch:{ SQLiteException -> 0x0099 }
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfj.zza(r13)     // Catch:{ SQLiteException -> 0x0099 }
            r2.zza(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0099 }
        L_0x008d:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0099 }
            if (r1 != 0) goto L_0x0043
            if (r14 == 0) goto L_0x0098
            r14.close()
        L_0x0098:
            return r0
        L_0x0099:
            r0 = move-exception
            goto L_0x00a0
        L_0x009b:
            r13 = move-exception
            r14 = r9
            goto L_0x00b8
        L_0x009e:
            r0 = move-exception
            r14 = r9
        L_0x00a0:
            com.google.android.gms.measurement.internal.zzfj r1 = r12.zzr()     // Catch:{ all -> 0x00b7 }
            com.google.android.gms.measurement.internal.zzfl r1 = r1.zzf()     // Catch:{ all -> 0x00b7 }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzfj.zza(r13)     // Catch:{ all -> 0x00b7 }
            r1.zza(r2, r13, r0)     // Catch:{ all -> 0x00b7 }
            if (r14 == 0) goto L_0x00b6
            r14.close()
        L_0x00b6:
            return r9
        L_0x00b7:
            r13 = move-exception
        L_0x00b8:
            if (r14 == 0) goto L_0x00bd
            r14.close()
        L_0x00bd:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzf(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: 0000 */
    public final Map<Integer, List<zzb>> zze(String str) {
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = mo16207c_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{str}, null, null, null);
            if (!cursor.moveToFirst()) {
                Map<Integer, List<zzb>> emptyMap = Collections.emptyMap();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyMap;
            }
            do {
                try {
                    zzb zzb2 = (zzb) ((zzfd) ((zzb.zza) zzkw.zza(zzb.zzl(), cursor.getBlob(1))).zzu());
                    if (zzb2.zzf()) {
                        int i = cursor.getInt(0);
                        List list = (List) arrayMap.get(Integer.valueOf(i));
                        if (list == null) {
                            list = new ArrayList();
                            arrayMap.put(Integer.valueOf(i), list);
                        }
                        list.add(zzb2);
                    }
                } catch (IOException e) {
                    zzr().zzf().zza("Failed to merge filter. appId", zzfj.zza(str), e);
                }
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayMap;
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Database error querying filters. appId", zzfj.zza(str), e2);
            Map<Integer, List<zzb>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzbj.zze>> zzg(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzak()
            r12.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.mo16207c_()
            r9 = 0
            java.lang.String r2 = "property_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            java.lang.String r4 = "app_id=? AND property_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            r10 = 0
            r5[r10] = r13     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            r11 = 1
            r5[r11] = r14     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0099 }
            if (r1 != 0) goto L_0x0043
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0099 }
            if (r14 == 0) goto L_0x0042
            r14.close()
        L_0x0042:
            return r13
        L_0x0043:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0099 }
            com.google.android.gms.internal.measurement.zzbj$zze$zza r2 = com.google.android.gms.internal.measurement.zzbj.zze.zzi()     // Catch:{ IOException -> 0x007a }
            com.google.android.gms.internal.measurement.zzgn r1 = com.google.android.gms.measurement.internal.zzkw.zza(r2, r1)     // Catch:{ IOException -> 0x007a }
            com.google.android.gms.internal.measurement.zzbj$zze$zza r1 = (com.google.android.gms.internal.measurement.zzbj.zze.zza) r1     // Catch:{ IOException -> 0x007a }
            com.google.android.gms.internal.measurement.zzgo r1 = r1.zzu()     // Catch:{ IOException -> 0x007a }
            com.google.android.gms.internal.measurement.zzfd r1 = (com.google.android.gms.internal.measurement.zzfd) r1     // Catch:{ IOException -> 0x007a }
            com.google.android.gms.internal.measurement.zzbj$zze r1 = (com.google.android.gms.internal.measurement.zzbj.zze) r1     // Catch:{ IOException -> 0x007a }
            int r2 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0099 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0099 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0099 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0099 }
            if (r3 != 0) goto L_0x0076
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0099 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0099 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0099 }
            r0.put(r2, r3)     // Catch:{ SQLiteException -> 0x0099 }
        L_0x0076:
            r3.add(r1)     // Catch:{ SQLiteException -> 0x0099 }
            goto L_0x008d
        L_0x007a:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzfj r2 = r12.zzr()     // Catch:{ SQLiteException -> 0x0099 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()     // Catch:{ SQLiteException -> 0x0099 }
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfj.zza(r13)     // Catch:{ SQLiteException -> 0x0099 }
            r2.zza(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0099 }
        L_0x008d:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0099 }
            if (r1 != 0) goto L_0x0043
            if (r14 == 0) goto L_0x0098
            r14.close()
        L_0x0098:
            return r0
        L_0x0099:
            r0 = move-exception
            goto L_0x00a0
        L_0x009b:
            r13 = move-exception
            r14 = r9
            goto L_0x00b8
        L_0x009e:
            r0 = move-exception
            r14 = r9
        L_0x00a0:
            com.google.android.gms.measurement.internal.zzfj r1 = r12.zzr()     // Catch:{ all -> 0x00b7 }
            com.google.android.gms.measurement.internal.zzfl r1 = r1.zzf()     // Catch:{ all -> 0x00b7 }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzfj.zza(r13)     // Catch:{ all -> 0x00b7 }
            r1.zza(r2, r13, r0)     // Catch:{ all -> 0x00b7 }
            if (r14 == 0) goto L_0x00b6
            r14.close()
        L_0x00b6:
            return r9
        L_0x00b7:
            r13 = move-exception
        L_0x00b8:
            if (r14 == 0) goto L_0x00bd
            r14.close()
        L_0x00bd:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzg(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<java.lang.Integer>> zza(java.lang.String r13, java.util.List<java.lang.String> r14) {
        /*
            r12 = this;
            r12.zzak()
            r12.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            boolean r1 = r14.isEmpty()
            if (r1 == 0) goto L_0x0018
            return r0
        L_0x0018:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "app_id=? AND property_name in ("
            r1.append(r2)
            r2 = 0
            r3 = 0
        L_0x0024:
            int r4 = r14.size()
            if (r3 >= r4) goto L_0x0039
            if (r3 <= 0) goto L_0x0031
            java.lang.String r4 = ","
            r1.append(r4)
        L_0x0031:
            java.lang.String r4 = "?"
            r1.append(r4)
            int r3 = r3 + 1
            goto L_0x0024
        L_0x0039:
            java.lang.String r3 = ")"
            r1.append(r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r14)
            r3.add(r2, r13)
            android.database.sqlite.SQLiteDatabase r4 = r12.mo16207c_()
            r14 = 0
            java.lang.String r5 = "property_filters"
            java.lang.String r6 = "audience_id"
            java.lang.String r7 = "filter_id"
            java.lang.String[] r6 = new java.lang.String[]{r6, r7}     // Catch:{ SQLiteException -> 0x00b0, all -> 0x00ad }
            java.lang.String r7 = r1.toString()     // Catch:{ SQLiteException -> 0x00b0, all -> 0x00ad }
            java.lang.String[] r1 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00b0, all -> 0x00ad }
            java.lang.Object[] r1 = r3.toArray(r1)     // Catch:{ SQLiteException -> 0x00b0, all -> 0x00ad }
            r8 = r1
            java.lang.String[] r8 = (java.lang.String[]) r8     // Catch:{ SQLiteException -> 0x00b0, all -> 0x00ad }
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x00b0, all -> 0x00ad }
            boolean r3 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x00ab }
            if (r3 != 0) goto L_0x0077
            if (r1 == 0) goto L_0x0076
            r1.close()
        L_0x0076:
            return r0
        L_0x0077:
            int r3 = r1.getInt(r2)     // Catch:{ SQLiteException -> 0x00ab }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x00ab }
            java.lang.Object r4 = r0.get(r4)     // Catch:{ SQLiteException -> 0x00ab }
            java.util.List r4 = (java.util.List) r4     // Catch:{ SQLiteException -> 0x00ab }
            if (r4 != 0) goto L_0x0093
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00ab }
            r4.<init>()     // Catch:{ SQLiteException -> 0x00ab }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x00ab }
            r0.put(r3, r4)     // Catch:{ SQLiteException -> 0x00ab }
        L_0x0093:
            r3 = 1
            int r3 = r1.getInt(r3)     // Catch:{ SQLiteException -> 0x00ab }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x00ab }
            r4.add(r3)     // Catch:{ SQLiteException -> 0x00ab }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x00ab }
            if (r3 != 0) goto L_0x0077
            if (r1 == 0) goto L_0x00aa
            r1.close()
        L_0x00aa:
            return r0
        L_0x00ab:
            r0 = move-exception
            goto L_0x00b2
        L_0x00ad:
            r13 = move-exception
            r1 = r14
            goto L_0x00ca
        L_0x00b0:
            r0 = move-exception
            r1 = r14
        L_0x00b2:
            com.google.android.gms.measurement.internal.zzfj r2 = r12.zzr()     // Catch:{ all -> 0x00c9 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()     // Catch:{ all -> 0x00c9 }
            java.lang.String r3 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzfj.zza(r13)     // Catch:{ all -> 0x00c9 }
            r2.zza(r3, r13, r0)     // Catch:{ all -> 0x00c9 }
            if (r1 == 0) goto L_0x00c8
            r1.close()
        L_0x00c8:
            return r14
        L_0x00c9:
            r13 = move-exception
        L_0x00ca:
            if (r1 == 0) goto L_0x00cf
            r1.close()
        L_0x00cf:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(java.lang.String, java.util.List):java.util.Map");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0087  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<java.lang.Integer>> zzf(java.lang.String r8) {
        /*
            r7 = this;
            r7.zzak()
            r7.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r7.mo16207c_()
            r2 = 0
            java.lang.String r3 = "select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x006b, all -> 0x0068 }
            r5 = 0
            r4[r5] = r8     // Catch:{ SQLiteException -> 0x006b, all -> 0x0068 }
            r6 = 1
            r4[r6] = r8     // Catch:{ SQLiteException -> 0x006b, all -> 0x0068 }
            android.database.Cursor r1 = r1.rawQuery(r3, r4)     // Catch:{ SQLiteException -> 0x006b, all -> 0x0068 }
            boolean r3 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0066 }
            if (r3 != 0) goto L_0x0033
            java.util.Map r8 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0066 }
            if (r1 == 0) goto L_0x0032
            r1.close()
        L_0x0032:
            return r8
        L_0x0033:
            int r3 = r1.getInt(r5)     // Catch:{ SQLiteException -> 0x0066 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0066 }
            java.lang.Object r4 = r0.get(r4)     // Catch:{ SQLiteException -> 0x0066 }
            java.util.List r4 = (java.util.List) r4     // Catch:{ SQLiteException -> 0x0066 }
            if (r4 != 0) goto L_0x004f
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0066 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0066 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0066 }
            r0.put(r3, r4)     // Catch:{ SQLiteException -> 0x0066 }
        L_0x004f:
            int r3 = r1.getInt(r6)     // Catch:{ SQLiteException -> 0x0066 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0066 }
            r4.add(r3)     // Catch:{ SQLiteException -> 0x0066 }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0066 }
            if (r3 != 0) goto L_0x0033
            if (r1 == 0) goto L_0x0065
            r1.close()
        L_0x0065:
            return r0
        L_0x0066:
            r0 = move-exception
            goto L_0x006d
        L_0x0068:
            r8 = move-exception
            r1 = r2
            goto L_0x0085
        L_0x006b:
            r0 = move-exception
            r1 = r2
        L_0x006d:
            com.google.android.gms.measurement.internal.zzfj r3 = r7.zzr()     // Catch:{ all -> 0x0084 }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()     // Catch:{ all -> 0x0084 }
            java.lang.String r4 = "Database error querying scoped filters. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzfj.zza(r8)     // Catch:{ all -> 0x0084 }
            r3.zza(r4, r8, r0)     // Catch:{ all -> 0x0084 }
            if (r1 == 0) goto L_0x0083
            r1.close()
        L_0x0083:
            return r2
        L_0x0084:
            r8 = move-exception
        L_0x0085:
            if (r1 == 0) goto L_0x008a
            r1.close()
        L_0x008a:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzf(java.lang.String):java.util.Map");
    }

    private final boolean zzc(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzak();
        zzd();
        SQLiteDatabase c_ = mo16207c_();
        try {
            long zzb2 = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, zzt().zzb(str, zzap.zzae)));
            if (zzb2 <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = (Integer) list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 140);
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return c_.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)}) > 0;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Database error querying filters. appId", zzfj.zza(str), e);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzbr.zzi> zzg(java.lang.String r12) {
        /*
            r11 = this;
            r11.zzak()
            r11.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.mo16207c_()
            r8 = 0
            java.lang.String r1 = "audience_filter_values"
            java.lang.String r2 = "audience_id"
            java.lang.String r3 = "current_results"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3}     // Catch:{ SQLiteException -> 0x0086, all -> 0x0083 }
            java.lang.String r3 = "app_id=?"
            r9 = 1
            java.lang.String[] r4 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x0086, all -> 0x0083 }
            r10 = 0
            r4[r10] = r12     // Catch:{ SQLiteException -> 0x0086, all -> 0x0083 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0086, all -> 0x0083 }
            boolean r1 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0081 }
            if (r1 != 0) goto L_0x0035
            if (r0 == 0) goto L_0x0034
            r0.close()
        L_0x0034:
            return r8
        L_0x0035:
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap     // Catch:{ SQLiteException -> 0x0081 }
            r1.<init>()     // Catch:{ SQLiteException -> 0x0081 }
        L_0x003a:
            int r2 = r0.getInt(r10)     // Catch:{ SQLiteException -> 0x0081 }
            byte[] r3 = r0.getBlob(r9)     // Catch:{ SQLiteException -> 0x0081 }
            com.google.android.gms.internal.measurement.zzbr$zzi$zza r4 = com.google.android.gms.internal.measurement.zzbr.zzi.zzi()     // Catch:{ IOException -> 0x005d }
            com.google.android.gms.internal.measurement.zzgn r3 = com.google.android.gms.measurement.internal.zzkw.zza(r4, r3)     // Catch:{ IOException -> 0x005d }
            com.google.android.gms.internal.measurement.zzbr$zzi$zza r3 = (com.google.android.gms.internal.measurement.zzbr.zzi.zza) r3     // Catch:{ IOException -> 0x005d }
            com.google.android.gms.internal.measurement.zzgo r3 = r3.zzu()     // Catch:{ IOException -> 0x005d }
            com.google.android.gms.internal.measurement.zzfd r3 = (com.google.android.gms.internal.measurement.zzfd) r3     // Catch:{ IOException -> 0x005d }
            com.google.android.gms.internal.measurement.zzbr$zzi r3 = (com.google.android.gms.internal.measurement.zzbr.zzi) r3     // Catch:{ IOException -> 0x005d }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0081 }
            r1.put(r2, r3)     // Catch:{ SQLiteException -> 0x0081 }
            goto L_0x0074
        L_0x005d:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzfj r4 = r11.zzr()     // Catch:{ SQLiteException -> 0x0081 }
            com.google.android.gms.measurement.internal.zzfl r4 = r4.zzf()     // Catch:{ SQLiteException -> 0x0081 }
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfj.zza(r12)     // Catch:{ SQLiteException -> 0x0081 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0081 }
            r4.zza(r5, r6, r2, r3)     // Catch:{ SQLiteException -> 0x0081 }
        L_0x0074:
            boolean r2 = r0.moveToNext()     // Catch:{ SQLiteException -> 0x0081 }
            if (r2 != 0) goto L_0x003a
            if (r0 == 0) goto L_0x0080
            r0.close()
        L_0x0080:
            return r1
        L_0x0081:
            r1 = move-exception
            goto L_0x0088
        L_0x0083:
            r12 = move-exception
            r0 = r8
            goto L_0x00a0
        L_0x0086:
            r1 = move-exception
            r0 = r8
        L_0x0088:
            com.google.android.gms.measurement.internal.zzfj r2 = r11.zzr()     // Catch:{ all -> 0x009f }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()     // Catch:{ all -> 0x009f }
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzfj.zza(r12)     // Catch:{ all -> 0x009f }
            r2.zza(r3, r12, r1)     // Catch:{ all -> 0x009f }
            if (r0 == 0) goto L_0x009e
            r0.close()
        L_0x009e:
            return r8
        L_0x009f:
            r12 = move-exception
        L_0x00a0:
            if (r0 == 0) goto L_0x00a5
            r0.close()
        L_0x00a5:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzg(java.lang.String):java.util.Map");
    }

    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            zzr().zzf().zza("Loaded invalid null value from database");
            return null;
        } else if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        } else {
            if (type == 2) {
                return Double.valueOf(cursor.getDouble(i));
            }
            if (type == 3) {
                return cursor.getString(i);
            }
            if (type != 4) {
                zzr().zzf().zza("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
            }
            zzr().zzf().zza("Loaded invalid blob type value, ignoring it");
            return null;
        }
    }

    public final long zzw() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    /* access modifiers changed from: protected */
    public final long zzh(String str, String str2) {
        long j;
        String str3;
        String str4;
        String str5 = str;
        String str6 = str2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzd();
        zzak();
        SQLiteDatabase c_ = mo16207c_();
        c_.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 32);
            sb.append("select ");
            sb.append(str6);
            sb.append(" from app2 where app_id=?");
            try {
                j = zza(sb.toString(), new String[]{str5}, -1);
                str3 = "app2";
                str4 = "app_id";
                if (j == -1) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(str4, str5);
                    contentValues.put("first_open_count", Integer.valueOf(0));
                    contentValues.put("previous_install_count", Integer.valueOf(0));
                    if (c_.insertWithOnConflict(str3, null, contentValues, 5) == -1) {
                        zzr().zzf().zza("Failed to insert column (got -1). appId", zzfj.zza(str), str6);
                        c_.endTransaction();
                        return -1;
                    }
                    j = 0;
                }
            } catch (SQLiteException e) {
                e = e;
                j = 0;
                try {
                    zzr().zzf().zza("Error inserting column. appId", zzfj.zza(str), str6, e);
                    c_.endTransaction();
                    return j;
                } catch (Throwable th) {
                    th = th;
                    c_.endTransaction();
                    throw th;
                }
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put(str4, str5);
                contentValues2.put(str6, Long.valueOf(1 + j));
                if (((long) c_.update(str3, contentValues2, "app_id = ?", new String[]{str5})) == 0) {
                    zzr().zzf().zza("Failed to update column (got 0). appId", zzfj.zza(str), str6);
                    c_.endTransaction();
                    return -1;
                }
                c_.setTransactionSuccessful();
                c_.endTransaction();
                return j;
            } catch (SQLiteException e2) {
                e = e2;
                zzr().zzf().zza("Error inserting column. appId", zzfj.zza(str), str6, e);
                c_.endTransaction();
                return j;
            }
        } catch (SQLiteException e3) {
            e = e3;
            j = 0;
            zzr().zzf().zza("Error inserting column. appId", zzfj.zza(str), str6, e);
            c_.endTransaction();
            return j;
        } catch (Throwable th2) {
            th = th2;
            c_.endTransaction();
            throw th;
        }
    }

    public final long zzx() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final long zza(zzg zzg2) throws IOException {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzg2);
        Preconditions.checkNotEmpty(zzg2.zzx());
        byte[] zzbi = zzg2.zzbi();
        long zza = zzg().zza(zzbi);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzg2.zzx());
        contentValues.put("metadata_fingerprint", Long.valueOf(zza));
        contentValues.put("metadata", zzbi);
        try {
            mo16207c_().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
            return zza;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing raw event metadata. appId", zzfj.zza(zzg2.zzx()), e);
            throw e;
        }
    }

    public final boolean zzy() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzz() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final long zzh(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(long r5) {
        /*
            r4 = this;
            r4.zzd()
            r4.zzak()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.mo16207c_()     // Catch:{ SQLiteException -> 0x0044, all -> 0x0041 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0044, all -> 0x0041 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0044, all -> 0x0041 }
            r6 = 0
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0044, all -> 0x0041 }
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0044, all -> 0x0041 }
            boolean r1 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x003f }
            if (r1 != 0) goto L_0x0035
            com.google.android.gms.measurement.internal.zzfj r6 = r4.zzr()     // Catch:{ SQLiteException -> 0x003f }
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzx()     // Catch:{ SQLiteException -> 0x003f }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.zza(r1)     // Catch:{ SQLiteException -> 0x003f }
            if (r5 == 0) goto L_0x0034
            r5.close()
        L_0x0034:
            return r0
        L_0x0035:
            java.lang.String r6 = r5.getString(r6)     // Catch:{ SQLiteException -> 0x003f }
            if (r5 == 0) goto L_0x003e
            r5.close()
        L_0x003e:
            return r6
        L_0x003f:
            r6 = move-exception
            goto L_0x0046
        L_0x0041:
            r6 = move-exception
            r5 = r0
            goto L_0x005a
        L_0x0044:
            r6 = move-exception
            r5 = r0
        L_0x0046:
            com.google.android.gms.measurement.internal.zzfj r1 = r4.zzr()     // Catch:{ all -> 0x0059 }
            com.google.android.gms.measurement.internal.zzfl r1 = r1.zzf()     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = "Error selecting expired configs"
            r1.zza(r2, r6)     // Catch:{ all -> 0x0059 }
            if (r5 == 0) goto L_0x0058
            r5.close()
        L_0x0058:
            return r0
        L_0x0059:
            r6 = move-exception
        L_0x005a:
            if (r5 == 0) goto L_0x005f
            r5.close()
        L_0x005f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(long):java.lang.String");
    }

    public final long zzaa() {
        Cursor cursor = null;
        try {
            cursor = mo16207c_().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return -1;
            }
            long j = cursor.getLong(0);
            if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzbr.zzc, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) {
        /*
            r7 = this;
            r7.zzd()
            r7.zzak()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.mo16207c_()     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            r4 = 0
            r3[r4] = r8     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            r6 = 1
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0079 }
            if (r2 != 0) goto L_0x0038
            com.google.android.gms.measurement.internal.zzfj r8 = r7.zzr()     // Catch:{ SQLiteException -> 0x0079 }
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzx()     // Catch:{ SQLiteException -> 0x0079 }
            java.lang.String r9 = "Main event not found"
            r8.zza(r9)     // Catch:{ SQLiteException -> 0x0079 }
            if (r1 == 0) goto L_0x0037
            r1.close()
        L_0x0037:
            return r0
        L_0x0038:
            byte[] r2 = r1.getBlob(r4)     // Catch:{ SQLiteException -> 0x0079 }
            long r3 = r1.getLong(r6)     // Catch:{ SQLiteException -> 0x0079 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0079 }
            com.google.android.gms.internal.measurement.zzbr$zzc$zza r4 = com.google.android.gms.internal.measurement.zzbr.zzc.zzj()     // Catch:{ IOException -> 0x0061 }
            com.google.android.gms.internal.measurement.zzgn r2 = com.google.android.gms.measurement.internal.zzkw.zza(r4, r2)     // Catch:{ IOException -> 0x0061 }
            com.google.android.gms.internal.measurement.zzbr$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzbr.zzc.zza) r2     // Catch:{ IOException -> 0x0061 }
            com.google.android.gms.internal.measurement.zzgo r2 = r2.zzu()     // Catch:{ IOException -> 0x0061 }
            com.google.android.gms.internal.measurement.zzfd r2 = (com.google.android.gms.internal.measurement.zzfd) r2     // Catch:{ IOException -> 0x0061 }
            com.google.android.gms.internal.measurement.zzbr$zzc r2 = (com.google.android.gms.internal.measurement.zzbr.zzc) r2     // Catch:{ IOException -> 0x0061 }
            android.util.Pair r8 = android.util.Pair.create(r2, r3)     // Catch:{ SQLiteException -> 0x0079 }
            if (r1 == 0) goto L_0x0060
            r1.close()
        L_0x0060:
            return r8
        L_0x0061:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzfj r3 = r7.zzr()     // Catch:{ SQLiteException -> 0x0079 }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()     // Catch:{ SQLiteException -> 0x0079 }
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzfj.zza(r8)     // Catch:{ SQLiteException -> 0x0079 }
            r3.zza(r4, r8, r9, r2)     // Catch:{ SQLiteException -> 0x0079 }
            if (r1 == 0) goto L_0x0078
            r1.close()
        L_0x0078:
            return r0
        L_0x0079:
            r8 = move-exception
            goto L_0x0080
        L_0x007b:
            r8 = move-exception
            r1 = r0
            goto L_0x0094
        L_0x007e:
            r8 = move-exception
            r1 = r0
        L_0x0080:
            com.google.android.gms.measurement.internal.zzfj r9 = r7.zzr()     // Catch:{ all -> 0x0093 }
            com.google.android.gms.measurement.internal.zzfl r9 = r9.zzf()     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = "Error selecting main event"
            r9.zza(r2, r8)     // Catch:{ all -> 0x0093 }
            if (r1 == 0) goto L_0x0092
            r1.close()
        L_0x0092:
            return r0
        L_0x0093:
            r8 = move-exception
        L_0x0094:
            if (r1 == 0) goto L_0x0099
            r1.close()
        L_0x0099:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    public final boolean zza(String str, Long l, long j, zzc zzc2) {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzc2);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] zzbi = zzc2.zzbi();
        zzr().zzx().zza("Saving complex main event, appId, data size", zzo().zza(str), Integer.valueOf(zzbi.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", zzbi);
        try {
            if (mo16207c_().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert complex main event (got -1). appId", zzfj.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing complex main event. appId", zzfj.zza(str), e);
            return false;
        }
    }

    public final boolean zza(zzak zzak, long j, boolean z) {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzak);
        Preconditions.checkNotEmpty(zzak.zza);
        zzc.zza zzb2 = zzc.zzj().zzb(zzak.zzd);
        Iterator it = zzak.zze.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            zzbr.zze.zza zza = zzbr.zze.zzk().zza(str);
            zzg().zza(zza, zzak.zze.zza(str));
            zzb2.zza(zza);
        }
        byte[] zzbi = ((zzc) ((zzfd) zzb2.zzu())).zzbi();
        if (!zzkz.zzb() || !zzt().zze(zzak.zza, zzap.zzcy)) {
            zzr().zzx().zza("Saving event, name, data size", zzo().zza(zzak.zzb), Integer.valueOf(zzbi.length));
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzak.zza);
        contentValues.put("name", zzak.zzb);
        contentValues.put("timestamp", Long.valueOf(zzak.zzc));
        contentValues.put("metadata_fingerprint", Long.valueOf(j));
        contentValues.put("data", zzbi);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            if (mo16207c_().insert("raw_events", null, contentValues) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert raw event (got -1). appId", zzfj.zza(zzak.zza));
            return false;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing raw event. appId", zzfj.zza(zzak.zza), e);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(String str, List<zzbj.zza> list) {
        boolean z;
        boolean z2;
        String str2 = str;
        List<zzbj.zza> list2 = list;
        String str3 = "app_id=? and audience_id=?";
        String str4 = "event_filters";
        String str5 = "app_id=?";
        String str6 = "property_filters";
        Preconditions.checkNotNull(list);
        for (int i = 0; i < list.size(); i++) {
            C1741zza zza = (C1741zza) ((zzbj.zza) list2.get(i)).zzbm();
            if (zza.zzb() != 0) {
                C1741zza zza2 = zza;
                for (int i2 = 0; i2 < zza2.zzb(); i2++) {
                    zzb.zza zza3 = (zzb.zza) zza2.zzb(i2).zzbm();
                    zzb.zza zza4 = (zzb.zza) ((zzfd.zzb) zza3.clone());
                    String zzb2 = zzhl.zzb(zza3.zza());
                    if (zzb2 != null) {
                        zza4.zza(zzb2);
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z3 = z2;
                    for (int i3 = 0; i3 < zza3.zzb(); i3++) {
                        zzbj.zzc zza5 = zza3.zza(i3);
                        String zza6 = zzho.zza(zza5.zzh());
                        if (zza6 != null) {
                            zza4.zza(i3, (zzbj.zzc) ((zzfd) ((zzbj.zzc.zza) zza5.zzbm()).zza(zza6).zzu()));
                            z3 = true;
                        }
                    }
                    if (z3) {
                        zza2 = zza2.zza(i2, zza4);
                        list2.set(i, (zzbj.zza) ((zzfd) zza2.zzu()));
                    }
                }
                zza = zza2;
            }
            if (zza.zza() != 0) {
                for (int i4 = 0; i4 < zza.zza(); i4++) {
                    zze zza7 = zza.zza(i4);
                    String zza8 = zzhn.zza(zza7.zzc());
                    if (zza8 != null) {
                        zza = zza.zza(i4, ((zze.zza) zza7.zzbm()).zza(zza8));
                        list2.set(i, (zzbj.zza) ((zzfd) zza.zzu()));
                    }
                }
            }
        }
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase c_ = mo16207c_();
        c_.beginTransaction();
        try {
            zzak();
            zzd();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase c_2 = mo16207c_();
            c_2.delete(str6, str5, new String[]{str2});
            c_2.delete(str4, str5, new String[]{str2});
            for (zzbj.zza zza9 : list) {
                zzak();
                zzd();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zza9);
                if (!zza9.zza()) {
                    zzr().zzi().zza("Audience with no ID. appId", zzfj.zza(str));
                } else {
                    int zzb3 = zza9.zzb();
                    Iterator it = zza9.zze().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            Iterator it2 = zza9.zzc().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    Iterator it3 = zza9.zze().iterator();
                                    while (true) {
                                        if (!it3.hasNext()) {
                                            z = true;
                                            break;
                                        } else if (!zza(str2, zzb3, (zzb) it3.next())) {
                                            z = false;
                                            break;
                                        }
                                    }
                                    if (z) {
                                        Iterator it4 = zza9.zzc().iterator();
                                        while (true) {
                                            if (!it4.hasNext()) {
                                                break;
                                            } else if (!zza(str2, zzb3, (zze) it4.next())) {
                                                z = false;
                                                break;
                                            }
                                        }
                                    }
                                    if (!z) {
                                        zzak();
                                        zzd();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase c_3 = mo16207c_();
                                        c_3.delete(str6, str3, new String[]{str2, String.valueOf(zzb3)});
                                        c_3.delete(str4, str3, new String[]{str2, String.valueOf(zzb3)});
                                    }
                                } else if (!((zze) it2.next()).zza()) {
                                    zzr().zzi().zza("Property filter with no ID. Audience definition ignored. appId, audienceId", zzfj.zza(str), Integer.valueOf(zzb3));
                                    break;
                                }
                            }
                        } else if (!((zzb) it.next()).zza()) {
                            zzr().zzi().zza("Event filter with no ID. Audience definition ignored. appId, audienceId", zzfj.zza(str), Integer.valueOf(zzb3));
                            break;
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzbj.zza zza10 : list) {
                arrayList.add(zza10.zza() ? Integer.valueOf(zza10.zzb()) : null);
            }
            zzc(str2, (List<Integer>) arrayList);
            c_.setTransactionSuccessful();
        } finally {
            c_.endTransaction();
        }
    }

    private final boolean zzam() {
        return zzn().getDatabasePath("google_app_measurement.db").exists();
    }

    static {
        String str = "session_scoped";
        zzg = new String[]{str, "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
        zzh = new String[]{str, "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    }
}
