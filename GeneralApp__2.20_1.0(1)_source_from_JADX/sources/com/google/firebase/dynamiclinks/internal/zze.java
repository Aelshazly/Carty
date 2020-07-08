package com.google.firebase.dynamiclinks.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.dynamiclinks.DynamicLink.Builder;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
public final class zze extends FirebaseDynamicLinks {
    private final GoogleApi<NoOptions> zzq;
    private final AnalyticsConnector zzr;

    public zze(FirebaseApp firebaseApp, AnalyticsConnector analyticsConnector) {
        this((GoogleApi<NoOptions>) new zzc<NoOptions>(firebaseApp.getApplicationContext()), analyticsConnector);
    }

    private zze(GoogleApi<NoOptions> googleApi, AnalyticsConnector analyticsConnector) {
        this.zzq = googleApi;
        this.zzr = analyticsConnector;
        if (analyticsConnector == null) {
            Log.w("FDL", "FDL logging failed. Add a dependency for Firebase Analytics to your app to enable logging of Dynamic Link events.");
        }
    }

    public final Task<PendingDynamicLinkData> getDynamicLink(Intent intent) {
        Task<PendingDynamicLinkData> doWrite = this.zzq.doWrite((TaskApiCall<A, TResult>) new zzl<A,TResult>(this.zzr, intent.getDataString()));
        DynamicLinkData dynamicLinkData = (DynamicLinkData) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.firebase.dynamiclinks.DYNAMIC_LINK_DATA", DynamicLinkData.CREATOR);
        PendingDynamicLinkData pendingDynamicLinkData = dynamicLinkData != null ? new PendingDynamicLinkData(dynamicLinkData) : null;
        if (pendingDynamicLinkData != null) {
            return Tasks.forResult(pendingDynamicLinkData);
        }
        return doWrite;
    }

    public final Task<PendingDynamicLinkData> getDynamicLink(Uri uri) {
        return this.zzq.doWrite((TaskApiCall<A, TResult>) new zzl<A,TResult>(this.zzr, uri.toString()));
    }

    public final Builder createDynamicLink() {
        return new Builder(this);
    }

    public final Task<ShortDynamicLink> zza(Bundle bundle) {
        zzb(bundle);
        return this.zzq.doWrite((TaskApiCall<A, TResult>) new zzj<A,TResult>(bundle));
    }

    public static void zzb(Bundle bundle) {
        Uri uri = (Uri) bundle.getParcelable("dynamicLink");
        if (TextUtils.isEmpty(bundle.getString("domainUriPrefix")) && uri == null) {
            throw new IllegalArgumentException("FDL domain is missing. Set with setDomainUriPrefix() or setDynamicLinkDomain().");
        }
    }
}
