package com.google.android.datatransport.runtime;

import java.util.Map;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class AutoValue_EventInternal extends EventInternal {
    private final Map<String, String> autoMetadata;
    private final Integer code;
    private final EncodedPayload encodedPayload;
    private final long eventMillis;
    private final String transportName;
    private final long uptimeMillis;

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    static final class Builder extends com.google.android.datatransport.runtime.EventInternal.Builder {
        private Map<String, String> autoMetadata;
        private Integer code;
        private EncodedPayload encodedPayload;
        private Long eventMillis;
        private String transportName;
        private Long uptimeMillis;

        Builder() {
        }

        public com.google.android.datatransport.runtime.EventInternal.Builder setTransportName(String transportName2) {
            if (transportName2 != null) {
                this.transportName = transportName2;
                return this;
            }
            throw new NullPointerException("Null transportName");
        }

        public com.google.android.datatransport.runtime.EventInternal.Builder setCode(Integer code2) {
            this.code = code2;
            return this;
        }

        public com.google.android.datatransport.runtime.EventInternal.Builder setEncodedPayload(EncodedPayload encodedPayload2) {
            if (encodedPayload2 != null) {
                this.encodedPayload = encodedPayload2;
                return this;
            }
            throw new NullPointerException("Null encodedPayload");
        }

        public com.google.android.datatransport.runtime.EventInternal.Builder setEventMillis(long eventMillis2) {
            this.eventMillis = Long.valueOf(eventMillis2);
            return this;
        }

        public com.google.android.datatransport.runtime.EventInternal.Builder setUptimeMillis(long uptimeMillis2) {
            this.uptimeMillis = Long.valueOf(uptimeMillis2);
            return this;
        }

        /* access modifiers changed from: protected */
        public com.google.android.datatransport.runtime.EventInternal.Builder setAutoMetadata(Map<String, String> autoMetadata2) {
            if (autoMetadata2 != null) {
                this.autoMetadata = autoMetadata2;
                return this;
            }
            throw new NullPointerException("Null autoMetadata");
        }

        /* access modifiers changed from: protected */
        public Map<String, String> getAutoMetadata() {
            Map<String, String> map = this.autoMetadata;
            if (map != null) {
                return map;
            }
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }

        public EventInternal build() {
            String missing = "";
            if (this.transportName == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" transportName");
                missing = sb.toString();
            }
            if (this.encodedPayload == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" encodedPayload");
                missing = sb2.toString();
            }
            if (this.eventMillis == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" eventMillis");
                missing = sb3.toString();
            }
            if (this.uptimeMillis == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(missing);
                sb4.append(" uptimeMillis");
                missing = sb4.toString();
            }
            if (this.autoMetadata == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(missing);
                sb5.append(" autoMetadata");
                missing = sb5.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_EventInternal autoValue_EventInternal = new AutoValue_EventInternal(this.transportName, this.code, this.encodedPayload, this.eventMillis.longValue(), this.uptimeMillis.longValue(), this.autoMetadata);
                return autoValue_EventInternal;
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Missing required properties:");
            sb6.append(missing);
            throw new IllegalStateException(sb6.toString());
        }
    }

    private AutoValue_EventInternal(String transportName2, Integer code2, EncodedPayload encodedPayload2, long eventMillis2, long uptimeMillis2, Map<String, String> autoMetadata2) {
        this.transportName = transportName2;
        this.code = code2;
        this.encodedPayload = encodedPayload2;
        this.eventMillis = eventMillis2;
        this.uptimeMillis = uptimeMillis2;
        this.autoMetadata = autoMetadata2;
    }

    public String getTransportName() {
        return this.transportName;
    }

    public Integer getCode() {
        return this.code;
    }

    public EncodedPayload getEncodedPayload() {
        return this.encodedPayload;
    }

    public long getEventMillis() {
        return this.eventMillis;
    }

    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getAutoMetadata() {
        return this.autoMetadata;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EventInternal{transportName=");
        sb.append(this.transportName);
        sb.append(", code=");
        sb.append(this.code);
        sb.append(", encodedPayload=");
        sb.append(this.encodedPayload);
        sb.append(", eventMillis=");
        sb.append(this.eventMillis);
        sb.append(", uptimeMillis=");
        sb.append(this.uptimeMillis);
        sb.append(", autoMetadata=");
        sb.append(this.autoMetadata);
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0057, code lost:
        if (r8.autoMetadata.equals(r1.getAutoMetadata()) != false) goto L_0x005b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 1
            if (r9 != r8) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r9 instanceof com.google.android.datatransport.runtime.EventInternal
            r2 = 0
            if (r1 == 0) goto L_0x005c
            r1 = r9
            com.google.android.datatransport.runtime.EventInternal r1 = (com.google.android.datatransport.runtime.EventInternal) r1
            java.lang.String r3 = r8.transportName
            java.lang.String r4 = r1.getTransportName()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x005a
            java.lang.Integer r3 = r8.code
            if (r3 != 0) goto L_0x0023
            java.lang.Integer r3 = r1.getCode()
            if (r3 != 0) goto L_0x005a
            goto L_0x002d
        L_0x0023:
            java.lang.Integer r4 = r1.getCode()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x005a
        L_0x002d:
            com.google.android.datatransport.runtime.EncodedPayload r3 = r8.encodedPayload
            com.google.android.datatransport.runtime.EncodedPayload r4 = r1.getEncodedPayload()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x005a
            long r3 = r8.eventMillis
            long r5 = r1.getEventMillis()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x005a
            long r3 = r8.uptimeMillis
            long r5 = r1.getUptimeMillis()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x005a
            java.util.Map<java.lang.String, java.lang.String> r3 = r8.autoMetadata
            java.util.Map r4 = r1.getAutoMetadata()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x005a
            goto L_0x005b
        L_0x005a:
            r0 = 0
        L_0x005b:
            return r0
        L_0x005c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.datatransport.runtime.AutoValue_EventInternal.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int h$ = ((1 * 1000003) ^ this.transportName.hashCode()) * 1000003;
        Integer num = this.code;
        int h$2 = (((h$ ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.encodedPayload.hashCode()) * 1000003;
        long j = this.eventMillis;
        int h$3 = (h$2 ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.uptimeMillis;
        return ((h$3 ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.autoMetadata.hashCode();
    }
}
