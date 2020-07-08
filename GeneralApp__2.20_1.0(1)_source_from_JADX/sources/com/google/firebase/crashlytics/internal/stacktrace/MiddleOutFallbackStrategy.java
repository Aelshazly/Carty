package com.google.firebase.crashlytics.internal.stacktrace;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class MiddleOutFallbackStrategy implements StackTraceTrimmingStrategy {
    private final int maximumStackSize;
    private final MiddleOutStrategy middleOutStrategy;
    private final StackTraceTrimmingStrategy[] trimmingStrategies;

    public MiddleOutFallbackStrategy(int maximumStackSize2, StackTraceTrimmingStrategy... strategies) {
        this.maximumStackSize = maximumStackSize2;
        this.trimmingStrategies = strategies;
        this.middleOutStrategy = new MiddleOutStrategy(maximumStackSize2);
    }

    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stacktrace) {
        StackTraceTrimmingStrategy[] stackTraceTrimmingStrategyArr;
        if (stacktrace.length <= this.maximumStackSize) {
            return stacktrace;
        }
        StackTraceElement[] trimmed = stacktrace;
        for (StackTraceTrimmingStrategy strategy : this.trimmingStrategies) {
            if (trimmed.length <= this.maximumStackSize) {
                break;
            }
            trimmed = strategy.getTrimmedStackTrace(stacktrace);
        }
        if (trimmed.length > this.maximumStackSize) {
            trimmed = this.middleOutStrategy.getTrimmedStackTrace(trimmed);
        }
        return trimmed;
    }
}
