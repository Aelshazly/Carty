package com.google.android.gms.internal.vision;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final /* synthetic */ class zzbm implements OnSharedPreferenceChangeListener {
    private final zzbj zzhe;

    zzbm(zzbj zzbj) {
        this.zzhe = zzbj;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zzhe.zza(sharedPreferences, str);
    }
}
