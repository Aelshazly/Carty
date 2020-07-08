package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_Session_Event extends Event {
    private final Application app;
    private final Device device;
    private final Log log;
    private final long timestamp;
    private final String type;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder {
        private Application app;
        private Device device;
        private Log log;
        private Long timestamp;
        private String type;

        Builder() {
        }

        private Builder(Event source) {
            this.timestamp = Long.valueOf(source.getTimestamp());
            this.type = source.getType();
            this.app = source.getApp();
            this.device = source.getDevice();
            this.log = source.getLog();
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder setTimestamp(long timestamp2) {
            this.timestamp = Long.valueOf(timestamp2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder setType(String type2) {
            if (type2 != null) {
                this.type = type2;
                return this;
            }
            throw new NullPointerException("Null type");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder setApp(Application app2) {
            if (app2 != null) {
                this.app = app2;
                return this;
            }
            throw new NullPointerException("Null app");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder setDevice(Device device2) {
            if (device2 != null) {
                this.device = device2;
                return this;
            }
            throw new NullPointerException("Null device");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder setLog(Log log2) {
            this.log = log2;
            return this;
        }

        public Event build() {
            String missing = "";
            if (this.timestamp == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" timestamp");
                missing = sb.toString();
            }
            if (this.type == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" type");
                missing = sb2.toString();
            }
            if (this.app == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" app");
                missing = sb3.toString();
            }
            if (this.device == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(missing);
                sb4.append(" device");
                missing = sb4.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_CrashlyticsReport_Session_Event autoValue_CrashlyticsReport_Session_Event = new AutoValue_CrashlyticsReport_Session_Event(this.timestamp.longValue(), this.type, this.app, this.device, this.log);
                return autoValue_CrashlyticsReport_Session_Event;
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Missing required properties:");
            sb5.append(missing);
            throw new IllegalStateException(sb5.toString());
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event(long timestamp2, String type2, Application app2, Device device2, Log log2) {
        this.timestamp = timestamp2;
        this.type = type2;
        this.app = app2;
        this.device = device2;
        this.log = log2;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getType() {
        return this.type;
    }

    public Application getApp() {
        return this.app;
    }

    public Device getDevice() {
        return this.device;
    }

    public Log getLog() {
        return this.log;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event{timestamp=");
        sb.append(this.timestamp);
        sb.append(", type=");
        sb.append(this.type);
        sb.append(", app=");
        sb.append(this.app);
        sb.append(", device=");
        sb.append(this.device);
        sb.append(", log=");
        sb.append(this.log);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event that = (Event) o;
        if (this.timestamp == that.getTimestamp() && this.type.equals(that.getType()) && this.app.equals(that.getApp()) && this.device.equals(that.getDevice())) {
            Log log2 = this.log;
            if (log2 != null) {
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        long j = this.timestamp;
        int h$2 = (((((((h$ ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.type.hashCode()) * 1000003) ^ this.app.hashCode()) * 1000003) ^ this.device.hashCode()) * 1000003;
        Log log2 = this.log;
        return h$2 ^ (log2 == null ? 0 : log2.hashCode());
    }

    public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder toBuilder() {
        return new Builder(this);
    }
}
