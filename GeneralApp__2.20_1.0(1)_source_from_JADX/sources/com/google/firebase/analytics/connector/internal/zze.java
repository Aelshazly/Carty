package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-api@@17.3.0 */
public final class zze implements zza {
    /* access modifiers changed from: private */
    public AnalyticsConnectorListener zza;
    private AppMeasurement zzb;
    private zzg zzc = new zzg(this);

    public zze(AppMeasurement appMeasurement, AnalyticsConnectorListener analyticsConnectorListener) {
        this.zza = analyticsConnectorListener;
        this.zzb = appMeasurement;
        this.zzb.registerOnMeasurementEventListener(this.zzc);
    }

    public final AnalyticsConnectorListener zza() {
        return this.zza;
    }

    public final void zza(Set<String> set) {
    }

    public final void zzb() {
    }
}
