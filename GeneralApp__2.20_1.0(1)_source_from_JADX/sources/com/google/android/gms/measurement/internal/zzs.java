package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzbr.zzc;
import com.google.android.gms.internal.measurement.zzbr.zzc.zza;
import com.google.android.gms.internal.measurement.zzbr.zze;
import com.google.android.gms.internal.measurement.zzfd;
import com.google.android.gms.internal.measurement.zzkz;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@17.3.0 */
final class zzs {
    private zzc zza;
    private Long zzb;
    private long zzc;
    private final /* synthetic */ zzn zzd;

    private zzs(zzn zzn) {
        this.zzd = zzn;
    }

    /* access modifiers changed from: 0000 */
    public final zzc zza(String str, zzc zzc2) {
        String str2;
        String str3 = str;
        zzc zzc3 = zzc2;
        String zzc4 = zzc2.zzc();
        List zza2 = zzc2.zza();
        String str4 = "_eid";
        Long l = (Long) this.zzd.zzg().zzb(zzc3, str4);
        boolean z = l != null;
        if (z && zzc4.equals("_ep")) {
            str2 = (String) this.zzd.zzg().zzb(zzc3, "_en");
            if (TextUtils.isEmpty(str2)) {
                String str5 = "Extra parameter without an event name. eventId";
                if (!zzkz.zzb() || !this.zzd.zzt().zzd(str3, zzap.zzcy)) {
                    this.zzd.zzr().zzf().zza(str5, l);
                } else {
                    this.zzd.zzr().zzg().zza(str5, l);
                }
                return null;
            }
            if (this.zza == null || this.zzb == null || l.longValue() != this.zzb.longValue()) {
                Pair zza3 = this.zzd.zzi().zza(str3, l);
                if (zza3 == null || zza3.first == null) {
                    String str6 = "Extra parameter without existing main event. eventName, eventId";
                    if (!zzkz.zzb() || !this.zzd.zzt().zzd(str3, zzap.zzcy)) {
                        this.zzd.zzr().zzf().zza(str6, str2, l);
                    } else {
                        this.zzd.zzr().zzg().zza(str6, str2, l);
                    }
                    return null;
                }
                this.zza = (zzc) zza3.first;
                this.zzc = ((Long) zza3.second).longValue();
                this.zzb = (Long) this.zzd.zzg().zzb(this.zza, str4);
            }
            this.zzc--;
            if (this.zzc <= 0) {
                zzac zzi = this.zzd.zzi();
                zzi.zzd();
                zzi.zzr().zzx().zza("Clearing complex main event info. appId", str3);
                try {
                    zzi.mo16207c_().execSQL("delete from main_event_params where app_id=?", new String[]{str3});
                } catch (SQLiteException e) {
                    zzi.zzr().zzf().zza("Error clearing complex main event", e);
                }
            } else {
                this.zzd.zzi().zza(str, l, this.zzc, this.zza);
            }
            List arrayList = new ArrayList();
            for (zze zze : this.zza.zza()) {
                this.zzd.zzg();
                if (zzkw.zza(zzc3, zze.zzb()) == null) {
                    arrayList.add(zze);
                }
            }
            if (!arrayList.isEmpty()) {
                arrayList.addAll(zza2);
                zza2 = arrayList;
            } else {
                String str7 = "No unique parameters in main event. eventName";
                if (!zzkz.zzb() || !this.zzd.zzt().zzd(str3, zzap.zzcy)) {
                    this.zzd.zzr().zzi().zza(str7, str2);
                } else {
                    this.zzd.zzr().zzg().zza(str7, str2);
                }
            }
        } else {
            if (z) {
                this.zzb = l;
                this.zza = zzc3;
                zzkw zzg = this.zzd.zzg();
                Object valueOf = Long.valueOf(0);
                Object zzb2 = zzg.zzb(zzc3, "_epc");
                if (zzb2 == null) {
                    zzb2 = valueOf;
                }
                this.zzc = ((Long) zzb2).longValue();
                if (this.zzc <= 0) {
                    String str8 = "Complex event with zero extra param count. eventName";
                    if (!zzkz.zzb() || !this.zzd.zzt().zzd(str3, zzap.zzcy)) {
                        this.zzd.zzr().zzi().zza(str8, zzc4);
                    } else {
                        this.zzd.zzr().zzg().zza(str8, zzc4);
                    }
                } else {
                    this.zzd.zzi().zza(str, l, this.zzc, zzc2);
                }
            }
            str2 = zzc4;
        }
        return (zzc) ((zzfd) ((zza) zzc2.zzbm()).zza(str2).zzc().zza((Iterable<? extends zze>) zza2).zzu());
    }

    /* synthetic */ zzs(zzn zzn, zzq zzq) {
        this(zzn);
    }
}
