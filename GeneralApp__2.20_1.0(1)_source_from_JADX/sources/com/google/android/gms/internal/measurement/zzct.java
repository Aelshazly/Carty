package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
final /* synthetic */ class zzct implements OnSharedPreferenceChangeListener {
    private final zzcu zza;

    zzct(zzcu zzcu) {
        this.zza = zzcu;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zza.zza(sharedPreferences, str);
    }
}
