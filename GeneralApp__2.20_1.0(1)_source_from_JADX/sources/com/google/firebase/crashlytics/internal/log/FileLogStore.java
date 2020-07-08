package com.google.firebase.crashlytics.internal.log;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
interface FileLogStore {
    void closeLogFile();

    void deleteLogFile();

    byte[] getLogAsBytes();

    String getLogAsString();

    void writeToLog(long j, String str);
}
