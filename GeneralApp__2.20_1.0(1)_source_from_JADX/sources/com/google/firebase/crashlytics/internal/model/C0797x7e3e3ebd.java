package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame;

/* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread */
/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class C0797x7e3e3ebd extends Thread {
    private final ImmutableList<Frame> frames;
    private final int importance;
    private final String name;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread$Builder */
    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder {
        private ImmutableList<Frame> frames;
        private Integer importance;
        private String name;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setName(String name2) {
            if (name2 != null) {
                this.name = name2;
                return this;
            }
            throw new NullPointerException("Null name");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setImportance(int importance2) {
            this.importance = Integer.valueOf(importance2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setFrames(ImmutableList<Frame> frames2) {
            if (frames2 != null) {
                this.frames = frames2;
                return this;
            }
            throw new NullPointerException("Null frames");
        }

        public Thread build() {
            String missing = "";
            if (this.name == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" name");
                missing = sb.toString();
            }
            if (this.importance == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" importance");
                missing = sb2.toString();
            }
            if (this.frames == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" frames");
                missing = sb3.toString();
            }
            if (missing.isEmpty()) {
                return new C0797x7e3e3ebd(this.name, this.importance.intValue(), this.frames);
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Missing required properties:");
            sb4.append(missing);
            throw new IllegalStateException(sb4.toString());
        }
    }

    private C0797x7e3e3ebd(String name2, int importance2, ImmutableList<Frame> frames2) {
        this.name = name2;
        this.importance = importance2;
        this.frames = frames2;
    }

    public String getName() {
        return this.name;
    }

    public int getImportance() {
        return this.importance;
    }

    public ImmutableList<Frame> getFrames() {
        return this.frames;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Thread{name=");
        sb.append(this.name);
        sb.append(", importance=");
        sb.append(this.importance);
        sb.append(", frames=");
        sb.append(this.frames);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof Thread)) {
            return false;
        }
        Thread that = (Thread) o;
        if (!this.name.equals(that.getName()) || this.importance != that.getImportance() || !this.frames.equals(that.getFrames())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.importance) * 1000003) ^ this.frames.hashCode();
    }
}
