package com.google.firebase.installations;

import android.text.TextUtils;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
class Utils {
    public static final long AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS = TimeUnit.HOURS.toSeconds(1);

    Utils() {
    }

    public boolean isAuthTokenExpired(PersistedInstallationEntry entry) {
        if (!TextUtils.isEmpty(entry.getAuthToken()) && entry.getTokenCreationEpochInSecs() + entry.getExpiresInSecs() >= currentTimeInSecs() + AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS) {
            return false;
        }
        return true;
    }

    public long currentTimeInSecs() {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }
}
