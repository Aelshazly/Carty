package com.google.firebase.crashlytics.internal.common;

import android.content.Context;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class InstallerPackageNameProvider {
    private static final String NO_INSTALLER_PACKAGE_NAME = "";
    private String installerPackageName;

    InstallerPackageNameProvider() {
    }

    /* access modifiers changed from: 0000 */
    public synchronized String getInstallerPackageName(Context appContext) {
        if (this.installerPackageName == null) {
            this.installerPackageName = loadInstallerPackageName(appContext);
        }
        return "".equals(this.installerPackageName) ? null : this.installerPackageName;
    }

    private static String loadInstallerPackageName(Context appContext) {
        String installerPackageName2 = appContext.getPackageManager().getInstallerPackageName(appContext.getPackageName());
        return installerPackageName2 == null ? "" : installerPackageName2;
    }
}
