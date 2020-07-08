package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.BuildConfig;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsConnectorReceiver;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsConnectorReceiver.BreadcrumbHandler;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.persistence.FileStoreImpl;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import com.google.firebase.crashlytics.internal.settings.model.Settings;
import com.google.firebase.crashlytics.internal.unity.ResourceUnityVersionProvider;
import com.google.firebase.crashlytics.internal.unity.UnityVersionProvider;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class CrashlyticsCore {
    private static final float CLS_DEFAULT_PROCESS_DELAY = 1.0f;
    static final String CRASHLYTICS_REQUIRE_BUILD_ID = "com.crashlytics.RequireBuildId";
    static final boolean CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT = true;
    static final String CRASH_MARKER_FILE_NAME = "crash_marker";
    static final int DEFAULT_MAIN_HANDLER_TIMEOUT_SEC = 4;
    private static final String INITIALIZATION_MARKER_FILE_NAME = "initialization_marker";
    private static final String MISSING_BUILD_ID_MSG = "The Crashlytics build ID is missing. This occurs when Crashlytics tooling is absent from your app's build configuration. Please review Crashlytics onboarding instructions and ensure you have a valid Crashlytics account.";
    private final AnalyticsConnector analyticsConnector;
    private final FirebaseApp app;
    private CrashlyticsBackgroundWorker backgroundWorker;
    private final Context context;
    /* access modifiers changed from: private */
    public CrashlyticsController controller;
    private ExecutorService crashHandlerExecutor;
    private CrashlyticsFileMarker crashMarker;
    private final DataCollectionArbiter dataCollectionArbiter;
    private boolean didCrashOnPreviousExecution;
    private final IdManager idManager;
    /* access modifiers changed from: private */
    public CrashlyticsFileMarker initializationMarker;
    private CrashlyticsNativeComponent nativeComponent;
    private final long startTime;

    public CrashlyticsCore(FirebaseApp app2, IdManager idManager2, CrashlyticsNativeComponent nativeComponent2, DataCollectionArbiter dataCollectionArbiter2, AnalyticsConnector analyticsConnector2) {
        this(app2, idManager2, nativeComponent2, dataCollectionArbiter2, analyticsConnector2, ExecutorUtils.buildSingleThreadExecutorService("Crashlytics Exception Handler"));
    }

    CrashlyticsCore(FirebaseApp app2, IdManager idManager2, CrashlyticsNativeComponent nativeComponent2, DataCollectionArbiter dataCollectionArbiter2, AnalyticsConnector analyticsConnector2, ExecutorService crashHandlerExecutor2) {
        this.app = app2;
        this.dataCollectionArbiter = dataCollectionArbiter2;
        this.context = app2.getApplicationContext();
        this.idManager = idManager2;
        this.nativeComponent = nativeComponent2;
        this.analyticsConnector = analyticsConnector2;
        this.crashHandlerExecutor = crashHandlerExecutor2;
        this.backgroundWorker = new CrashlyticsBackgroundWorker(crashHandlerExecutor2);
        this.startTime = System.currentTimeMillis();
    }

    public boolean onPreExecute(SettingsDataProvider settingsProvider) {
        String mappingFileId = CommonUtils.getMappingFileId(this.context);
        Logger logger = Logger.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append("Mapping file ID is: ");
        sb.append(mappingFileId);
        logger.mo19679d(sb.toString());
        if (isBuildIdValid(mappingFileId, CommonUtils.getBooleanResourceValue(this.context, CRASHLYTICS_REQUIRE_BUILD_ID, CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT))) {
            String googleAppId = this.app.getOptions().getApplicationId();
            try {
                Logger logger2 = Logger.getLogger();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Initializing Crashlytics ");
                sb2.append(getVersion());
                logger2.mo19683i(sb2.toString());
                FileStoreImpl fileStoreImpl = new FileStoreImpl(this.context);
                this.crashMarker = new CrashlyticsFileMarker(CRASH_MARKER_FILE_NAME, fileStoreImpl);
                this.initializationMarker = new CrashlyticsFileMarker(INITIALIZATION_MARKER_FILE_NAME, fileStoreImpl);
                HttpRequestFactory httpRequestFactory = new HttpRequestFactory();
                AppData appData = AppData.create(this.context, this.idManager, googleAppId, mappingFileId);
                UnityVersionProvider unityVersionProvider = new ResourceUnityVersionProvider(this.context);
                AnalyticsReceiver analyticsReceiver = new AnalyticsConnectorReceiver(this.analyticsConnector, new BreadcrumbHandler() {
                    public void dropBreadcrumb(String breadcrumb) {
                        CrashlyticsCore.this.log(breadcrumb);
                    }
                });
                Logger logger3 = Logger.getLogger();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Installer package name is: ");
                sb3.append(appData.installerPackageName);
                logger3.mo19679d(sb3.toString());
                Context context2 = this.context;
                CrashlyticsBackgroundWorker crashlyticsBackgroundWorker = this.backgroundWorker;
                IdManager idManager2 = this.idManager;
                DataCollectionArbiter dataCollectionArbiter2 = this.dataCollectionArbiter;
                CrashlyticsFileMarker crashlyticsFileMarker = this.crashMarker;
                CrashlyticsFileMarker crashlyticsFileMarker2 = crashlyticsFileMarker;
                FileStoreImpl fileStoreImpl2 = fileStoreImpl;
                CrashlyticsController crashlyticsController = r8;
                CrashlyticsFileMarker crashlyticsFileMarker3 = crashlyticsFileMarker2;
                AppData appData2 = appData;
                CrashlyticsController crashlyticsController2 = new CrashlyticsController(context2, crashlyticsBackgroundWorker, httpRequestFactory, idManager2, dataCollectionArbiter2, fileStoreImpl2, crashlyticsFileMarker3, appData2, null, null, this.nativeComponent, unityVersionProvider, analyticsReceiver, this.analyticsConnector, settingsProvider);
                this.controller = crashlyticsController;
                boolean initializeSynchronously = didPreviousInitializationFail();
                checkForPreviousCrash();
                try {
                    this.controller.enableExceptionHandling(Thread.getDefaultUncaughtExceptionHandler(), settingsProvider);
                    if (!initializeSynchronously || !CommonUtils.canTryConnection(this.context)) {
                        Logger.getLogger().mo19679d("Exception handling initialization successful");
                        return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
                    }
                    Logger.getLogger().mo19679d("Crashlytics did not finish previous background initialization. Initializing synchronously.");
                    finishInitSynchronously(settingsProvider);
                    return false;
                } catch (Exception e) {
                    e = e;
                    Logger.getLogger().mo19682e("Crashlytics was not started due to an exception during initialization", e);
                    this.controller = null;
                    return false;
                }
            } catch (Exception e2) {
                e = e2;
                SettingsDataProvider settingsDataProvider = settingsProvider;
                Logger.getLogger().mo19682e("Crashlytics was not started due to an exception during initialization", e);
                this.controller = null;
                return false;
            }
        } else {
            SettingsDataProvider settingsDataProvider2 = settingsProvider;
            throw new IllegalStateException(MISSING_BUILD_ID_MSG);
        }
    }

    public Task<Void> doBackgroundInitializationAsync(final SettingsDataProvider settingsProvider) {
        return Utils.callTask(this.crashHandlerExecutor, new Callable<Task<Void>>() {
            public Task<Void> call() throws Exception {
                return CrashlyticsCore.this.doBackgroundInitialization(settingsProvider);
            }
        });
    }

    /* access modifiers changed from: private */
    public Task<Void> doBackgroundInitialization(SettingsDataProvider settingsProvider) {
        String str = "Collection of crash reports disabled in Crashlytics settings.";
        markInitializationStarted();
        this.controller.cleanInvalidTempFiles();
        try {
            this.controller.registerAnalyticsListener();
            Settings settingsData = settingsProvider.getSettings();
            if (!settingsData.getFeaturesData().collectReports) {
                Logger.getLogger().mo19679d(str);
                return Tasks.forException(new RuntimeException(str));
            }
            if (!this.controller.finalizeSessions(settingsData.getSessionData().maxCustomExceptionEvents)) {
                Logger.getLogger().mo19679d("Could not finalize previous sessions.");
            }
            Task<Void> submitAllReports = this.controller.submitAllReports(1.0f, settingsProvider.getAppSettings());
            markInitializationComplete();
            return submitAllReports;
        } catch (Exception e) {
            Logger.getLogger().mo19682e("Crashlytics encountered a problem during asynchronous initialization.", e);
            return Tasks.forException(e);
        } finally {
            markInitializationComplete();
        }
    }

    public void setCrashlyticsCollectionEnabled(boolean enabled) {
        this.dataCollectionArbiter.setCrashlyticsDataCollectionEnabled(enabled);
    }

    public Task<Boolean> checkForUnsentReports() {
        return this.controller.checkForUnsentReports();
    }

    public Task<Void> sendUnsentReports() {
        return this.controller.sendUnsentReports();
    }

    public Task<Void> deleteUnsentReports() {
        return this.controller.deleteUnsentReports();
    }

    public static String getVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public void logException(Throwable throwable) {
        this.controller.writeNonFatalException(Thread.currentThread(), throwable);
    }

    public void log(String msg) {
        this.controller.writeToLog(System.currentTimeMillis() - this.startTime, msg);
    }

    public void setUserId(String identifier) {
        this.controller.setUserId(identifier);
    }

    public void setCustomKey(String key, String value) {
        this.controller.setCustomKey(key, value);
    }

    /* access modifiers changed from: 0000 */
    public CrashlyticsController getController() {
        return this.controller;
    }

    private void finishInitSynchronously(final SettingsDataProvider settingsDataProvider) {
        Future<?> future = this.crashHandlerExecutor.submit(new Runnable() {
            public void run() {
                CrashlyticsCore.this.doBackgroundInitialization(settingsDataProvider);
            }
        });
        Logger.getLogger().mo19679d("Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        try {
            future.get(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Logger.getLogger().mo19682e("Crashlytics was interrupted during initialization.", e);
        } catch (ExecutionException e2) {
            Logger.getLogger().mo19682e("Problem encountered during Crashlytics initialization.", e2);
        } catch (TimeoutException e3) {
            Logger.getLogger().mo19682e("Crashlytics timed out during initialization.", e3);
        }
    }

    /* access modifiers changed from: 0000 */
    public void markInitializationStarted() {
        this.backgroundWorker.checkRunningOnThread();
        this.initializationMarker.create();
        Logger.getLogger().mo19679d("Initialization marker file created.");
    }

    /* access modifiers changed from: 0000 */
    public void markInitializationComplete() {
        this.backgroundWorker.submit((Callable<T>) new Callable<Boolean>() {
            public Boolean call() throws Exception {
                try {
                    boolean removed = CrashlyticsCore.this.initializationMarker.remove();
                    Logger logger = Logger.getLogger();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Initialization marker file removed: ");
                    sb.append(removed);
                    logger.mo19679d(sb.toString());
                    return Boolean.valueOf(removed);
                } catch (Exception e) {
                    Logger.getLogger().mo19682e("Problem encountered deleting Crashlytics initialization marker.", e);
                    return Boolean.valueOf(false);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public boolean didPreviousInitializationFail() {
        return this.initializationMarker.isPresent();
    }

    private void checkForPreviousCrash() {
        try {
            this.didCrashOnPreviousExecution = Boolean.TRUE.equals((Boolean) Utils.awaitEvenIfOnMainThread(this.backgroundWorker.submit((Callable<T>) new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return Boolean.valueOf(CrashlyticsCore.this.controller.didCrashOnPreviousExecution());
                }
            })));
        } catch (Exception e) {
            this.didCrashOnPreviousExecution = false;
        }
    }

    public boolean didCrashOnPreviousExecution() {
        return this.didCrashOnPreviousExecution;
    }

    static boolean isBuildIdValid(String buildId, boolean requiresBuildId) {
        if (!requiresBuildId) {
            Logger.getLogger().mo19679d("Configured not to require a build ID.");
            return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        } else if (!CommonUtils.isNullOrEmpty(buildId)) {
            return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        } else {
            String str = ".";
            String str2 = Logger.TAG;
            Log.e(str2, str);
            Log.e(str2, ".     |  | ");
            String str3 = ".     |  |";
            Log.e(str2, str3);
            Log.e(str2, str3);
            Log.e(str2, ".   \\ |  | /");
            Log.e(str2, ".    \\    /");
            Log.e(str2, ".     \\  /");
            Log.e(str2, ".      \\/");
            Log.e(str2, str);
            Log.e(str2, MISSING_BUILD_ID_MSG);
            Log.e(str2, str);
            Log.e(str2, ".      /\\");
            Log.e(str2, ".     /  \\");
            Log.e(str2, ".    /    \\");
            Log.e(str2, ".   / |  | \\");
            Log.e(str2, str3);
            Log.e(str2, str3);
            Log.e(str2, str3);
            Log.e(str2, str);
            return false;
        }
    }
}
