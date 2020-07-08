package com.google.firebase.crashlytics.internal.settings;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.DeliveryMechanism;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.common.SystemCurrentTimeProvider;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.Settings;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import com.google.firebase.crashlytics.internal.settings.model.SettingsRequest;
import com.google.firebase.crashlytics.internal.settings.network.DefaultSettingsSpiCall;
import com.google.firebase.crashlytics.internal.settings.network.SettingsSpiCall;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class SettingsController implements SettingsDataProvider {
    private static final String PREFS_BUILD_INSTANCE_IDENTIFIER = "existing_instance_identifier";
    private static final String SETTINGS_URL_FORMAT = "https://firebase-settings.crashlytics.com/spi/v2/platforms/android/gmp/%s/settings";
    /* access modifiers changed from: private */
    public final AtomicReference<TaskCompletionSource<AppSettingsData>> appSettingsData = new AtomicReference<>(new TaskCompletionSource());
    /* access modifiers changed from: private */
    public final CachedSettingsIo cachedSettingsIo;
    private final Context context;
    private final CurrentTimeProvider currentTimeProvider;
    private final DataCollectionArbiter dataCollectionArbiter;
    /* access modifiers changed from: private */
    public final AtomicReference<Settings> settings = new AtomicReference<>();
    /* access modifiers changed from: private */
    public final SettingsJsonParser settingsJsonParser;
    /* access modifiers changed from: private */
    public final SettingsRequest settingsRequest;
    /* access modifiers changed from: private */
    public final SettingsSpiCall settingsSpiCall;

    SettingsController(Context context2, SettingsRequest settingsRequest2, CurrentTimeProvider currentTimeProvider2, SettingsJsonParser settingsJsonParser2, CachedSettingsIo cachedSettingsIo2, SettingsSpiCall settingsSpiCall2, DataCollectionArbiter dataCollectionArbiter2) {
        this.context = context2;
        this.settingsRequest = settingsRequest2;
        this.currentTimeProvider = currentTimeProvider2;
        this.settingsJsonParser = settingsJsonParser2;
        this.cachedSettingsIo = cachedSettingsIo2;
        this.settingsSpiCall = settingsSpiCall2;
        this.dataCollectionArbiter = dataCollectionArbiter2;
        this.settings.set(DefaultSettingsJsonTransform.defaultSettings(currentTimeProvider2));
    }

    public static SettingsController create(Context context2, String googleAppId, IdManager idManager, HttpRequestFactory httpRequestFactory, String versionCode, String versionName, String urlEndpoint, DataCollectionArbiter dataCollectionArbiter2) {
        String installerPackageName = idManager.getInstallerPackageName();
        SystemCurrentTimeProvider systemCurrentTimeProvider = new SystemCurrentTimeProvider();
        SettingsJsonParser settingsJsonParser2 = new SettingsJsonParser(systemCurrentTimeProvider);
        Context context3 = context2;
        CachedSettingsIo cachedSettingsIo2 = new CachedSettingsIo(context3);
        String settingsUrl = String.format(Locale.US, SETTINGS_URL_FORMAT, new Object[]{googleAppId});
        SettingsSpiCall settingsSpiCall2 = new DefaultSettingsSpiCall(urlEndpoint, settingsUrl, httpRequestFactory);
        SettingsRequest settingsRequest2 = new SettingsRequest(googleAppId, idManager.getModelName(), idManager.getOsBuildVersionString(), idManager.getOsDisplayVersionString(), idManager, CommonUtils.createInstanceIdFrom(CommonUtils.getMappingFileId(context2), googleAppId, versionName, versionCode), versionName, versionCode, DeliveryMechanism.determineFrom(installerPackageName).getId());
        String str = settingsUrl;
        SystemCurrentTimeProvider systemCurrentTimeProvider2 = systemCurrentTimeProvider;
        SettingsController settingsController = new SettingsController(context3, settingsRequest2, systemCurrentTimeProvider, settingsJsonParser2, cachedSettingsIo2, settingsSpiCall2, dataCollectionArbiter2);
        return settingsController;
    }

    public Settings getSettings() {
        return (Settings) this.settings.get();
    }

    public Task<AppSettingsData> getAppSettings() {
        return ((TaskCompletionSource) this.appSettingsData.get()).getTask();
    }

    public Task<Void> loadSettingsData(Executor executor) {
        return loadSettingsData(SettingsCacheBehavior.USE_CACHE, executor);
    }

    public Task<Void> loadSettingsData(SettingsCacheBehavior cacheBehavior, Executor executor) {
        if (!buildInstanceIdentifierChanged()) {
            SettingsData cachedSettings = getCachedSettingsData(cacheBehavior);
            if (cachedSettings != null) {
                this.settings.set(cachedSettings);
                ((TaskCompletionSource) this.appSettingsData.get()).trySetResult(cachedSettings.getAppSettingsData());
                return Tasks.forResult(null);
            }
        }
        SettingsData expiredSettings = getCachedSettingsData(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
        if (expiredSettings != null) {
            this.settings.set(expiredSettings);
            ((TaskCompletionSource) this.appSettingsData.get()).trySetResult(expiredSettings.getAppSettingsData());
        }
        return this.dataCollectionArbiter.waitForDataCollectionPermission().onSuccessTask(executor, new SuccessContinuation<Void, Void>() {
            public Task<Void> then(Void aVoid) throws Exception {
                JSONObject settingsJson = SettingsController.this.settingsSpiCall.invoke(SettingsController.this.settingsRequest, true);
                if (settingsJson != null) {
                    SettingsData fetchedSettings = SettingsController.this.settingsJsonParser.parseSettingsJson(settingsJson);
                    SettingsController.this.cachedSettingsIo.writeCachedSettings(fetchedSettings.getExpiresAtMillis(), settingsJson);
                    SettingsController.this.logSettings(settingsJson, "Loaded settings: ");
                    SettingsController settingsController = SettingsController.this;
                    settingsController.setStoredBuildInstanceIdentifier(settingsController.settingsRequest.instanceId);
                    SettingsController.this.settings.set(fetchedSettings);
                    ((TaskCompletionSource) SettingsController.this.appSettingsData.get()).trySetResult(fetchedSettings.getAppSettingsData());
                    TaskCompletionSource<AppSettingsData> fetchedAppSettings = new TaskCompletionSource<>();
                    fetchedAppSettings.trySetResult(fetchedSettings.getAppSettingsData());
                    SettingsController.this.appSettingsData.set(fetchedAppSettings);
                }
                return Tasks.forResult(null);
            }
        });
    }

    private SettingsData getCachedSettingsData(SettingsCacheBehavior cacheBehavior) {
        SettingsData toReturn = null;
        try {
            if (!SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(cacheBehavior)) {
                JSONObject settingsJson = this.cachedSettingsIo.readCachedSettings();
                if (settingsJson != null) {
                    SettingsData settingsData = this.settingsJsonParser.parseSettingsJson(settingsJson);
                    if (settingsData != null) {
                        logSettings(settingsJson, "Loaded cached settings: ");
                        long currentTimeMillis = this.currentTimeProvider.getCurrentTimeMillis();
                        if (!SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(cacheBehavior)) {
                            if (settingsData.isExpired(currentTimeMillis)) {
                                Logger.getLogger().mo19679d("Cached settings have expired.");
                            }
                        }
                        toReturn = settingsData;
                        Logger.getLogger().mo19679d("Returning cached settings.");
                    } else {
                        Logger.getLogger().mo19682e("Failed to parse cached settings data.", null);
                    }
                } else {
                    Logger.getLogger().mo19679d("No cached settings data found.");
                }
            }
        } catch (Exception e) {
            Logger.getLogger().mo19682e("Failed to get cached settings", e);
        }
        return toReturn;
    }

    /* access modifiers changed from: private */
    public void logSettings(JSONObject json, String message) throws JSONException {
        Logger logger = Logger.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        sb.append(json.toString());
        logger.mo19679d(sb.toString());
    }

    private String getStoredBuildInstanceIdentifier() {
        return CommonUtils.getSharedPrefs(this.context).getString(PREFS_BUILD_INSTANCE_IDENTIFIER, "");
    }

    /* access modifiers changed from: private */
    public boolean setStoredBuildInstanceIdentifier(String buildInstanceIdentifier) {
        Editor editor = CommonUtils.getSharedPrefs(this.context).edit();
        editor.putString(PREFS_BUILD_INSTANCE_IDENTIFIER, buildInstanceIdentifier);
        editor.apply();
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean buildInstanceIdentifierChanged() {
        return !getStoredBuildInstanceIdentifier().equals(this.settingsRequest.instanceId);
    }
}
