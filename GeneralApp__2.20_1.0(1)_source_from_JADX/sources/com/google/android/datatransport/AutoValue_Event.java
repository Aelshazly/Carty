package com.google.android.datatransport;

/* compiled from: com.google.android.datatransport:transport-api@@2.2.0 */
final class AutoValue_Event<T> extends Event<T> {
    private final Integer code;
    private final T payload;
    private final Priority priority;

    AutoValue_Event(Integer code2, T payload2, Priority priority2) {
        this.code = code2;
        if (payload2 != null) {
            this.payload = payload2;
            if (priority2 != null) {
                this.priority = priority2;
                return;
            }
            throw new NullPointerException("Null priority");
        }
        throw new NullPointerException("Null payload");
    }

    public Integer getCode() {
        return this.code;
    }

    public T getPayload() {
        return this.payload;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event{code=");
        sb.append(this.code);
        sb.append(", payload=");
        sb.append(this.payload);
        sb.append(", priority=");
        sb.append(this.priority);
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        if (r5.priority.equals(r1.getPriority()) != false) goto L_0x003b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r6 instanceof com.google.android.datatransport.Event
            r2 = 0
            if (r1 == 0) goto L_0x003c
            r1 = r6
            com.google.android.datatransport.Event r1 = (com.google.android.datatransport.Event) r1
            java.lang.Integer r3 = r5.code
            if (r3 != 0) goto L_0x0017
            java.lang.Integer r3 = r1.getCode()
            if (r3 != 0) goto L_0x003a
            goto L_0x0021
        L_0x0017:
            java.lang.Integer r4 = r1.getCode()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003a
        L_0x0021:
            T r3 = r5.payload
            java.lang.Object r4 = r1.getPayload()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003a
            com.google.android.datatransport.Priority r3 = r5.priority
            com.google.android.datatransport.Priority r4 = r1.getPriority()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003a
            goto L_0x003b
        L_0x003a:
            r0 = 0
        L_0x003b:
            return r0
        L_0x003c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.datatransport.AutoValue_Event.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        Integer num = this.code;
        return ((((h$ ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.payload.hashCode()) * 1000003) ^ this.priority.hashCode();
    }
}
