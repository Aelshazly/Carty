package com.google.firebase.crashlytics.internal;

import java.io.File;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public interface NativeSessionFileProvider {
    File getAppFile();

    File getBinaryImagesFile();

    File getDeviceFile();

    File getMetadataFile();

    File getMinidumpFile();

    File getOsFile();

    File getSessionFile();
}
