package com.google.firebase.installations;

/* compiled from: com.google.firebase:firebase-installations-interop@@16.0.0 */
public abstract class InstallationTokenResult {

    /* compiled from: com.google.firebase:firebase-installations-interop@@16.0.0 */
    public static abstract class Builder {
        public abstract InstallationTokenResult build();

        public abstract Builder setToken(String str);

        public abstract Builder setTokenCreationTimestamp(long j);

        public abstract Builder setTokenExpirationTimestamp(long j);
    }

    public abstract String getToken();

    public abstract long getTokenCreationTimestamp();

    public abstract long getTokenExpirationTimestamp();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder();
    }
}
