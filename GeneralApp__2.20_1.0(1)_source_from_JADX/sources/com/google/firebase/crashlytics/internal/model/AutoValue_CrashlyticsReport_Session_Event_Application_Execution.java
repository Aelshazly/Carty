package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution extends Execution {
    private final ImmutableList<BinaryImage> binaries;
    private final Exception exception;
    private final Signal signal;
    private final ImmutableList<Thread> threads;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder {
        private ImmutableList<BinaryImage> binaries;
        private Exception exception;
        private Signal signal;
        private ImmutableList<Thread> threads;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder setThreads(ImmutableList<Thread> threads2) {
            if (threads2 != null) {
                this.threads = threads2;
                return this;
            }
            throw new NullPointerException("Null threads");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder setException(Exception exception2) {
            if (exception2 != null) {
                this.exception = exception2;
                return this;
            }
            throw new NullPointerException("Null exception");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder setSignal(Signal signal2) {
            if (signal2 != null) {
                this.signal = signal2;
                return this;
            }
            throw new NullPointerException("Null signal");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder setBinaries(ImmutableList<BinaryImage> binaries2) {
            if (binaries2 != null) {
                this.binaries = binaries2;
                return this;
            }
            throw new NullPointerException("Null binaries");
        }

        public Execution build() {
            String missing = "";
            if (this.threads == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" threads");
                missing = sb.toString();
            }
            if (this.exception == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" exception");
                missing = sb2.toString();
            }
            if (this.signal == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" signal");
                missing = sb3.toString();
            }
            if (this.binaries == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(missing);
                sb4.append(" binaries");
                missing = sb4.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_CrashlyticsReport_Session_Event_Application_Execution autoValue_CrashlyticsReport_Session_Event_Application_Execution = new AutoValue_CrashlyticsReport_Session_Event_Application_Execution(this.threads, this.exception, this.signal, this.binaries);
                return autoValue_CrashlyticsReport_Session_Event_Application_Execution;
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Missing required properties:");
            sb5.append(missing);
            throw new IllegalStateException(sb5.toString());
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution(ImmutableList<Thread> threads2, Exception exception2, Signal signal2, ImmutableList<BinaryImage> binaries2) {
        this.threads = threads2;
        this.exception = exception2;
        this.signal = signal2;
        this.binaries = binaries2;
    }

    public ImmutableList<Thread> getThreads() {
        return this.threads;
    }

    public Exception getException() {
        return this.exception;
    }

    public Signal getSignal() {
        return this.signal;
    }

    public ImmutableList<BinaryImage> getBinaries() {
        return this.binaries;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Execution{threads=");
        sb.append(this.threads);
        sb.append(", exception=");
        sb.append(this.exception);
        sb.append(", signal=");
        sb.append(this.signal);
        sb.append(", binaries=");
        sb.append(this.binaries);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof Execution)) {
            return false;
        }
        Execution that = (Execution) o;
        if (!this.threads.equals(that.getThreads()) || !this.exception.equals(that.getException()) || !this.signal.equals(that.getSignal()) || !this.binaries.equals(that.getBinaries())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((((((1 * 1000003) ^ this.threads.hashCode()) * 1000003) ^ this.exception.hashCode()) * 1000003) ^ this.signal.hashCode()) * 1000003) ^ this.binaries.hashCode();
    }
}
