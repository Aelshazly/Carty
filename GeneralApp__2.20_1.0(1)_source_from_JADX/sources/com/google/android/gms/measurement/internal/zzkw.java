package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import bolts.MeasurementEvent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzbj;
import com.google.android.gms.internal.measurement.zzbj.zzb;
import com.google.android.gms.internal.measurement.zzbj.zzd;
import com.google.android.gms.internal.measurement.zzbr;
import com.google.android.gms.internal.measurement.zzbr.zzc;
import com.google.android.gms.internal.measurement.zzbr.zze;
import com.google.android.gms.internal.measurement.zzbr.zzf;
import com.google.android.gms.internal.measurement.zzbr.zzg;
import com.google.android.gms.internal.measurement.zzbr.zzi;
import com.google.android.gms.internal.measurement.zzbr.zzj;
import com.google.android.gms.internal.measurement.zzbr.zzk;
import com.google.android.gms.internal.measurement.zzbr.zzk.zza;
import com.google.android.gms.internal.measurement.zzeq;
import com.google.android.gms.internal.measurement.zzfd;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzgn;
import com.google.android.gms.internal.measurement.zzjo;
import com.google.android.gms.internal.measurement.zzju;
import com.google.android.gms.internal.measurement.zzkz;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* compiled from: com.google.android.gms:play-services-measurement@@17.3.0 */
public final class zzkw extends zzkp {
    zzkw(zzks zzks) {
        super(zzks);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zza().zzb().zzc();
        if (obj instanceof String) {
            zza.zzb((String) obj);
        } else if (obj instanceof Long) {
            zza.zzb(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else {
            zzr().zzf().zza("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zze.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zza().zzb().zzc().zze();
        if (obj instanceof String) {
            zza.zzb((String) obj);
        } else if (obj instanceof Long) {
            zza.zza(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else if (!zzju.zzb() || !zzt().zza(zzap.zzdb) || !(obj instanceof Bundle[])) {
            zzr().zzf().zza("Ignoring invalid (type) event param value", obj);
        } else {
            zza.zza((Iterable<? extends zze>) zza((Bundle[]) obj));
        }
    }

    static zze zza(zzc zzc, String str) {
        for (zze zze : zzc.zza()) {
            if (zze.zzb().equals(str)) {
                return zze;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final Object zzb(zzc zzc, String str) {
        zze zza = zza(zzc, str);
        if (zza != null) {
            if (zza.zzc()) {
                return zza.zzd();
            }
            if (zza.zze()) {
                return Long.valueOf(zza.zzf());
            }
            if (zza.zzg()) {
                return Double.valueOf(zza.zzh());
            }
            if (zzju.zzb() && zzt().zza(zzap.zzdb) && zza.zzj() > 0) {
                List<zze> zzi = zza.zzi();
                ArrayList arrayList = new ArrayList();
                for (zze zze : zzi) {
                    if (zze != null) {
                        Bundle bundle = new Bundle();
                        for (zze zze2 : zze.zzi()) {
                            if (zze2.zzc()) {
                                bundle.putString(zze2.zzb(), zze2.zzd());
                            } else if (zze2.zze()) {
                                bundle.putLong(zze2.zzb(), zze2.zzf());
                            } else if (zze2.zzg()) {
                                bundle.putDouble(zze2.zzb(), zze2.zzh());
                            }
                        }
                        if (!bundle.isEmpty()) {
                            arrayList.add(bundle);
                        }
                    }
                }
                return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzc.zza zza, String str, Object obj) {
        List zza2 = zza.zza();
        int i = 0;
        while (true) {
            if (i >= zza2.size()) {
                i = -1;
                break;
            } else if (str.equals(((zze) zza2.get(i)).zzb())) {
                break;
            } else {
                i++;
            }
        }
        zze.zza zza3 = zze.zzk().zza(str);
        if (obj instanceof Long) {
            zza3.zza(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zza3.zzb((String) obj);
        } else if (obj instanceof Double) {
            zza3.zza(((Double) obj).doubleValue());
        } else if (zzju.zzb() && zzt().zza(zzap.zzdb) && (obj instanceof Bundle[])) {
            zza3.zza((Iterable<? extends zze>) zza((Bundle[]) obj));
        }
        if (i >= 0) {
            zza.zza(i, zza3);
        } else {
            zza.zza(zza3);
        }
    }

    /* access modifiers changed from: 0000 */
    public final String zza(zzf zzf) {
        if (zzf == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        Iterator it = zzf.zza().iterator();
        while (true) {
            String str = "}\n";
            if (it.hasNext()) {
                zzg zzg = (zzg) it.next();
                if (zzg != null) {
                    zza(sb, 1);
                    sb.append("bundle {\n");
                    if (zzg.zza()) {
                        zza(sb, 1, "protocol_version", (Object) Integer.valueOf(zzg.zzb()));
                    }
                    zza(sb, 1, "platform", (Object) zzg.zzq());
                    if (zzg.zzz()) {
                        zza(sb, 1, "gmp_version", (Object) Long.valueOf(zzg.zzaa()));
                    }
                    if (zzg.zzab()) {
                        zza(sb, 1, "uploading_gmp_version", (Object) Long.valueOf(zzg.zzac()));
                    }
                    if (zzg.zzbc()) {
                        zza(sb, 1, "dynamite_version", (Object) Long.valueOf(zzg.zzbd()));
                    }
                    if (zzg.zzau()) {
                        zza(sb, 1, "config_version", (Object) Long.valueOf(zzg.zzav()));
                    }
                    zza(sb, 1, "gmp_app_id", (Object) zzg.zzam());
                    zza(sb, 1, "admob_app_id", (Object) zzg.zzbb());
                    zza(sb, 1, "app_id", (Object) zzg.zzx());
                    zza(sb, 1, "app_version", (Object) zzg.zzy());
                    if (zzg.zzar()) {
                        zza(sb, 1, "app_version_major", (Object) Integer.valueOf(zzg.zzas()));
                    }
                    zza(sb, 1, "firebase_instance_id", (Object) zzg.zzaq());
                    if (zzg.zzah()) {
                        zza(sb, 1, "dev_cert_hash", (Object) Long.valueOf(zzg.zzai()));
                    }
                    zza(sb, 1, "app_store", (Object) zzg.zzw());
                    if (zzg.zzg()) {
                        zza(sb, 1, "upload_timestamp_millis", (Object) Long.valueOf(zzg.zzh()));
                    }
                    if (zzg.zzi()) {
                        zza(sb, 1, "start_timestamp_millis", (Object) Long.valueOf(zzg.zzj()));
                    }
                    if (zzg.zzk()) {
                        zza(sb, 1, "end_timestamp_millis", (Object) Long.valueOf(zzg.zzl()));
                    }
                    if (zzg.zzm()) {
                        zza(sb, 1, "previous_bundle_start_timestamp_millis", (Object) Long.valueOf(zzg.zzn()));
                    }
                    if (zzg.zzo()) {
                        zza(sb, 1, "previous_bundle_end_timestamp_millis", (Object) Long.valueOf(zzg.zzp()));
                    }
                    zza(sb, 1, "app_instance_id", (Object) zzg.zzag());
                    zza(sb, 1, "resettable_device_id", (Object) zzg.zzad());
                    zza(sb, 1, "device_id", (Object) zzg.zzat());
                    zza(sb, 1, "ds_id", (Object) zzg.zzay());
                    if (zzg.zzae()) {
                        zza(sb, 1, "limited_ad_tracking", (Object) Boolean.valueOf(zzg.zzaf()));
                    }
                    zza(sb, 1, "os_version", (Object) zzg.zzr());
                    zza(sb, 1, "device_model", (Object) zzg.zzs());
                    zza(sb, 1, "user_default_language", (Object) zzg.zzt());
                    if (zzg.zzu()) {
                        zza(sb, 1, "time_zone_offset_minutes", (Object) Integer.valueOf(zzg.zzv()));
                    }
                    if (zzg.zzaj()) {
                        zza(sb, 1, "bundle_sequential_index", (Object) Integer.valueOf(zzg.zzak()));
                    }
                    if (zzg.zzan()) {
                        zza(sb, 1, "service_upload", (Object) Boolean.valueOf(zzg.zzao()));
                    }
                    zza(sb, 1, "health_monitor", (Object) zzg.zzal());
                    if (!zzt().zza(zzap.zzdh) && zzg.zzaw() && zzg.zzax() != 0) {
                        zza(sb, 1, "android_id", (Object) Long.valueOf(zzg.zzax()));
                    }
                    if (zzg.zzaz()) {
                        zza(sb, 1, "retry_counter", (Object) Integer.valueOf(zzg.zzba()));
                    }
                    List<zzk> zze = zzg.zze();
                    String str2 = "name";
                    if (zze != null) {
                        for (zzk zzk : zze) {
                            if (zzk != null) {
                                zza(sb, 2);
                                sb.append("user_property {\n");
                                Double d = null;
                                zza(sb, 2, "set_timestamp_millis", zzk.zza() ? Long.valueOf(zzk.zzb()) : null);
                                zza(sb, 2, str2, (Object) zzo().zzc(zzk.zzc()));
                                zza(sb, 2, "string_value", (Object) zzk.zze());
                                zza(sb, 2, "int_value", zzk.zzf() ? Long.valueOf(zzk.zzg()) : null);
                                if (zzk.zzh()) {
                                    d = Double.valueOf(zzk.zzi());
                                }
                                zza(sb, 2, "double_value", (Object) d);
                                zza(sb, 2);
                                sb.append(str);
                            }
                        }
                    }
                    List<zzbr.zza> zzap = zzg.zzap();
                    String zzx = zzg.zzx();
                    if (zzap != null) {
                        for (zzbr.zza zza : zzap) {
                            if (zza != null) {
                                zza(sb, 2);
                                sb.append("audience_membership {\n");
                                if (zza.zza()) {
                                    zza(sb, 2, "audience_id", (Object) Integer.valueOf(zza.zzb()));
                                }
                                if (zza.zzf()) {
                                    zza(sb, 2, "new_audience", (Object) Boolean.valueOf(zza.zzg()));
                                }
                                zza(sb, 2, "current_data", zza.zzc(), zzx);
                                if (!zzkz.zzb() || !zzt().zza(zzap.zzcy) || zza.zzd()) {
                                    zza(sb, 2, "previous_data", zza.zze(), zzx);
                                }
                                zza(sb, 2);
                                sb.append(str);
                            }
                        }
                    }
                    List<zzc> zzc = zzg.zzc();
                    if (zzc != null) {
                        for (zzc zzc2 : zzc) {
                            if (zzc2 != null) {
                                zza(sb, 2);
                                sb.append("event {\n");
                                zza(sb, 2, str2, (Object) zzo().zza(zzc2.zzc()));
                                if (zzc2.zzd()) {
                                    zza(sb, 2, "timestamp_millis", (Object) Long.valueOf(zzc2.zze()));
                                }
                                if (zzc2.zzf()) {
                                    zza(sb, 2, "previous_timestamp_millis", (Object) Long.valueOf(zzc2.zzg()));
                                }
                                if (zzc2.zzh()) {
                                    zza(sb, 2, "count", (Object) Integer.valueOf(zzc2.zzi()));
                                }
                                if (zzc2.zzb() != 0) {
                                    zza(sb, 2, zzc2.zza());
                                }
                                zza(sb, 2);
                                sb.append(str);
                            }
                        }
                    }
                    zza(sb, 1);
                    sb.append(str);
                }
            } else {
                sb.append(str);
                return sb.toString();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final String zza(zzb zzb) {
        if (zzb == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzb.zza()) {
            zza(sb, 0, "filter_id", (Object) Integer.valueOf(zzb.zzb()));
        }
        zza(sb, 0, MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, (Object) zzo().zza(zzb.zzc()));
        String zza = zza(zzb.zzh(), zzb.zzi(), zzb.zzk());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", (Object) zza);
        }
        if (!zzkz.zzb() || !zzt().zza(zzap.zzcy) || zzb.zzf()) {
            zza(sb, 1, "event_count_filter", zzb.zzg());
        }
        if (!zzkz.zzb() || !zzt().zza(zzap.zzcy) || zzb.zze() > 0) {
            sb.append("  filters {\n");
            for (zzbj.zzc zza2 : zzb.zzd()) {
                zza(sb, 2, zza2);
            }
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public final String zza(zzbj.zze zze) {
        if (zze == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zze.zza()) {
            zza(sb, 0, "filter_id", (Object) Integer.valueOf(zze.zzb()));
        }
        zza(sb, 0, "property_name", (Object) zzo().zzc(zze.zzc()));
        String zza = zza(zze.zze(), zze.zzf(), zze.zzh());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", (Object) zza);
        }
        zza(sb, 1, zze.zzd());
        sb.append("}\n");
        return sb.toString();
    }

    private static String zza(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private final void zza(StringBuilder sb, int i, List<zze> list) {
        if (list != null) {
            int i2 = i + 1;
            for (zze zze : list) {
                if (zze != null) {
                    zza(sb, i2);
                    sb.append("param {\n");
                    String str = "double_value";
                    String str2 = "int_value";
                    String str3 = "string_value";
                    String str4 = "name";
                    Double d = null;
                    if (!zzju.zzb() || !zzt().zza(zzap.zzcz)) {
                        zza(sb, i2, str4, (Object) zzo().zzb(zze.zzb()));
                        zza(sb, i2, str3, (Object) zze.zzd());
                        zza(sb, i2, str2, zze.zze() ? Long.valueOf(zze.zzf()) : null);
                        if (zze.zzg()) {
                            d = Double.valueOf(zze.zzh());
                        }
                        zza(sb, i2, str, (Object) d);
                    } else {
                        zza(sb, i2, str4, zze.zza() ? zzo().zzb(zze.zzb()) : null);
                        zza(sb, i2, str3, zze.zzc() ? zze.zzd() : null);
                        zza(sb, i2, str2, zze.zze() ? Long.valueOf(zze.zzf()) : null);
                        if (zze.zzg()) {
                            d = Double.valueOf(zze.zzh());
                        }
                        zza(sb, i2, str, (Object) d);
                        if (zze.zzj() > 0) {
                            zza(sb, i2, zze.zzi());
                        }
                    }
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzi zzi, String str2) {
        if (zzi != null) {
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            String str3 = ", ";
            if (zzi.zzd() != 0) {
                zza(sb, 4);
                sb.append("results: ");
                int i2 = 0;
                for (Long l : zzi.zzc()) {
                    int i3 = i2 + 1;
                    if (i2 != 0) {
                        sb.append(str3);
                    }
                    sb.append(l);
                    i2 = i3;
                }
                sb.append(10);
            }
            if (zzi.zzb() != 0) {
                zza(sb, 4);
                sb.append("status: ");
                int i4 = 0;
                for (Long l2 : zzi.zza()) {
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(str3);
                    }
                    sb.append(l2);
                    i4 = i5;
                }
                sb.append(10);
            }
            String str4 = "}\n";
            if (zzi.zzf() != 0) {
                zza(sb, 4);
                sb.append("dynamic_filter_timestamps: {");
                int i6 = 0;
                for (zzbr.zzb zzb : zzi.zze()) {
                    int i7 = i6 + 1;
                    if (i6 != 0) {
                        sb.append(str3);
                    }
                    sb.append(zzb.zza() ? Integer.valueOf(zzb.zzb()) : null);
                    sb.append(":");
                    sb.append(zzb.zzc() ? Long.valueOf(zzb.zzd()) : null);
                    i6 = i7;
                }
                sb.append(str4);
            }
            if (zzi.zzh() != 0) {
                zza(sb, 4);
                sb.append("sequence_filter_timestamps: {");
                int i8 = 0;
                for (zzj zzj : zzi.zzg()) {
                    int i9 = i8 + 1;
                    if (i8 != 0) {
                        sb.append(str3);
                    }
                    sb.append(zzj.zza() ? Integer.valueOf(zzj.zzb()) : null);
                    sb.append(": [");
                    int i10 = 0;
                    for (Long longValue : zzj.zzc()) {
                        long longValue2 = longValue.longValue();
                        int i11 = i10 + 1;
                        if (i10 != 0) {
                            sb.append(str3);
                        }
                        sb.append(longValue2);
                        i10 = i11;
                    }
                    sb.append("]");
                    i8 = i9;
                }
                sb.append(str4);
            }
            zza(sb, 3);
            sb.append(str4);
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzd zzd) {
        if (zzd != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzd.zza()) {
                zza(sb, i, "comparison_type", (Object) zzd.zzb().name());
            }
            if (zzd.zzc()) {
                zza(sb, i, "match_as_float", (Object) Boolean.valueOf(zzd.zzd()));
            }
            if (!zzkz.zzb() || !zzt().zza(zzap.zzcy) || zzd.zze()) {
                zza(sb, i, "comparison_value", (Object) zzd.zzf());
            }
            if (!zzkz.zzb() || !zzt().zza(zzap.zzcy) || zzd.zzg()) {
                zza(sb, i, "min_comparison_value", (Object) zzd.zzh());
            }
            if (!zzkz.zzb() || !zzt().zza(zzap.zzcy) || zzd.zzi()) {
                zza(sb, i, "max_comparison_value", (Object) zzd.zzj());
            }
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, zzbj.zzc zzc) {
        if (zzc != null) {
            zza(sb, i);
            sb.append("filter {\n");
            if (zzc.zze()) {
                zza(sb, i, "complement", (Object) Boolean.valueOf(zzc.zzf()));
            }
            if (!zzkz.zzb() || !zzt().zza(zzap.zzcy) || zzc.zzg()) {
                zza(sb, i, "param_name", (Object) zzo().zzb(zzc.zzh()));
            }
            String str = "}\n";
            if (!zzkz.zzb() || !zzt().zza(zzap.zzcy) || zzc.zza()) {
                int i2 = i + 1;
                zzbj.zzf zzb = zzc.zzb();
                if (zzb != null) {
                    zza(sb, i2);
                    sb.append("string_filter");
                    sb.append(" {\n");
                    if (zzb.zza()) {
                        zza(sb, i2, "match_type", (Object) zzb.zzb().name());
                    }
                    if (!zzkz.zzb() || !zzt().zza(zzap.zzcy) || zzb.zzc()) {
                        zza(sb, i2, "expression", (Object) zzb.zzd());
                    }
                    if (zzb.zze()) {
                        zza(sb, i2, "case_sensitive", (Object) Boolean.valueOf(zzb.zzf()));
                    }
                    if (zzb.zzh() > 0) {
                        zza(sb, i2 + 1);
                        sb.append("expression_list {\n");
                        for (String str2 : zzb.zzg()) {
                            zza(sb, i2 + 2);
                            sb.append(str2);
                            sb.append("\n");
                        }
                        sb.append(str);
                    }
                    zza(sb, i2);
                    sb.append(str);
                }
            }
            if (!zzkz.zzb() || !zzt().zza(zzap.zzcy) || zzc.zzc()) {
                zza(sb, i + 1, "number_filter", zzc.zzd());
            }
            zza(sb, i);
            sb.append(str);
        }
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    /* access modifiers changed from: 0000 */
    public final <T extends Parcelable> T zza(byte[] bArr, Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            return (Parcelable) creator.createFromParcel(obtain);
        } catch (ParseException e) {
            zzr().zzf().zza("Failed to load parcelable from buffer");
            return null;
        } finally {
            obtain.recycle();
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(zzan zzan, zzm zzm) {
        Preconditions.checkNotNull(zzan);
        Preconditions.checkNotNull(zzm);
        if (!zzjo.zzb() || !zzt().zza(zzap.zzco)) {
            if (TextUtils.isEmpty(zzm.zzb) && TextUtils.isEmpty(zzm.zzr)) {
                zzu();
                return false;
            }
        } else if (TextUtils.isEmpty(zzm.zzb) && TextUtils.isEmpty(zzm.zzr)) {
            return false;
        }
        return true;
    }

    static boolean zza(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static boolean zza(List<Long> list, int i) {
        if (i < (list.size() << 6)) {
            if (((1 << (i % 64)) & ((Long) list.get(i / 64)).longValue()) != 0) {
                return true;
            }
        }
        return false;
    }

    static List<Long> zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public final List<Long> zza(List<Long> list, List<Integer> list2) {
        ArrayList arrayList = new ArrayList(list);
        for (Integer num : list2) {
            if (num.intValue() < 0) {
                zzr().zzi().zza("Ignoring negative bit index to be cleared", num);
            } else {
                int intValue = num.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    zzr().zzi().zza("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & (~(1 << (num.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (size2 >= 0 && ((Long) arrayList.get(size2)).longValue() == 0) {
            size = size2;
            size2--;
        }
        return arrayList.subList(0, size);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzm().currentTimeMillis() - j) > j2;
    }

    /* access modifiers changed from: 0000 */
    public final long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzp().zzd();
        MessageDigest zzi = zzla.zzi();
        if (zzi != null) {
            return zzla.zza(zzi.digest(bArr));
        }
        zzr().zzf().zza("Failed to get MD5");
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final byte[] zzb(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzr().zzf().zza("Failed to ungzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: 0000 */
    public final byte[] zzc(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzr().zzf().zza("Failed to gzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: 0000 */
    public final List<Integer> zzf() {
        Map zza = zzap.zza(this.zza.zzn());
        if (zza == null || zza.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int intValue = ((Integer) zzap.zzao.zza(null)).intValue();
        Iterator it = zza.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry entry = (Entry) it.next();
            if (((String) entry.getKey()).startsWith("measurement.id.")) {
                try {
                    int parseInt = Integer.parseInt((String) entry.getValue());
                    if (parseInt != 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                        if (arrayList.size() >= intValue) {
                            zzr().zzi().zza("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                            break;
                        }
                    } else {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    zzr().zzi().zza("Experiment ID NumberFormatException", e);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    static <Builder extends zzgn> Builder zza(Builder builder, byte[] bArr) throws zzfo {
        zzeq zzb = zzeq.zzb();
        if (zzb != null) {
            return builder.zza(bArr, zzb);
        }
        return builder.zza(bArr);
    }

    static int zza(zzg.zza zza, String str) {
        if (zza == null) {
            return -1;
        }
        for (int i = 0; i < zza.zze(); i++) {
            if (str.equals(zza.zzd(i).zzc())) {
                return i;
            }
        }
        return -1;
    }

    private static List<zze> zza(Bundle[] bundleArr) {
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle : bundleArr) {
            if (bundle != null) {
                zze.zza zzk = zze.zzk();
                for (String str : bundle.keySet()) {
                    zze.zza zza = zze.zzk().zza(str);
                    Object obj = bundle.get(str);
                    if (obj instanceof Long) {
                        zza.zza(((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        zza.zzb((String) obj);
                    } else if (obj instanceof Double) {
                        zza.zza(((Double) obj).doubleValue());
                    }
                    zzk.zza(zza);
                }
                if (zzk.zzd() > 0) {
                    arrayList.add((zze) ((zzfd) zzk.zzu()));
                }
            }
        }
        return arrayList;
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
