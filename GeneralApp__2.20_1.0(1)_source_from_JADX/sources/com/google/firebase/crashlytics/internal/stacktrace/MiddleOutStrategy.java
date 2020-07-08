package com.google.firebase.crashlytics.internal.stacktrace;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class MiddleOutStrategy implements StackTraceTrimmingStrategy {
    private final int trimmedSize;

    public MiddleOutStrategy(int trimmedSize2) {
        this.trimmedSize = trimmedSize2;
    }

    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stacktrace) {
        int length = stacktrace.length;
        int i = this.trimmedSize;
        if (length <= i) {
            return stacktrace;
        }
        int backHalf = i / 2;
        int frontHalf = i - backHalf;
        StackTraceElement[] trimmed = new StackTraceElement[i];
        System.arraycopy(stacktrace, 0, trimmed, 0, frontHalf);
        System.arraycopy(stacktrace, stacktrace.length - backHalf, trimmed, frontHalf, backHalf);
        return trimmed;
    }
}
