package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-api@@17.3.0 */
public final class zzc implements zza {
    Set<String> zza;
    /* access modifiers changed from: private */
    public AnalyticsConnectorListener zzb;
    private AppMeasurement zzc;
    private zzf zzd = new zzf(this);

    public zzc(AppMeasurement appMeasurement, AnalyticsConnectorListener analyticsConnectorListener) {
        this.zzb = analyticsConnectorListener;
        this.zzc = appMeasurement;
        this.zzc.registerOnMeasurementEventListener(this.zzd);
        this.zza = new HashSet();
    }

    public final AnalyticsConnectorListener zza() {
        return this.zzb;
    }

    public final void zza(Set<String> set) {
        this.zza.clear();
        Set<String> set2 = this.zza;
        HashSet hashSet = new HashSet();
        for (String str : set) {
            if (hashSet.size() >= 50) {
                break;
            } else if (zzd.zzd(str) && zzd.zzc(str)) {
                hashSet.add(zzd.zzf(str));
            }
        }
        set2.addAll(hashSet);
    }

    public final void zzb() {
        this.zza.clear();
    }
}
