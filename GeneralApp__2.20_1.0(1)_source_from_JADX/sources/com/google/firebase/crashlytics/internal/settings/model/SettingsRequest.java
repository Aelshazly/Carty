package com.google.firebase.crashlytics.internal.settings.model;

import com.google.firebase.crashlytics.internal.common.InstallIdProvider;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class SettingsRequest {
    public final String buildVersion;
    public final String deviceModel;
    public final String displayVersion;
    public final String googleAppId;
    public final InstallIdProvider installIdProvider;
    public final String instanceId;
    public final String osBuildVersion;
    public final String osDisplayVersion;
    public final int source;

    public SettingsRequest(String googleAppId2, String deviceModel2, String osBuildVersion2, String osDisplayVersion2, InstallIdProvider installIdProvier, String instanceId2, String displayVersion2, String buildVersion2, int source2) {
        this.googleAppId = googleAppId2;
        this.deviceModel = deviceModel2;
        this.osBuildVersion = osBuildVersion2;
        this.osDisplayVersion = osDisplayVersion2;
        this.installIdProvider = installIdProvier;
        this.instanceId = instanceId2;
        this.displayVersion = displayVersion2;
        this.buildVersion = buildVersion2;
        this.source = source2;
    }
}
