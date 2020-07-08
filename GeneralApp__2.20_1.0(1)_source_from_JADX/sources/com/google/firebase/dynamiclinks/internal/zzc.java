package com.google.firebase.dynamiclinks.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
public final class zzc extends GoogleApi<NoOptions> {
    private static final Api<NoOptions> API = new Api<>("DynamicLinks.API", zzp, CLIENT_KEY);
    private static final ClientKey<zzd> CLIENT_KEY = new ClientKey<>();
    private static final AbstractClientBuilder<zzd, NoOptions> zzp = new zzb();

    public zzc(Context context) {
        super(context, API, null, Settings.DEFAULT_SETTINGS);
    }
}
