package com.google.firebase.dynamiclinks.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
final class zzg extends zzh {
    private final TaskCompletionSource<ShortDynamicLink> zzt;

    zzg(TaskCompletionSource<ShortDynamicLink> taskCompletionSource) {
        this.zzt = taskCompletionSource;
    }

    public final void zza(Status status, zzo zzo) {
        TaskUtil.setResultOrApiException(status, zzo, this.zzt);
    }
}
