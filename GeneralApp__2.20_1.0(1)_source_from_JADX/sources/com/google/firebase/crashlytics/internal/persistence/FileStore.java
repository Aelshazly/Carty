package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public interface FileStore {
    File getFilesDir();

    String getFilesDirPath();
}
