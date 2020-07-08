package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.measurement.zzju;
import com.google.android.gms.internal.measurement.zzkb;
import com.google.android.gms.internal.measurement.zzkm;
import com.google.android.gms.internal.measurement.zzky;
import com.google.android.gms.internal.measurement.zzle;
import com.google.android.gms.internal.measurement.zzmv;
import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzhr extends zze {
    protected zzio zza;
    protected boolean zzb = true;
    private zzhq zzc;
    private final Set<zzhp> zzd = new CopyOnWriteArraySet();
    private boolean zze;
    private final AtomicReference<String> zzf = new AtomicReference<>();

    protected zzhr(zzgq zzgq) {
        super(zzgq);
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    public final void zzab() {
        if (zzn().getApplicationContext() instanceof Application) {
            ((Application) zzn().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
        }
    }

    public final Boolean zzac() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzq().zza(atomicReference, 15000, "boolean test flag value", new zzht(this, atomicReference));
    }

    public final String zzad() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzq().zza(atomicReference, 15000, "String test flag value", new zzid(this, atomicReference));
    }

    public final Long zzae() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzq().zza(atomicReference, 15000, "long test flag value", new zzif(this, atomicReference));
    }

    public final Integer zzaf() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzq().zza(atomicReference, 15000, "int test flag value", new zzii(this, atomicReference));
    }

    public final Double zzag() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzq().zza(atomicReference, 15000, "double test flag value", new zzih(this, atomicReference));
    }

    public final void zza(boolean z) {
        zzw();
        zzb();
        zzq().zza((Runnable) new zzik(this, z));
    }

    public final void zzb(boolean z) {
        zzw();
        zzb();
        zzq().zza((Runnable) new zzij(this, z));
    }

    /* access modifiers changed from: private */
    public final void zzd(boolean z) {
        zzd();
        zzb();
        zzw();
        zzr().zzw().zza("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzs().zzb(z);
        zzam();
    }

    /* access modifiers changed from: private */
    public final void zzam() {
        if (zzt().zza(zzap.zzbe)) {
            zzd();
            String zza2 = zzs().zzn.zza();
            if (zza2 != null) {
                if ("unset".equals(zza2)) {
                    zza("app", "_npa", (Object) null, zzm().currentTimeMillis());
                } else {
                    zza("app", "_npa", (Object) Long.valueOf("true".equals(zza2) ? 1 : 0), zzm().currentTimeMillis());
                }
            }
        }
        if (!this.zzx.zzab() || !this.zzb) {
            zzr().zzw().zza("Updating Scion state (FE)");
            zzh().zzac();
            return;
        }
        zzr().zzw().zza("Recording app launch after enabling measurement for the first time (FE)");
        zzai();
        if (zzle.zzb() && zzt().zza(zzap.zzcr)) {
            zzk().zza.zza();
        }
        if (zzkm.zzb() && zzt().zza(zzap.zzcw)) {
            if (!(this.zzx.zzf().zza.zzc().zzi.zza() > 0)) {
                this.zzx.zzf().zza();
            }
        }
    }

    public final void zza(long j) {
        zzb();
        zzq().zza((Runnable) new zzim(this, j));
    }

    public final void zzb(long j) {
        zzb();
        zzq().zza((Runnable) new zzil(this, j));
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        zza(str, str2, bundle, false, true, zzm().currentTimeMillis());
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, zzm().currentTimeMillis());
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(String str, String str2, Bundle bundle) {
        zzb();
        zzd();
        zza(str, str2, zzm().currentTimeMillis(), bundle);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(String str, String str2, long j, Bundle bundle) {
        zzb();
        zzd();
        zza(str, str2, j, bundle, true, this.zzc == null || zzla.zze(str2), false, null);
    }

    /* access modifiers changed from: protected */
    public final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zziv zziv;
        zziv zziv2;
        String str4;
        String str5;
        String str6;
        ArrayList arrayList;
        Bundle bundle2;
        String str7;
        zziv zziv3;
        int i;
        int i2;
        long j2;
        ArrayList arrayList2;
        Bundle bundle3;
        String str8;
        boolean z4;
        Class cls;
        String str9 = str;
        String str10 = str2;
        long j3 = j;
        Bundle bundle4 = bundle;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzd();
        zzw();
        if (!this.zzx.zzab()) {
            zzr().zzw().zza("Event not sent since app measurement is disabled");
            return;
        }
        if (zzt().zza(zzap.zzbl)) {
            List zzah = zzg().zzah();
            if (zzah != null && !zzah.contains(str10)) {
                zzr().zzw().zza("Dropping non-safelisted event. event name, origin", str10, str9);
                return;
            }
        }
        int i3 = 0;
        if (!this.zze) {
            this.zze = true;
            try {
                String str11 = "com.google.android.gms.tagmanager.TagManagerService";
                if (!this.zzx.zzt()) {
                    cls = Class.forName(str11, true, zzn().getClassLoader());
                } else {
                    cls = Class.forName(str11);
                }
                try {
                    cls.getDeclaredMethod("initialize", new Class[]{Context.class}).invoke(null, new Object[]{zzn()});
                } catch (Exception e) {
                    zzr().zzi().zza("Failed to invoke Tag Manager's initialize() method", e);
                }
            } catch (ClassNotFoundException e2) {
                zzr().zzv().zza("Tag Manager is not found and thus will not be used");
            }
        }
        if (zzt().zza(zzap.zzca) && "_cmp".equals(str10)) {
            String str12 = "gclid";
            if (bundle4.containsKey(str12)) {
                zza("auto", "_lgclid", (Object) bundle4.getString(str12), zzm().currentTimeMillis());
            }
        }
        if (z3) {
            zzu();
            if (!"_iap".equals(str10)) {
                zzla zzi = this.zzx.zzi();
                String str13 = NotificationCompat.CATEGORY_EVENT;
                int i4 = 2;
                if (zzi.zza(str13, str10)) {
                    if (!zzi.zza(str13, zzhl.zza, str10)) {
                        i4 = 13;
                    } else if (zzi.zza(str13, 40, str10)) {
                        i4 = 0;
                    }
                }
                if (i4 != 0) {
                    zzr().zzh().zza("Invalid public event name. Event will not be logged (FE)", zzo().zza(str10));
                    this.zzx.zzi();
                    this.zzx.zzi().zza(i4, "_ev", zzla.zza(str10, 40, true), str10 != null ? str2.length() : 0);
                    return;
                }
            }
        }
        zzu();
        zziv zzab = zzi().zzab();
        String str14 = "_sc";
        if (zzab != null && !bundle4.containsKey(str14)) {
            zzab.zzd = true;
        }
        zziy.zza(zzab, bundle4, z && z3);
        boolean equals = "am".equals(str9);
        boolean zze2 = zzla.zze(str2);
        if (z && this.zzc != null && !zze2 && !equals) {
            zzr().zzw().zza("Passing event to registered event handler (FE)", zzo().zza(str10), zzo().zza(bundle4));
            this.zzc.interceptEvent(str, str2, bundle, j);
        } else if (this.zzx.zzah()) {
            int zzb2 = zzp().zzb(str10);
            if (zzb2 != 0) {
                zzr().zzh().zza("Invalid event name. Event will not be logged (FE)", zzo().zza(str10));
                zzp();
                String zza2 = zzla.zza(str10, 40, true);
                if (str10 != null) {
                    i3 = str2.length();
                }
                this.zzx.zzi().zza(str3, zzb2, "_ev", zza2, i3);
                return;
            }
            String str15 = "_sn";
            String str16 = "_o";
            String str17 = "_si";
            List listOf = CollectionUtils.listOf((T[]) new String[]{str16, str15, str14, str17});
            String str18 = str10;
            Bundle zza3 = zzp().zza(str3, str2, bundle, listOf, z3, true);
            if (zza3 == null || !zza3.containsKey(str14) || !zza3.containsKey(str17)) {
                zziv = null;
            } else {
                zziv = new zziv(zza3.getString(str15), zza3.getString(str14), Long.valueOf(zza3.getLong(str17)).longValue());
            }
            if (zziv == null) {
                zziv2 = zzab;
            } else {
                zziv2 = zziv;
            }
            String str19 = "_ae";
            if (zzt().zza(zzap.zzbb)) {
                zzu();
                if (zzi().zzab() != null && str19.equals(str18)) {
                    long zzb3 = zzk().zzb.zzb();
                    if (zzb3 > 0) {
                        zzp().zza(zza3, zzb3);
                    }
                }
            }
            if (zzkb.zzb() && zzt().zza(zzap.zzcq)) {
                String str20 = "_ffr";
                if (!"auto".equals(str9) && "_ssr".equals(str18)) {
                    zzla zzp = zzp();
                    String string = zza3.getString(str20);
                    if (Strings.isEmptyOrWhitespace(string)) {
                        str8 = null;
                    } else {
                        str8 = string.trim();
                    }
                    if (zzla.zzc(str8, zzp.zzs().zzw.zza())) {
                        zzp.zzr().zzw().zza("Not logging duplicate session_start_with_rollout event");
                        z4 = false;
                    } else {
                        zzp.zzs().zzw.zza(str8);
                        z4 = true;
                    }
                    if (!z4) {
                        return;
                    }
                } else if (str19.equals(str18)) {
                    String zza4 = zzp().zzs().zzw.zza();
                    if (!TextUtils.isEmpty(zza4)) {
                        zza3.putString(str20, zza4);
                    }
                }
            }
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(zza3);
            long nextLong = zzp().zzh().nextLong();
            if (!zzt().zza(zzap.zzav)) {
                str4 = str16;
            } else if (zzs().zzq.zza() <= 0) {
                str4 = str16;
            } else if (!zzs().zza(j)) {
                str4 = str16;
            } else if (zzs().zzt.zza()) {
                zzr().zzx().zza("Current session is expired, remove the session number, ID, and engagement time");
                if (zzt().zza(zzap.zzas)) {
                    str4 = str16;
                    zza("auto", "_sid", (Object) null, zzm().currentTimeMillis());
                } else {
                    str4 = str16;
                }
                if (zzt().zza(zzap.zzat)) {
                    zza("auto", "_sno", (Object) null, zzm().currentTimeMillis());
                }
                if (zzmv.zzb() && zzt().zza(zzap.zzbq)) {
                    zza("auto", "_se", (Object) null, zzm().currentTimeMillis());
                }
            } else {
                str4 = str16;
            }
            if (!zzt().zza(zzap.zzau)) {
                long j4 = j;
                str5 = str18;
            } else if (zza3.getLong(Param.EXTEND_SESSION, 0) == 1) {
                zzr().zzx().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                str5 = str18;
                this.zzx.zze().zza.zza(j, true);
            } else {
                long j5 = j;
                str5 = str18;
            }
            String[] strArr = (String[]) zza3.keySet().toArray(new String[zza3.size()]);
            Arrays.sort(strArr);
            if (!zzju.zzb() || !zzt().zza(zzap.zzdc) || !zzt().zza(zzap.zzdb)) {
                int length = strArr.length;
                int i5 = 0;
                int i6 = 0;
                while (i6 < length) {
                    String str21 = strArr[i6];
                    Object obj = zza3.get(str21);
                    zzp();
                    String[] strArr2 = strArr;
                    Bundle[] zza5 = zzla.zza(obj);
                    if (zza5 != null) {
                        i2 = length;
                        zza3.putInt(str21, zza5.length);
                        int i7 = 0;
                        while (i7 < zza5.length) {
                            Bundle bundle5 = zza5[i7];
                            zziy.zza(zziv2, bundle5, true);
                            int i8 = i5;
                            int i9 = i6;
                            long j6 = nextLong;
                            Bundle bundle6 = bundle5;
                            ArrayList arrayList4 = arrayList3;
                            zziv zziv4 = zziv2;
                            String str22 = str19;
                            Bundle bundle7 = zza3;
                            Bundle zza6 = zzp().zza(str3, "_ep", bundle6, listOf, z3, false);
                            zza6.putString("_en", str5);
                            zza6.putLong("_eid", j6);
                            zza6.putString("_gn", str21);
                            zza6.putInt("_ll", zza5.length);
                            zza6.putInt("_i", i7);
                            arrayList4.add(zza6);
                            i7++;
                            str19 = str22;
                            zza3 = bundle7;
                            arrayList3 = arrayList4;
                            i5 = i8;
                            nextLong = j6;
                            i6 = i9;
                            zziv2 = zziv4;
                            String str23 = str;
                            long j7 = j;
                        }
                        zziv3 = zziv2;
                        i = i6;
                        j2 = nextLong;
                        arrayList2 = arrayList3;
                        str7 = str19;
                        bundle3 = zza3;
                        i5 += zza5.length;
                    } else {
                        zziv3 = zziv2;
                        i2 = length;
                        int i10 = i5;
                        i = i6;
                        j2 = nextLong;
                        arrayList2 = arrayList3;
                        str7 = str19;
                        bundle3 = zza3;
                    }
                    i6 = i + 1;
                    strArr = strArr2;
                    str19 = str7;
                    zza3 = bundle3;
                    arrayList3 = arrayList2;
                    nextLong = j2;
                    length = i2;
                    zziv2 = zziv3;
                    String str24 = str;
                    long j8 = j;
                }
                int i11 = i5;
                long j9 = nextLong;
                arrayList = arrayList3;
                str6 = str19;
                Bundle bundle8 = zza3;
                if (i11 != 0) {
                    bundle8.putLong("_eid", j9);
                    bundle8.putInt("_epc", i11);
                }
            } else {
                for (String str25 : strArr) {
                    zzp();
                    Bundle[] zza7 = zzla.zza(zza3.get(str25));
                    if (zza7 != null) {
                        zza3.putParcelableArray(str25, zza7);
                    }
                }
                arrayList = arrayList3;
                str6 = str19;
            }
            int i12 = 0;
            while (i12 < arrayList.size()) {
                Bundle bundle9 = (Bundle) arrayList.get(i12);
                String str26 = i12 != 0 ? "_ep" : str5;
                String str27 = str4;
                bundle9.putString(str27, str);
                if (z2) {
                    bundle2 = zzp().zza(bundle9);
                } else {
                    bundle2 = bundle9;
                }
                if (!zzky.zzb() || !zzt().zza(zzap.zzcx)) {
                    zzr().zzw().zza("Logging event (FE)", zzo().zza(str5), zzo().zza(bundle2));
                }
                ArrayList arrayList5 = arrayList;
                String str28 = str5;
                zzan zzan = new zzan(str26, new zzam(bundle2), str, j);
                zzh().zza(zzan, str3);
                if (!equals) {
                    for (zzhp onEvent : this.zzd) {
                        onEvent.onEvent(str, str2, new Bundle(bundle2), j);
                    }
                }
                i12++;
                str4 = str27;
                arrayList = arrayList5;
                str5 = str28;
            }
            String str29 = str5;
            zzu();
            if (zzi().zzab() != null && str6.equals(str29)) {
                zzk().zza(true, true, zzm().elapsedRealtime());
            }
        }
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        String str3;
        Bundle bundle2;
        boolean z3;
        zzb();
        if (str == null) {
            str3 = "app";
        } else {
            str3 = str;
        }
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            bundle2 = bundle;
        }
        if (z2) {
            if (this.zzc != null && !zzla.zze(str2)) {
                z3 = false;
                zzb(str3, str2, j, bundle2, z2, z3, !z, null);
            }
        }
        z3 = true;
        zzb(str3, str2, j, bundle2, z2, z3, !z, null);
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle zzb2 = zzla.zzb(bundle);
        zzgj zzq = zzq();
        zzhw zzhw = new zzhw(this, str, str2, j, zzb2, z, z2, z3, str3);
        zzq.zza((Runnable) zzhw);
    }

    public final void zza(String str, String str2, Object obj, boolean z) {
        zza(str, str2, obj, z, zzm().currentTimeMillis());
    }

    public final void zza(String str, String str2, Object obj, boolean z, long j) {
        String str3;
        if (str == null) {
            str3 = "app";
        } else {
            str3 = str;
        }
        int i = 6;
        int i2 = 0;
        if (z) {
            i = zzp().zzc(str2);
        } else {
            zzla zzp = zzp();
            String str4 = "user property";
            if (zzp.zza(str4, str2)) {
                if (!zzp.zza(str4, zzhn.zza, str2)) {
                    i = 15;
                } else if (zzp.zza(str4, 24, str2)) {
                    i = 0;
                }
            }
        }
        String str5 = "_ev";
        if (i != 0) {
            zzp();
            String zza2 = zzla.zza(str2, 24, true);
            if (str2 != null) {
                i2 = str2.length();
            }
            this.zzx.zzi().zza(i, str5, zza2, i2);
        } else if (obj != null) {
            int zzb2 = zzp().zzb(str2, obj);
            if (zzb2 != 0) {
                zzp();
                String zza3 = zzla.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i2 = String.valueOf(obj).length();
                }
                this.zzx.zzi().zza(zzb2, str5, zza3, i2);
                return;
            }
            Object zzc2 = zzp().zzc(str2, obj);
            if (zzc2 != null) {
                zza(str3, str2, j, zzc2);
            }
        } else {
            zza(str3, str2, j, (Object) null);
        }
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzgj zzq = zzq();
        zzhv zzhv = new zzhv(this, str, str2, obj, j);
        zzq.zza((Runnable) zzhv);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r9, java.lang.String r10, java.lang.Object r11, long r12) {
        /*
            r8 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            r8.zzd()
            r8.zzb()
            r8.zzw()
            com.google.android.gms.measurement.internal.zzx r0 = r8.zzt()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzap.zzbe
            boolean r0 = r0.zza(r1)
            java.lang.String r1 = "_npa"
            if (r0 == 0) goto L_0x0075
            java.lang.String r0 = "allow_personalized_ads"
            boolean r0 = r0.equals(r10)
            if (r0 == 0) goto L_0x0075
            boolean r0 = r11 instanceof java.lang.String
            if (r0 == 0) goto L_0x0064
            r0 = r11
            java.lang.String r0 = (java.lang.String) r0
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x0064
            java.util.Locale r10 = java.util.Locale.ENGLISH
            java.lang.String r10 = r0.toLowerCase(r10)
            java.lang.String r11 = "false"
            boolean r10 = r11.equals(r10)
            r2 = 1
            if (r10 == 0) goto L_0x0044
            r4 = r2
            goto L_0x0046
        L_0x0044:
            r4 = 0
        L_0x0046:
            java.lang.Long r10 = java.lang.Long.valueOf(r4)
            com.google.android.gms.measurement.internal.zzfv r0 = r8.zzs()
            com.google.android.gms.measurement.internal.zzgc r0 = r0.zzn
            r4 = r10
            java.lang.Long r4 = (java.lang.Long) r4
            long r4 = r4.longValue()
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x005e
            java.lang.String r11 = "true"
        L_0x005e:
            r0.zza(r11)
            r6 = r10
            r3 = r1
            goto L_0x0077
        L_0x0064:
            if (r11 != 0) goto L_0x0075
            com.google.android.gms.measurement.internal.zzfv r10 = r8.zzs()
            com.google.android.gms.measurement.internal.zzgc r10 = r10.zzn
            java.lang.String r0 = "unset"
            r10.zza(r0)
            r6 = r11
            r3 = r1
            goto L_0x0077
        L_0x0075:
            r3 = r10
            r6 = r11
        L_0x0077:
            com.google.android.gms.measurement.internal.zzgq r10 = r8.zzx
            boolean r10 = r10.zzab()
            if (r10 != 0) goto L_0x008d
            com.google.android.gms.measurement.internal.zzfj r9 = r8.zzr()
            com.google.android.gms.measurement.internal.zzfl r9 = r9.zzx()
            java.lang.String r10 = "User property not set since app measurement is disabled"
            r9.zza(r10)
            return
        L_0x008d:
            com.google.android.gms.measurement.internal.zzgq r10 = r8.zzx
            boolean r10 = r10.zzah()
            if (r10 != 0) goto L_0x0096
            return
        L_0x0096:
            com.google.android.gms.measurement.internal.zzkz r10 = new com.google.android.gms.measurement.internal.zzkz
            r2 = r10
            r4 = r12
            r7 = r9
            r2.<init>(r3, r4, r6, r7)
            com.google.android.gms.measurement.internal.zziz r9 = r8.zzh()
            r9.zza(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhr.zza(java.lang.String, java.lang.String, java.lang.Object, long):void");
    }

    public final List<zzkz> zzc(boolean z) {
        zzb();
        zzw();
        zzr().zzx().zza("Getting user properties (FE)");
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        } else if (zzw.zza()) {
            zzr().zzf().zza("Cannot get all user properties from main thread");
            return Collections.emptyList();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            AtomicReference atomicReference2 = atomicReference;
            this.zzx.zzq().zza(atomicReference2, 5000, "get user properties", new zzhy(this, atomicReference, z));
            List<zzkz> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zzr().zzf().zza("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyList();
        }
    }

    public final String zzah() {
        zzb();
        return (String) this.zzf.get();
    }

    public final String zzc(long j) {
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot retrieve app instance id from analytics worker thread");
            return null;
        } else if (zzw.zza()) {
            zzr().zzf().zza("Cannot retrieve app instance id from main thread");
            return null;
        } else {
            long elapsedRealtime = zzm().elapsedRealtime();
            String zze2 = zze(120000);
            long elapsedRealtime2 = zzm().elapsedRealtime() - elapsedRealtime;
            if (zze2 == null && elapsedRealtime2 < 120000) {
                zze2 = zze(120000 - elapsedRealtime2);
            }
            return zze2;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(String str) {
        this.zzf.set(str);
    }

    private final String zze(long j) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            zzq().zza((Runnable) new zzhx(this, atomicReference));
            try {
                atomicReference.wait(j);
            } catch (InterruptedException e) {
                zzr().zzi().zza("Interrupted waiting for app instance id");
                return null;
            }
        }
        return (String) atomicReference.get();
    }

    public final void zzd(long j) {
        zza((String) null);
        zzq().zza((Runnable) new zzia(this, j));
    }

    public final void zzai() {
        zzd();
        zzb();
        zzw();
        if (this.zzx.zzah()) {
            if (zzt().zza(zzap.zzby)) {
                zzx zzt = zzt();
                zzt.zzu();
                Boolean zzd2 = zzt.zzd("google_analytics_deferred_deep_link_enabled");
                if (zzd2 != null && zzd2.booleanValue()) {
                    zzr().zzw().zza("Deferred Deep Link feature enabled.");
                    zzq().zza((Runnable) new zzhu(this));
                }
            }
            zzh().zzae();
            this.zzb = false;
            String zzw = zzs().zzw();
            if (!TextUtils.isEmpty(zzw)) {
                zzl().zzaa();
                if (!zzw.equals(VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzw);
                    zza("auto", "_ou", bundle);
                }
            }
        }
    }

    public final void zza(zzhq zzhq) {
        zzd();
        zzb();
        zzw();
        if (zzhq != null) {
            zzhq zzhq2 = this.zzc;
            if (zzhq != zzhq2) {
                Preconditions.checkState(zzhq2 == null, "EventInterceptor already set.");
            }
        }
        this.zzc = zzhq;
    }

    public final void zza(zzhp zzhp) {
        zzb();
        zzw();
        Preconditions.checkNotNull(zzhp);
        if (!this.zzd.add(zzhp)) {
            zzr().zzi().zza("OnEventListener already registered");
        }
    }

    public final void zzb(zzhp zzhp) {
        zzb();
        zzw();
        Preconditions.checkNotNull(zzhp);
        if (!this.zzd.remove(zzhp)) {
            zzr().zzi().zza("OnEventListener had not been registered");
        }
    }

    public final void zza(Bundle bundle) {
        zza(bundle, zzm().currentTimeMillis());
    }

    public final void zza(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzb();
        Bundle bundle2 = new Bundle(bundle);
        String str = "app_id";
        if (!TextUtils.isEmpty(bundle2.getString(str))) {
            zzr().zzi().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove(str);
        zzb(bundle2, j);
    }

    public final void zzb(Bundle bundle) {
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString("app_id"));
        zza();
        zzb(new Bundle(bundle), zzm().currentTimeMillis());
    }

    private final void zzb(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzhm.zza(bundle, "app_id", String.class, null);
        String str = "origin";
        zzhm.zza(bundle, str, String.class, null);
        String str2 = "name";
        zzhm.zza(bundle, str2, String.class, null);
        String str3 = "value";
        zzhm.zza(bundle, str3, Object.class, null);
        String str4 = ConditionalUserProperty.TRIGGER_EVENT_NAME;
        zzhm.zza(bundle, str4, String.class, null);
        Long valueOf = Long.valueOf(0);
        String str5 = ConditionalUserProperty.TRIGGER_TIMEOUT;
        zzhm.zza(bundle, str5, Long.class, valueOf);
        zzhm.zza(bundle, ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzhm.zza(bundle, ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzhm.zza(bundle, ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzhm.zza(bundle, ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        String str6 = ConditionalUserProperty.TIME_TO_LIVE;
        zzhm.zza(bundle, str6, Long.class, valueOf);
        zzhm.zza(bundle, ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzhm.zza(bundle, ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle.getString(str2));
        Preconditions.checkNotEmpty(bundle.getString(str));
        Preconditions.checkNotNull(bundle.get(str3));
        bundle.putLong(ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle.getString(str2);
        Object obj = bundle.get(str3);
        if (zzp().zzc(string) != 0) {
            zzr().zzf().zza("Invalid conditional user property name", zzo().zzc(string));
        } else if (zzp().zzb(string, obj) != 0) {
            zzr().zzf().zza("Invalid conditional user property value", zzo().zzc(string), obj);
        } else {
            Object zzc2 = zzp().zzc(string, obj);
            if (zzc2 == null) {
                zzr().zzf().zza("Unable to normalize conditional user property value", zzo().zzc(string), obj);
                return;
            }
            zzhm.zza(bundle, zzc2);
            long j2 = bundle.getLong(str5);
            if (TextUtils.isEmpty(bundle.getString(str4)) || (j2 <= 15552000000L && j2 >= 1)) {
                long j3 = bundle.getLong(str6);
                if (j3 > 15552000000L || j3 < 1) {
                    zzr().zzf().zza("Invalid conditional user property time to live", zzo().zzc(string), Long.valueOf(j3));
                    return;
                }
                zzq().zza((Runnable) new zzic(this, bundle));
                return;
            }
            zzr().zzf().zza("Invalid conditional user property timeout", zzo().zzc(string), Long.valueOf(j2));
        }
    }

    public final void zzc(String str, String str2, Bundle bundle) {
        zzb();
        zzb((String) null, str, str2, bundle);
    }

    public final void zza(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zza();
        zzb(str, str2, str3, bundle);
    }

    private final void zzb(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzm().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        Bundle bundle2 = new Bundle();
        if (str != null) {
            bundle2.putString("app_id", str);
        }
        bundle2.putString("name", str2);
        bundle2.putLong(ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str3 != null) {
            bundle2.putString(ConditionalUserProperty.EXPIRED_EVENT_NAME, str3);
            bundle2.putBundle(ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzq().zza((Runnable) new zzib(this, bundle2));
    }

    /* access modifiers changed from: private */
    public final void zzc(Bundle bundle) {
        Bundle bundle2 = bundle;
        String str = "app_id";
        zzd();
        zzw();
        Preconditions.checkNotNull(bundle);
        String str2 = "name";
        Preconditions.checkNotEmpty(bundle2.getString(str2));
        String str3 = "origin";
        Preconditions.checkNotEmpty(bundle2.getString(str3));
        String str4 = "value";
        Preconditions.checkNotNull(bundle2.get(str4));
        if (!this.zzx.zzab()) {
            zzr().zzx().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        zzkz zzkz = new zzkz(bundle2.getString(str2), bundle2.getLong(ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle2.get(str4), bundle2.getString(str3));
        try {
            zzan zza2 = zzp().zza(bundle2.getString(str), bundle2.getString(ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle2.getBundle(ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), bundle2.getString(str3), 0, true, false);
            zzv zzv = new zzv(bundle2.getString(str), bundle2.getString(str3), zzkz, bundle2.getLong(ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle2.getString(ConditionalUserProperty.TRIGGER_EVENT_NAME), zzp().zza(bundle2.getString(str), bundle2.getString(ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle2.getBundle(ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), bundle2.getString(str3), 0, true, false), bundle2.getLong(ConditionalUserProperty.TRIGGER_TIMEOUT), zza2, bundle2.getLong(ConditionalUserProperty.TIME_TO_LIVE), zzp().zza(bundle2.getString(str), bundle2.getString(ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle2.getBundle(ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle2.getString(str3), 0, true, false));
            zzh().zza(zzv);
        } catch (IllegalArgumentException e) {
        }
    }

    /* access modifiers changed from: private */
    public final void zzd(Bundle bundle) {
        Bundle bundle2 = bundle;
        String str = ConditionalUserProperty.CREATION_TIMESTAMP;
        String str2 = "origin";
        String str3 = "app_id";
        zzd();
        zzw();
        Preconditions.checkNotNull(bundle);
        String str4 = "name";
        Preconditions.checkNotEmpty(bundle2.getString(str4));
        if (!this.zzx.zzab()) {
            zzr().zzx().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        zzkz zzkz = new zzkz(bundle2.getString(str4), 0, null, null);
        try {
            zzan zza2 = zzp().zza(bundle2.getString(str3), bundle2.getString(ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle2.getBundle(ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle2.getString(str2), bundle2.getLong(str), true, false);
            zzkz zzkz2 = zzkz;
            zzv zzv = new zzv(bundle2.getString(str3), bundle2.getString(str2), zzkz2, bundle2.getLong(str), bundle2.getBoolean(ConditionalUserProperty.ACTIVE), bundle2.getString(ConditionalUserProperty.TRIGGER_EVENT_NAME), null, bundle2.getLong(ConditionalUserProperty.TRIGGER_TIMEOUT), null, bundle2.getLong(ConditionalUserProperty.TIME_TO_LIVE), zza2);
            zzh().zza(zzv);
        } catch (IllegalArgumentException e) {
        }
    }

    public final ArrayList<Bundle> zza(String str, String str2) {
        zzb();
        return zzb((String) null, str, str2);
    }

    public final ArrayList<Bundle> zza(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zza();
        return zzb(str, str2, str3);
    }

    private final ArrayList<Bundle> zzb(String str, String str2, String str3) {
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        } else if (zzw.zza()) {
            zzr().zzf().zza("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            zzgj zzq = this.zzx.zzq();
            zzie zzie = new zzie(this, atomicReference, str, str2, str3);
            zzq.zza(atomicReference, 5000, "get conditional user properties", zzie);
            List list = (List) atomicReference.get();
            if (list != null) {
                return zzla.zzb(list);
            }
            zzr().zzf().zza("Timed out waiting for get conditional user properties", str);
            return new ArrayList<>();
        }
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        zzb();
        return zzb((String) null, str, str2, z);
    }

    public final Map<String, Object> zza(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zza();
        return zzb(str, str2, str3, z);
    }

    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        } else if (zzw.zza()) {
            zzr().zzf().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            zzgj zzq = this.zzx.zzq();
            zzig zzig = new zzig(this, atomicReference, str, str2, str3, z);
            zzq.zza(atomicReference, 5000, "get user properties", zzig);
            List<zzkz> list = (List) atomicReference.get();
            if (list == null) {
                zzr().zzf().zza("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
                return Collections.emptyMap();
            }
            ArrayMap arrayMap = new ArrayMap(list.size());
            for (zzkz zzkz : list) {
                arrayMap.put(zzkz.zza, zzkz.zza());
            }
            return arrayMap;
        }
    }

    public final String zzaj() {
        zziv zzac = this.zzx.zzv().zzac();
        if (zzac != null) {
            return zzac.zza;
        }
        return null;
    }

    public final String zzak() {
        zziv zzac = this.zzx.zzv().zzac();
        if (zzac != null) {
            return zzac.zzb;
        }
        return null;
    }

    public final String zzal() {
        if (this.zzx.zzo() != null) {
            return this.zzx.zzo();
        }
        try {
            return GoogleServices.getGoogleAppId();
        } catch (IllegalStateException e) {
            this.zzx.zzr().zzf().zza("getGoogleAppId failed with exception", e);
            return null;
        }
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
