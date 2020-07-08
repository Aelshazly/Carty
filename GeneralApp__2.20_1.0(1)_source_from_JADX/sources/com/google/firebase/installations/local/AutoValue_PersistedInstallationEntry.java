package com.google.firebase.installations.local;

import com.google.firebase.installations.local.PersistedInstallation.RegistrationStatus;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
final class AutoValue_PersistedInstallationEntry extends PersistedInstallationEntry {
    private final String authToken;
    private final long expiresInSecs;
    private final String firebaseInstallationId;
    private final String fisError;
    private final String refreshToken;
    private final RegistrationStatus registrationStatus;
    private final long tokenCreationEpochInSecs;

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    static final class Builder extends com.google.firebase.installations.local.PersistedInstallationEntry.Builder {
        private String authToken;
        private Long expiresInSecs;
        private String firebaseInstallationId;
        private String fisError;
        private String refreshToken;
        private RegistrationStatus registrationStatus;
        private Long tokenCreationEpochInSecs;

        Builder() {
        }

        private Builder(PersistedInstallationEntry source) {
            this.firebaseInstallationId = source.getFirebaseInstallationId();
            this.registrationStatus = source.getRegistrationStatus();
            this.authToken = source.getAuthToken();
            this.refreshToken = source.getRefreshToken();
            this.expiresInSecs = Long.valueOf(source.getExpiresInSecs());
            this.tokenCreationEpochInSecs = Long.valueOf(source.getTokenCreationEpochInSecs());
            this.fisError = source.getFisError();
        }

        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setFirebaseInstallationId(String firebaseInstallationId2) {
            this.firebaseInstallationId = firebaseInstallationId2;
            return this;
        }

        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setRegistrationStatus(RegistrationStatus registrationStatus2) {
            if (registrationStatus2 != null) {
                this.registrationStatus = registrationStatus2;
                return this;
            }
            throw new NullPointerException("Null registrationStatus");
        }

        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setAuthToken(String authToken2) {
            this.authToken = authToken2;
            return this;
        }

        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setRefreshToken(String refreshToken2) {
            this.refreshToken = refreshToken2;
            return this;
        }

        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setExpiresInSecs(long expiresInSecs2) {
            this.expiresInSecs = Long.valueOf(expiresInSecs2);
            return this;
        }

        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setTokenCreationEpochInSecs(long tokenCreationEpochInSecs2) {
            this.tokenCreationEpochInSecs = Long.valueOf(tokenCreationEpochInSecs2);
            return this;
        }

        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setFisError(String fisError2) {
            this.fisError = fisError2;
            return this;
        }

        public PersistedInstallationEntry build() {
            String missing = "";
            if (this.registrationStatus == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" registrationStatus");
                missing = sb.toString();
            }
            if (this.expiresInSecs == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" expiresInSecs");
                missing = sb2.toString();
            }
            if (this.tokenCreationEpochInSecs == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" tokenCreationEpochInSecs");
                missing = sb3.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_PersistedInstallationEntry autoValue_PersistedInstallationEntry = new AutoValue_PersistedInstallationEntry(this.firebaseInstallationId, this.registrationStatus, this.authToken, this.refreshToken, this.expiresInSecs.longValue(), this.tokenCreationEpochInSecs.longValue(), this.fisError);
                return autoValue_PersistedInstallationEntry;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Missing required properties:");
            sb4.append(missing);
            throw new IllegalStateException(sb4.toString());
        }
    }

    private AutoValue_PersistedInstallationEntry(String firebaseInstallationId2, RegistrationStatus registrationStatus2, String authToken2, String refreshToken2, long expiresInSecs2, long tokenCreationEpochInSecs2, String fisError2) {
        this.firebaseInstallationId = firebaseInstallationId2;
        this.registrationStatus = registrationStatus2;
        this.authToken = authToken2;
        this.refreshToken = refreshToken2;
        this.expiresInSecs = expiresInSecs2;
        this.tokenCreationEpochInSecs = tokenCreationEpochInSecs2;
        this.fisError = fisError2;
    }

    public String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    public RegistrationStatus getRegistrationStatus() {
        return this.registrationStatus;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public long getExpiresInSecs() {
        return this.expiresInSecs;
    }

    public long getTokenCreationEpochInSecs() {
        return this.tokenCreationEpochInSecs;
    }

    public String getFisError() {
        return this.fisError;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PersistedInstallationEntry{firebaseInstallationId=");
        sb.append(this.firebaseInstallationId);
        sb.append(", registrationStatus=");
        sb.append(this.registrationStatus);
        sb.append(", authToken=");
        sb.append(this.authToken);
        sb.append(", refreshToken=");
        sb.append(this.refreshToken);
        sb.append(", expiresInSecs=");
        sb.append(this.expiresInSecs);
        sb.append(", tokenCreationEpochInSecs=");
        sb.append(this.tokenCreationEpochInSecs);
        sb.append(", fisError=");
        sb.append(this.fisError);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof PersistedInstallationEntry)) {
            return false;
        }
        PersistedInstallationEntry that = (PersistedInstallationEntry) o;
        String str = this.firebaseInstallationId;
        if (str != null ? str.equals(that.getFirebaseInstallationId()) : that.getFirebaseInstallationId() == null) {
            if (this.registrationStatus.equals(that.getRegistrationStatus())) {
                String str2 = this.authToken;
                if (str2 != null ? str2.equals(that.getAuthToken()) : that.getAuthToken() == null) {
                    String str3 = this.refreshToken;
                    if (str3 != null ? str3.equals(that.getRefreshToken()) : that.getRefreshToken() == null) {
                        if (this.expiresInSecs == that.getExpiresInSecs() && this.tokenCreationEpochInSecs == that.getTokenCreationEpochInSecs()) {
                            String str4 = this.fisError;
                            if (str4 != null) {
                            }
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
        String str = this.firebaseInstallationId;
        int i = 0;
        int h$2 = (((h$ ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.registrationStatus.hashCode()) * 1000003;
        String str2 = this.authToken;
        int h$3 = (h$2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.refreshToken;
        int h$4 = (h$3 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        long j = this.expiresInSecs;
        int h$5 = (h$4 ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.tokenCreationEpochInSecs;
        int h$6 = (h$5 ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003;
        String str4 = this.fisError;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return h$6 ^ i;
    }

    public com.google.firebase.installations.local.PersistedInstallationEntry.Builder toBuilder() {
        return new Builder(this);
    }
}
