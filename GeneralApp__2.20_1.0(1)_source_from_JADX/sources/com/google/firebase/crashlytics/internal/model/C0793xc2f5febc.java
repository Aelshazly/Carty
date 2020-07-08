package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame;

/* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception */
/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class C0793xc2f5febc extends Exception {
    private final Exception causedBy;
    private final ImmutableList<Frame> frames;
    private final int overflowCount;
    private final String reason;
    private final String type;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception$Builder */
    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder {
        private Exception causedBy;
        private ImmutableList<Frame> frames;
        private Integer overflowCount;
        private String reason;
        private String type;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setType(String type2) {
            if (type2 != null) {
                this.type = type2;
                return this;
            }
            throw new NullPointerException("Null type");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setReason(String reason2) {
            this.reason = reason2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setFrames(ImmutableList<Frame> frames2) {
            if (frames2 != null) {
                this.frames = frames2;
                return this;
            }
            throw new NullPointerException("Null frames");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setCausedBy(Exception causedBy2) {
            this.causedBy = causedBy2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setOverflowCount(int overflowCount2) {
            this.overflowCount = Integer.valueOf(overflowCount2);
            return this;
        }

        public Exception build() {
            String missing = "";
            if (this.type == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" type");
                missing = sb.toString();
            }
            if (this.frames == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" frames");
                missing = sb2.toString();
            }
            if (this.overflowCount == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" overflowCount");
                missing = sb3.toString();
            }
            if (missing.isEmpty()) {
                C0793xc2f5febc autoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception = new C0793xc2f5febc(this.type, this.reason, this.frames, this.causedBy, this.overflowCount.intValue());
                return autoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Missing required properties:");
            sb4.append(missing);
            throw new IllegalStateException(sb4.toString());
        }
    }

    private C0793xc2f5febc(String type2, String reason2, ImmutableList<Frame> frames2, Exception causedBy2, int overflowCount2) {
        this.type = type2;
        this.reason = reason2;
        this.frames = frames2;
        this.causedBy = causedBy2;
        this.overflowCount = overflowCount2;
    }

    public String getType() {
        return this.type;
    }

    public String getReason() {
        return this.reason;
    }

    public ImmutableList<Frame> getFrames() {
        return this.frames;
    }

    public Exception getCausedBy() {
        return this.causedBy;
    }

    public int getOverflowCount() {
        return this.overflowCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Exception{type=");
        sb.append(this.type);
        sb.append(", reason=");
        sb.append(this.reason);
        sb.append(", frames=");
        sb.append(this.frames);
        sb.append(", causedBy=");
        sb.append(this.causedBy);
        sb.append(", overflowCount=");
        sb.append(this.overflowCount);
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        if (r5.overflowCount == r1.getOverflowCount()) goto L_0x0058;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r6 instanceof com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception
            r2 = 0
            if (r1 == 0) goto L_0x0059
            r1 = r6
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$Execution$Exception r1 = (com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception) r1
            java.lang.String r3 = r5.type
            java.lang.String r4 = r1.getType()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0057
            java.lang.String r3 = r5.reason
            if (r3 != 0) goto L_0x0023
            java.lang.String r3 = r1.getReason()
            if (r3 != 0) goto L_0x0057
            goto L_0x002d
        L_0x0023:
            java.lang.String r4 = r1.getReason()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0057
        L_0x002d:
            com.google.firebase.crashlytics.internal.model.ImmutableList<com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$Execution$Thread$Frame> r3 = r5.frames
            com.google.firebase.crashlytics.internal.model.ImmutableList r4 = r1.getFrames()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0057
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$Execution$Exception r3 = r5.causedBy
            if (r3 != 0) goto L_0x0044
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$Execution$Exception r3 = r1.getCausedBy()
            if (r3 != 0) goto L_0x0057
            goto L_0x004e
        L_0x0044:
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$Execution$Exception r4 = r1.getCausedBy()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0057
        L_0x004e:
            int r3 = r5.overflowCount
            int r4 = r1.getOverflowCount()
            if (r3 != r4) goto L_0x0057
            goto L_0x0058
        L_0x0057:
            r0 = 0
        L_0x0058:
            return r0
        L_0x0059:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.model.C0793xc2f5febc.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int h$ = ((1 * 1000003) ^ this.type.hashCode()) * 1000003;
        String str = this.reason;
        int i = 0;
        int h$2 = (((h$ ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.frames.hashCode()) * 1000003;
        Exception exception = this.causedBy;
        if (exception != null) {
            i = exception.hashCode();
        }
        return ((h$2 ^ i) * 1000003) ^ this.overflowCount;
    }
}
