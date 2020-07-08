package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class AppData {
    public final String buildId;
    public final String googleAppId;
    public final String installerPackageName;
    public final String packageName;
    public final String versionCode;
    public final String versionName;

    public static AppData create(Context context, IdManager idManager, String googleAppId2, String buildId2) throws NameNotFoundException {
        String packageName2 = context.getPackageName();
        String installerPackageName2 = idManager.getInstallerPackageName();
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName2, 0);
        AppData appData = new AppData(googleAppId2, buildId2, installerPackageName2, packageName2, Integer.toString(packageInfo.versionCode), packageInfo.versionName == null ? IdManager.DEFAULT_VERSION_NAME : packageInfo.versionName);
        return appData;
    }

    public AppData(String googleAppId2, String buildId2, String installerPackageName2, String packageName2, String versionCode2, String versionName2) {
        this.googleAppId = googleAppId2;
        this.buildId = buildId2;
        this.installerPackageName = installerPackageName2;
        this.packageName = packageName2;
        this.versionCode = versionCode2;
        this.versionName = versionName2;
    }
}
