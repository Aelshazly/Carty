package com.bumptech.glide.signature;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.bumptech.glide.load.Key;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class ApplicationVersionSignature {
    private static final ConcurrentMap<String, Key> PACKAGE_NAME_TO_KEY = new ConcurrentHashMap();
    private static final String TAG = "AppVersionSignature";

    public static Key obtain(Context context) {
        String packageName = context.getPackageName();
        Key result = (Key) PACKAGE_NAME_TO_KEY.get(packageName);
        if (result != null) {
            return result;
        }
        Key toAdd = obtainVersionSignature(context);
        Key result2 = (Key) PACKAGE_NAME_TO_KEY.putIfAbsent(packageName, toAdd);
        if (result2 == null) {
            return toAdd;
        }
        return result2;
    }

    static void reset() {
        PACKAGE_NAME_TO_KEY.clear();
    }

    private static Key obtainVersionSignature(Context context) {
        return new ObjectKey(getVersionCode(getPackageInfo(context)));
    }

    private static String getVersionCode(PackageInfo packageInfo) {
        if (packageInfo != null) {
            return String.valueOf(packageInfo.versionCode);
        }
        return UUID.randomUUID().toString();
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot resolve info for");
            sb.append(context.getPackageName());
            Log.e(TAG, sb.toString(), e);
            return null;
        }
    }

    private ApplicationVersionSignature() {
    }
}
