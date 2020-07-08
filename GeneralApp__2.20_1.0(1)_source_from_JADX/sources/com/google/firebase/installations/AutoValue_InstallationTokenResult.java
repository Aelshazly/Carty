package com.google.firebase.installations;

/* compiled from: com.google.firebase:firebase-installations-interop@@16.0.0 */
final class AutoValue_InstallationTokenResult extends InstallationTokenResult {
    private final String token;
    private final long tokenCreationTimestamp;
    private final long tokenExpirationTimestamp;

    /* compiled from: com.google.firebase:firebase-installations-interop@@16.0.0 */
    static final class Builder extends com.google.firebase.installations.InstallationTokenResult.Builder {
        private String token;
        private Long tokenCreationTimestamp;
        private Long tokenExpirationTimestamp;

        Builder() {
        }

        private Builder(InstallationTokenResult source) {
            this.token = source.getToken();
            this.tokenExpirationTimestamp = Long.valueOf(source.getTokenExpirationTimestamp());
            this.tokenCreationTimestamp = Long.valueOf(source.getTokenCreationTimestamp());
        }

        public com.google.firebase.installations.InstallationTokenResult.Builder setToken(String token2) {
            if (token2 != null) {
                this.token = token2;
                return this;
            }
            throw new NullPointerException("Null token");
        }

        public com.google.firebase.installations.InstallationTokenResult.Builder setTokenExpirationTimestamp(long tokenExpirationTimestamp2) {
            this.tokenExpirationTimestamp = Long.valueOf(tokenExpirationTimestamp2);
            return this;
        }

        public com.google.firebase.installations.InstallationTokenResult.Builder setTokenCreationTimestamp(long tokenCreationTimestamp2) {
            this.tokenCreationTimestamp = Long.valueOf(tokenCreationTimestamp2);
            return this;
        }

        public InstallationTokenResult build() {
            String missing = "";
            if (this.token == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" token");
                missing = sb.toString();
            }
            if (this.tokenExpirationTimestamp == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" tokenExpirationTimestamp");
                missing = sb2.toString();
            }
            if (this.tokenCreationTimestamp == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" tokenCreationTimestamp");
                missing = sb3.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_InstallationTokenResult autoValue_InstallationTokenResult = new AutoValue_InstallationTokenResult(this.token, this.tokenExpirationTimestamp.longValue(), this.tokenCreationTimestamp.longValue());
                return autoValue_InstallationTokenResult;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Missing required properties:");
            sb4.append(missing);
            throw new IllegalStateException(sb4.toString());
        }
    }

    private AutoValue_InstallationTokenResult(String token2, long tokenExpirationTimestamp2, long tokenCreationTimestamp2) {
        this.token = token2;
        this.tokenExpirationTimestamp = tokenExpirationTimestamp2;
        this.tokenCreationTimestamp = tokenCreationTimestamp2;
    }

    public String getToken() {
        return this.token;
    }

    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    public long getTokenCreationTimestamp() {
        return this.tokenCreationTimestamp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InstallationTokenResult{token=");
        sb.append(this.token);
        sb.append(", tokenExpirationTimestamp=");
        sb.append(this.tokenExpirationTimestamp);
        sb.append(", tokenCreationTimestamp=");
        sb.append(this.tokenCreationTimestamp);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof InstallationTokenResult)) {
            return false;
        }
        InstallationTokenResult that = (InstallationTokenResult) o;
        if (!(this.token.equals(that.getToken()) && this.tokenExpirationTimestamp == that.getTokenExpirationTimestamp() && this.tokenCreationTimestamp == that.getTokenCreationTimestamp())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int h$ = ((1 * 1000003) ^ this.token.hashCode()) * 1000003;
        long j = this.tokenExpirationTimestamp;
        int h$2 = (h$ ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.tokenCreationTimestamp;
        return h$2 ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    public com.google.firebase.installations.InstallationTokenResult.Builder toBuilder() {
        return new Builder(this);
    }
}
