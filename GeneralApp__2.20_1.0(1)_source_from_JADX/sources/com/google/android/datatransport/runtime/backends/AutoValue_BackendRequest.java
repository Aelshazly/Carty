package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.EventInternal;
import java.util.Arrays;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class AutoValue_BackendRequest extends BackendRequest {
    private final Iterable<EventInternal> events;
    private final byte[] extras;

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    static final class Builder extends com.google.android.datatransport.runtime.backends.BackendRequest.Builder {
        private Iterable<EventInternal> events;
        private byte[] extras;

        Builder() {
        }

        public com.google.android.datatransport.runtime.backends.BackendRequest.Builder setEvents(Iterable<EventInternal> events2) {
            if (events2 != null) {
                this.events = events2;
                return this;
            }
            throw new NullPointerException("Null events");
        }

        public com.google.android.datatransport.runtime.backends.BackendRequest.Builder setExtras(byte[] extras2) {
            this.extras = extras2;
            return this;
        }

        public BackendRequest build() {
            String missing = "";
            if (this.events == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" events");
                missing = sb.toString();
            }
            if (missing.isEmpty()) {
                return new AutoValue_BackendRequest(this.events, this.extras);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Missing required properties:");
            sb2.append(missing);
            throw new IllegalStateException(sb2.toString());
        }
    }

    private AutoValue_BackendRequest(Iterable<EventInternal> events2, byte[] extras2) {
        this.events = events2;
        this.extras = extras2;
    }

    public Iterable<EventInternal> getEvents() {
        return this.events;
    }

    public byte[] getExtras() {
        return this.extras;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BackendRequest{events=");
        sb.append(this.events);
        sb.append(", extras=");
        sb.append(Arrays.toString(this.extras));
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        if (java.util.Arrays.equals(r5.extras, r1 instanceof com.google.android.datatransport.runtime.backends.AutoValue_BackendRequest ? ((com.google.android.datatransport.runtime.backends.AutoValue_BackendRequest) r1).extras : r1.getExtras()) != false) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r6 instanceof com.google.android.datatransport.runtime.backends.BackendRequest
            r2 = 0
            if (r1 == 0) goto L_0x0031
            r1 = r6
            com.google.android.datatransport.runtime.backends.BackendRequest r1 = (com.google.android.datatransport.runtime.backends.BackendRequest) r1
            java.lang.Iterable<com.google.android.datatransport.runtime.EventInternal> r3 = r5.events
            java.lang.Iterable r4 = r1.getEvents()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x002f
            byte[] r3 = r5.extras
            boolean r4 = r1 instanceof com.google.android.datatransport.runtime.backends.AutoValue_BackendRequest
            if (r4 == 0) goto L_0x0024
            r4 = r1
            com.google.android.datatransport.runtime.backends.AutoValue_BackendRequest r4 = (com.google.android.datatransport.runtime.backends.AutoValue_BackendRequest) r4
            byte[] r4 = r4.extras
            goto L_0x0028
        L_0x0024:
            byte[] r4 = r1.getExtras()
        L_0x0028:
            boolean r3 = java.util.Arrays.equals(r3, r4)
            if (r3 == 0) goto L_0x002f
            goto L_0x0030
        L_0x002f:
            r0 = 0
        L_0x0030:
            return r0
        L_0x0031:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.datatransport.runtime.backends.AutoValue_BackendRequest.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.events.hashCode()) * 1000003) ^ Arrays.hashCode(this.extras);
    }
}
