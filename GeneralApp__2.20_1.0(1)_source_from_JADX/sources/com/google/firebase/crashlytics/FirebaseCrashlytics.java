package com.google.firebase.crashlytics;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.MissingNativeComponent;
import com.google.firebase.crashlytics.internal.Onboarding;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.ExecutorUtils;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.settings.SettingsController;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class FirebaseCrashlytics {
    private final CrashlyticsCore core;

    static FirebaseCrashlytics init(FirebaseApp app, FirebaseInstanceIdInternal instanceId, CrashlyticsNativeComponent nativeComponent, AnalyticsConnector analyticsConnector) {
        CrashlyticsNativeComponent nativeComponent2;
        FirebaseApp firebaseApp = app;
        Context context = app.getApplicationContext();
        IdManager idManager = new IdManager(context, context.getPackageName(), instanceId);
        DataCollectionArbiter arbiter = new DataCollectionArbiter(firebaseApp);
        if (nativeComponent == null) {
            nativeComponent2 = new MissingNativeComponent();
        } else {
            nativeComponent2 = nativeComponent;
        }
        Onboarding onboarding = new Onboarding(firebaseApp, context, idManager, arbiter);
        CrashlyticsCore core2 = new CrashlyticsCore(app, idManager, nativeComponent2, arbiter, analyticsConnector);
        if (!onboarding.onPreExecute()) {
            Logger.getLogger().mo19681e("Unable to start Crashlytics.");
            return null;
        }
        ExecutorService threadPoolExecutor = ExecutorUtils.buildSingleThreadExecutorService("com.google.firebase.crashlytics.startup");
        SettingsController settingsController = onboarding.retrieveSettingsData(context, firebaseApp, threadPoolExecutor);
        final Onboarding onboarding2 = onboarding;
        Onboarding onboarding3 = onboarding;
        final ExecutorService executorService = threadPoolExecutor;
        final SettingsController settingsController2 = settingsController;
        final boolean onPreExecute = core2.onPreExecute(settingsController);
        final CrashlyticsCore crashlyticsCore = core2;
        C07241 r13 = new Callable<Void>() {
            public Void call() throws Exception {
                Onboarding.this.doOnboarding(executorService, settingsController2);
                if (onPreExecute) {
                    crashlyticsCore.doBackgroundInitializationAsync(settingsController2);
                }
                return null;
            }
        };
        Tasks.call(threadPoolExecutor, r13);
        return new FirebaseCrashlytics(core2);
    }

    private FirebaseCrashlytics(CrashlyticsCore core2) {
        this.core = core2;
    }

    public static FirebaseCrashlytics getInstance() {
        FirebaseCrashlytics instance = (FirebaseCrashlytics) FirebaseApp.getInstance().get(FirebaseCrashlytics.class);
        if (instance != null) {
            return instance;
        }
        throw new NullPointerException("FirebaseCrashlytics component is not present.");
    }

    public void recordException(Throwable throwable) {
        if (throwable == null) {
            Logger.getLogger().mo19689w("Crashlytics is ignoring a request to log a null exception.");
        } else {
            this.core.logException(throwable);
        }
    }

    public void log(String message) {
        this.core.log(message);
    }

    public void setUserId(String identifier) {
        this.core.setUserId(identifier);
    }

    public void setCustomKey(String key, boolean value) {
        this.core.setCustomKey(key, Boolean.toString(value));
    }

    public void setCustomKey(String key, double value) {
        this.core.setCustomKey(key, Double.toString(value));
    }

    public void setCustomKey(String key, float value) {
        this.core.setCustomKey(key, Float.toString(value));
    }

    public void setCustomKey(String key, int value) {
        this.core.setCustomKey(key, Integer.toString(value));
    }

    public void setCustomKey(String key, long value) {
        this.core.setCustomKey(key, Long.toString(value));
    }

    public void setCustomKey(String key, String value) {
        this.core.setCustomKey(key, value);
    }

    public Task<Boolean> checkForUnsentReports() {
        return this.core.checkForUnsentReports();
    }

    public void sendUnsentReports() {
        this.core.sendUnsentReports();
    }

    public void deleteUnsentReports() {
        this.core.deleteUnsentReports();
    }

    public boolean didCrashOnPreviousExecution() {
        return this.core.didCrashOnPreviousExecution();
    }

    public void setCrashlyticsCollectionEnabled(boolean enabled) {
        this.core.setCrashlyticsCollectionEnabled(enabled);
    }
}
