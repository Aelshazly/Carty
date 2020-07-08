package com.google.firebase.installations;

import com.google.firebase.FirebaseException;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
public class FirebaseInstallationsException extends FirebaseException {
    private final Status status;

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    public enum Status {
        BAD_CONFIG
    }

    public FirebaseInstallationsException(Status status2) {
        this.status = status2;
    }

    public FirebaseInstallationsException(String message, Status status2) {
        super(message);
        this.status = status2;
    }

    public FirebaseInstallationsException(String message, Status status2, Throwable cause) {
        super(message, cause);
        this.status = status2;
    }

    public Status getStatus() {
        return this.status;
    }
}
