package com.google.firebase.crashlytics.internal.report.model;

import java.io.File;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public interface Report {

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public enum Type {
        JAVA,
        NATIVE
    }

    Map<String, String> getCustomHeaders();

    File getFile();

    String getFileName();

    File[] getFiles();

    String getIdentifier();

    Type getType();

    void remove();
}
