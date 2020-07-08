package com.google.firebase.crashlytics.internal.common;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public enum DeliveryMechanism {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);
    

    /* renamed from: id */
    private final int f77id;

    private DeliveryMechanism(int id) {
        this.f77id = id;
    }

    public int getId() {
        return this.f77id;
    }

    public String toString() {
        return Integer.toString(this.f77id);
    }

    public static DeliveryMechanism determineFrom(String installerPackageName) {
        return installerPackageName != null ? APP_STORE : DEVELOPER;
    }
}
