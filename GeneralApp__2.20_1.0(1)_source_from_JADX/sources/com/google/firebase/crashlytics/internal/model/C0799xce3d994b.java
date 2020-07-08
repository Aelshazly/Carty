package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame;

/* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame */
/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class C0799xce3d994b extends Frame {
    private final String file;
    private final int importance;
    private final long offset;

    /* renamed from: pc */
    private final long f80pc;
    private final String symbol;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame$Builder */
    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder {
        private String file;
        private Integer importance;
        private Long offset;

        /* renamed from: pc */
        private Long f81pc;
        private String symbol;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder setPc(long pc) {
            this.f81pc = Long.valueOf(pc);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder setSymbol(String symbol2) {
            if (symbol2 != null) {
                this.symbol = symbol2;
                return this;
            }
            throw new NullPointerException("Null symbol");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder setFile(String file2) {
            this.file = file2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder setOffset(long offset2) {
            this.offset = Long.valueOf(offset2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder setImportance(int importance2) {
            this.importance = Integer.valueOf(importance2);
            return this;
        }

        public Frame build() {
            String missing = "";
            if (this.f81pc == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" pc");
                missing = sb.toString();
            }
            if (this.symbol == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" symbol");
                missing = sb2.toString();
            }
            if (this.offset == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" offset");
                missing = sb3.toString();
            }
            if (this.importance == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(missing);
                sb4.append(" importance");
                missing = sb4.toString();
            }
            if (missing.isEmpty()) {
                C0799xce3d994b autoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame = new C0799xce3d994b(this.f81pc.longValue(), this.symbol, this.file, this.offset.longValue(), this.importance.intValue());
                return autoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame;
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Missing required properties:");
            sb5.append(missing);
            throw new IllegalStateException(sb5.toString());
        }
    }

    private C0799xce3d994b(long pc, String symbol2, String file2, long offset2, int importance2) {
        this.f80pc = pc;
        this.symbol = symbol2;
        this.file = file2;
        this.offset = offset2;
        this.importance = importance2;
    }

    public long getPc() {
        return this.f80pc;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getFile() {
        return this.file;
    }

    public long getOffset() {
        return this.offset;
    }

    public int getImportance() {
        return this.importance;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Frame{pc=");
        sb.append(this.f80pc);
        sb.append(", symbol=");
        sb.append(this.symbol);
        sb.append(", file=");
        sb.append(this.file);
        sb.append(", offset=");
        sb.append(this.offset);
        sb.append(", importance=");
        sb.append(this.importance);
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        if (r8.importance == r1.getImportance()) goto L_0x004b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 1
            if (r9 != r8) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r9 instanceof com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame
            r2 = 0
            if (r1 == 0) goto L_0x004c
            r1 = r9
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$Execution$Thread$Frame r1 = (com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame) r1
            long r3 = r8.f80pc
            long r5 = r1.getPc()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x004a
            java.lang.String r3 = r8.symbol
            java.lang.String r4 = r1.getSymbol()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x004a
            java.lang.String r3 = r8.file
            if (r3 != 0) goto L_0x002d
            java.lang.String r3 = r1.getFile()
            if (r3 != 0) goto L_0x004a
            goto L_0x0037
        L_0x002d:
            java.lang.String r4 = r1.getFile()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x004a
        L_0x0037:
            long r3 = r8.offset
            long r5 = r1.getOffset()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x004a
            int r3 = r8.importance
            int r4 = r1.getImportance()
            if (r3 != r4) goto L_0x004a
            goto L_0x004b
        L_0x004a:
            r0 = 0
        L_0x004b:
            return r0
        L_0x004c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.model.C0799xce3d994b.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        long j = this.f80pc;
        int h$2 = (((h$ ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.symbol.hashCode()) * 1000003;
        String str = this.file;
        int h$3 = (h$2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        long j2 = this.offset;
        return ((h$3 ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.importance;
    }
}
