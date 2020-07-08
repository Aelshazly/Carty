package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class Uploader$$Lambda$2 implements CriticalSection {
    private final Uploader arg$1;
    private final TransportContext arg$2;

    private Uploader$$Lambda$2(Uploader uploader, TransportContext transportContext) {
        this.arg$1 = uploader;
        this.arg$2 = transportContext;
    }

    public static CriticalSection lambdaFactory$(Uploader uploader, TransportContext transportContext) {
        return new Uploader$$Lambda$2(uploader, transportContext);
    }

    public Object execute() {
        return this.arg$1.eventStore.loadBatch(this.arg$2);
    }
}
