package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class AutoValue_PersistedEvent extends PersistedEvent {
    private final EventInternal event;

    /* renamed from: id */
    private final long f63id;
    private final TransportContext transportContext;

    AutoValue_PersistedEvent(long id, TransportContext transportContext2, EventInternal event2) {
        this.f63id = id;
        if (transportContext2 != null) {
            this.transportContext = transportContext2;
            if (event2 != null) {
                this.event = event2;
                return;
            }
            throw new NullPointerException("Null event");
        }
        throw new NullPointerException("Null transportContext");
    }

    public long getId() {
        return this.f63id;
    }

    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    public EventInternal getEvent() {
        return this.event;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PersistedEvent{id=");
        sb.append(this.f63id);
        sb.append(", transportContext=");
        sb.append(this.transportContext);
        sb.append(", event=");
        sb.append(this.event);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof PersistedEvent)) {
            return false;
        }
        PersistedEvent that = (PersistedEvent) o;
        if (this.f63id != that.getId() || !this.transportContext.equals(that.getTransportContext()) || !this.event.equals(that.getEvent())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        long j = this.f63id;
        return ((((h$ ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.transportContext.hashCode()) * 1000003) ^ this.event.hashCode();
    }
}
