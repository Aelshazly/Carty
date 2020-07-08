package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File;
import java.io.InputStream;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
interface NativeSessionFile {
    File asFilePayload();

    String getReportsEndpointFilename();

    InputStream getStream();
}
