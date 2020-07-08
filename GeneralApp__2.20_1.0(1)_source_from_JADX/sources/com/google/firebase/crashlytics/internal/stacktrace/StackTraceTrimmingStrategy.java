package com.google.firebase.crashlytics.internal.stacktrace;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public interface StackTraceTrimmingStrategy {
    StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stackTraceElementArr);
}
