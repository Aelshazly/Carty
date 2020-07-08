package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzbj;
import com.google.android.gms.internal.measurement.zzbo.zza.C1742zza;
import com.google.android.gms.internal.measurement.zzbo.zzb;
import com.google.android.gms.internal.measurement.zzbo.zzb.zza;
import com.google.android.gms.internal.measurement.zzbo.zzc;
import com.google.android.gms.internal.measurement.zzfd;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzju;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.internal.cache.DiskLruCache;

/* compiled from: com.google.android.gms:play-services-measurement@@17.3.0 */
public final class zzgk extends zzkp implements zzz {
    private static int zzb = 65535;
    private static int zzc = 2;
    private final Map<String, Map<String, String>> zzd = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zze = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzf = new ArrayMap();
    private final Map<String, zzb> zzg = new ArrayMap();
    private final Map<String, Map<String, Integer>> zzh = new ArrayMap();
    private final Map<String, String> zzi = new ArrayMap();

    zzgk(zzks zzks) {
        super(zzks);
    }

    private final void zzi(String str) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        if (this.zzg.get(str) == null) {
            byte[] zzd2 = zzi().zzd(str);
            if (zzd2 == null) {
                this.zzd.put(str, null);
                this.zze.put(str, null);
                this.zzf.put(str, null);
                this.zzg.put(str, null);
                this.zzi.put(str, null);
                this.zzh.put(str, null);
                return;
            }
            zza zza = (zza) zza(str, zzd2).zzbm();
            zza(str, zza);
            this.zzd.put(str, zza((zzb) ((zzfd) zza.zzu())));
            this.zzg.put(str, (zzb) ((zzfd) zza.zzu()));
            this.zzi.put(str, null);
        }
    }

    /* access modifiers changed from: protected */
    public final zzb zza(String str) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        zzi(str);
        return (zzb) this.zzg.get(str);
    }

    /* access modifiers changed from: protected */
    public final String zzb(String str) {
        zzd();
        return (String) this.zzi.get(str);
    }

    /* access modifiers changed from: protected */
    public final void zzc(String str) {
        zzd();
        this.zzi.put(str, null);
    }

    /* access modifiers changed from: 0000 */
    public final void zzd(String str) {
        zzd();
        this.zzg.remove(str);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zze(String str) {
        zzd();
        zzb zza = zza(str);
        if (zza == null) {
            return false;
        }
        return zza.zzh();
    }

    public final String zza(String str, String str2) {
        zzd();
        zzi(str);
        Map map = (Map) this.zzd.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    private static Map<String, String> zza(zzb zzb2) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzb2 != null) {
            for (zzc zzc2 : zzb2.zze()) {
                arrayMap.put(zzc2.zza(), zzc2.zzb());
            }
        }
        return arrayMap;
    }

    private final void zza(String str, zza zza) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (zza != null) {
            for (int i = 0; i < zza.zza(); i++) {
                C1742zza zza2 = (C1742zza) zza.zza(i).zzbm();
                if (TextUtils.isEmpty(zza2.zza())) {
                    zzr().zzi().zza("EventConfig contained null event name");
                } else {
                    String zzb2 = zzhl.zzb(zza2.zza());
                    if (!TextUtils.isEmpty(zzb2)) {
                        zza2 = zza2.zza(zzb2);
                        zza.zza(i, zza2);
                    }
                    arrayMap.put(zza2.zza(), Boolean.valueOf(zza2.zzb()));
                    arrayMap2.put(zza2.zza(), Boolean.valueOf(zza2.zzc()));
                    if (zza2.zzd()) {
                        if (zza2.zze() < zzc || zza2.zze() > zzb) {
                            zzr().zzi().zza("Invalid sampling rate. Event name, sample rate", zza2.zza(), Integer.valueOf(zza2.zze()));
                        } else {
                            arrayMap3.put(zza2.zza(), Integer.valueOf(zza2.zze()));
                        }
                    }
                }
            }
        }
        this.zze.put(str, arrayMap);
        this.zzf.put(str, arrayMap2);
        this.zzh.put(str, arrayMap3);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(String str, byte[] bArr, String str2) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        zza zza = (zza) zza(str, bArr).zzbm();
        if (zza == null) {
            return false;
        }
        zza(str, zza);
        this.zzg.put(str, (zzb) ((zzfd) zza.zzu()));
        this.zzi.put(str, str2);
        this.zzd.put(str, zza((zzb) ((zzfd) zza.zzu())));
        zzi().zzb(str, (List<zzbj.zza>) new ArrayList<zzbj.zza>(zza.zzb()));
        try {
            zza.zzc();
            bArr = ((zzb) ((zzfd) zza.zzu())).zzbi();
        } catch (RuntimeException e) {
            zzr().zzi().zza("Unable to serialize reduced-size config. Storing full config instead. appId", zzfj.zza(str), e);
        }
        zzac zzi2 = zzi();
        Preconditions.checkNotEmpty(str);
        zzi2.zzd();
        zzi2.zzak();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (((long) zzi2.mo16207c_().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzi2.zzr().zzf().zza("Failed to update remote config (got 0). appId", zzfj.zza(str));
            }
        } catch (SQLiteException e2) {
            zzi2.zzr().zzf().zza("Error storing remote config. appId", zzfj.zza(str), e2);
        }
        this.zzg.put(str, (zzb) ((zzfd) zza.zzu()));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzb(String str, String str2) {
        zzd();
        zzi(str);
        if (zzg(str) && zzla.zze(str2)) {
            return true;
        }
        if (zzh(str) && zzla.zza(str2)) {
            return true;
        }
        Map map = (Map) this.zze.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzc(String str, String str2) {
        zzd();
        zzi(str);
        if (Event.ECOMMERCE_PURCHASE.equals(str2)) {
            return true;
        }
        if (zzju.zzb() && zzt().zza(zzap.zzde) && (Event.PURCHASE.equals(str2) || Event.REFUND.equals(str2))) {
            return true;
        }
        Map map = (Map) this.zzf.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: 0000 */
    public final int zzd(String str, String str2) {
        zzd();
        zzi(str);
        Map map = (Map) this.zzh.get(str);
        if (map == null) {
            return 1;
        }
        Integer num = (Integer) map.get(str2);
        if (num == null) {
            return 1;
        }
        return num.intValue();
    }

    /* access modifiers changed from: 0000 */
    public final long zzf(String str) {
        String zza = zza(str, "measurement.account.time_zone_offset_minutes");
        if (!TextUtils.isEmpty(zza)) {
            try {
                return Long.parseLong(zza);
            } catch (NumberFormatException e) {
                zzr().zzi().zza("Unable to parse timezone offset. appId", zzfj.zza(str), e);
            }
        }
        return 0;
    }

    private final zzb zza(String str, byte[] bArr) {
        String str2 = "Unable to merge remote config. appId";
        if (bArr == null) {
            return zzb.zzj();
        }
        try {
            zzb zzb2 = (zzb) ((zzfd) ((zza) zzkw.zza(zzb.zzi(), bArr)).zzu());
            zzfl zzx = zzr().zzx();
            String str3 = "Parsed config. version, gmp_app_id";
            String str4 = null;
            Object valueOf = zzb2.zza() ? Long.valueOf(zzb2.zzb()) : null;
            if (zzb2.zzc()) {
                str4 = zzb2.zzd();
            }
            zzx.zza(str3, valueOf, str4);
            return zzb2;
        } catch (zzfo e) {
            zzr().zzi().zza(str2, zzfj.zza(str), e);
            return zzb.zzj();
        } catch (RuntimeException e2) {
            zzr().zzi().zza(str2, zzfj.zza(str), e2);
            return zzb.zzj();
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzg(String str) {
        return DiskLruCache.VERSION_1.equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzh(String str) {
        return DiskLruCache.VERSION_1.equals(zza(str, "measurement.upload.blacklist_public"));
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return false;
    }

    public final /* bridge */ /* synthetic */ zzkw zzg() {
        return super.zzg();
    }

    /* renamed from: e_ */
    public final /* bridge */ /* synthetic */ zzn mo16392e_() {
        return super.mo16392e_();
    }

    public final /* bridge */ /* synthetic */ zzac zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzgk zzj() {
        return super.zzj();
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
