package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class AutoValue_CreationContext extends CreationContext {
    private final Context applicationContext;
    private final String backendName;
    private final Clock monotonicClock;
    private final Clock wallClock;

    AutoValue_CreationContext(Context applicationContext2, Clock wallClock2, Clock monotonicClock2, String backendName2) {
        if (applicationContext2 != null) {
            this.applicationContext = applicationContext2;
            if (wallClock2 != null) {
                this.wallClock = wallClock2;
                if (monotonicClock2 != null) {
                    this.monotonicClock = monotonicClock2;
                    if (backendName2 != null) {
                        this.backendName = backendName2;
                        return;
                    }
                    throw new NullPointerException("Null backendName");
                }
                throw new NullPointerException("Null monotonicClock");
            }
            throw new NullPointerException("Null wallClock");
        }
        throw new NullPointerException("Null applicationContext");
    }

    public Context getApplicationContext() {
        return this.applicationContext;
    }

    public Clock getWallClock() {
        return this.wallClock;
    }

    public Clock getMonotonicClock() {
        return this.monotonicClock;
    }

    public String getBackendName() {
        return this.backendName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CreationContext{applicationContext=");
        sb.append(this.applicationContext);
        sb.append(", wallClock=");
        sb.append(this.wallClock);
        sb.append(", monotonicClock=");
        sb.append(this.monotonicClock);
        sb.append(", backendName=");
        sb.append(this.backendName);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreationContext)) {
            return false;
        }
        CreationContext that = (CreationContext) o;
        if (!this.applicationContext.equals(that.getApplicationContext()) || !this.wallClock.equals(that.getWallClock()) || !this.monotonicClock.equals(that.getMonotonicClock()) || !this.backendName.equals(that.getBackendName())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((((((1 * 1000003) ^ this.applicationContext.hashCode()) * 1000003) ^ this.wallClock.hashCode()) * 1000003) ^ this.monotonicClock.hashCode()) * 1000003) ^ this.backendName.hashCode();
    }
}
