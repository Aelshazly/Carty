package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal;

/* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal */
/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class C0795x7c929f5b extends Signal {
    private final long address;
    private final String code;
    private final String name;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal$Builder */
    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder {
        private Long address;
        private String code;
        private String name;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder setName(String name2) {
            if (name2 != null) {
                this.name = name2;
                return this;
            }
            throw new NullPointerException("Null name");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder setCode(String code2) {
            if (code2 != null) {
                this.code = code2;
                return this;
            }
            throw new NullPointerException("Null code");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder setAddress(long address2) {
            this.address = Long.valueOf(address2);
            return this;
        }

        public Signal build() {
            String missing = "";
            if (this.name == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" name");
                missing = sb.toString();
            }
            if (this.code == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" code");
                missing = sb2.toString();
            }
            if (this.address == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" address");
                missing = sb3.toString();
            }
            if (missing.isEmpty()) {
                C0795x7c929f5b autoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal = new C0795x7c929f5b(this.name, this.code, this.address.longValue());
                return autoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Missing required properties:");
            sb4.append(missing);
            throw new IllegalStateException(sb4.toString());
        }
    }

    private C0795x7c929f5b(String name2, String code2, long address2) {
        this.name = name2;
        this.code = code2;
        this.address = address2;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public long getAddress() {
        return this.address;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Signal{name=");
        sb.append(this.name);
        sb.append(", code=");
        sb.append(this.code);
        sb.append(", address=");
        sb.append(this.address);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof Signal)) {
            return false;
        }
        Signal that = (Signal) o;
        if (!this.name.equals(that.getName()) || !this.code.equals(that.getCode()) || this.address != that.getAddress()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int h$ = ((((1 * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.code.hashCode()) * 1000003;
        long j = this.address;
        return h$ ^ ((int) (j ^ (j >>> 32)));
    }
}
