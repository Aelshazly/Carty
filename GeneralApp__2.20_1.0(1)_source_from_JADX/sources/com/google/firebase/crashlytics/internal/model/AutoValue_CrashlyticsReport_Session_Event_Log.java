package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_Session_Event_Log extends Log {
    private final String content;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log.Builder {
        private String content;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log.Builder setContent(String content2) {
            if (content2 != null) {
                this.content = content2;
                return this;
            }
            throw new NullPointerException("Null content");
        }

        public Log build() {
            String missing = "";
            if (this.content == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" content");
                missing = sb.toString();
            }
            if (missing.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_Event_Log(this.content);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Missing required properties:");
            sb2.append(missing);
            throw new IllegalStateException(sb2.toString());
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Log(String content2) {
        this.content = content2;
    }

    public String getContent() {
        return this.content;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Log{content=");
        sb.append(this.content);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Log)) {
            return false;
        }
        return this.content.equals(((Log) o).getContent());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.content.hashCode();
    }
}
