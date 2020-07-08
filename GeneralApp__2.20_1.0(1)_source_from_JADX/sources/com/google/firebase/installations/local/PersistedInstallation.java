package com.google.firebase.installations.local;

import com.google.firebase.FirebaseApp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
public class PersistedInstallation {
    private static final String AUTH_TOKEN_KEY = "AuthToken";
    private static final String EXPIRES_IN_SECONDS_KEY = "ExpiresInSecs";
    private static final String FIREBASE_INSTALLATION_ID_KEY = "Fid";
    private static final String FIS_ERROR_KEY = "FisError";
    private static final String PERSISTED_STATUS_KEY = "Status";
    private static final String REFRESH_TOKEN_KEY = "RefreshToken";
    private static final String SETTINGS_FILE_NAME_PREFIX = "PersistedInstallation";
    private static final String TOKEN_CREATION_TIME_IN_SECONDS_KEY = "TokenCreationEpochInSecs";
    private final File dataFile;
    private final FirebaseApp firebaseApp;

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    public enum RegistrationStatus {
        ATTEMPT_MIGRATION,
        NOT_GENERATED,
        UNREGISTERED,
        REGISTERED,
        REGISTER_ERROR
    }

    public PersistedInstallation(FirebaseApp firebaseApp2) {
        File filesDir = firebaseApp2.getApplicationContext().getFilesDir();
        StringBuilder sb = new StringBuilder();
        sb.append("PersistedInstallation.");
        sb.append(firebaseApp2.getPersistenceKey());
        sb.append(".json");
        this.dataFile = new File(filesDir, sb.toString());
        this.firebaseApp = firebaseApp2;
    }

    public PersistedInstallationEntry readPersistedInstallationEntryValue() {
        JSONObject json = readJSONFromFile();
        String fid = json.optString(FIREBASE_INSTALLATION_ID_KEY, null);
        int status = json.optInt(PERSISTED_STATUS_KEY, RegistrationStatus.ATTEMPT_MIGRATION.ordinal());
        String authToken = json.optString(AUTH_TOKEN_KEY, null);
        String refreshToken = json.optString(REFRESH_TOKEN_KEY, null);
        long tokenCreationTime = json.optLong(TOKEN_CREATION_TIME_IN_SECONDS_KEY, 0);
        long expiresIn = json.optLong(EXPIRES_IN_SECONDS_KEY, 0);
        return PersistedInstallationEntry.builder().setFirebaseInstallationId(fid).setRegistrationStatus(RegistrationStatus.values()[status]).setAuthToken(authToken).setRefreshToken(refreshToken).setTokenCreationEpochInSecs(tokenCreationTime).setExpiresInSecs(expiresIn).setFisError(json.optString(FIS_ERROR_KEY, null)).build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject readJSONFromFile() {
        /*
            r5 = this;
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 16384(0x4000, float:2.2959E-41)
            byte[] r1 = new byte[r1]
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0035, JSONException -> 0x0033 }
            java.io.File r3 = r5.dataFile     // Catch:{ IOException -> 0x0035, JSONException -> 0x0033 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0035, JSONException -> 0x0033 }
        L_0x0010:
            int r3 = r1.length     // Catch:{ all -> 0x002a }
            r4 = 0
            int r3 = r2.read(r1, r4, r3)     // Catch:{ all -> 0x002a }
            if (r3 >= 0) goto L_0x0026
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ all -> 0x002a }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x002a }
            r3.<init>(r4)     // Catch:{ all -> 0x002a }
            r2.close()     // Catch:{ IOException -> 0x0035, JSONException -> 0x0033 }
            return r3
        L_0x0026:
            r0.write(r1, r4, r3)     // Catch:{ all -> 0x002a }
            goto L_0x0010
        L_0x002a:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x002c }
        L_0x002c:
            r3 = move-exception
            r2.close()     // Catch:{ all -> 0x0031 }
            goto L_0x0032
        L_0x0031:
            r4 = move-exception
        L_0x0032:
            throw r3     // Catch:{ IOException -> 0x0035, JSONException -> 0x0033 }
        L_0x0033:
            r2 = move-exception
            goto L_0x0036
        L_0x0035:
            r2 = move-exception
        L_0x0036:
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.local.PersistedInstallation.readJSONFromFile():org.json.JSONObject");
    }

    public PersistedInstallationEntry insertOrUpdatePersistedInstallationEntry(PersistedInstallationEntry prefs) {
        try {
            JSONObject json = new JSONObject();
            json.put(FIREBASE_INSTALLATION_ID_KEY, prefs.getFirebaseInstallationId());
            json.put(PERSISTED_STATUS_KEY, prefs.getRegistrationStatus().ordinal());
            json.put(AUTH_TOKEN_KEY, prefs.getAuthToken());
            json.put(REFRESH_TOKEN_KEY, prefs.getRefreshToken());
            json.put(TOKEN_CREATION_TIME_IN_SECONDS_KEY, prefs.getTokenCreationEpochInSecs());
            json.put(EXPIRES_IN_SECONDS_KEY, prefs.getExpiresInSecs());
            json.put(FIS_ERROR_KEY, prefs.getFisError());
            File tmpFile = File.createTempFile(SETTINGS_FILE_NAME_PREFIX, "tmp", this.firebaseApp.getApplicationContext().getFilesDir());
            FileOutputStream fos = new FileOutputStream(tmpFile);
            fos.write(json.toString().getBytes("UTF-8"));
            fos.close();
            if (tmpFile.renameTo(this.dataFile)) {
                return prefs;
            }
            throw new IOException("unable to rename the tmpfile to PersistedInstallation");
        } catch (IOException | JSONException e) {
        }
    }

    public void clearForTesting() {
        this.dataFile.delete();
    }
}
