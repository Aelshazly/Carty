package com.google.firebase.crashlytics.internal.unity;

import android.content.Context;
import com.google.firebase.crashlytics.internal.common.CommonUtils;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class ResourceUnityVersionProvider implements UnityVersionProvider {
    private final Context context;
    private boolean hasRead = false;
    private String unityVersion;

    public ResourceUnityVersionProvider(Context context2) {
        this.context = context2;
    }

    public String getUnityVersion() {
        if (!this.hasRead) {
            this.unityVersion = CommonUtils.resolveUnityEditorVersion(this.context);
            this.hasRead = true;
        }
        String str = this.unityVersion;
        if (str != null) {
            return str;
        }
        return null;
    }
}
