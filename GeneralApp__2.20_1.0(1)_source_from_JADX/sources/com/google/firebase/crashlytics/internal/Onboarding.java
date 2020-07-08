package com.google.firebase.crashlytics.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.DeliveryMechanism;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.settings.SettingsCacheBehavior;
import com.google.firebase.crashlytics.internal.settings.SettingsController;
import com.google.firebase.crashlytics.internal.settings.model.AppRequestData;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.network.CreateAppSpiCall;
import com.google.firebase.crashlytics.internal.settings.network.UpdateAppSpiCall;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class Onboarding {
    static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private final FirebaseApp app;
    private String applicationLabel;
    private final Context context;
    private DataCollectionArbiter dataCollectionArbiter;
    private IdManager idManager;
    private String installerPackageName;
    private PackageInfo packageInfo;
    private PackageManager packageManager;
    private String packageName;
    private final HttpRequestFactory requestFactory = new HttpRequestFactory();
    private String targetAndroidSdkVersion;
    private String versionCode;
    private String versionName;

    public Onboarding(FirebaseApp app2, Context context2, IdManager idManager2, DataCollectionArbiter dataCollectionArbiter2) {
        this.app = app2;
        this.context = context2;
        this.idManager = idManager2;
        this.dataCollectionArbiter = dataCollectionArbiter2;
    }

    public Context getContext() {
        return this.context;
    }

    private static String getVersion() {
        return CrashlyticsCore.getVersion();
    }

    public boolean onPreExecute() {
        String str;
        try {
            this.installerPackageName = this.idManager.getInstallerPackageName();
            this.packageManager = this.context.getPackageManager();
            this.packageName = this.context.getPackageName();
            this.packageInfo = this.packageManager.getPackageInfo(this.packageName, 0);
            this.versionCode = Integer.toString(this.packageInfo.versionCode);
            if (this.packageInfo.versionName == null) {
                str = IdManager.DEFAULT_VERSION_NAME;
            } else {
                str = this.packageInfo.versionName;
            }
            this.versionName = str;
            this.applicationLabel = this.packageManager.getApplicationLabel(this.context.getApplicationInfo()).toString();
            this.targetAndroidSdkVersion = Integer.toString(this.context.getApplicationInfo().targetSdkVersion);
            return true;
        } catch (NameNotFoundException e) {
            Logger.getLogger().mo19682e("Failed init", e);
            return false;
        }
    }

    public void doOnboarding(final Executor backgroundExecutor, final SettingsController settingsDataController) {
        final String googleAppId = this.app.getOptions().getApplicationId();
        this.dataCollectionArbiter.waitForDataCollectionPermission().onSuccessTask(backgroundExecutor, new SuccessContinuation<Void, AppSettingsData>() {
            public Task<AppSettingsData> then(Void ignored) throws Exception {
                return settingsDataController.getAppSettings();
            }
        }).onSuccessTask(backgroundExecutor, new SuccessContinuation<AppSettingsData, Void>() {
            public Task<Void> then(AppSettingsData appSettingsData) throws Exception {
                try {
                    Onboarding.this.performAutoConfigure(appSettingsData, googleAppId, settingsDataController, backgroundExecutor, true);
                    return null;
                } catch (Exception e) {
                    Logger.getLogger().mo19682e("Error performing auto configuration.", e);
                    throw e;
                }
            }
        });
    }

    public SettingsController retrieveSettingsData(Context context2, FirebaseApp app2, Executor backgroundExecutor) {
        SettingsController controller = SettingsController.create(context2, app2.getOptions().getApplicationId(), this.idManager, this.requestFactory, this.versionCode, this.versionName, getOverridenSpiEndpoint(), this.dataCollectionArbiter);
        controller.loadSettingsData(backgroundExecutor).continueWith(backgroundExecutor, new Continuation<Void, Object>() {
            public Object then(Task<Void> task) throws Exception {
                if (!task.isSuccessful()) {
                    Logger.getLogger().mo19682e("Error fetching settings.", task.getException());
                }
                return null;
            }
        });
        return controller;
    }

    /* access modifiers changed from: private */
    public void performAutoConfigure(AppSettingsData appSettings, String googleAppId, SettingsController settingsController, Executor backgroundExecutor, boolean dataCollectionToken) {
        if (!AppSettingsData.STATUS_NEW.equals(appSettings.status)) {
            if (AppSettingsData.STATUS_CONFIGURED.equals(appSettings.status)) {
                settingsController.loadSettingsData(SettingsCacheBehavior.SKIP_CACHE_LOOKUP, backgroundExecutor);
            } else if (appSettings.updateRequired) {
                Logger.getLogger().mo19679d("Server says an update is required - forcing a full App update.");
                performUpdateApp(appSettings, googleAppId, dataCollectionToken);
            }
        } else if (performCreateApp(appSettings, googleAppId, dataCollectionToken)) {
            settingsController.loadSettingsData(SettingsCacheBehavior.SKIP_CACHE_LOOKUP, backgroundExecutor);
        } else {
            Logger.getLogger().mo19682e("Failed to create app with Crashlytics service.", null);
        }
    }

    private boolean performCreateApp(AppSettingsData appSettings, String googleAppId, boolean dataCollectionToken) {
        return new CreateAppSpiCall(getOverridenSpiEndpoint(), appSettings.url, this.requestFactory, getVersion()).invoke(buildAppRequest(appSettings.organizationId, googleAppId), dataCollectionToken);
    }

    private boolean performUpdateApp(AppSettingsData appSettings, String googleAppId, boolean dataCollectionToken) {
        return new UpdateAppSpiCall(getOverridenSpiEndpoint(), appSettings.url, this.requestFactory, getVersion()).invoke(buildAppRequest(appSettings.organizationId, googleAppId), dataCollectionToken);
    }

    private AppRequestData buildAppRequest(String organizationId, String googleAppId) {
        String instanceId = CommonUtils.createInstanceIdFrom(CommonUtils.getMappingFileId(getContext()), googleAppId, this.versionName, this.versionCode);
        int source = DeliveryMechanism.determineFrom(this.installerPackageName).getId();
        AppRequestData appRequestData = new AppRequestData(organizationId, googleAppId, getIdManager().getAppIdentifier(), this.versionName, this.versionCode, instanceId, this.applicationLabel, source, this.targetAndroidSdkVersion, "0");
        return appRequestData;
    }

    /* access modifiers changed from: 0000 */
    public String getOverridenSpiEndpoint() {
        return CommonUtils.getStringsFileValue(this.context, CRASHLYTICS_API_ENDPOINT);
    }

    private IdManager getIdManager() {
        return this.idManager;
    }
}
