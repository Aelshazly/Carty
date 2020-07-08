package com.google.firebase.dynamiclinks;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.dynamiclinks.internal.DynamicLinkData;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
public class PendingDynamicLinkData {
    private final DynamicLinkData zzi;

    public PendingDynamicLinkData(DynamicLinkData dynamicLinkData) {
        if (dynamicLinkData == null) {
            this.zzi = null;
            return;
        }
        if (dynamicLinkData.getClickTimestamp() == 0) {
            dynamicLinkData.zza(DefaultClock.getInstance().currentTimeMillis());
        }
        this.zzi = dynamicLinkData;
    }

    protected PendingDynamicLinkData(String str, int i, long j, Uri uri) {
        DynamicLinkData dynamicLinkData = new DynamicLinkData(null, str, i, j, null, uri);
        this.zzi = dynamicLinkData;
    }

    public Bundle getExtensions() {
        DynamicLinkData dynamicLinkData = this.zzi;
        if (dynamicLinkData == null) {
            return new Bundle();
        }
        return dynamicLinkData.zzf();
    }

    public Uri getLink() {
        DynamicLinkData dynamicLinkData = this.zzi;
        if (dynamicLinkData == null) {
            return null;
        }
        String zzd = dynamicLinkData.zzd();
        if (zzd != null) {
            return Uri.parse(zzd);
        }
        return null;
    }

    public int getMinimumAppVersion() {
        DynamicLinkData dynamicLinkData = this.zzi;
        if (dynamicLinkData == null) {
            return 0;
        }
        return dynamicLinkData.zze();
    }

    public long getClickTimestamp() {
        DynamicLinkData dynamicLinkData = this.zzi;
        if (dynamicLinkData == null) {
            return 0;
        }
        return dynamicLinkData.getClickTimestamp();
    }

    private final Uri zzc() {
        DynamicLinkData dynamicLinkData = this.zzi;
        if (dynamicLinkData == null) {
            return null;
        }
        return dynamicLinkData.zzc();
    }

    public Intent getUpdateAppIntent(Context context) {
        if (getMinimumAppVersion() == 0) {
            return null;
        }
        try {
            if (context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionCode >= getMinimumAppVersion() || zzc() == null) {
                return null;
            }
            return new Intent("android.intent.action.VIEW").setData(zzc()).setPackage("com.android.vending");
        } catch (NameNotFoundException e) {
            return null;
        }
    }
}
