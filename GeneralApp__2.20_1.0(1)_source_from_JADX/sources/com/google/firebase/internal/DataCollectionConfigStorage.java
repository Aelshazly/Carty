package com.google.firebase.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import androidx.core.content.ContextCompat;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
public class DataCollectionConfigStorage {
    public static final String DATA_COLLECTION_DEFAULT_ENABLED = "firebase_data_collection_default_enabled";
    private static final String FIREBASE_APP_PREFS = "com.google.firebase.common.prefs:";
    private final Context applicationContext;
    private final AtomicBoolean dataCollectionDefaultEnabled = new AtomicBoolean(readAutoDataCollectionEnabled());
    private final Publisher publisher;
    private final SharedPreferences sharedPreferences;

    public DataCollectionConfigStorage(Context applicationContext2, String persistenceKey, Publisher publisher2) {
        this.applicationContext = directBootSafe(applicationContext2);
        StringBuilder sb = new StringBuilder();
        sb.append(FIREBASE_APP_PREFS);
        sb.append(persistenceKey);
        this.sharedPreferences = applicationContext2.getSharedPreferences(sb.toString(), 0);
        this.publisher = publisher2;
    }

    private static Context directBootSafe(Context applicationContext2) {
        if (VERSION.SDK_INT < 24 || ContextCompat.isDeviceProtectedStorage(applicationContext2)) {
            return applicationContext2;
        }
        return ContextCompat.createDeviceProtectedStorageContext(applicationContext2);
    }

    public boolean isEnabled() {
        return this.dataCollectionDefaultEnabled.get();
    }

    public void setEnabled(boolean enabled) {
        if (this.dataCollectionDefaultEnabled.compareAndSet(!enabled, enabled)) {
            this.sharedPreferences.edit().putBoolean(DATA_COLLECTION_DEFAULT_ENABLED, enabled).apply();
            this.publisher.publish(new Event(DataCollectionDefaultChange.class, new DataCollectionDefaultChange(enabled)));
        }
    }

    private boolean readAutoDataCollectionEnabled() {
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        String str = DATA_COLLECTION_DEFAULT_ENABLED;
        if (sharedPreferences2.contains(str)) {
            return this.sharedPreferences.getBoolean(str, true);
        }
        try {
            PackageManager packageManager = this.applicationContext.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.applicationContext.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(str))) {
                    return applicationInfo.metaData.getBoolean(str);
                }
            }
        } catch (NameNotFoundException e) {
        }
        return true;
    }
}
