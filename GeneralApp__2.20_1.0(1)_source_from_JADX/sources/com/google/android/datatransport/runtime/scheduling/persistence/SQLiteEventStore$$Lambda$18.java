package com.google.android.datatransport.runtime.scheduling.persistence;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class SQLiteEventStore$$Lambda$18 implements Function {
    private static final SQLiteEventStore$$Lambda$18 instance = new SQLiteEventStore$$Lambda$18();

    private SQLiteEventStore$$Lambda$18() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$ensureBeginTransaction$15((Throwable) obj);
    }
}
