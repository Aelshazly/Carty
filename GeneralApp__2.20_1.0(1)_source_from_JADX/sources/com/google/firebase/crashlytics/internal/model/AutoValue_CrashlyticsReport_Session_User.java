package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_Session_User extends User {
    private final String identifier;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User.Builder {
        private String identifier;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User.Builder setIdentifier(String identifier2) {
            if (identifier2 != null) {
                this.identifier = identifier2;
                return this;
            }
            throw new NullPointerException("Null identifier");
        }

        public User build() {
            String missing = "";
            if (this.identifier == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" identifier");
                missing = sb.toString();
            }
            if (missing.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_User(this.identifier);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Missing required properties:");
            sb2.append(missing);
            throw new IllegalStateException(sb2.toString());
        }
    }

    private AutoValue_CrashlyticsReport_Session_User(String identifier2) {
        this.identifier = identifier2;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{identifier=");
        sb.append(this.identifier);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return this.identifier.equals(((User) o).getIdentifier());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.identifier.hashCode();
    }
}
