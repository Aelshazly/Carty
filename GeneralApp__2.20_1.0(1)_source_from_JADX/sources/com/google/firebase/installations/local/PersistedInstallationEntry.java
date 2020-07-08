package com.google.firebase.installations.local;

import com.google.firebase.installations.local.PersistedInstallation.RegistrationStatus;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
public abstract class PersistedInstallationEntry {
    public static PersistedInstallationEntry INSTANCE = builder().build();

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    public static abstract class Builder {
        public abstract PersistedInstallationEntry build();

        public abstract Builder setAuthToken(String str);

        public abstract Builder setExpiresInSecs(long j);

        public abstract Builder setFirebaseInstallationId(String str);

        public abstract Builder setFisError(String str);

        public abstract Builder setRefreshToken(String str);

        public abstract Builder setRegistrationStatus(RegistrationStatus registrationStatus);

        public abstract Builder setTokenCreationEpochInSecs(long j);
    }

    public abstract String getAuthToken();

    public abstract long getExpiresInSecs();

    public abstract String getFirebaseInstallationId();

    public abstract String getFisError();

    public abstract String getRefreshToken();

    public abstract RegistrationStatus getRegistrationStatus();

    public abstract long getTokenCreationEpochInSecs();

    public abstract Builder toBuilder();

    public boolean isRegistered() {
        return getRegistrationStatus() == RegistrationStatus.REGISTERED;
    }

    public boolean isErrored() {
        return getRegistrationStatus() == RegistrationStatus.REGISTER_ERROR;
    }

    public boolean isUnregistered() {
        return getRegistrationStatus() == RegistrationStatus.UNREGISTERED;
    }

    public boolean isNotGenerated() {
        return getRegistrationStatus() == RegistrationStatus.NOT_GENERATED || getRegistrationStatus() == RegistrationStatus.ATTEMPT_MIGRATION;
    }

    public boolean shouldAttemptMigration() {
        return getRegistrationStatus() == RegistrationStatus.ATTEMPT_MIGRATION;
    }

    public PersistedInstallationEntry withUnregisteredFid(String fid) {
        return toBuilder().setFirebaseInstallationId(fid).setRegistrationStatus(RegistrationStatus.UNREGISTERED).build();
    }

    public PersistedInstallationEntry withRegisteredFid(String fid, String refreshToken, long creationTime, String authToken, long authTokenExpiration) {
        return toBuilder().setFirebaseInstallationId(fid).setRegistrationStatus(RegistrationStatus.REGISTERED).setAuthToken(authToken).setRefreshToken(refreshToken).setExpiresInSecs(authTokenExpiration).setTokenCreationEpochInSecs(creationTime).build();
    }

    public PersistedInstallationEntry withFisError(String message) {
        return toBuilder().setFisError(message).setRegistrationStatus(RegistrationStatus.REGISTER_ERROR).build();
    }

    public PersistedInstallationEntry withNoGeneratedFid() {
        return toBuilder().setRegistrationStatus(RegistrationStatus.NOT_GENERATED).build();
    }

    public PersistedInstallationEntry withAuthToken(String authToken, long authTokenExpiration, long creationTime) {
        return toBuilder().setAuthToken(authToken).setExpiresInSecs(authTokenExpiration).setTokenCreationEpochInSecs(creationTime).build();
    }

    public PersistedInstallationEntry withClearedAuthToken() {
        return toBuilder().setAuthToken(null).build();
    }

    public static Builder builder() {
        return new Builder().setTokenCreationEpochInSecs(0).setRegistrationStatus(RegistrationStatus.ATTEMPT_MIGRATION).setExpiresInSecs(0);
    }
}
