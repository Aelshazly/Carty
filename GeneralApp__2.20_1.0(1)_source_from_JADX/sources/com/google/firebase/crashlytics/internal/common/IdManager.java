package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class IdManager implements InstallIdProvider {
    public static final String DEFAULT_VERSION_NAME = "0.0";
    private static final String FORWARD_SLASH_REGEX = Pattern.quote("/");
    private static final Pattern ID_PATTERN = Pattern.compile("[^\\p{Alnum}]");
    static final String PREFKEY_ADVERTISING_ID = "crashlytics.advertising.id";
    static final String PREFKEY_FIREBASE_IID = "firebase.installation.id";
    static final String PREFKEY_INSTALLATION_UUID = "crashlytics.installation.id";
    static final String PREFKEY_LEGACY_INSTALLATION_UUID = "crashlytics.installation.id";
    private final Context appContext;
    private final String appIdentifier;
    private String crashlyticsInstallId;
    private final FirebaseInstanceIdInternal firebaseInstallId;
    private final InstallerPackageNameProvider installerPackageNameProvider;

    public IdManager(Context appContext2, String appIdentifier2, FirebaseInstanceIdInternal firebaseInstallId2) {
        if (appContext2 == null) {
            throw new IllegalArgumentException("appContext must not be null");
        } else if (appIdentifier2 != null) {
            this.appContext = appContext2;
            this.appIdentifier = appIdentifier2;
            this.firebaseInstallId = firebaseInstallId2;
            this.installerPackageNameProvider = new InstallerPackageNameProvider();
        } else {
            throw new IllegalArgumentException("appIdentifier must not be null");
        }
    }

    private static String formatId(String id) {
        if (id == null) {
            return null;
        }
        return ID_PATTERN.matcher(id).replaceAll("").toLowerCase(Locale.US);
    }

    public synchronized String getCrashlyticsInstallId() {
        if (this.crashlyticsInstallId != null) {
            return this.crashlyticsInstallId;
        }
        SharedPreferences prefs = CommonUtils.getSharedPrefs(this.appContext);
        String currentFid = this.firebaseInstallId.getId();
        String cachedFid = prefs.getString(PREFKEY_FIREBASE_IID, null);
        if (cachedFid == null) {
            SharedPreferences legacyPrefs = CommonUtils.getLegacySharedPrefs(this.appContext);
            String legacyId = legacyPrefs.getString("crashlytics.installation.id", null);
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("No cached FID; legacy id is ");
            sb.append(legacyId);
            logger.mo19679d(sb.toString());
            if (legacyId == null) {
                this.crashlyticsInstallId = createAndStoreIid(currentFid, prefs);
            } else {
                this.crashlyticsInstallId = legacyId;
                migrateLegacyId(legacyId, currentFid, prefs, legacyPrefs);
            }
            return this.crashlyticsInstallId;
        }
        if (cachedFid.equals(currentFid)) {
            this.crashlyticsInstallId = prefs.getString("crashlytics.installation.id", null);
            Logger logger2 = Logger.getLogger();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Found matching FID, using Crashlytics IID: ");
            sb2.append(this.crashlyticsInstallId);
            logger2.mo19679d(sb2.toString());
            if (this.crashlyticsInstallId == null) {
                this.crashlyticsInstallId = createAndStoreIid(currentFid, prefs);
            }
        } else {
            this.crashlyticsInstallId = createAndStoreIid(currentFid, prefs);
        }
        return this.crashlyticsInstallId;
    }

    private synchronized void migrateLegacyId(String legacyId, String fidToCache, SharedPreferences prefs, SharedPreferences legacyPrefs) {
        Logger logger = Logger.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append("Migrating legacy Crashlytics IID: ");
        sb.append(legacyId);
        logger.mo19679d(sb.toString());
        prefs.edit().putString("crashlytics.installation.id", legacyId).putString(PREFKEY_FIREBASE_IID, fidToCache).apply();
        legacyPrefs.edit().remove("crashlytics.installation.id").remove(PREFKEY_ADVERTISING_ID).apply();
    }

    private synchronized String createAndStoreIid(String fidToCache, SharedPreferences prefs) {
        String iid;
        iid = formatId(UUID.randomUUID().toString());
        Logger logger = Logger.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append("Created new Crashlytics IID: ");
        sb.append(iid);
        logger.mo19679d(sb.toString());
        prefs.edit().putString("crashlytics.installation.id", iid).putString(PREFKEY_FIREBASE_IID, fidToCache).apply();
        return iid;
    }

    public String getAppIdentifier() {
        return this.appIdentifier;
    }

    public String getOsDisplayVersionString() {
        return removeForwardSlashesIn(VERSION.RELEASE);
    }

    public String getOsBuildVersionString() {
        return removeForwardSlashesIn(VERSION.INCREMENTAL);
    }

    public String getModelName() {
        return String.format(Locale.US, "%s/%s", new Object[]{removeForwardSlashesIn(Build.MANUFACTURER), removeForwardSlashesIn(Build.MODEL)});
    }

    private String removeForwardSlashesIn(String s) {
        return s.replaceAll(FORWARD_SLASH_REGEX, "");
    }

    public String getInstallerPackageName() {
        return this.installerPackageNameProvider.getInstallerPackageName(this.appContext);
    }
}
