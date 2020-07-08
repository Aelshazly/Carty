package com.google.firebase.messaging;

import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
final class zzi {
    static Executor zza() {
        return zza("Firebase-Messaging-Rpc-Task");
    }

    static Executor zzb() {
        return zza("Firebase-Messaging-Trigger-Topics-Io");
    }

    private static Executor zza(String str) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory(str));
        return threadPoolExecutor;
    }
}
