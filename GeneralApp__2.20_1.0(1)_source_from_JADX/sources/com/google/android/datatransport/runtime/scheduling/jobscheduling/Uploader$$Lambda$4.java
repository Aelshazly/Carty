package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class Uploader$$Lambda$4 implements CriticalSection {
    private final EventStore arg$1;

    private Uploader$$Lambda$4(EventStore eventStore) {
        this.arg$1 = eventStore;
    }

    public static CriticalSection lambdaFactory$(EventStore eventStore) {
        return new Uploader$$Lambda$4(eventStore);
    }

    public Object execute() {
        return Integer.valueOf(this.arg$1.cleanUp());
    }
}
