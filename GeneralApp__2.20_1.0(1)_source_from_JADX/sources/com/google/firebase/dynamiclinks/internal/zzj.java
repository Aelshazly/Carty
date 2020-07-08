package com.google.firebase.dynamiclinks.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
final class zzj extends TaskApiCall<zzd, ShortDynamicLink> {
    private final Bundle zze;

    zzj(Bundle bundle) {
        this.zze = bundle;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzd zzd = (zzd) anyClient;
        try {
            ((zzm) zzd.getService()).zza((zzk) new zzg(taskCompletionSource), this.zze);
        } catch (RemoteException e) {
        }
    }
}
