package com.google.firebase.installations.remote;

import com.google.firebase.installations.remote.TokenResult.ResponseCode;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
final class AutoValue_TokenResult extends TokenResult {
    private final ResponseCode responseCode;
    private final String token;
    private final long tokenExpirationTimestamp;

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    static final class Builder extends com.google.firebase.installations.remote.TokenResult.Builder {
        private ResponseCode responseCode;
        private String token;
        private Long tokenExpirationTimestamp;

        Builder() {
        }

        private Builder(TokenResult source) {
            this.token = source.getToken();
            this.tokenExpirationTimestamp = Long.valueOf(source.getTokenExpirationTimestamp());
            this.responseCode = source.getResponseCode();
        }

        public com.google.firebase.installations.remote.TokenResult.Builder setToken(String token2) {
            this.token = token2;
            return this;
        }

        public com.google.firebase.installations.remote.TokenResult.Builder setTokenExpirationTimestamp(long tokenExpirationTimestamp2) {
            this.tokenExpirationTimestamp = Long.valueOf(tokenExpirationTimestamp2);
            return this;
        }

        public com.google.firebase.installations.remote.TokenResult.Builder setResponseCode(ResponseCode responseCode2) {
            this.responseCode = responseCode2;
            return this;
        }

        public TokenResult build() {
            String missing = "";
            if (this.tokenExpirationTimestamp == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" tokenExpirationTimestamp");
                missing = sb.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_TokenResult autoValue_TokenResult = new AutoValue_TokenResult(this.token, this.tokenExpirationTimestamp.longValue(), this.responseCode);
                return autoValue_TokenResult;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Missing required properties:");
            sb2.append(missing);
            throw new IllegalStateException(sb2.toString());
        }
    }

    private AutoValue_TokenResult(String token2, long tokenExpirationTimestamp2, ResponseCode responseCode2) {
        this.token = token2;
        this.tokenExpirationTimestamp = tokenExpirationTimestamp2;
        this.responseCode = responseCode2;
    }

    public String getToken() {
        return this.token;
    }

    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    public ResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TokenResult{token=");
        sb.append(this.token);
        sb.append(", tokenExpirationTimestamp=");
        sb.append(this.tokenExpirationTimestamp);
        sb.append(", responseCode=");
        sb.append(this.responseCode);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof TokenResult)) {
            return false;
        }
        TokenResult that = (TokenResult) o;
        String str = this.token;
        if (str != null ? str.equals(that.getToken()) : that.getToken() == null) {
            if (this.tokenExpirationTimestamp == that.getTokenExpirationTimestamp()) {
                ResponseCode responseCode2 = this.responseCode;
                if (responseCode2 != null) {
                }
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        String str = this.token;
        int i = 0;
        int h$2 = (h$ ^ (str == null ? 0 : str.hashCode())) * 1000003;
        long j = this.tokenExpirationTimestamp;
        int h$3 = (h$2 ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        ResponseCode responseCode2 = this.responseCode;
        if (responseCode2 != null) {
            i = responseCode2.hashCode();
        }
        return h$3 ^ i;
    }

    public com.google.firebase.installations.remote.TokenResult.Builder toBuilder() {
        return new Builder(this);
    }
}
