package com.google.firebase.installations.remote;

import com.google.firebase.installations.remote.InstallationResponse.ResponseCode;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
final class AutoValue_InstallationResponse extends InstallationResponse {
    private final TokenResult authToken;
    private final String fid;
    private final String refreshToken;
    private final ResponseCode responseCode;
    private final String uri;

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    static final class Builder extends com.google.firebase.installations.remote.InstallationResponse.Builder {
        private TokenResult authToken;
        private String fid;
        private String refreshToken;
        private ResponseCode responseCode;
        private String uri;

        Builder() {
        }

        private Builder(InstallationResponse source) {
            this.uri = source.getUri();
            this.fid = source.getFid();
            this.refreshToken = source.getRefreshToken();
            this.authToken = source.getAuthToken();
            this.responseCode = source.getResponseCode();
        }

        public com.google.firebase.installations.remote.InstallationResponse.Builder setUri(String uri2) {
            this.uri = uri2;
            return this;
        }

        public com.google.firebase.installations.remote.InstallationResponse.Builder setFid(String fid2) {
            this.fid = fid2;
            return this;
        }

        public com.google.firebase.installations.remote.InstallationResponse.Builder setRefreshToken(String refreshToken2) {
            this.refreshToken = refreshToken2;
            return this;
        }

        public com.google.firebase.installations.remote.InstallationResponse.Builder setAuthToken(TokenResult authToken2) {
            this.authToken = authToken2;
            return this;
        }

        public com.google.firebase.installations.remote.InstallationResponse.Builder setResponseCode(ResponseCode responseCode2) {
            this.responseCode = responseCode2;
            return this;
        }

        public InstallationResponse build() {
            AutoValue_InstallationResponse autoValue_InstallationResponse = new AutoValue_InstallationResponse(this.uri, this.fid, this.refreshToken, this.authToken, this.responseCode);
            return autoValue_InstallationResponse;
        }
    }

    private AutoValue_InstallationResponse(String uri2, String fid2, String refreshToken2, TokenResult authToken2, ResponseCode responseCode2) {
        this.uri = uri2;
        this.fid = fid2;
        this.refreshToken = refreshToken2;
        this.authToken = authToken2;
        this.responseCode = responseCode2;
    }

    public String getUri() {
        return this.uri;
    }

    public String getFid() {
        return this.fid;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public TokenResult getAuthToken() {
        return this.authToken;
    }

    public ResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InstallationResponse{uri=");
        sb.append(this.uri);
        sb.append(", fid=");
        sb.append(this.fid);
        sb.append(", refreshToken=");
        sb.append(this.refreshToken);
        sb.append(", authToken=");
        sb.append(this.authToken);
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
        if (!(o instanceof InstallationResponse)) {
            return false;
        }
        InstallationResponse that = (InstallationResponse) o;
        String str = this.uri;
        if (str != null ? str.equals(that.getUri()) : that.getUri() == null) {
            String str2 = this.fid;
            if (str2 != null ? str2.equals(that.getFid()) : that.getFid() == null) {
                String str3 = this.refreshToken;
                if (str3 != null ? str3.equals(that.getRefreshToken()) : that.getRefreshToken() == null) {
                    TokenResult tokenResult = this.authToken;
                    if (tokenResult != null ? tokenResult.equals(that.getAuthToken()) : that.getAuthToken() == null) {
                        ResponseCode responseCode2 = this.responseCode;
                        if (responseCode2 != null) {
                        }
                    }
                }
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        String str = this.uri;
        int i = 0;
        int h$2 = (h$ ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.fid;
        int h$3 = (h$2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.refreshToken;
        int h$4 = (h$3 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        TokenResult tokenResult = this.authToken;
        int h$5 = (h$4 ^ (tokenResult == null ? 0 : tokenResult.hashCode())) * 1000003;
        ResponseCode responseCode2 = this.responseCode;
        if (responseCode2 != null) {
            i = responseCode2.hashCode();
        }
        return h$5 ^ i;
    }

    public com.google.firebase.installations.remote.InstallationResponse.Builder toBuilder() {
        return new Builder(this);
    }
}
