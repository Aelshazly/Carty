package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Inject;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
class CreationContextFactory {
    private final Context applicationContext;
    private final Clock monotonicClock;
    private final Clock wallClock;

    @Inject
    CreationContextFactory(Context applicationContext2, Clock wallClock2, Clock monotonicClock2) {
        this.applicationContext = applicationContext2;
        this.wallClock = wallClock2;
        this.monotonicClock = monotonicClock2;
    }

    /* access modifiers changed from: 0000 */
    public CreationContext create(String backendName) {
        return CreationContext.create(this.applicationContext, this.wallClock, this.monotonicClock, backendName);
    }
}
