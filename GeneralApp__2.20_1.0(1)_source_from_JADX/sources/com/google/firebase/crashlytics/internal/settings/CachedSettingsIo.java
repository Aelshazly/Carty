package com.google.firebase.crashlytics.internal.settings;

import android.content.Context;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.persistence.FileStoreImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class CachedSettingsIo {
    private static final String SETTINGS_CACHE_FILENAME = "com.crashlytics.settings.json";
    private final Context context;

    public CachedSettingsIo(Context context2) {
        this.context = context2;
    }

    private File getSettingsFile() {
        return new File(new FileStoreImpl(this.context).getFilesDir(), SETTINGS_CACHE_FILENAME);
    }

    public JSONObject readCachedSettings() {
        String str = "Error while closing settings cache file.";
        Logger.getLogger().mo19679d("Reading cached settings...");
        FileInputStream fis = null;
        JSONObject toReturn = null;
        try {
            File settingsFile = getSettingsFile();
            if (settingsFile.exists()) {
                fis = new FileInputStream(settingsFile);
                toReturn = new JSONObject(CommonUtils.streamToString(fis));
            } else {
                Logger.getLogger().mo19679d("No cached settings found.");
            }
        } catch (Exception e) {
            Logger.getLogger().mo19682e("Failed to fetch cached settings", e);
        } catch (Throwable th) {
            CommonUtils.closeOrLog(null, str);
            throw th;
        }
        CommonUtils.closeOrLog(fis, str);
        return toReturn;
    }

    public void writeCachedSettings(long expiresAtMillis, JSONObject settingsJson) {
        String str = "Failed to close settings writer.";
        Logger.getLogger().mo19679d("Writing settings to cache file...");
        if (settingsJson != null) {
            FileWriter writer = null;
            try {
                settingsJson.put("expires_at", expiresAtMillis);
                writer = new FileWriter(getSettingsFile());
                writer.write(settingsJson.toString());
                writer.flush();
            } catch (Exception e) {
                Logger.getLogger().mo19682e("Failed to cache settings", e);
            } catch (Throwable th) {
                CommonUtils.closeOrLog(writer, str);
                throw th;
            }
            CommonUtils.closeOrLog(writer, str);
        }
    }
}
