package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Priority;
import java.util.Arrays;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class AutoValue_TransportContext extends TransportContext {
    private final String backendName;
    private final byte[] extras;
    private final Priority priority;

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    static final class Builder extends com.google.android.datatransport.runtime.TransportContext.Builder {
        private String backendName;
        private byte[] extras;
        private Priority priority;

        Builder() {
        }

        public com.google.android.datatransport.runtime.TransportContext.Builder setBackendName(String backendName2) {
            if (backendName2 != null) {
                this.backendName = backendName2;
                return this;
            }
            throw new NullPointerException("Null backendName");
        }

        public com.google.android.datatransport.runtime.TransportContext.Builder setExtras(byte[] extras2) {
            this.extras = extras2;
            return this;
        }

        public com.google.android.datatransport.runtime.TransportContext.Builder setPriority(Priority priority2) {
            if (priority2 != null) {
                this.priority = priority2;
                return this;
            }
            throw new NullPointerException("Null priority");
        }

        public TransportContext build() {
            String missing = "";
            if (this.backendName == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" backendName");
                missing = sb.toString();
            }
            if (this.priority == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" priority");
                missing = sb2.toString();
            }
            if (missing.isEmpty()) {
                return new AutoValue_TransportContext(this.backendName, this.extras, this.priority);
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Missing required properties:");
            sb3.append(missing);
            throw new IllegalStateException(sb3.toString());
        }
    }

    private AutoValue_TransportContext(String backendName2, byte[] extras2, Priority priority2) {
        this.backendName = backendName2;
        this.extras = extras2;
        this.priority = priority2;
    }

    public String getBackendName() {
        return this.backendName;
    }

    public byte[] getExtras() {
        return this.extras;
    }

    public Priority getPriority() {
        return this.priority;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0038, code lost:
        if (r5.priority.equals(r1.getPriority()) != false) goto L_0x003c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r6 instanceof com.google.android.datatransport.runtime.TransportContext
            r2 = 0
            if (r1 == 0) goto L_0x003d
            r1 = r6
            com.google.android.datatransport.runtime.TransportContext r1 = (com.google.android.datatransport.runtime.TransportContext) r1
            java.lang.String r3 = r5.backendName
            java.lang.String r4 = r1.getBackendName()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003b
            byte[] r3 = r5.extras
            boolean r4 = r1 instanceof com.google.android.datatransport.runtime.AutoValue_TransportContext
            if (r4 == 0) goto L_0x0024
            r4 = r1
            com.google.android.datatransport.runtime.AutoValue_TransportContext r4 = (com.google.android.datatransport.runtime.AutoValue_TransportContext) r4
            byte[] r4 = r4.extras
            goto L_0x0028
        L_0x0024:
            byte[] r4 = r1.getExtras()
        L_0x0028:
            boolean r3 = java.util.Arrays.equals(r3, r4)
            if (r3 == 0) goto L_0x003b
            com.google.android.datatransport.Priority r3 = r5.priority
            com.google.android.datatransport.Priority r4 = r1.getPriority()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003b
            goto L_0x003c
        L_0x003b:
            r0 = 0
        L_0x003c:
            return r0
        L_0x003d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.datatransport.runtime.AutoValue_TransportContext.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.backendName.hashCode()) * 1000003) ^ Arrays.hashCode(this.extras)) * 1000003) ^ this.priority.hashCode();
    }
}
