package com.google.android.datatransport.runtime.scheduling.persistence;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class SQLiteEventStore$$Lambda$4 implements Function {
    private static final SQLiteEventStore$$Lambda$4 instance = new SQLiteEventStore$$Lambda$4();

    private SQLiteEventStore$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$getDb$0((Throwable) obj);
    }
}