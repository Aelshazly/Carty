package com.google.firebase.messaging;

/* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
final /* synthetic */ class zzk implements Runnable {
    private final FirebaseMessaging zza;

    zzk(FirebaseMessaging firebaseMessaging) {
        this.zza = firebaseMessaging;
    }

    public final void run() {
        this.zza.zza();
    }
}
