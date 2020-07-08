package com.google.firebase.crashlytics.internal.settings.model;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class AppRequestData {
    public final String appId;
    public final String buildVersion;
    public final String builtSdkVersion;
    public final String displayVersion;
    public final String googleAppId;
    public final String instanceIdentifier;
    public final String minSdkVersion;
    public final String name;
    public final String organizationId;
    public final int source;

    public AppRequestData(String organizationId2, String googleAppId2, String appId2, String displayVersion2, String buildVersion2, String instanceIdentifier2, String name2, int source2, String minSdkVersion2, String builtSdkVersion2) {
        this.organizationId = organizationId2;
        this.googleAppId = googleAppId2;
        this.appId = appId2;
        this.displayVersion = displayVersion2;
        this.buildVersion = buildVersion2;
        this.instanceIdentifier = instanceIdentifier2;
        this.name = name2;
        this.source = source2;
        this.minSdkVersion = minSdkVersion2;
        this.builtSdkVersion = builtSdkVersion2;
    }
}
