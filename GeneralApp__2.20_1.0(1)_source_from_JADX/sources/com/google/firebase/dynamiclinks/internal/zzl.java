package com.google.firebase.dynamiclinks.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
final class zzl extends TaskApiCall<zzd, PendingDynamicLinkData> {
    private final String zzj;
    private final AnalyticsConnector zzr;

    zzl(AnalyticsConnector analyticsConnector, String str) {
        this.zzj = str;
        this.zzr = analyticsConnector;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzd zzd = (zzd) anyClient;
        try {
            ((zzm) zzd.getService()).zza((zzk) new zzi(this.zzr, taskCompletionSource), this.zzj);
        } catch (RemoteException e) {
        }
    }
}
