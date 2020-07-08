package com.google.firebase.installations.remote;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
public abstract class TokenResult {

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    public static abstract class Builder {
        public abstract TokenResult build();

        public abstract Builder setResponseCode(ResponseCode responseCode);

        public abstract Builder setToken(String str);

        public abstract Builder setTokenExpirationTimestamp(long j);
    }

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    public enum ResponseCode {
        OK,
        BAD_CONFIG,
        AUTH_ERROR
    }

    public abstract ResponseCode getResponseCode();

    public abstract String getToken();

    public abstract long getTokenExpirationTimestamp();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder().setTokenExpirationTimestamp(0);
    }
}
