package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzju;
import com.google.android.gms.internal.measurement.zzll;
import com.google.android.gms.internal.measurement.zzn;
import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzla extends zzhk {
    private static final String[] zza = {"firebase_", "google_", "ga_"};
    private SecureRandom zzb;
    private final AtomicLong zzc = new AtomicLong(0);
    private int zzd;
    private Integer zze = null;

    zzla(zzgq zzgq) {
        super(zzgq);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f_ */
    public final void mo16404f_() {
        zzd();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzr().zzi().zza("Utils falling back to Random for random id");
            }
        }
        this.zzc.set(nextLong);
    }

    public final long zzg() {
        long andIncrement;
        long j;
        if (this.zzc.get() == 0) {
            synchronized (this.zzc) {
                long nextLong = new Random(System.nanoTime() ^ zzm().currentTimeMillis()).nextLong();
                int i = this.zzd + 1;
                this.zzd = i;
                j = nextLong + ((long) i);
            }
            return j;
        }
        synchronized (this.zzc) {
            this.zzc.compareAndSet(-1, 1);
            andIncrement = this.zzc.getAndIncrement();
        }
        return andIncrement;
    }

    /* access modifiers changed from: 0000 */
    public final SecureRandom zzh() {
        zzd();
        if (this.zzb == null) {
            this.zzb = new SecureRandom();
        }
        return this.zzb;
    }

    static boolean zza(String str) {
        Preconditions.checkNotEmpty(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    /* access modifiers changed from: 0000 */
    public final Bundle zza(Uri uri) {
        String str;
        String str2;
        String str3;
        String str4;
        if (uri == null) {
            return null;
        }
        try {
            String str5 = "gclid";
            if (uri.isHierarchical()) {
                str4 = uri.getQueryParameter("utm_campaign");
                str3 = uri.getQueryParameter("utm_source");
                str2 = uri.getQueryParameter("utm_medium");
                str = uri.getQueryParameter(str5);
            } else {
                str4 = null;
                str3 = null;
                str2 = null;
                str = null;
            }
            if (TextUtils.isEmpty(str4) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str)) {
                return null;
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str4)) {
                bundle.putString(Param.CAMPAIGN, str4);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString(Param.SOURCE, str3);
            }
            if (!TextUtils.isEmpty(str2)) {
                bundle.putString(Param.MEDIUM, str2);
            }
            if (!TextUtils.isEmpty(str)) {
                bundle.putString(str5, str);
            }
            String queryParameter = uri.getQueryParameter("utm_term");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(Param.TERM, queryParameter);
            }
            String queryParameter2 = uri.getQueryParameter("utm_content");
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString(Param.CONTENT, queryParameter2);
            }
            String str6 = Param.ACLID;
            String queryParameter3 = uri.getQueryParameter(str6);
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString(str6, queryParameter3);
            }
            String str7 = Param.CP1;
            String queryParameter4 = uri.getQueryParameter(str7);
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString(str7, queryParameter4);
            }
            String str8 = "anid";
            String queryParameter5 = uri.getQueryParameter(str8);
            if (!TextUtils.isEmpty(queryParameter5)) {
                bundle.putString(str8, queryParameter5);
            }
            return bundle;
        } catch (UnsupportedOperationException e) {
            zzr().zzi().zza("Install referrer url isn't a hierarchical URI", e);
            return null;
        }
    }

    static boolean zza(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(String str, String str2) {
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzr().zzh().zza("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                zzr().zzh().zza("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    zzr().zzh().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzb(String str, String str2) {
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzr().zzh().zza("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = str2.codePointAt(charCount);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        charCount += Character.charCount(codePointAt2);
                    } else {
                        zzr().zzh().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzr().zzh().zza("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(String str, String[] strArr, String str2) {
        boolean z;
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        }
        Preconditions.checkNotNull(str2);
        String[] strArr2 = zza;
        int length = strArr2.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (str2.startsWith(strArr2[i])) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            zzr().zzh().zza("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        } else if (strArr == null || !zza(str2, strArr)) {
            return true;
        } else {
            zzr().zzh().zza("Name is reserved. Type, name", str, str2);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(String str, int i, String str2) {
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        } else {
            zzr().zzh().zza("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final int zzb(String str) {
        String str2 = NotificationCompat.CATEGORY_EVENT;
        if (!zzb(str2, str)) {
            return 2;
        }
        if (!zza(str2, zzhl.zza, str)) {
            return 13;
        }
        if (!zza(str2, 40, str)) {
            return 2;
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final int zzc(String str) {
        String str2 = "user property";
        if (!zzb(str2, str)) {
            return 6;
        }
        if (!zza(str2, zzhn.zza, str)) {
            return 15;
        }
        if (!zza(str2, 24, str)) {
            return 6;
        }
        return 0;
    }

    private final int zzg(String str) {
        String str2 = "event param";
        if (!zza(str2, str)) {
            return 3;
        }
        if (!zza(str2, (String[]) null, str)) {
            return 14;
        }
        if (!zza(str2, 40, str)) {
            return 3;
        }
        return 0;
    }

    private final int zzh(String str) {
        String str2 = "event param";
        if (!zzb(str2, str)) {
            return 3;
        }
        if (!zza(str2, (String[]) null, str)) {
            return 14;
        }
        if (!zza(str2, 40, str)) {
            return 3;
        }
        return 0;
    }

    private static boolean zzb(Object obj) {
        return (obj instanceof Parcelable[]) || (obj instanceof ArrayList) || (obj instanceof Bundle);
    }

    private final boolean zza(String str, String str2, int i, Object obj) {
        int i2;
        if (obj instanceof Parcelable[]) {
            i2 = ((Parcelable[]) obj).length;
        } else if (!(obj instanceof ArrayList)) {
            return true;
        } else {
            i2 = ((ArrayList) obj).size();
        }
        if (i2 <= i) {
            return true;
        }
        zzr().zzk().zza("Parameter array is too long; discarded. Value kind, name, array length", str, str2, Integer.valueOf(i2));
        return false;
    }

    private final boolean zzb(String str, String str2, int i, Object obj) {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
            return false;
        }
        String valueOf = String.valueOf(obj);
        if (valueOf.codePointCount(0, valueOf.length()) <= i) {
            return true;
        }
        zzr().zzk().zza("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
        return false;
    }

    private final void zza(String str, String str2, String str3, Bundle bundle, List<String> list, boolean z) {
        int i;
        int i2;
        String str4;
        int i3;
        String str5;
        int i4;
        String str6 = str2;
        Bundle bundle2 = bundle;
        List<String> list2 = list;
        if (bundle2 != null) {
            boolean zza2 = zzt().zza(zzap.zzdd);
            if (zza2) {
                i = 0;
            } else {
                i = zzt().zze();
            }
            int i5 = 0;
            for (String str7 : new TreeSet(bundle.keySet())) {
                if (list2 == null || !list2.contains(str7)) {
                    if (z) {
                        i2 = zzg(str7);
                    } else {
                        i2 = 0;
                    }
                    if (i2 == 0) {
                        i2 = zzh(str7);
                    }
                } else {
                    i2 = 0;
                }
                if (i2 != 0) {
                    zza(bundle2, i2, str7, str7, (Object) i2 == 3 ? str7 : null);
                    bundle2.remove(str7);
                } else {
                    if (zzb(bundle2.get(str7))) {
                        zzr().zzk().zza("Nested Bundle parameters are not allowed; discarded. event name, param name, child param name", str6, str3, str7);
                        i3 = 22;
                        str4 = str7;
                    } else {
                        String str8 = str3;
                        str4 = str7;
                        i3 = zza(str, str2, str7, bundle2.get(str7), bundle, list, z, false);
                    }
                    if (i3 != 0 && !"_ev".equals(str4)) {
                        zza(bundle2, i3, str4, str4, bundle2.get(str4));
                        bundle2.remove(str4);
                    } else if (zza(str4) && (!zza2 || !zza(str4, zzho.zzd))) {
                        int i6 = i5 + 1;
                        if (i6 > i) {
                            if (zza2) {
                                str5 = "Item cannot contain custom parameters";
                            } else {
                                StringBuilder sb = new StringBuilder(63);
                                sb.append("Child bundles can't contain more than ");
                                sb.append(i);
                                sb.append(" custom params");
                                str5 = sb.toString();
                            }
                            zzr().zzh().zza(str5, zzo().zza(str6), zzo().zza(bundle2));
                            if (zza2) {
                                i4 = 23;
                            } else {
                                i4 = 5;
                            }
                            zza(bundle2, i4);
                            bundle2.remove(str4);
                            i5 = i6;
                        } else {
                            i5 = i6;
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            if (!zzi(str)) {
                if (this.zzx.zzl()) {
                    zzr().zzh().zza("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzfj.zza(str));
                }
                return false;
            }
        } else if (!zzll.zzb() || !zzt().zza(zzap.zzch) || TextUtils.isEmpty(str3)) {
            if (TextUtils.isEmpty(str2)) {
                if (this.zzx.zzl()) {
                    zzr().zzh().zza("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
                }
                return false;
            } else if (!zzi(str2)) {
                zzr().zzh().zza("Invalid admob_app_id. Analytics disabled.", zzfj.zza(str2));
                return false;
            }
        }
        return true;
    }

    static boolean zza(String str, String str2, String str3, String str4) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (!isEmpty && !isEmpty2) {
            return !str.equals(str2);
        }
        if (isEmpty && isEmpty2) {
            return (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) ? !TextUtils.isEmpty(str4) : !str3.equals(str4);
        }
        if (isEmpty || !isEmpty2) {
            return TextUtils.isEmpty(str3) || !str3.equals(str4);
        }
        if (TextUtils.isEmpty(str4)) {
            return false;
        }
        return TextUtils.isEmpty(str3) || !str3.equals(str4);
    }

    private static boolean zzi(String str) {
        Preconditions.checkNotNull(str);
        return str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
    }

    private final Object zza(int i, Object obj, boolean z, boolean z2) {
        Parcelable[] parcelableArr;
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zza(String.valueOf(obj), i, z);
            }
            if (!zzju.zzb() || !zzt().zza(zzap.zzdc) || !zzt().zza(zzap.zzdb) || !z2 || (!(obj instanceof Bundle[]) && !(obj instanceof Parcelable[]))) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : (Parcelable[]) obj) {
                if (parcelable instanceof Bundle) {
                    Bundle zza2 = zza((Bundle) parcelable);
                    if (zza2 != null && !zza2.isEmpty()) {
                        arrayList.add(zza2);
                    }
                }
            }
            return arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    public static String zza(String str, int i, boolean z) {
        if (str == null) {
            return null;
        }
        if (str.codePointCount(0, str.length()) <= i) {
            return str;
        }
        if (z) {
            return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...");
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.Object r21, android.os.Bundle r22, java.util.List<java.lang.String> r23, boolean r24, boolean r25) {
        /*
            r17 = this;
            r7 = r17
            r8 = r20
            r0 = r21
            r1 = r22
            r17.zzd()
            boolean r2 = com.google.android.gms.internal.measurement.zzju.zzb()
            r3 = 17
            java.lang.String r4 = "param"
            r9 = 0
            if (r2 == 0) goto L_0x0087
            com.google.android.gms.measurement.internal.zzx r2 = r17.zzt()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzap.zzdd
            boolean r2 = r2.zza(r5)
            if (r2 == 0) goto L_0x0087
            boolean r2 = zzb(r21)
            if (r2 == 0) goto L_0x0092
            if (r25 == 0) goto L_0x0084
            java.lang.String[] r2 = com.google.android.gms.measurement.internal.zzho.zzc
            boolean r2 = zza(r8, r2)
            if (r2 != 0) goto L_0x0036
            r0 = 20
            return r0
        L_0x0036:
            com.google.android.gms.measurement.internal.zzgq r2 = r7.zzx
            com.google.android.gms.measurement.internal.zziz r2 = r2.zzw()
            boolean r2 = r2.zzai()
            if (r2 != 0) goto L_0x0045
            r0 = 25
            return r0
        L_0x0045:
            r2 = 200(0xc8, float:2.8E-43)
            boolean r5 = r7.zza(r4, r8, r2, r0)
            if (r5 != 0) goto L_0x0092
            boolean r5 = r0 instanceof android.os.Parcelable[]
            if (r5 == 0) goto L_0x0067
            r5 = r0
            android.os.Parcelable[] r5 = (android.os.Parcelable[]) r5
            int r6 = r5.length
            if (r6 <= r2) goto L_0x0080
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r5, r2)
            android.os.Parcelable[] r2 = (android.os.Parcelable[]) r2
            r1.putParcelableArray(r8, r2)
            goto L_0x0081
        L_0x0067:
            boolean r5 = r0 instanceof java.util.ArrayList
            if (r5 == 0) goto L_0x0080
            r5 = r0
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            int r6 = r5.size()
            if (r6 <= r2) goto L_0x0080
            java.util.ArrayList r6 = new java.util.ArrayList
            java.util.List r2 = r5.subList(r9, r2)
            r6.<init>(r2)
            r1.putParcelableArrayList(r8, r6)
        L_0x0080:
        L_0x0081:
            r10 = 17
            goto L_0x0093
        L_0x0084:
            r0 = 21
            return r0
        L_0x0087:
            if (r25 == 0) goto L_0x0092
            r1 = 1000(0x3e8, float:1.401E-42)
            boolean r1 = r7.zza(r4, r8, r1, r0)
            if (r1 != 0) goto L_0x0092
            return r3
        L_0x0092:
            r10 = 0
        L_0x0093:
            com.google.android.gms.measurement.internal.zzx r1 = r17.zzt()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzap.zzaq
            r11 = r18
            boolean r1 = r1.zze(r11, r2)
            if (r1 == 0) goto L_0x00a7
            boolean r1 = zze(r19)
            if (r1 != 0) goto L_0x00ad
        L_0x00a7:
            boolean r1 = zze(r20)
            if (r1 == 0) goto L_0x00b0
        L_0x00ad:
            r1 = 256(0x100, float:3.59E-43)
            goto L_0x00b2
        L_0x00b0:
            r1 = 100
        L_0x00b2:
            boolean r1 = r7.zzb(r4, r8, r1, r0)
            if (r1 == 0) goto L_0x00b9
            return r10
        L_0x00b9:
            if (r25 == 0) goto L_0x0173
            boolean r1 = com.google.android.gms.internal.measurement.zzju.zzb()
            r12 = 1
            if (r1 == 0) goto L_0x00d1
            com.google.android.gms.measurement.internal.zzx r1 = r17.zzt()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzap.zzdc
            boolean r1 = r1.zza(r2)
            if (r1 == 0) goto L_0x00d1
            r13 = 1
            goto L_0x00d2
        L_0x00d1:
            r13 = 0
        L_0x00d2:
            boolean r1 = r0 instanceof android.os.Bundle
            if (r1 == 0) goto L_0x00ec
            if (r13 == 0) goto L_0x00ea
            r4 = r0
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r5 = r23
            r6 = r24
            r0.zza(r1, r2, r3, r4, r5, r6)
        L_0x00ea:
            goto L_0x0170
        L_0x00ec:
            boolean r1 = r0 instanceof android.os.Parcelable[]
            if (r1 == 0) goto L_0x012d
            r14 = r0
            android.os.Parcelable[] r14 = (android.os.Parcelable[]) r14
            int r15 = r14.length
            r6 = 0
        L_0x00f5:
            if (r6 >= r15) goto L_0x012c
            r0 = r14[r6]
            boolean r1 = r0 instanceof android.os.Bundle
            if (r1 != 0) goto L_0x0110
            com.google.android.gms.measurement.internal.zzfj r1 = r17.zzr()
            com.google.android.gms.measurement.internal.zzfl r1 = r1.zzk()
            java.lang.Class r0 = r0.getClass()
            java.lang.String r2 = "All Parcelable[] elements must be of type Bundle. Value type, name"
            r1.zza(r2, r0, r8)
            goto L_0x016f
        L_0x0110:
            if (r13 == 0) goto L_0x0127
            r4 = r0
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r5 = r23
            r16 = r6
            r6 = r24
            r0.zza(r1, r2, r3, r4, r5, r6)
            goto L_0x0129
        L_0x0127:
            r16 = r6
        L_0x0129:
            int r6 = r16 + 1
            goto L_0x00f5
        L_0x012c:
            goto L_0x0170
        L_0x012d:
            boolean r1 = r0 instanceof java.util.ArrayList
            if (r1 == 0) goto L_0x016f
            r14 = r0
            java.util.ArrayList r14 = (java.util.ArrayList) r14
            int r15 = r14.size()
            r0 = 0
        L_0x0139:
            if (r0 >= r15) goto L_0x016e
            java.lang.Object r1 = r14.get(r0)
            int r16 = r0 + 1
            boolean r0 = r1 instanceof android.os.Bundle
            if (r0 != 0) goto L_0x0157
            com.google.android.gms.measurement.internal.zzfj r0 = r17.zzr()
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzk()
            java.lang.Class r1 = r1.getClass()
            java.lang.String r2 = "All ArrayList elements must be of type Bundle. Value type, name"
            r0.zza(r2, r1, r8)
            goto L_0x016f
        L_0x0157:
            if (r13 == 0) goto L_0x016b
            r4 = r1
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r5 = r23
            r6 = r24
            r0.zza(r1, r2, r3, r4, r5, r6)
        L_0x016b:
            r0 = r16
            goto L_0x0139
        L_0x016e:
            goto L_0x0170
        L_0x016f:
            r12 = 0
        L_0x0170:
            if (r12 == 0) goto L_0x0173
            return r10
        L_0x0173:
            r0 = 4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzla.zza(java.lang.String, java.lang.String, java.lang.String, java.lang.Object, android.os.Bundle, java.util.List, boolean, boolean):int");
    }

    /* access modifiers changed from: 0000 */
    public final Object zza(String str, Object obj) {
        int i = 256;
        if ("_ev".equals(str)) {
            return zza(256, obj, true, true);
        }
        if (!zze(str)) {
            i = 100;
        }
        return zza(i, obj, false, true);
    }

    static Bundle[] zza(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        } else if (obj instanceof Parcelable[]) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            return (Bundle[]) Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
        } else if (!(obj instanceof ArrayList)) {
            return null;
        } else {
            ArrayList arrayList = (ArrayList) obj;
            return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    /* access modifiers changed from: 0000 */
    public final Bundle zza(String str, String str2, Bundle bundle, List<String> list, boolean z, boolean z2) {
        boolean z3;
        Set<String> set;
        int i;
        int i2;
        Bundle bundle2;
        String str3;
        int i3;
        String str4;
        String str5 = str2;
        Bundle bundle3 = bundle;
        List<String> list2 = list;
        boolean z4 = zzju.zzb() && zzt().zza(zzap.zzdd);
        if (z4) {
            z3 = zza(str5, zzhl.zzc);
        } else {
            z3 = z2;
        }
        if (bundle3 == null) {
            return null;
        }
        Bundle bundle4 = new Bundle(bundle3);
        int zze2 = zzt().zze();
        if (zzt().zze(str, zzap.zzbk)) {
            set = new TreeSet<>(bundle.keySet());
        } else {
            set = bundle.keySet();
        }
        int i4 = 0;
        for (String str6 : set) {
            if (list2 == null || !list2.contains(str6)) {
                if (z) {
                    i = zzg(str6);
                } else {
                    i = 0;
                }
                if (i == 0) {
                    i = zzh(str6);
                }
            } else {
                i = 0;
            }
            if (i != 0) {
                zza(bundle4, i, str6, str6, (Object) i == 3 ? str6 : null);
                bundle4.remove(str6);
                i2 = zze2;
                bundle2 = bundle4;
            } else {
                String str7 = str6;
                i2 = zze2;
                Bundle bundle5 = bundle4;
                int zza2 = zza(str, str2, str6, bundle3.get(str6), bundle4, list, z, z3);
                if (!z4 || zza2 != 17) {
                    str3 = str7;
                    bundle2 = bundle5;
                    if (zza2 != 0 && !"_ev".equals(str3)) {
                        if (zza2 == 21) {
                            str4 = str5;
                        } else {
                            str4 = str3;
                        }
                        zza(bundle2, zza2, str4, str3, bundle3.get(str3));
                        bundle2.remove(str3);
                    }
                } else {
                    str3 = str7;
                    bundle2 = bundle5;
                    zza(bundle2, zza2, str3, str3, (Object) Boolean.valueOf(false));
                }
                if (zza(str3)) {
                    int i5 = i4 + 1;
                    i3 = i2;
                    if (i5 > i3) {
                        StringBuilder sb = new StringBuilder(48);
                        sb.append("Event can't contain more than ");
                        sb.append(i3);
                        sb.append(" params");
                        zzr().zzh().zza(sb.toString(), zzo().zza(str5), zzo().zza(bundle3));
                        zza(bundle2, 5);
                        bundle2.remove(str3);
                        String str8 = str;
                        i4 = i5;
                        zze2 = i3;
                        bundle4 = bundle2;
                    } else {
                        i4 = i5;
                    }
                } else {
                    i3 = i2;
                }
                String str9 = str;
                zze2 = i3;
                bundle4 = bundle2;
            }
            String str10 = str;
            bundle4 = bundle2;
            zze2 = i2;
        }
        return bundle4;
    }

    private static void zza(Bundle bundle, int i, String str, String str2, Object obj) {
        if (zza(bundle, i)) {
            bundle.putString("_ev", zza(str, 40, true));
            if (obj != null) {
                Preconditions.checkNotNull(bundle);
                if (obj == null) {
                    return;
                }
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    bundle.putLong("_el", (long) String.valueOf(obj).length());
                }
            }
        }
    }

    static boolean zza(Bundle bundle, int i) {
        if (bundle == null) {
            return false;
        }
        String str = "_err";
        if (bundle.getLong(str) != 0) {
            return false;
        }
        bundle.putLong(str, (long) i);
        return true;
    }

    private final int zzj(String str) {
        if ("_ldl".equals(str)) {
            return 2048;
        }
        if ("_id".equals(str)) {
            return 256;
        }
        if (!zzt().zza(zzap.zzce) || !"_lgclid".equals(str)) {
            return 36;
        }
        return 100;
    }

    /* access modifiers changed from: 0000 */
    public final int zzb(String str, Object obj) {
        boolean z;
        if ("_ldl".equals(str)) {
            z = zzb("user property referrer", str, zzj(str), obj);
        } else {
            z = zzb("user property", str, zzj(str), obj);
        }
        return z ? 0 : 7;
    }

    /* access modifiers changed from: 0000 */
    public final Object zzc(String str, Object obj) {
        if ("_ldl".equals(str)) {
            return zza(zzj(str), obj, true, false);
        }
        return zza(zzj(str), obj, false, false);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (!zzju.zzb() || !zzt().zza(zzap.zzdc) || !zzt().zza(zzap.zzdb) || !(obj instanceof Bundle[])) {
                if (str != null) {
                    zzr().zzk().zza("Not putting event parameter. Invalid value type. name, type", zzo().zzb(str), obj != null ? obj.getClass().getSimpleName() : null);
                }
            } else {
                bundle.putParcelableArray(str, (Bundle[]) obj);
            }
        }
    }

    public final void zza(int i, String str, String str2, int i2) {
        zza((String) null, i, str, str2, i2);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zza(bundle, i);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.zzx.zzu();
        this.zzx.zzh().zza("auto", "_err", bundle);
    }

    static MessageDigest zzi() {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return null;
    }

    static long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        int i = 0;
        Preconditions.checkState(bArr.length > 0);
        long j = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j += (((long) bArr[length]) & 255) << i;
            i += 8;
            length--;
        }
        return j;
    }

    static boolean zza(Context context, boolean z) {
        Preconditions.checkNotNull(context);
        if (VERSION.SDK_INT >= 24) {
            return zzb(context, "com.google.android.gms.measurement.AppMeasurementJobService");
        }
        return zzb(context, "com.google.android.gms.measurement.AppMeasurementService");
    }

    private static boolean zzb(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0);
            if (serviceInfo != null && serviceInfo.enabled) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzd(String str) {
        zzd();
        if (Wrappers.packageManager(zzn()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzr().zzw().zza("Permission not granted", str);
        return false;
    }

    static boolean zze(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    static boolean zzc(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    static boolean zza(Boolean bool, Boolean bool2) {
        if (bool == null && bool2 == null) {
            return true;
        }
        if (bool == null) {
            return false;
        }
        return bool.equals(bool2);
    }

    static boolean zza(List<String> list, List<String> list2) {
        if (list == null && list2 == null) {
            return true;
        }
        if (list == null) {
            return false;
        }
        return list.equals(list2);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzf(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String zzw = zzt().zzw();
        zzu();
        return zzw.equals(str);
    }

    /* access modifiers changed from: 0000 */
    public final Bundle zza(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object zza2 = zza(str, bundle.get(str));
                if (zza2 == null) {
                    zzr().zzk().zza("Param value can't be null", zzo().zzb(str));
                } else {
                    zza(bundle2, str, zza2);
                }
            }
        }
        return bundle2;
    }

    /* access modifiers changed from: 0000 */
    public final zzan zza(String str, String str2, Bundle bundle, String str3, long j, boolean z, boolean z2) {
        Bundle bundle2;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (zzb(str2) == 0) {
            if (bundle != null) {
                bundle2 = new Bundle(bundle);
            } else {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            String str4 = "_o";
            bundle3.putString(str4, str3);
            String str5 = str2;
            zzan zzan = new zzan(str5, new zzam(zza(zza(str, str2, bundle3, CollectionUtils.listOf(str4), false, false))), str3, j);
            return zzan;
        }
        zzr().zzf().zza("Invalid conditional property event name", zzo().zzc(str2));
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: 0000 */
    public final long zza(Context context, String str) {
        zzd();
        Preconditions.checkNotNull(context);
        Preconditions.checkNotEmpty(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest zzi = zzi();
        if (zzi == null) {
            zzr().zzf().zza("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (zzc(context, str)) {
                    return 0;
                }
                PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(zzn().getPackageName(), 64);
                if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                    return zza(zzi.digest(packageInfo.signatures[0].toByteArray()));
                }
                zzr().zzi().zza("Could not get signatures");
                return -1;
            } catch (NameNotFoundException e) {
                zzr().zzf().zza("Package name not found", e);
            }
        }
        return 0;
    }

    private final boolean zzc(Context context, String str) {
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
            }
        } catch (CertificateException e) {
            zzr().zzf().zza("Error obtaining certificate", e);
        } catch (NameNotFoundException e2) {
            zzr().zzf().zza("Package name not found", e2);
        }
        return true;
    }

    static byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    public static Bundle zzb(Bundle bundle) {
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = new Bundle(bundle);
        for (String str : bundle2.keySet()) {
            Object obj = bundle2.get(str);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str, new Bundle((Bundle) obj));
            } else {
                int i = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i < parcelableArr.length) {
                        if (parcelableArr[i] instanceof Bundle) {
                            parcelableArr[i] = new Bundle((Bundle) parcelableArr[i]);
                        }
                        i++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i < list.size()) {
                        Object obj2 = list.get(i);
                        if (obj2 instanceof Bundle) {
                            list.set(i, new Bundle((Bundle) obj2));
                        }
                        i++;
                    }
                }
            }
        }
        return bundle2;
    }

    private static boolean zza(String str, String[] strArr) {
        Preconditions.checkNotNull(strArr);
        for (String zzc2 : strArr) {
            if (zzc(str, zzc2)) {
                return true;
            }
        }
        return false;
    }

    public final int zzj() {
        if (this.zze == null) {
            this.zze = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(zzn()) / 1000);
        }
        return this.zze.intValue();
    }

    public final int zza(int i) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(zzn(), 12451000);
    }

    public static long zza(long j, long j2) {
        return (j + (j2 * 60000)) / 86400000;
    }

    /* access modifiers changed from: 0000 */
    public final String zzk() {
        byte[] bArr = new byte[16];
        zzh().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Bundle bundle, long j) {
        String str = "_et";
        long j2 = bundle.getLong(str);
        if (j2 != 0) {
            zzr().zzi().zza("Params already contained engagement", Long.valueOf(j2));
        }
        bundle.putLong(str, j + j2);
    }

    public final void zza(zzn zzn, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("r", str);
        try {
            zzn.zza(bundle);
        } catch (RemoteException e) {
            this.zzx.zzr().zzi().zza("Error returning string value to wrapper", e);
        }
    }

    public final void zza(zzn zzn, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("r", j);
        try {
            zzn.zza(bundle);
        } catch (RemoteException e) {
            this.zzx.zzr().zzi().zza("Error returning long value to wrapper", e);
        }
    }

    public final void zza(zzn zzn, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("r", i);
        try {
            zzn.zza(bundle);
        } catch (RemoteException e) {
            this.zzx.zzr().zzi().zza("Error returning int value to wrapper", e);
        }
    }

    public final void zza(zzn zzn, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("r", bArr);
        try {
            zzn.zza(bundle);
        } catch (RemoteException e) {
            this.zzx.zzr().zzi().zza("Error returning byte array to wrapper", e);
        }
    }

    public final void zza(zzn zzn, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("r", z);
        try {
            zzn.zza(bundle);
        } catch (RemoteException e) {
            this.zzx.zzr().zzi().zza("Error returning boolean value to wrapper", e);
        }
    }

    public final void zza(zzn zzn, Bundle bundle) {
        try {
            zzn.zza(bundle);
        } catch (RemoteException e) {
            this.zzx.zzr().zzi().zza("Error returning bundle value to wrapper", e);
        }
    }

    public static Bundle zza(List<zzkz> list) {
        Bundle bundle = new Bundle();
        if (list == null) {
            return bundle;
        }
        for (zzkz zzkz : list) {
            if (zzkz.zzd != null) {
                bundle.putString(zzkz.zza, zzkz.zzd);
            } else if (zzkz.zzc != null) {
                bundle.putLong(zzkz.zza, zzkz.zzc.longValue());
            } else if (zzkz.zzf != null) {
                bundle.putDouble(zzkz.zza, zzkz.zzf.doubleValue());
            }
        }
        return bundle;
    }

    public final void zza(zzn zzn, ArrayList<Bundle> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("r", arrayList);
        try {
            zzn.zza(bundle);
        } catch (RemoteException e) {
            this.zzx.zzr().zzi().zza("Error returning bundle list to wrapper", e);
        }
    }

    public static ArrayList<Bundle> zzb(List<zzv> list) {
        if (list == null) {
            return new ArrayList<>(0);
        }
        ArrayList<Bundle> arrayList = new ArrayList<>(list.size());
        for (zzv zzv : list) {
            Bundle bundle = new Bundle();
            bundle.putString("app_id", zzv.zza);
            bundle.putString("origin", zzv.zzb);
            bundle.putLong(ConditionalUserProperty.CREATION_TIMESTAMP, zzv.zzd);
            bundle.putString("name", zzv.zzc.zza);
            zzhm.zza(bundle, zzv.zzc.zza());
            bundle.putBoolean(ConditionalUserProperty.ACTIVE, zzv.zze);
            if (zzv.zzf != null) {
                bundle.putString(ConditionalUserProperty.TRIGGER_EVENT_NAME, zzv.zzf);
            }
            if (zzv.zzg != null) {
                bundle.putString(ConditionalUserProperty.TIMED_OUT_EVENT_NAME, zzv.zzg.zza);
                if (zzv.zzg.zzb != null) {
                    bundle.putBundle(ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, zzv.zzg.zzb.zzb());
                }
            }
            bundle.putLong(ConditionalUserProperty.TRIGGER_TIMEOUT, zzv.zzh);
            if (zzv.zzi != null) {
                bundle.putString(ConditionalUserProperty.TRIGGERED_EVENT_NAME, zzv.zzi.zza);
                if (zzv.zzi.zzb != null) {
                    bundle.putBundle(ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, zzv.zzi.zzb.zzb());
                }
            }
            bundle.putLong(ConditionalUserProperty.TRIGGERED_TIMESTAMP, zzv.zzc.zzb);
            bundle.putLong(ConditionalUserProperty.TIME_TO_LIVE, zzv.zzj);
            if (zzv.zzk != null) {
                bundle.putString(ConditionalUserProperty.EXPIRED_EVENT_NAME, zzv.zzk.zza);
                if (zzv.zzk.zzb != null) {
                    bundle.putBundle(ConditionalUserProperty.EXPIRED_EVENT_PARAMS, zzv.zzk.zzb.zzb());
                }
            }
            arrayList.add(bundle);
        }
        return arrayList;
    }

    public final URL zza(long j, String str, String str2, long j2) {
        try {
            Preconditions.checkNotEmpty(str2);
            Preconditions.checkNotEmpty(str);
            String format = String.format("https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=%s&rdid=%s&bundleid=%s&retry=%s", new Object[]{String.format("v%s.%s", new Object[]{Long.valueOf(j), Integer.valueOf(zzj())}), str2, str, Long.valueOf(j2)});
            if (str.equals(zzt().zzx())) {
                format = format.concat("&ddl_test=1");
            }
            return new URL(format);
        } catch (IllegalArgumentException | MalformedURLException e) {
            zzr().zzf().zza("Failed to create BOW URL for Deferred Deep Link. exception", e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(String str, double d) {
        try {
            Editor edit = zzn().getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
            edit.putString("deeplink", str);
            edit.putLong("timestamp", Double.doubleToRawLongBits(d));
            return edit.commit();
        } catch (Exception e) {
            zzr().zzf().zza("Failed to persist Deferred Deep Link. exception", e);
            return false;
        }
    }

    public final boolean zzv() {
        try {
            zzn().getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static long zza(zzam zzam) {
        long j = 0;
        if (zzam == null) {
            return 0;
        }
        Iterator it = zzam.iterator();
        while (it.hasNext()) {
            Object zza2 = zzam.zza((String) it.next());
            if (zza2 instanceof Parcelable[]) {
                j += (long) ((Parcelable[]) zza2).length;
            }
        }
        return j;
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
