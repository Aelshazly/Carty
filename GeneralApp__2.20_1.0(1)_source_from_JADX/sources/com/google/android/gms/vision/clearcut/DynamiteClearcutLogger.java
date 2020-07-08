package com.google.android.gms.vision.clearcut;

import android.content.Context;
import com.google.android.gms.internal.vision.zzea.zzo;
import com.google.android.gms.vision.C0548L;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public class DynamiteClearcutLogger {
    private static final ThreadPoolExecutor zzbo;
    private zzb zzbp = new zzb(0.03333333333333333d);
    /* access modifiers changed from: private */
    public VisionClearcutLogger zzbq;

    public DynamiteClearcutLogger(Context context) {
        this.zzbq = new VisionClearcutLogger(context);
    }

    public final void zza(int i, zzo zzo) {
        if (i != 3 || this.zzbp.tryAcquire()) {
            zzbo.execute(new zza(this, i, zzo));
            return;
        }
        C0548L.m66v("Skipping image analysis log due to rate limiting", new Object[0]);
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 2, TimeUnit.SECONDS, new LinkedBlockingQueue(10), new DiscardPolicy());
        zzbo = threadPoolExecutor;
    }
}
