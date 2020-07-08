package com.google.firebase.dynamiclinks;

import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.dynamiclinks.DynamicLink.Builder;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
public abstract class FirebaseDynamicLinks {
    public abstract Builder createDynamicLink();

    public abstract Task<PendingDynamicLinkData> getDynamicLink(Intent intent);

    public abstract Task<PendingDynamicLinkData> getDynamicLink(Uri uri);

    public static synchronized FirebaseDynamicLinks getInstance() {
        FirebaseDynamicLinks instance;
        synchronized (FirebaseDynamicLinks.class) {
            instance = getInstance(FirebaseApp.getInstance());
        }
        return instance;
    }

    public static synchronized FirebaseDynamicLinks getInstance(FirebaseApp firebaseApp) {
        FirebaseDynamicLinks firebaseDynamicLinks;
        Class<FirebaseDynamicLinks> cls = FirebaseDynamicLinks.class;
        synchronized (cls) {
            firebaseDynamicLinks = (FirebaseDynamicLinks) firebaseApp.get(cls);
        }
        return firebaseDynamicLinks;
    }
}
