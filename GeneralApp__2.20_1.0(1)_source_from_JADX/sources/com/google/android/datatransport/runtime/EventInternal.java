package com.google.android.datatransport.runtime;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public abstract class EventInternal {

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    public static abstract class Builder {
        public abstract EventInternal build();

        /* access modifiers changed from: protected */
        public abstract Map<String, String> getAutoMetadata();

        /* access modifiers changed from: protected */
        public abstract Builder setAutoMetadata(Map<String, String> map);

        public abstract Builder setCode(Integer num);

        public abstract Builder setEncodedPayload(EncodedPayload encodedPayload);

        public abstract Builder setEventMillis(long j);

        public abstract Builder setTransportName(String str);

        public abstract Builder setUptimeMillis(long j);

        public final Builder addMetadata(String key, String value) {
            getAutoMetadata().put(key, value);
            return this;
        }

        public final Builder addMetadata(String key, long value) {
            getAutoMetadata().put(key, String.valueOf(value));
            return this;
        }

        public final Builder addMetadata(String key, int value) {
            getAutoMetadata().put(key, String.valueOf(value));
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public abstract Map<String, String> getAutoMetadata();

    public abstract Integer getCode();

    public abstract EncodedPayload getEncodedPayload();

    public abstract long getEventMillis();

    public abstract String getTransportName();

    public abstract long getUptimeMillis();

    @Deprecated
    public byte[] getPayload() {
        return getEncodedPayload().getBytes();
    }

    public final Map<String, String> getMetadata() {
        return Collections.unmodifiableMap(getAutoMetadata());
    }

    public final String getOrDefault(String key, String defaultValue) {
        String value = (String) getAutoMetadata().get(key);
        return value == null ? defaultValue : value;
    }

    public final int getInteger(String key) {
        String value = (String) getAutoMetadata().get(key);
        if (value == null) {
            return 0;
        }
        return Integer.valueOf(value).intValue();
    }

    public final long getLong(String key) {
        String value = (String) getAutoMetadata().get(key);
        if (value == null) {
            return 0;
        }
        return Long.valueOf(value).longValue();
    }

    public final String get(String key) {
        String value = (String) getAutoMetadata().get(key);
        return value == null ? "" : value;
    }

    public Builder toBuilder() {
        return new Builder().setTransportName(getTransportName()).setCode(getCode()).setEncodedPayload(getEncodedPayload()).setEventMillis(getEventMillis()).setUptimeMillis(getUptimeMillis()).setAutoMetadata(new HashMap(getAutoMetadata()));
    }

    public static Builder builder() {
        return new Builder().setAutoMetadata(new HashMap());
    }
}
