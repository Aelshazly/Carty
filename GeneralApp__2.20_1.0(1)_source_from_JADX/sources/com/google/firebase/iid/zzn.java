package com.google.firebase.iid;

import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
final /* synthetic */ class zzn implements Executor {
    static final Executor zza = new zzn();

    private zzn() {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
