package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.internal.Logger;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class DataCollectionArbiter {
    private static final String FIREBASE_CRASHLYTICS_COLLECTION_ENABLED = "firebase_crashlytics_collection_enabled";
    private volatile boolean crashlyticsDataCollectionEnabled;
    private volatile boolean crashlyticsDataCollectionExplicitlySet;
    TaskCompletionSource<Void> dataCollectionEnabledTask = new TaskCompletionSource<>();
    private TaskCompletionSource<Void> dataCollectionExplicitlyApproved = new TaskCompletionSource<>();
    private final FirebaseApp firebaseApp;
    private final SharedPreferences sharedPreferences;
    private Object taskLock = new Object();
    boolean taskResolved = false;

    public DataCollectionArbiter(FirebaseApp app) {
        this.firebaseApp = app;
        Context applicationContext = app.getApplicationContext();
        if (applicationContext != null) {
            this.sharedPreferences = CommonUtils.getSharedPrefs(applicationContext);
            boolean enabled = true;
            boolean explicitlySet = false;
            if (this.sharedPreferences.contains(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED)) {
                enabled = this.sharedPreferences.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, true);
                explicitlySet = true;
            } else {
                try {
                    PackageManager packageManager = applicationContext.getPackageManager();
                    if (packageManager != null) {
                        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128);
                        if (!(applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED))) {
                            enabled = applicationInfo.metaData.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED);
                            explicitlySet = true;
                        }
                    }
                } catch (NameNotFoundException e) {
                    Logger.getLogger().mo19680d("Unable to get PackageManager. Falling through", e);
                }
            }
            this.crashlyticsDataCollectionEnabled = enabled;
            this.crashlyticsDataCollectionExplicitlySet = explicitlySet;
            synchronized (this.taskLock) {
                if (isAutomaticDataCollectionEnabled()) {
                    this.dataCollectionEnabledTask.trySetResult(null);
                    this.taskResolved = true;
                }
            }
            return;
        }
        throw new RuntimeException("null context");
    }

    public boolean isAutomaticDataCollectionEnabled() {
        if (this.crashlyticsDataCollectionExplicitlySet) {
            return this.crashlyticsDataCollectionEnabled;
        }
        return this.firebaseApp.isDataCollectionDefaultEnabled();
    }

    public Task<Void> waitForAutomaticDataCollectionEnabled() {
        Task<Void> task;
        synchronized (this.taskLock) {
            task = this.dataCollectionEnabledTask.getTask();
        }
        return task;
    }

    public void setCrashlyticsDataCollectionEnabled(boolean enabled) {
        this.crashlyticsDataCollectionEnabled = enabled;
        this.crashlyticsDataCollectionExplicitlySet = true;
        this.sharedPreferences.edit().putBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, enabled).commit();
        synchronized (this.taskLock) {
            if (enabled) {
                if (!this.taskResolved) {
                    this.dataCollectionEnabledTask.trySetResult(null);
                    this.taskResolved = true;
                }
            } else if (this.taskResolved) {
                this.dataCollectionEnabledTask = new TaskCompletionSource<>();
                this.taskResolved = false;
            }
        }
    }

    public Task<Void> waitForDataCollectionPermission() {
        return Utils.race(this.dataCollectionExplicitlyApproved.getTask(), waitForAutomaticDataCollectionEnabled());
    }

    public void grantDataCollectionPermission(boolean dataCollectionToken) {
        if (dataCollectionToken) {
            this.dataCollectionExplicitlyApproved.trySetResult(null);
            return;
        }
        throw new IllegalStateException("An invalid data collection token was used.");
    }
}
