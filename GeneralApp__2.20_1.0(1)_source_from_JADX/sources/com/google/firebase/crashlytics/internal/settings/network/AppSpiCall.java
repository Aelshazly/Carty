package com.google.firebase.crashlytics.internal.settings.network;

import com.google.firebase.crashlytics.internal.settings.model.AppRequestData;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
interface AppSpiCall {
    boolean invoke(AppRequestData appRequestData, boolean z);
}
