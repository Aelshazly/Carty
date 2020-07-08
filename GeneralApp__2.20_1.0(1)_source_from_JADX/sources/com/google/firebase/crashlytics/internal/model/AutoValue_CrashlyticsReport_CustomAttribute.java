package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_CustomAttribute extends CustomAttribute {
    private final String key;
    private final String value;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute.Builder {
        private String key;
        private String value;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute.Builder setKey(String key2) {
            if (key2 != null) {
                this.key = key2;
                return this;
            }
            throw new NullPointerException("Null key");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute.Builder setValue(String value2) {
            if (value2 != null) {
                this.value = value2;
                return this;
            }
            throw new NullPointerException("Null value");
        }

        public CustomAttribute build() {
            String missing = "";
            if (this.key == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" key");
                missing = sb.toString();
            }
            if (this.value == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" value");
                missing = sb2.toString();
            }
            if (missing.isEmpty()) {
                return new AutoValue_CrashlyticsReport_CustomAttribute(this.key, this.value);
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Missing required properties:");
            sb3.append(missing);
            throw new IllegalStateException(sb3.toString());
        }
    }

    private AutoValue_CrashlyticsReport_CustomAttribute(String key2, String value2) {
        this.key = key2;
        this.value = value2;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CustomAttribute{key=");
        sb.append(this.key);
        sb.append(", value=");
        sb.append(this.value);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof CustomAttribute)) {
            return false;
        }
        CustomAttribute that = (CustomAttribute) o;
        if (!this.key.equals(that.getKey()) || !this.value.equals(that.getValue())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.key.hashCode()) * 1000003) ^ this.value.hashCode();
    }
}
